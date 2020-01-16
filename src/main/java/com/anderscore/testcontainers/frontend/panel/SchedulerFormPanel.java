package com.anderscore.testcontainers.frontend.panel;

import com.anderscore.testcontainers.frontend.mapping.SchedulerUi;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

public abstract class SchedulerFormPanel extends Panel{
	private static final long serialVersionUID = 1L;

	public SchedulerFormPanel(String wicketId, IModel<SchedulerUi> scheduler) {
		super(wicketId, new CompoundPropertyModel<>(scheduler));
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(createForm("form"));
	}
	
	private Form<SchedulerUi> createForm(String wicketId){
		Form<SchedulerUi> form = new Form<SchedulerUi>(wicketId) {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onInitialize() {
				super.onInitialize();
				
				add(new TextField<String>("name"));
			}
			
			@Override
			protected void onSubmit() {
				onSave((SchedulerUi) SchedulerFormPanel.this.getDefaultModelObject()); 
			}
		};
		
		return form;
	}
	
	protected abstract void onSave(SchedulerUi scheduler);
}