/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.repositoriotcc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author Frank
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ImplementsUserDetailsService userDetailsService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET, "/listaTrabalhosCards").permitAll()
                .antMatchers(HttpMethod.GET, "/pesquisaPessoas").permitAll()
                .antMatchers(HttpMethod.GET, "/listaTrabalhosConcluidos").permitAll()
                .antMatchers(HttpMethod.GET, "/detalhesPessoa").permitAll()
                .antMatchers(HttpMethod.GET, "/detalhesTrabalho").permitAll()
                .antMatchers(HttpMethod.GET, "/detalhesBanca").permitAll()
                //.antMatchers(HttpMethod.GET, "/listaBancas").hasRole("ADMIN")
                //.antMatchers(HttpMethod.POST, "/salvarBanca").hasRole("ADMIN")
                //.antMatchers(HttpMethod.GET, "/listaPessoas").hasRole("ADMIN")
                        
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/bootstrap/**", "/datepicker/**", "/bootstrap-datetimepicker-master/**", "/static.validacpfjs/**");
    }
}
