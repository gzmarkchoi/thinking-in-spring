package com.mci.thinking.in.spring.ioc.dependency.injection;

import com.mci.thinking.in.spring.ioc.dependency.injection.annotation.UserGroup;
import com.mci.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Set;

/**
 * {@link org.springframework.beans.factory.ObjectProvider}
 * <p>
 * Ref.64
 */
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; // real time injection

    @Autowired
    private ObjectProvider<User> userObjectProvider; // delayed injection

    @Autowired
    private ObjectFactory<Set<User>> userObjectFactory;

    public static void main(String[] args) {
        // create context
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // register configuration class -> Spring Bean
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        // load XML resource and generate BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        // dependency lookup QualifierAnnotationDependencyInjectionDemo
        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

        // superUser Bean
        System.out.println("demo.user = " + demo.user);
        // superUser Bean
        System.out.println("demo.userObjectProvider = " + demo.userObjectProvider.getObject()); // from ObjectFactory
        System.out.println("demo.userObjectFactory = " + demo.userObjectFactory.getObject());

        demo.userObjectProvider.forEach(System.out::println);

        applicationContext.close();
    }
}
