package com.cjj.config;

import java.util.List;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cjj.aop.MyInterceptor;
import com.cjj.filter.MyFilter;
import com.cjj.message.converter.MyMessageConverter;
@Configuration
@EnableWebMvc
public class MyMvcConfig extends WebMvcConfigurerAdapter{
	@Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//		converters.clear();
        converters.add(converter());
    }
	/**
	* 配置自定义的HttpMessageConverter 的Bean ，在Spring MVC 里注册HttpMessageConverter有两个方法：
	* 1、configureMessageConverters ：重载会覆盖掉Spring MVC 默认注册的多个HttpMessageConverter
	* 2、extendMessageConverters ：仅添加一个自定义的HttpMessageConverter ，不覆盖默认注册的HttpMessageConverter
	* 在这里重写extendMessageConverters
	*/
	
	
    @Bean
    public MyMessageConverter converter() {
        return new MyMessageConverter();
    }
    
    @Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
    	registry.addInterceptor(myInterceptor());
	}
	@Bean
    public MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }
    
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
    	registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		super.addResourceHandlers(registry);
	}
	@Bean
    public FilterRegistrationBean myFilterRegistration() {
      FilterRegistrationBean registration = new FilterRegistrationBean(myFilter());
      registration.addUrlPatterns("/*");
      return registration;
    }
    @Bean
    public Filter myFilter() {
        return new MyFilter();
    }

}
