package com.anderscore.testcontainers.frontend.component;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

public class PageLink extends Link<Void>{
	private static final long serialVersionUID = 1L;
	
	private Class<? extends WebPage> targetPage;

	public PageLink(String id, Class<? extends WebPage> targetPage) {
		super(id);
		this.targetPage = targetPage;
	}

	@Override
	public void onClick() {
		setResponsePage(targetPage);
	}
}