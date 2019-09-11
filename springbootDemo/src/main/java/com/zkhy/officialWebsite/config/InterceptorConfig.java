package com.zkhy.officialWebsite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zkhy.officialWebsite.interceptor.LoginHandleInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginHandleInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/config","/**/login","/**/logout","/**/api/**");
		super.addInterceptors(registry);
	}

	
}
