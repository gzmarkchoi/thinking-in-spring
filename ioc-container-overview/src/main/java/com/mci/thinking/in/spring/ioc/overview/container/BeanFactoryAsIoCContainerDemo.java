package com.mci.thinking.in.spring.ioc.overview.container;

import com.mci.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

public class BeanFactoryAsIoCContainerDemo {
    public static void main(String[] args) {
        // create BeanFactory container
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // load config
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        // XML config file
        String location = "classpath:/META-INF/dependency-lookup-context.xml";

        // load config
        int beanDefinitionsCount = reader.loadBeanDefinitions(location);
        System.out.println("Bean definitions count: " + beanDefinitionsCount);

        // dependency lookup
        lookupByCollectionType(beanFactory);
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);

            System.out.println("All users found: " + users);
        }
    }
}
