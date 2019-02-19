package com.fedex.moon.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	
	//Added this for backward compatibility of spring-context
	@Bean
	public static PropertySourcesPlaceholderConfigurer configurer () {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	// Check what happens if we add this in the SecOauthClientApplication class by extending the class from SpringBootServletInitializer
	@Bean
	public RequestContextListener listener () {
		return new RequestContextListener();
	}
	
	@Override
	public void configureDefaultServletHandling (final DefaultServletHandlerConfigurer dConfigurer) {
		dConfigurer.enable();
	}
	
	@Override
	public void addViewControllers (final ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index");
		registry.addViewController("/index");
		registry.addViewController("/secureClientPage");
	}
	
	@Override
	public void addResourceHandlers (final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
			.addResourceLocations("/resources/");
	}
}
