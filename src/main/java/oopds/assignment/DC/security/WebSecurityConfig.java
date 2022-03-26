package oopds.assignment.DC.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;
import oopds.assignment.DC.filter.JWTAuthFilter;
import oopds.assignment.DC.filter.authFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTAuthFilter jwtauthfilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // http.formLogin().loginPage("/api/auth/donor/login").usernameParameter("email");
        // http.cors().and().csrf().disable().authorizeRequests().antMatchers("/api/auth/**").permitAll().anyRequest()
        // .authenticated();
        // http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // // http.authorizeHttpRequests().anyRequest().permitAll();
        // http.addFilter(new authFilter(authenticationManagerBean()));
        // http.addFilterBefore(jwtauthfilter,
        // UsernamePasswordAuthenticationFilter.class);

        http.cors()
                .and().csrf().disable()
        // .authorizeRequests().antMatchers("/api/auth/**").permitAll().anyRequest()
        // .authenticated().and()
        // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        // .addFilterBefore(jwtauthfilter,
        // UsernamePasswordAuthenticationFilter.class);
        ;
        // http.authorizeHttpRequests().anyRequest().permitAll();
        // http.addFilter(new authFilter(authenticationManagerBean()));

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
