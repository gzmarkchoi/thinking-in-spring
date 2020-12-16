package com.mci.thinking.in.spring.ioc.dependency.injection;

import com.mci.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * Manuel Java Annotation dependency field injection
 * <p>
 * Ref. 57
 */
public class AnnotationDependencyFieldInjectionDemo {

    @Autowired // attention @Autowired would ignore static field
    private UserHolder userHolderWithAutowire;

    @Resource
    private UserHolder userHolderWithResource;

    public static void main(String[] args) {
        // create context
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // register configuration class -> Spring Bean
        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        // load XML resource and generate BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        // dependency lookup AnnotationDependencyFieldInjectionDemo
        AnnotationDependencyFieldInjectionDemo demo = applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);

        // @Autowired field create
        System.out.println(demo.userHolderWithAutowire);
        System.out.println(demo.userHolderWithResource);

        applicationContext.close();

    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
