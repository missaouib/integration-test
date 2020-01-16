package com.anderscore.testcontainers.resolver;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;

public class WebDriverParameterResolver implements ParameterResolver {

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		
		Class<?> type = parameterContext.getParameter().getType();
		return RemoteWebDriver.class.isAssignableFrom(type);
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		
		BrowserWebDriverContainer<?> container = extensionContext.getStore(GLOBAL)
				.get(BrowserWebDriverContainer.class.getSimpleName(), BrowserWebDriverContainer.class);
		
		return container.getWebDriver();
	}
}