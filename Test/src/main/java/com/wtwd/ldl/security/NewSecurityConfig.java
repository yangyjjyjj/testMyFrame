package com.wtwd.ldl.security;

import com.wtwd.ldl.filter.VerifyCodeFilter;
import com.wtwd.ldl.security.authdeal.AuthenticationAccessDeniedHandler;
import com.wtwd.ldl.security.authdeal.CustomAuthenticationEntryPoint;
import com.wtwd.ldl.security.logindecide.CustomAuthenticationFilter;
import com.wtwd.ldl.security.logindecide.MyPasswordEncoder;
import com.wtwd.ldl.security.logindecide.WebAuthenticationFailureHander;
import com.wtwd.ldl.security.logindecide.WebAuthenticationSuccessHander;
import com.wtwd.ldl.security.support.*;
import com.wtwd.ldl.service.Impl.SysUserServiceImpl;
import com.wtwd.ldl.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/**
 * @Author ldaoliang
 * @Date 2019/10/26 0026 上午 9:25
 * @Description TODO
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)   //启用方法级别验证
public class NewSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private SysUserServiceImpl userService;
	@Autowired
	UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
	@Autowired
	private DataSource dataSource; // 数据源
	/**
	 * 权限的配置
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	protected void configure(HttpSecurity http) throws Exception{

		//添加登录过滤器（跟UsernamePasswordAuthenticationFilter相关的那个)
//		http.addFilter(customAuthenticationFilter());
		//添加过滤器（验证码过滤器，在UserNamePasswordAuthenticationFilter之前）
		http.addFilterBefore(verifyCodeFilter(),UsernamePasswordAuthenticationFilter.class);
		http.addFilterAt(customAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
		//对所有前置测试请求放行（在真正发送请求之前会有一个请求来探测)
		http.cors().and().authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS)
				.permitAll()
		//对所有请求进行处理
				.and().authorizeRequests()
				.withObjectPostProcessor(objectPostProcessor())
//				.and().formLogin().loginPage("/login")
//				rememberMe功能
				.and().rememberMe()
				.userDetailsService(userService)
				.tokenRepository(persistentTokenRepository()) // 设置数据访问层
				.tokenValiditySeconds(60 * 60)//保持时间
				.and().csrf().disable()
				.exceptionHandling()
 				.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
				.accessDeniedHandler(authenticationAccessDeniedHandler());

	}


	/**
	 * 持久化token
	 *
	 * Security中，默认是使用PersistentTokenRepository的子类InMemoryTokenRepositoryImpl，将token放在内存中
	 * 如果使用JdbcTokenRepositoryImpl，会创建表persistent_logins，将token持久化到数据库
	 */
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource); // 设置数据源
//        tokenRepository.setCreateTableOnStartup(true); // 启动创建表，创建成功后注释掉
		return tokenRepository;
	}

	/**
	 * 密码的处理
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new MyPasswordEncoder();
	}

	/**
	 * 登录的处理
	 * @return
	 * @throws Exception
	 */
	@Bean
	public CustomAuthenticationFilter customAuthenticationFilter() throws Exception{
		CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
		//自定义登录成功失败处理
		filter.setAuthenticationFailureHandler(webAuthenticationFailureHander());
		filter.setAuthenticationSuccessHandler(webAuthenticationSuccessHander());
		filter.setAuthenticationManager(authenticationManagerBean());
		//自定义登录地址
		filter.setRequiresAuthenticationRequestMatcher(
				new AntPathRequestMatcher("/login", "POST"));
		return filter;
	}

	/**
	 * url拦截
	 * 验证权限（url处理是这个）
	 * @return
	 */
	private ObjectPostProcessor<FilterSecurityInterceptor>  objectPostProcessor(){
		return new ObjectPostProcessor<FilterSecurityInterceptor>() {
			@Override
			public <O extends FilterSecurityInterceptor> O postProcess(O o) {
				o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
                    o.setAccessDecisionManager(urlAccessDecisionManager());
				return o;
			}
		};
	}

	/**
	 * 验证码过滤器
	 * @return
	 */
	@Bean
	public VerifyCodeFilter verifyCodeFilter(){
		return new VerifyCodeFilter();
	}


	/**
	 * 登录失败
	 */
	@Bean
	public WebAuthenticationFailureHander webAuthenticationFailureHander(){
		return new WebAuthenticationFailureHander();
	}

	/**
	 * 登录成功的处理
	 */
	@Bean
	public WebAuthenticationSuccessHander webAuthenticationSuccessHander(){
		return new WebAuthenticationSuccessHander();
	}

	/**
	 * url 的处理
	 * @return
	 */
	@Bean
	public AccessDecisionManager urlAccessDecisionManager() {
		return new UrlAccessDecisionManager();
	}

	/**
	 * 自定义403
	 * @return
	 */
	@Bean
	public AccessDeniedHandler authenticationAccessDeniedHandler() {
		return new AuthenticationAccessDeniedHandler();
	}
}
