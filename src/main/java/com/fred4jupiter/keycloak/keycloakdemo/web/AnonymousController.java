package com.fred4jupiter.keycloak.keycloakdemo.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AnonymousController {

	@GetMapping(path = "/simple")
	public String getSimple(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			List<String> authorities = authentication.getAuthorities().stream().map(grantedAuthority -> grantedAuthority.getAuthority())
					.collect(Collectors.toList());
			model.addAttribute("authorities", authorities);
			model.addAttribute("principal", authentication.getName());
		}

		return "anonymous";
	}
}
