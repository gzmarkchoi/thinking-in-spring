package com.mci.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {
    // 1 @PostConstruct
    @PostConstruct
    public void init() {
        System.out.println("PostConstruct UserFactory initializing");
    }

    public void initUserFactory() {
        System.out.println("User defined initUserFactory() UserFactory initializing");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet() UserFactory initializing");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy: UserFactory destroying...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy(): UserFactory destroying...");
    }

    public void doDestroy() {
        System.out.println("User defined doDestroy(): UserFactory destroying...");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("DefaultUserFactory object is being garbage collected");
    }
}
