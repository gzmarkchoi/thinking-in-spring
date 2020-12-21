package com.mci.thinking.in.spring.ioc.dependency.injection;

import com.mci.thinking.in.spring.ioc.dependency.injection.annotation.UserGroup;
import com.mci.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * {@link org.springframework.beans.factory.annotation.Qualifier}
 * <p>
 * Ref.63
 */
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("user") // specify Bean name or ID
    private User namedUser;

    /**
     * applicationcontext has 4 User type Bean:
     * - superUser
     * - user
     * - user1 -> Qualifier
     * - user2 -> Qualifier
     */

    @Autowired
    private Collection<User> allUsers; // 2 Beans = user + superUser

    @Autowired
    @Qualifier
    private Collection<User> qualifiedUsers; // 4 Beans = user1 + user2 + user3 + user4

    @Autowired
    @UserGroup
    private Collection<User> groupedUsers; // 2 Beans = user3 + user4

    @Bean
    @Qualifier
    public User user1() {
        return createUser(7L);
    }

    @Bean
    @Qualifier
    public User user2() {
        return createUser(8L);
    }

    @Bean
    @UserGroup
    public User user3() {
        return createUser(10L);
    }

    @Bean
    @UserGroup
    public User user4() {
        return createUser(12L);
    }

    private static User createUser(Long id) {
        User user = new User();
        user.setId(id);

        return user;
    }

    public static void main(String[] args) {
        // create context
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // register configuration class -> Spring Bean
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        // load XML resource and generate BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        // dependency lookup QualifierAnnotationDependencyInjectionDemo
        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        // superUser Bean
        System.out.println("demo.user = " + demo.user);
        // user Bean
        System.out.println("demo.namedUser = " + demo.namedUser);
        // user Bean + superUser Bean
        System.out.println("demo.allUsers = " + demo.allUsers);
        // qualified user Bean(user7 + user8)
        System.out.println("demo.qualifiedUsers = " + demo.qualifiedUsers);

        System.out.println("demo.groupedUsers = " + demo.groupedUsers);

        applicationContext.close();
    }
}
