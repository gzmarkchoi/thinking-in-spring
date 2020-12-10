package com.mci.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link org.springframework.beans.factory.NoUniqueBeanDefinitionException}
 */
public class NoUniqueBeanDefinitionExceptionDemo {
    public static void main(String[] args) {
        // create context
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // user current class "NoUniqueBeanDefinitionExceptionDemo" as configuration class
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);

        // load context
        applicationContext.refresh();

        try {
            applicationContext.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.err.printf("Spring applicationContext has %d type %s Bean, cause %s\n",
                    e.getNumberOfBeansFound(),
                    String.class.getName(),
                    e.getMessage());
        }

        applicationContext.close();
    }

    @Bean
    public String beanA() {
        return "Bean A";
    }

    @Bean
    public String beanB() {
        return "Bean B";
    }

    @Bean
    public String beanC() {
        return "Bean C";
    }
}
