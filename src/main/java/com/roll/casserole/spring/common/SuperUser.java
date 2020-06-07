package com.roll.casserole.spring.common;

/**
 * @author roll
 * created on 2020/6/7 5:04 下午
 */
public class SuperUser extends User {
    private String parentName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "parentName='" + parentName + '\'' +
                "} " + super.toString();
    }
}
