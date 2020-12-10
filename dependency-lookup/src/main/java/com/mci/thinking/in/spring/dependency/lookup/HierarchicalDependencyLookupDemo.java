package com.mci.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HierarchicalDependencyLookupDemo {
    public static void main(String[] args) {
        // create context
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // user current class "ObjectProviderDemo" as configuration class
        applicationContext.register(ObjectProviderDemo.class);

        // 1. get HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
//        System.out.println("current BeanFactory's parent BeanFactory: " + beanFactory.getParentBeanFactory());

        // 2. set Parent BeanFactory
        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
//        System.out.println("current BeanFactory's parent BeanFactory: " + beanFactory.getParentBeanFactory());

        displayContainsLocalBean(beanFactory, "user");
        displayContainsLocalBean(parentBeanFactory, "user");

        displayContainsBean(beanFactory, "user");
        displayContainsBean(parentBeanFactory, "user");

        // load context
        applicationContext.refresh();

        // close context
        applicationContext.close();
    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("current BeanFactory[%s] contains Bean name [%s] : %s\n", beanFactory, beanName,
                containsBean(beanFactory, beanName));
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();

        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containsBean(parentHierarchicalBeanFactory, beanName)) {
                return true;
            }
        }

        return beanFactory.containsLocalBean(beanName);
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("current BeanFactory[%s] contains Local Bean name [%s] : %s\n", beanFactory, beanName,
                beanFactory.containsLocalBean(beanName));
    }

    private static ConfigurableListableBeanFactory createParentBeanFactory() {
        // create BeanFactory container
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // load config
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        // XML config file
        String location = "classpath:/META-INF/dependency-lookup-context.xml";

        // load Bean
        reader.loadBeanDefinitions(location);

        return beanFactory;
    }
}
