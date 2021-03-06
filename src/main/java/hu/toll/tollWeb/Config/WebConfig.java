package hu.toll.tollWeb.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
            "/resources/**",
            "/css/**",
            "/images/**",
            "/js/**")
            .addResourceLocations(
                    "/resources/",
                    "classpath:/META-INF/resources/webjars/",
                    "classpath:/static/css/",
                    "classpath:/static/images/",
                    "classpath:/static/js/");
    }
}
