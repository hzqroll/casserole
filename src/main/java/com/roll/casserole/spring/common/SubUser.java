package com.roll.casserole.spring.common;

/**
 * @author roll
 * created on 2020/6/14 1:03 下午
 */
public class SubUser extends User {
    private String subName;

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    @Override
    public String toString() {
        return "SubUser{" +
                "subName='" + subName + '\'' +
                "} " + super.toString();
    }
}
