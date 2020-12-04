package com.mci.thinking.in.spring.bean.definition;

import com.mci.thinking.in.spring.bean.factory.DefaultUserFactory;
import com.mci.thinking.in.spring.bean.factory.UserFactory;
import com.mci.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");

        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");

//        demoServiceLoader();

        ServiceLoader<UserFactory> serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);

        displayServiceLoader(serviceLoader);

        UserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser());

    }

    public static void demoServiceLoader() {
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());

        displayServiceLoader(serviceLoader);
    }

    private static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {

        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.createUser());
        }
    }
}
