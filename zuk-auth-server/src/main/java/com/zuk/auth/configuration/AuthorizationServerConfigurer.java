package com.zuk.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private ClientDetailsService clientDetailsService;
	
	/**
	 * 用来配置令牌端点(Token Endpoint)的安全约束
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		
	}

	/**
	 * 配置客户端详情信息（Client Details)
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		/**
		 * 用来配置客户端详情服务（ClientDetailsService）
		 * 客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
		 */
		/**
		 * 使用内存或者JDBC来实现客户端详情服务
		 * clientId：（必须的）用来标识客户的Id。
		 * secret：（需要值得信任的客户端）客户端安全码，如果有的话。
		 * scope：用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围。
		 * authorizedGrantTypes：此客户端可以使用的授权类型，默认为空。
		 * authorities：此客户端可以使用的权限（基于Spring Security authorities）。
		 */
		clients.inMemory()//内存
		.withClient("zuk_zuul_server")
		.secret("secret")
		.scopes("WRIGTH", "read")
		.autoApprove(true)
		.authorities("WRIGTH_READ", "WRIGTH_WRITE")
		/**
		 * 1、授权码模式（authorization code） 
		 * 2、简化模式（implicit）
		 * 3、密码模式（resource owner password credentials） 
		 * 4、客户端模式（client credentials）
		 */
		.authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code");
		
		clients.withClientDetails(clientDetailsService);
		
	}

	/**
	 * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
		/**
		 * 管理令牌（Managing Token）
		 */
		.tokenStore(jwtTokenStore())
		/**
		 * 配置授权类型（Grant Types）
		 */
		.tokenEnhancer(jwtTokenConverter())
		.authenticationManager(authenticationManager);
	}

	@Bean
	public TokenStore jwtTokenStore() {
		return new JwtTokenStore(jwtTokenConverter());
	}

	@Bean
	protected JwtAccessTokenConverter jwtTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("springcloud123");
		return converter;
	}

}
