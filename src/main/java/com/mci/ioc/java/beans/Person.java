package com.mci.ioc.java.beans;

/**
 * A person POJO
 * <p>
 * Writable/Readable(Setter/Getter)
 */
public class Person {
    // String to String
    String name;

    // String to Integer
    Integer age;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
