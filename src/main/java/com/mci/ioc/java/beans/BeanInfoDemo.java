package com.mci.ioc.java.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

/**
 *
 */
public class BeanInfoDemo {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);

        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(propertyDescriptor -> {
                    System.out.println(propertyDescriptor);

                    // PropertyDescriptor allows adding property editor - PropertyEditor
                    // GUI -> text(String) -> PropertyType
                    // name -> String
                    // age -> Integer
                    Class<?> propertyType = propertyDescriptor.getPropertyType();

                    String propertyName = propertyDescriptor.getName();
                    if ("age".equals(propertyName)) { // add PropertyEditor for "age"
                        // String -> Integer
                        // Integer.valueOf("");
                        propertyDescriptor.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
                    }
                });
        ;
    }

    static class StringToIntegerPropertyEditor extends PropertyEditorSupport {
        public void setAsText(String text) throws IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            setValue(value);
        }
    }
}
