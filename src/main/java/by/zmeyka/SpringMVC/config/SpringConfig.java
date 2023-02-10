package by.zmeyka.SpringMVC.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.sql.DataSource;
import java.util.Set;

@Configuration
@ComponentScan("by.zmeyka.SpringMVC")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {
        private final ApplicationContext applicationContext;
        @Autowired
        public SpringConfig(ApplicationContext applicationContext){
            this.applicationContext=applicationContext;
        }
        @Bean
        public SpringResourceTemplateResolver templateResolver(){
            SpringResourceTemplateResolver templateResolver=new SpringResourceTemplateResolver();
            templateResolver.setApplicationContext(applicationContext);
            templateResolver.setPrefix("/WEB-INF/views/");
            templateResolver.setSuffix(".html");
            return templateResolver;

        }
        @Bean
        public SpringTemplateEngine templateEngine(){
            SpringTemplateEngine templateEngine=new SpringTemplateEngine();
            templateEngine.setTemplateResolver(templateResolver());
            templateEngine.setEnableSpringELCompiler(true);
            return templateEngine;
        }
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver=new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds=new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/library");
        ds.setPassword("Tanko1");
        ds.setUsername("root");
        return ds;
    }
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }





}


