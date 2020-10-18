package com.lx.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix="person")
public class Person {

    /*
    用户名
     */
    private String uname;

    /*
    年龄
     */
    private int age;

    /*
    宠物
     */
    private Dog dog;

    /*
    list
     */
    private List<Object> lists;

    /*
    Map
     */
    private Map<String,Object> maps;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    @Override
    public String toString() {
        return "Person{" +
                "uname='" + uname + '\'' +
                ", age=" + age +
                ", dog=" + dog +
                ", lists=" + lists +
                ", maps=" + maps +
                '}';
    }
}
