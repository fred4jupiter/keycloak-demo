package com.fred4jupiter.keycloak.keycloakdemo.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by exmstae on 12.07.2017.
 */
@Controller
public class IndexController {

	@Value("${spring.application.name}")
	private String applicationName;

	@GetMapping(path = "/")
	public ModelAndView showIndexPage() {
		return new ModelAndView("index", "applicationName", applicationName);
	}
}
