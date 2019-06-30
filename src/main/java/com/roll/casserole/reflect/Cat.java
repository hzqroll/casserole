package com.roll.casserole.reflect;

/**
 * @author zongqiang.hao
 * created on 2019-02-26 10:37.
 */
public class Cat {
    public String name;

    private boolean isCat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name + "set";
    }

    public boolean isCat() {
        return isCat;
    }

    public void setCat(boolean cat) {
        isCat = cat;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", isCat=" + isCat +
                '}';
    }
}
