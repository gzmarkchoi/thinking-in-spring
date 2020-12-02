package com.mci.thinking.in.spring.ioc.overview.container;

import com.mci.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class AnnotationApplicationContextAsIoCContainerDemo {
    public static void main(String[] args) {
        // create context
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // user current class as configuration class
        applicationContext.register(AnnotationApplicationContextAsIoCContainerDemo.class);

        // load context
        applicationContext.refresh();

        // dependency lookup
        lookupByCollectionType(applicationContext);

        // close context
        applicationContext.close();
    }

    /**
     * Using Java Annotation to define a Bean
     *
     * @return User
     */
    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("Christophe");

        return user;
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("All users found: " + users);
        }
    }
}
