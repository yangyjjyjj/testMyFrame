package com.wtwd.ldl;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.wtwd.ldl.config.KaptchaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @Author ldaoliang
 * @Date 2019/9/30 0030 上午 10:20
 * @Description TODO
 **/

@SpringBootApplication
public class TestApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder bulider){
		return bulider.sources(this.getClass());
	}

	//验证码相关bean
	@Bean
	public KaptchaConfig kaptchaConfig(){
		return new KaptchaConfig();
	}
	@Bean(name="captchaProducer")
	public DefaultKaptcha getKaptchaBean(){
		return kaptchaConfig().getKaptchaBean();
	}

	/**
	 * 连接池使用
	 * @return
	 */
	@Bean(destroyMethod = "close", initMethod = "init")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource druidDatasource()
	{
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setConnectionInitSqls(Arrays.asList("set names utf8mb4")); // 可添加表情
		return druidDataSource;
	}

	/**
	 * 注册后台界面的Servlet bean, 用于显示后台界面
	 * @return
	 */
	@Bean
	public ServletRegistrationBean<StatViewServlet> druidStatViewServlet()
	{
		// 创建StatViewServlet， 映射到/druid/路径下
		ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
		//添加初始化参数：initParams
		//白名单：
		servletRegistrationBean.addInitParameter("allow", "");
		//IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
		servletRegistrationBean.addInitParameter("deny", "");
		//登录查看信息的账号密码.
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		servletRegistrationBean.addInitParameter("loginPassword", "lock123");
		//是否能够重置数据.
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;
	}

	/**
	 * 监听获取应用的数据，Filter用于收集数据， Servlet用于展现数据
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<WebStatFilter> druidStatFilter()
	{
		FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>(new WebStatFilter());
		//添加过滤规则.
		filterRegistrationBean.addUrlPatterns("/*");
		//添加不需要忽略的格式信息.
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}

}
