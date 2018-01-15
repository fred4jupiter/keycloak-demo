package com.fred4jupiter.keycloak.keycloakdemo.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebUtil {

	@Value("${spring.application.name}")
	private String applicationName;

	public String getApplicationName() {
		return applicationName;
	}
}
