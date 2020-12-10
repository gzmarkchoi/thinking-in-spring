package com.mci.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link org.springframework.beans.BeanInstantiationException}
 */
public class BeanInstantiationExceptionDemo {
    public static void main(String[] args) {
        // create context
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // user CharSequence instead of String here
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
        applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());

        // load context
        applicationContext.refresh();

        applicationContext.close();
    }
}
