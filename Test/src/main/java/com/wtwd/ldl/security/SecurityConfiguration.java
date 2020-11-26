//package com.wtwd.ldl.security;
//
//
//import com.wtwd.ldl.service.SysUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//
//import javax.sql.DataSource;
//
///**
// * @Author ldaoliang
// * @Date 2019/10/23 0023 下午 4:25
// * @Description TODO
// **/
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)   //启用方法级别验证
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private SysUserService userService;
//	@Autowired
//	private DataSource dataSource;
//
//	/**
//	 * 设置token仓库的数据源，设置了这个数据源，表名字段名已经固定了；
//	 * 具体看JdbcTokenRepositoryImpl类
//	 * @return
//	 */
//	@Bean
//	public PersistentTokenRepository persistentTokenRepository(){
//		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//		tokenRepository.setDataSource(dataSource);
//		// 如果token表不存在，使用下面语句可以初始化该表；若存在，请注释掉这条语句，否则会报错。
//		// tokenRepository.setCreateTableOnStartup(true);
//		return tokenRepository;
//	}
//
//
//
//	/**
//	 * 权限的配置
//	 * @param auth
//	 * @throws Exception
//	 */
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//
//		auth.userDetailsService(userService).passwordEncoder(new PasswordEncoder() {
//
//			public String encode(CharSequence charSequence) {
//				return charSequence.toString();
//			}
//
//
//			public boolean matches(CharSequence charSequence, String s) {
//				return s.equals(charSequence.toString());
//			}
//		});
//
//	}
//
//	/**
//	 * BCrypt加密处理；前端使用这个加密方法，这里就开启这个验证
//	 * @param auth
//	 * @throws Exception
//	 */
////	@Override
////	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		auth.userDetailsService(userDetailsService)
////				.passwordEncoder(new BCryptPasswordEncoder());
////	}
//
//	/**
//	 * 对http请求的配置
//	 * @param http
//	 * @throws Exception
//	 */
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//				// 如果有允许匿名的url，填在下面
////                .antMatchers().permitAll()
//				.anyRequest().authenticated()
//				.and()
//				// 设置登陆页
//				.formLogin().loginPage("/login")
//				//设置登录失败页
//				.failureUrl("/login/error")
//				// 设置登陆成功页
//				.defaultSuccessUrl("/").permitAll()
//				// 自定义登陆用户名和密码参数，默认为username和password
////                .usernameParameter("username")
////                .passwordParameter("password")
//				.and()
//				.logout().permitAll()
//				.and().rememberMe()
//				.tokenRepository(persistentTokenRepository())
//				.tokenValiditySeconds(60)
//				.userDetailsService(userService)
//			;
//
//		// 关闭CSRF跨域
//		http.csrf().disable();
//	}
//
//	/**
//	 * 静态web的配置
//	 * @param web
//	 * @throws Exception
//	 */
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		// 设置拦截忽略文件夹，可以对静态资源放行
//		web.ignoring().antMatchers("/css/**", "/js/**");
//	}
//
//
//
//
//
//
//
//}
