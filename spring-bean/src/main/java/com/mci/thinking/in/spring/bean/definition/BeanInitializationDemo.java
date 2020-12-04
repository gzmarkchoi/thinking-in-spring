package com.mci.thinking.in.spring.bean.definition;

import com.mci.thinking.in.spring.bean.factory.DefaultUserFactory;
import com.mci.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class BeanInitializationDemo {
    public static void main(String[] args) {
        // BeanFactory container
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(BeanInitializationDemo.class);

        applicationContext.refresh();
        System.out.println("-------------- applicationContext started --------------");

        // dependency lookup
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);

        System.out.println("-------------- applicationContext ready to close --------------");
        applicationContext.close();
        System.out.println("-------------- applicationContext closed --------------");
    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
//    @Lazy
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
