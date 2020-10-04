package hu.toll.tollWeb.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
        auth
            .inMemoryAuthentication()
            .passwordEncoder(passwordEncoder())
            .withUser("admin").password("$2a$10$VZxD0gqknDDuX5ZuyscX/OOBMD1YzXEzA4Le4tCLaAEouskvujBre").roles("ADMIN").and()
            .withUser("milan").password("$2a$10$TTGm5LqinUrUFXn2gUNjje5CmOhFDH/8D6tcyuETm/3/3ZkDKHQxW").roles("USER").and()
            .withUser("micura2").password("$2a$10$bLy9ClDEkb3WeRduU.WPMOMmXuVsFJFLOwRwrE0wXCgnPG7wbUUG.").roles("USER");
    }
}