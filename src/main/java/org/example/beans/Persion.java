package org.example.beans;

import java.io.Serializable;

public class Persion implements Serializable {
    static final long serialVersionUID = 42L;

    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
