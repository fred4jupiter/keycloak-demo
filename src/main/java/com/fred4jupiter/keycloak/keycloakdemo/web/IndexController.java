package com.fred4jupiter.keycloak.keycloakdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by exmstae on 12.07.2017.
 */
@Controller
public class IndexController {

	@GetMapping(path = "/")
	public String showIndexPage() {
		return "index";
	}
}
