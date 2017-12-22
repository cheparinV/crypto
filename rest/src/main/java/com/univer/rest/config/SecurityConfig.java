package com.univer.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/**/version")
                .antMatchers("/**/metrics")
                .antMatchers("/**/trace")
                .antMatchers("/**/resources/**")
                .antMatchers("/**/swagger-resources/**")
                .antMatchers("/**/webjars/**")
                .antMatchers("/**/api-docs/**")
                .antMatchers("/**/images/**")
                .antMatchers("/**/swagger-ui.html")
                .anyRequest()
                .antMatchers("/mocked/oauth/userinfo")
        ;
    }
}

