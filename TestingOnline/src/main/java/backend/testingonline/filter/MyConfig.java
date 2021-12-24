package backend.testingonline.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
	
	@Bean
	public FilterRegistrationBean<LoginFilter> loginFilterBean() {
		FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();
		
		registrationBean.setFilter(new LoginFilter());
		registrationBean.addUrlPatterns("/staff/*");
		
		return registrationBean;
	}
	

}
