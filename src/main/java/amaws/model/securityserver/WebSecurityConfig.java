/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amaws.model.securityserver;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/**
 *
 * @author BlackSpider
 */

@Configuration
@EnableWebMvcSecurity
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    
    @Autowired
    DataSource dataSource;
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       
         http
                 .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response,authException ) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED) ) 
                .and()
                .requestMatchers()
                .antMatchers("/ama/datos/insertar")
                .antMatchers("/ama/user/existe")
                 .antMatchers("/ama/user/insertar")
                .and()
                .authorizeRequests()
                .antMatchers("/ama/datos/insertar").hasAuthority("AMA_ADMIN")
                .antMatchers("/ama/users/existe").hasAuthority("AMA_ADMIN")
                .antMatchers("/ama/users/insertar").hasAuthority("AMA_ADMIN")
                .and().httpBasic()
                /*.and()
                    .formLogin().permitAll()
                    .defaultSuccessUrl("/gopr", true).permitAll().and().logout().logoutSuccessUrl("/login").permitAll()
       */ ;
        /*http
            .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response,authException ) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED) ) 
               .and()
                
                .authorizeRequests()
                .antMatchers("/**").authenticated()
                
            .and()
              .httpBasic();*/
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       
        
        auth.jdbcAuthentication()
                .dataSource(dataSource)
		.usersByUsernameQuery(
			"select username,password, enabled from users where username=?")
		.authoritiesByUsernameQuery(
			"select username, authority from authorities where username=?");
        
                
        
     /*  auth.inMemoryAuthentication()
                .withUser("client")
                .password("client")
                .authorities("AMA_CLIENT")
                .and()
                .withUser("admin")
                .password("admin")
                .authorities("AMA_ADMIN");
        
      */
    }
    
}
