package app.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ServletHttpHandlerAdapter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {

        AnnotationConfigApplicationContext fluxContext = new AnnotationConfigApplicationContext(WebFluxConfig.class);

        HttpHandler httpHandler = WebHttpHandlerBuilder.applicationContext(fluxContext).build();
        ServletHttpHandlerAdapter handlerAdapter = new ServletHttpHandlerAdapter(httpHandler);
        ServletRegistration.Dynamic fluxServlet = servletContext.addServlet("SpringFluxDispatcher", handlerAdapter);

        fluxServlet.setLoadOnStartup(1);
        fluxServlet.setAsyncSupported(true);
        fluxServlet.addMapping("/");

    }
}
