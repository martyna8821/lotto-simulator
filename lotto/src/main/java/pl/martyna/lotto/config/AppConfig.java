package pl.martyna.lotto.config;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import pl.martyna.lotto.dto.Settings;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * Contains configuration for Spring MVC framework
 * @author Martyna Drabinska
 * @version 0.1
 */
@Configuration
@EnableWebMvc
@ComponentScan("pl.martyna.lotto")
public class AppConfig implements WebMvcConfigurer {

    /** application context */
    private final ApplicationContext applicationContext;



    /**
     * default constructor
     * @param applicationContext application context
     */
    @Autowired
    AppConfig(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    /**
     * creates Spring Bean SpringResourceTemplateResolver
     * @return SpringResourceTemplateResolver
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/view/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    /**
     * creates Spring Bean SpringTemplateEngine
     * @return SpringTemplateEngine
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }



    /**
     * Configure view resolvers to translate
     * String-based view names returned from controllers into concrete View implementations to perform rendering with.
     * @param registry ViewResolverRegistry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }

}
