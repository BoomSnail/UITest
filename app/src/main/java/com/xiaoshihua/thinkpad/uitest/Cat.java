package com.xiaoshihua.thinkpad.uitest;

/**
 * Created by ThinkPad on 2016/9/4.
 */
public class Cat {
    private String name;
    private int age;
    private int size;

    public Cat() {
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
}
