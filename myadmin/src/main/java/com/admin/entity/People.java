package com.admin.entity;

import com.admin.annotation.CheckValue;
import com.admin.annotation.NotEmpty;

public class People {
    @CheckValue("^[A-Za-z0-9_-]{13,32}$")
    private String id;
    @NotEmpty
    private String name;
    @CheckValue
    private int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "People [id=" + id + ", name=" + name + ", age=" + age + "]";
    }

}
