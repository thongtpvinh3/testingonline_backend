package backend.testingonline.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

//@Configuration
public class MyConfig {

	@Bean
	public FilterRegistrationBean<LoginFilter> loginFilterBean() {
		FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new LoginFilter());
		registrationBean.addUrlPatterns("/staff/*");
//		registrationBean.addUrlPatterns("/testingonline/*");

		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<JoinTestFilter> joinTestFilterBean() {
		FilterRegistrationBean<JoinTestFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new JoinTestFilter());
		registrationBean.addUrlPatterns("/testpage/*");
//		registrationBean.addUrlPatterns("/testingonline/*");

		return registrationBean;
	}

}
