package com.zs.springbootwebrestful.config;

import com.zs.springbootwebrestful.filter.MyFilter;
import com.zs.springbootwebrestful.listener.MyListener;
import com.zs.springbootwebrestful.servlet.MyServlet;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@Configuration
public class MyServerConfig {
    //注册三大组件
    @Bean
    public  ServletRegistrationBean  myServlet(){
        ServletRegistrationBean  registrationBean = new  ServletRegistrationBean (new MyServlet(),"/myServlet");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return registrationBean;
    }

    @Bean
    public  ServletListenerRegistrationBean  myListener(){
        ServletListenerRegistrationBean <MyListener> registrationBean = new  ServletListenerRegistrationBean<> (new MyListener());
        return registrationBean;
    }


    //配置嵌入式的Servlet容器
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> webServerFactoryCustomizer(){
        return new WebServerFactoryCustomizer<TomcatServletWebServerFactory>(){
            @Override
            public void customize(TomcatServletWebServerFactory factory) {
                factory.setPort(8081);
            }
        };
    }


}
