package bk.s3browser.config;

import bk.s3browser.security.AmazonBasedAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * Spring security related configuration
 * @author Biju Kunjummen
 *
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/css/**", "/img/**", "/fonts/**", "/js/**", "/webjars/**", "/html/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                        .loginPage("/login")
                        .permitAll()
                        .and()
                .logout()
                    .logoutUrl("/logout")
                    .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(amazonAuthenticationProvider());
    }

    @Bean
    public AmazonBasedAuthenticationProvider amazonAuthenticationProvider() {
        return new AmazonBasedAuthenticationProvider();
    }
}
