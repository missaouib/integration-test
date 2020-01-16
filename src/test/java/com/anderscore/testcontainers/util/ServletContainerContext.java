package com.anderscore.testcontainers.util;

public class ServletContainerContext {
	
	private final String host;
	private final int port;
	
	public ServletContainerContext(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public String getHttpUrl() {
		return "http://" + host + ":" + port;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}
}