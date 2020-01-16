package com.anderscore.testcontainers.extension;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_FAILING;

import java.io.File;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.Testcontainers;
import org.testcontainers.containers.BrowserWebDriverContainer;

import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//tag::WebDriverContainerExtension[]
public class WebDriverContainerExtension implements BeforeEachCallback, AfterEachCallback {

	@Override
	public void beforeEach(ExtensionContext extensionContext) throws Exception {
		String serverPort = SpringExtension.getApplicationContext(extensionContext).getEnvironment()
				.getProperty("local.server.port");

		Testcontainers.exposeHostPorts(parseInt(serverPort));
		
		BrowserWebDriverContainer<?> container = new BrowserWebDriverContainer<>()
				.withCapabilities(new ChromeOptions())
				.withRecordingMode(RECORD_FAILING, new File("./target/"));
		
		container.start();

		extensionContext.getStore(GLOBAL).put(BrowserWebDriverContainer.class.getSimpleName(), container);
	}

	@Override
	public void afterEach(ExtensionContext extensionContext) throws Exception {
		BrowserWebDriverContainer<?> container = extensionContext.getStore(GLOBAL)
				.get(BrowserWebDriverContainer.class.getSimpleName(), BrowserWebDriverContainer.class);
		
		container.stop();
	}
}
//tag::WebDriverContainerExtension[]