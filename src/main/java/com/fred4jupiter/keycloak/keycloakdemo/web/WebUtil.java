package com.fred4jupiter.keycloak.keycloakdemo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class WebUtil {

	@Value("${spring.application.name}")
	private String applicationName;

	@Autowired
	private Environment environment;

	public String getApplicationName() {
		return applicationName;
	}

	public boolean isAppOne() {
		return !environment.acceptsProfiles("app2");
	}
}
