package by.ermakovich.contacts.config;

import by.ermakovich.contacts.filters.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http.
                 httpBasic().disable()
                 .csrf().disable()
                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                 .and()
                 .authorizeRequests()
                 .antMatchers("/admin/*","/apiAdmin/*").hasRole("ADMIN")
                 .antMatchers("/user/*").hasRole("USER")
                 .antMatchers("/api/*").hasAnyRole("ADMIN","USER")
                 .antMatchers("/register","/auth","/getUserByToken","/api/getAllUsers").permitAll()
                 .and()
                 .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                 .cors()
                 .and();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
