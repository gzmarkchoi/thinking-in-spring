package com.mci.thinking.in.spring.bean.definition;

import com.mci.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanGarbageCollectionDemo {
    public static void main(String[] args) {
        // BeanFactory container
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(BeanInitializationDemo.class);

        applicationContext.refresh();

        applicationContext.close();

        System.gc();
    }
}
