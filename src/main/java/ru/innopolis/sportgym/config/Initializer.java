package ru.innopolis.sportgym.config;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import static ru.innopolis.sportgym.config.Constance.DISPATCHER_SERVLET_NAME;

/**
 * Класс настройки контейнера сервлетов
 * Для работы требуется поддержка Servlet API 3.0
 * Заменяет настройку в файле web.xml
 * Created by Кирилл on 03.11.2016.
 */
public class Initializer implements WebApplicationInitializer {

    /**
     * Настройка контейнера сервлетов вместо web.xml
     *
     * @param servletContext
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        // Регистрируем в контексте конфигурационный класс MVC, Security, JPA
        ctx.register(WebAppConfig.class);
        ctx.register(SecurityConfig.class);
        ctx.register(JPAConfig.class);

        // Добавляем слушателя
        servletContext.addListener(new ContextLoaderListener(ctx));
        ctx.setServletContext(servletContext);

        // Добавляем сервлет - диспатчер
        Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);


        //Фильтр Spring Security
        servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "/*");
        //Фильтр перекодировки в UTF-8
        servletContext.addFilter("charsetFilter", new CharacterEncodingFilter("UTF-8", true))
                .addMappingForUrlPatterns(null, false, "/*");

        //Фильтр OpenEntityManagerInViewFilter для Lazy загрузки полей
        servletContext.addFilter("oemInViewFilter", new OpenEntityManagerInViewFilter())
                .addMappingForUrlPatterns(null, false, "/*");


    }

}
