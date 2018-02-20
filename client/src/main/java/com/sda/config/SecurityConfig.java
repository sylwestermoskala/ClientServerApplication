package com.sda.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;



    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        //driverManagerDataSource.setUrl("postgres://qxnshzkzetwuon:3189ce5de88a1f6685b04865a401ba8f06e768f3894f56adc222ac977c3dbbae@ec2-174-129-195-73.compute-1.amazonaws.com:5432/d1ufoboq0nsots");
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://ec2-174-129-195-73.compute-1.amazonaws.com:5432/d1ufoboq0nsots?sslmode=require");
        driverManagerDataSource.setUsername("qxnshzkzetwuon");
        driverManagerDataSource.setPassword("3189ce5de88a1f6685b04865a401ba8f06e768f3894f56adc222ac977c3dbbae");
        return driverManagerDataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.authorizeRequests()
                .antMatchers("/log","/userdetail","/quiz1/*")
                //.access("hasRole('ROLE_USER')")
                .access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
                .and().formLogin().loginPage("/login")
                .usernameParameter("email").passwordParameter("password")
                /*.loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/",true)
                .failureUrl("/login.jsp?error=true")*/
                .and().logout().logoutSuccessUrl("/")
                .and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email,password,enabled from users where email=?")
                .authoritiesByUsernameQuery("select email,role from user_roles where email=?");
                //.authoritiesByUsernameQuery("select email, role from user_roles where username=?");
    }

}
