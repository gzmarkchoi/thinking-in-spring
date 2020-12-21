package com.mci.thinking.in.spring.ioc.overview.dependency.injection;

import com.mci.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

public class DependencyInjectionDemo {
    public static void main(String[] args) {
        // configure XML configuration file
        // start Spring application context
//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");
        // user defied bean
        UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);

        // dependency injection
        System.out.println(userRepository.getObjectFactory());

        ObjectFactory userFactory = userRepository.getObjectFactory();
        System.out.println(userFactory.getObject());
        System.out.println(userFactory.getObject() == applicationContext);

        // dependency lookup(exception)
//        System.out.println(beanFactory.getBean(BeanFactory.class));

        // bean in container
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println("Get (Spring) Environment bean: " + environment);
    }

    private static void whoIsIoCContainer(UserRepository userRepository, BeanFactory beanFactory) {
        // ConfigurableApplicationContext <- ApplicationContext <- BeanFactory

        // ConfigurableApplicationContext#getBeanFactory()

        // why is not true
        System.out.println(userRepository.getBeanFactory() == beanFactory);

        // ApplicationContext is BeanFactory
    }

}
