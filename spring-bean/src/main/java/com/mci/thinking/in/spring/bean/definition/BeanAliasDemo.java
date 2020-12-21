package com.mci.thinking.in.spring.bean.definition;

import com.mci.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanAliasDemo {
    public static void main(String[] args) {
        // configure XML configuration file
        // start Spring application context
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");

        User user = beanFactory.getBean("markchoi-user", User.class);
        User markchoiUser = beanFactory.getBean("markchoi-user", User.class);

        System.out.println("user equals markchoi alias user " + (user == markchoiUser));
    }
}
