package com.mci.thinking.in.spring.bean.factory.serviceloader;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.serviceloader.AbstractServiceLoaderBasedFactoryBean;

import java.util.ServiceLoader;

public class ServiceLoaderFactoryBean extends AbstractServiceLoaderBasedFactoryBean implements BeanClassLoaderAware {
    @Override
    protected Object getObjectToExpose(ServiceLoader<?> serviceLoader) {
        return serviceLoader;
    }

    @Override
    public Class<?> getObjectType() {
        return ServiceLoader.class;
    }
}
