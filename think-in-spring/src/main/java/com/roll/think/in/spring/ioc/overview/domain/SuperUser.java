package com.roll.think.in.spring.ioc.overview.domain;

import com.roll.think.in.spring.ioc.overview.annotation.Super;

/**
 * @author zongqiang.hao
 * created on 2020/5/12 9:43 下午.
 */
@Super
public class SuperUser extends User{
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
