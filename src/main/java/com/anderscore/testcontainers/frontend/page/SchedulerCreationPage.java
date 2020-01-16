package com.anderscore.testcontainers.frontend.page;

import com.anderscore.testcontainers.frontend.mapping.SchedulerMapper;
import com.anderscore.testcontainers.frontend.mapping.SchedulerUi;
import com.anderscore.testcontainers.frontend.panel.SchedulerFormPanel;
import com.anderscore.testcontainers.service.SchedulerService;
import com.anderscore.testcontainers.frontend.component.PageLink;

import javax.inject.Inject;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class SchedulerCreationPage extends SchedulerPage {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SchedulerService service;
	@Inject
	private SchedulerMapper mapper;
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		SchedulerFormPanel formPanel = createFormPanel("formPanel", Model.of(new SchedulerUi()));
		PageLink backButton = new PageLink("back", SchedulerOverviewPage.class);
		
		add(formPanel);
		add(backButton);
	}
	
	private SchedulerFormPanel createFormPanel(String wicketId, IModel<SchedulerUi> scheduler){
		SchedulerFormPanel formPanel = new SchedulerFormPanel(wicketId, scheduler) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSave(SchedulerUi scheduler) {
				service.store(mapper.asEntity(scheduler));
				setResponsePage(SchedulerOverviewPage.class);
			}
		};
		
		return formPanel;
	}
}