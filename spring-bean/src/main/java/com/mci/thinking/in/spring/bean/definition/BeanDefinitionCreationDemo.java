package com.mci.thinking.in.spring.bean.definition;

import com.mci.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition}
 */
public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        // 1. using BeanDefinitionBuilder to create
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);

        // property config
        beanDefinitionBuilder
                .addPropertyValue("id", 1)
                .addPropertyValue("name", "Nicolas");

        // get beanDefinition
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        // 2. using AbstractBeanDefinition to create
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();

        // set Bean type
        genericBeanDefinition.setBeanClass(User.class);

        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues
                .add("id", 1)
                .add("name", "Jean");

        genericBeanDefinition.setPropertyValues(propertyValues);

    }
}
