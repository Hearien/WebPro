package com.hearien.demo.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 认证服务器配置
 * @author lxg
 *
 * 2017年2月17日上午10:50:04
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	private static final Logger LOGGER = Logger.getLogger(AuthorizationServerConfiguration.class);

	@Value("${config.oauth2.realm}")
	private String realm;

	@Value("${config.oauth2.resource.id}")
	private String resourceId;

	@Value("${config.oauth2.clientID}")
	private String clientId;

	@Value("${config.oauth2.clientSecret}")
	private String secret;

	@Value("${config.oauth2.tokenTimeout}")
	private int expiration;
	
	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private UserApprovalHandler userApprovalHandler;

	/*@Autowired
	private MyUserDetailsService myUserDetailsService;*/

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory()
	        .withClient(clientId)//客户端ID
            .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit","client_credentials")
            .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
            .scopes("read", "write", "trust")//授权用户的操作权限
			.resourceIds(resourceId)
            .secret(secret)//密码
            .accessTokenValiditySeconds(expiration).//token有效期为120秒
            refreshTokenValiditySeconds(expiration);//刷新token有效期为600秒
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
				.tokenStore(tokenStore)
				.userApprovalHandler(userApprovalHandler)
				.authenticationManager(authenticationManager);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.realm(realm);
	}

}