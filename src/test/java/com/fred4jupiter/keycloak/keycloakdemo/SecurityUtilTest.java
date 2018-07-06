package com.fred4jupiter.keycloak.keycloakdemo;

import com.fred4jupiter.keycloak.keycloakdemo.web.SecurityUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityUtilTest {

    @Autowired
    private SecurityUtil securityUtil;

    @Test
    @WithMockUser(username = "fred", authorities = {"ROLE_PRODUCTS"})
    public void contextLoads() {
        assertEquals("fred", securityUtil.getUsername());
        assertThat(securityUtil.isUserInRole("ROLE_PRODUCTS"), equalTo(true));
    }
}
