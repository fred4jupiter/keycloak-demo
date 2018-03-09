package com.fred4jupiter.keycloak.keycloakdemo.web;

import com.fred4jupiter.keycloak.keycloakdemo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Set;

/**
 * Created by exmstae on 12.07.2017.
 */
@Controller
public class ProductController {

	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private SecurityUtil securityUtil;

	@GetMapping(path = "/products")
	public String getProducts(Principal principal, Model model, HttpServletRequest request) {
		Set<String> authorities = securityUtil.getUserAuthorities();
		model.addAttribute("authorities", authorities);
		model.addAttribute("principal", principal);
		model.addAttribute("products", productService.getProducts());

		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			LOG.info("cookie name: {}, path: {}, value: {}", cookie.getName(), cookie.getPath(), cookie.getValue());
		}

		return "products";
	}

	@GetMapping(path = "/logout")
	public String logout(HttpServletRequest request) throws ServletException {
		request.logout();
		return "index";
	}
}
