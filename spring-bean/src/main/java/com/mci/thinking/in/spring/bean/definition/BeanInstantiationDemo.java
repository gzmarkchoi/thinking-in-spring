package com.mci.thinking.in.spring.bean.definition;

import com.mci.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanInstantiationDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
        User userByStaticMethod = beanFactory.getBean("user-by-static-method", User.class);
        User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
        User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);

        System.out.println(userByStaticMethod);
        System.out.println(userByInstanceMethod);
        System.out.println(userByFactoryBean);

        System.out.println(userByStaticMethod == userByInstanceMethod);
        System.out.println(userByStaticMethod == userByFactoryBean);
    }

}
