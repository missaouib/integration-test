package com.anderscore.testcontainers.frontend.page;

import com.anderscore.testcontainers.frontend.mapping.SchedulerMapper;
import com.anderscore.testcontainers.frontend.mapping.SchedulerUi;
import com.anderscore.testcontainers.frontend.panel.SchedulerFormPanel;
import com.anderscore.testcontainers.service.SchedulerService;
import com.anderscore.testcontainers.frontend.component.PageLink;

import javax.inject.Inject;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

public class SchedulerEditPage extends SchedulerPage {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SchedulerService service;
	@Inject
	private SchedulerMapper mapper;
	
	private IModel<SchedulerUi> scheduler;
	
	public SchedulerEditPage(IModel<SchedulerUi> scheduler) {
		this.scheduler = scheduler;
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		SchedulerFormPanel formPanel = createFormPanel("formPanel", scheduler);
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