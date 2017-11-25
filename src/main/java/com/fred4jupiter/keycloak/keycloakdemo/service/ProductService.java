package com.fred4jupiter.keycloak.keycloakdemo.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by exmstae on 12.07.2017.
 */
@Service
public class ProductService {

	public List<String> getProducts() {
		return Arrays.asList("Apfel", "Birne");
	}
}
