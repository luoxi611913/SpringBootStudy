package com.lx.model;

import org.springframework.stereotype.Component;

public class Dog {

    /*
    宠物名
     */
    private String dname;

    /*
    宠物品种
     */
    private String dtype;

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "dname='" + dname + '\'' +
                ", dtype='" + dtype + '\'' +
                '}';
    }
}
