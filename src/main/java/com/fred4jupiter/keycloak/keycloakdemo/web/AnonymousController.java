package com.fred4jupiter.keycloak.keycloakdemo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Set;

@Controller
public class AnonymousController {

	@Autowired
	private SecurityUtil securityUtil;

	@GetMapping(path = "/simple")
	public String getSimple(Principal principal, Model model) {
		Set<String> authorities = securityUtil.getUserAuthorities();
		model.addAttribute("authorities", authorities);
		model.addAttribute("principal", principal);

		return "anonymous";
	}
}
