package com.mci.thinking.in.spring.bean.definition;

import com.mci.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;


/**
 * Define a Bean 3 different ways
 * <p>
 * 1. using @Bean
 * 2. using @Component
 * 3. using @Import
 * <p>
 * Ref.35
 */
@Import(AnnotationBeanDefinitionDemo.Config.class) // 3. using @Import
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        // create context
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // register configuration class
        applicationContext.register(AnnotationBeanDefinitionDemo.class);

        // register via BeanDefinition API
        // with a bean name
        registerUserBeanDefinition(applicationContext, "Christophe Lambert");

        // without a bean name
        registerUserBeanDefinition(applicationContext);

        applicationContext.refresh();

        // Dependency lookup
        System.out.println("Config Beans: " + applicationContext.getBeansOfType(Config.class));
        System.out.println("User Beans: " + applicationContext.getBeansOfType(User.class));

        applicationContext.close();
    }

    // named Bean register API
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id", 1L)
                .addPropertyValue("name", "Christophe");

        if (StringUtils.hasText(beanName)) { // beanName exists
            // register BeanDefinition
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getRawBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    // no named Bean register API
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }

    @Component // 2. using @Component
    public static class Config {
        /**
         * Using Java Annotation to define a Bean
         *
         * @return User
         */
        @Bean(name = {"user", "markchoiuser"}) // 1. using @Bean
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("Christophe");

            return user;
        }
    }


}
