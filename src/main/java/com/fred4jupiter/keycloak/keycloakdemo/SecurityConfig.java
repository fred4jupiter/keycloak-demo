package com.fred4jupiter.keycloak.keycloakdemo;

import org.keycloak.adapters.AdapterDeploymentContext;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.AdapterDeploymentContextFactoryBean;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticatedActionsFilter;
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
import org.keycloak.adapters.springsecurity.filter.KeycloakPreAuthActionsFilter;
import org.keycloak.adapters.springsecurity.filter.KeycloakSecurityContextRequestFilter;
import org.keycloak.adapters.springsecurity.management.HttpSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

/**
 * Created by exmstae on 13.07.2017.
 */
@KeycloakConfiguration
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    // see https://www.keycloak.org/docs/latest/securing_apps/index.html#avoid-double-bean-registration

    @Bean
    public FilterRegistrationBean<KeycloakAuthenticationProcessingFilter> keycloakAuthenticationProcessingFilterRegistrationBean(KeycloakAuthenticationProcessingFilter filter) {
        FilterRegistrationBean<KeycloakAuthenticationProcessingFilter> registrationBean = new FilterRegistrationBean<>(filter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<KeycloakPreAuthActionsFilter> keycloakPreAuthActionsFilterRegistrationBean(KeycloakPreAuthActionsFilter filter) {
        FilterRegistrationBean<KeycloakPreAuthActionsFilter> registrationBean = new FilterRegistrationBean<>(filter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<KeycloakAuthenticatedActionsFilter> keycloakAuthenticatedActionsFilterBean(KeycloakAuthenticatedActionsFilter filter) {
        FilterRegistrationBean<KeycloakAuthenticatedActionsFilter> registrationBean = new FilterRegistrationBean<>(filter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<KeycloakSecurityContextRequestFilter> keycloakSecurityContextRequestFilterBean(KeycloakSecurityContextRequestFilter filter) {
        FilterRegistrationBean<KeycloakSecurityContextRequestFilter> registrationBean = new FilterRegistrationBean<>(filter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }

    @Bean
    @Override
    @ConditionalOnMissingBean(HttpSessionManager.class)
    protected HttpSessionManager httpSessionManager() {
        return new HttpSessionManager();
    }



//    @Bean
//    @Override
//    protected AdapterDeploymentContext adapterDeploymentContext() throws Exception {
//        AdapterDeploymentContextFactoryBean factoryBean = new AdapterDeploymentContextFactoryBean(new KeycloakSpringBootConfigResolver());
//        factoryBean.afterPropertiesSet();
//        return factoryBean.getObject();
//    }

    /**
     * Registers the KeycloakAuthenticationProvider with the authentication manager.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(keycloakAuthenticationProvider());
    }

    /**
     * Defines the session authentication strategy.
     */
    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/webjars/**", "/css/**", "/fonts/**", "/images/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        http.authorizeRequests().antMatchers("/products*").hasAuthority("ROLE_PRODUCTS");
        http.authorizeRequests().antMatchers("/**").permitAll();
    }
}
