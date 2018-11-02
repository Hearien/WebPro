package com.hearien.demo.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * 资源服务器
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final Logger LOGGER = Logger.getLogger(ResourceServerConfiguration.class);

	@Value("${config.oauth2.resource.id}")
	private String resourceId;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(resourceId).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
				.requestMatchers().antMatchers("/api*/**")
				.and().authorizeRequests()
				.antMatchers("/api*/**").permitAll()
				.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

}