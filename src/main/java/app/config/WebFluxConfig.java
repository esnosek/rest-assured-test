package app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebFlux
@ComponentScan(basePackages = "app")
public class WebFluxConfig {

}
