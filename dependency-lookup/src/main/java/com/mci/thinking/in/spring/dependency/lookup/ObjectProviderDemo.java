package com.mci.thinking.in.spring.dependency.lookup;

import com.mci.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Iterator;

/**
 * Dependency lookup via {@link ObjectProvider}
 * <p>
 * Single object provider demo
 */
public class ObjectProviderDemo { // @Configuration is not mandatory
    public static void main(String[] args) {
        // create context
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // user current class "ObjectProviderDemo" as configuration class
        applicationContext.register(ObjectProviderDemo.class);

        // load context
        applicationContext.refresh();

        // dependency lookup
        lookupByObjectProvider(applicationContext);
        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);

        // close context
        applicationContext.close();
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
//        Iterable<String> stringIterable = objectProvider;
//        for (String str : stringIterable) {
//            System.out.println(str);
//        }

        // Stream -> Method reference
        objectProvider.stream().forEach(System.out::println);
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        User user = userObjectProvider.getIfAvailable(User::createUser);

        System.out.println("Current User Object: " + user);
    }

    @Bean
    @Primary
    public String helloString() { // Bean name is "helloString"
        return "Hello world";
    }

    @Bean
    public String message() {
        return "message";
    }

    @Bean
    public Integer helloInt() {
        return 1;
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<Integer> objectProvider = applicationContext.getBeanProvider(Integer.class); // lookup by type
        System.out.println(objectProvider.getObject());
    }
}
