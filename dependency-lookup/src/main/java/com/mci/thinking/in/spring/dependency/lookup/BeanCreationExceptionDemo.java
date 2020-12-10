package com.mci.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * {@link org.springframework.beans.factory.BeanCreationException}
 */
public class BeanCreationExceptionDemo {
    public static void main(String[] args) {
        // create context
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // user CharSequence instead of String here
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);
        applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());

        // load context
        applicationContext.refresh();

        applicationContext.close();
    }

    static class POJO implements InitializingBean {

        @PostConstruct
        public void init() throws Throwable {
            throw new Throwable("init(): for purpose...");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet(): for purpose...");
        }
    }
}
