package com.mci.thinking.in.spring.dependency.lookup;

import com.mci.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Different empty Bean behaviors of major Bean lookup
 * <p>
 * - BeanFactory#getBean
 * - ObjectFactory#getObject
 * - ObjectProvider#IfAvailable
 * - ListableBeanFactory#getBeansOfType
 * <p>
 * Ref.47
 */
public class TypeSafetyDependencyLookupDemo {
    public static void main(String[] args) {
        // create context
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // user current class "TypeSafetyDependencyLookupDemo" as configuration class
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);

        // load context
        applicationContext.refresh();

        // BeanFactory#getBean security issue
        displayBeanFactoryGetBean(applicationContext);
        // ObjectFactory#getObject security issue
        displayObjectFactoryGetObject(applicationContext);
        // ObjectProvider#IfAvailable security issue(no issue here)
        displayObjectProviderIfAvailable(applicationContext);

        // ListableBeanFactory#getBeansOfType security issue
        displayListableBeanFactoryGetBeansOfType(applicationContext);

        //ObjectProvider Stream operation security issue
        displayObjectProviderStreamOps(applicationContext);

        // close context
        applicationContext.close();
    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreamOps: ", () -> userObjectProvider.forEach(System.out::println));
    }

    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory beanFactory) {
        printBeansException("displayListableBeanFactoryGetBeansOfType: ", () -> beanFactory.getBeansOfType(User.class));
    }

    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable: ", () -> userObjectProvider.getIfAvailable());
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        // ObjectProvider is ObjectFactory
        ObjectFactory<User> userObjectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject: ", () -> userObjectFactory.getObject());
    }

    public static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean: ", () -> beanFactory.getBean(User.class));

    }

    private static void printBeansException(String source, Runnable runnable) {
        System.err.println("--------------------------------------------------------");
        System.err.println("Source from: " + source);
        try {
            runnable.run();
        } catch (BeansException exception) {
            exception.printStackTrace();
        }
    }
}