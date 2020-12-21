package com.mci.thinking.in.spring.bean.definition;

import com.mci.thinking.in.spring.bean.factory.DefaultUserFactory;
import com.mci.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonBeanRegistrationDemo {
    public static void main(String[] args) {
        // BeanFactory container
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // create an external UserFactory
        UserFactory userFactory = new DefaultUserFactory();

//        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        SingletonBeanRegistry singletonBeanRegistry = applicationContext.getBeanFactory();

        // register an external singleton object
//        beanFactory.registerSingleton("userFactory", userFactory);
        singletonBeanRegistry.registerSingleton("userFactory", userFactory);
        // launch application context
        applicationContext.refresh();


        // use dependency lookup to obtain UserFactory
        UserFactory userFactoryByLookup = applicationContext.getBean("userFactory", UserFactory.class);
        System.out.println("userFactory == userFactoryByLookup: " + (userFactory == userFactoryByLookup));

        applicationContext.close();
    }
}
