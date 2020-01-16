package com.anderscore.testcontainers.frontend.page;

import java.util.Iterator;

import javax.inject.Inject;

import com.anderscore.testcontainers.frontend.mapping.SchedulerMapper;
import com.anderscore.testcontainers.frontend.mapping.SchedulerUi;
import com.anderscore.testcontainers.service.SchedulerService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;

@WicketHomePage
public class SchedulerOverviewPage extends SchedulerPage {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SchedulerService service;
	@Inject
	private SchedulerMapper mapper;
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		IDataProvider<SchedulerUi> dataProvider = createDataProvider();
		DataView<SchedulerUi> dataView = createDataView("schedulers", dataProvider);
		Link<Void> newLink = createNewLink("new");
		
		add(dataView);
		add(newLink);
	}
	
	private IDataProvider<SchedulerUi> createDataProvider(){
		IDataProvider<SchedulerUi> dataProvider = new IDataProvider<SchedulerUi>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Iterator<? extends SchedulerUi> iterator(long first, long count) {
				return service.findAll(first, count).stream().map(mapper::asUi).iterator();
			}

			@Override
			public long size() {
				return service.count();
			}

			@Override
			public IModel<SchedulerUi> model(SchedulerUi object) {
				return Model.of(object);
			}
		};
		
		return dataProvider;
	}
	
	private DataView<SchedulerUi>  createDataView(String wicketId, IDataProvider<SchedulerUi> dataProvider) {
		DataView<SchedulerUi> dataView = new DataView<SchedulerUi>(wicketId, dataProvider) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<SchedulerUi> item) {
				IModel<SchedulerUi> model = new CompoundPropertyModel<>(item.getModel());
				item.setDefaultModel(model);
				
				Link<Void> editLink = new Link<Void>("edit") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						SchedulerEditPage editPage = new SchedulerEditPage(model);
						setResponsePage(editPage);
					}
				};
				
				Link<Void> deleteLink = new Link<Void>("delete") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						service.delete(mapper.asEntity(model.getObject()));
					}
				};
				
				item.add(new Label("id"));
				item.add(new Label("name"));
				item.add(editLink);
				item.add(deleteLink);
			}
		};
		
		return dataView;
	}
	
	private Link<Void> createNewLink(String wicketId){
		Link<Void> newLink = new Link<Void>(wicketId) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(SchedulerCreationPage.class);
			}
		};
		
		return newLink;
	}
}