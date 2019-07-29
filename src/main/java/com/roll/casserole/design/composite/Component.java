package com.roll.casserole.design.composite;

/**
 * 组合模式
 * 将对象组合成树形结构以表示'部分-整体'的层次结构，组合模式使得用户对单个对象和组合对象的使用具有一致性。
 * 组合中的对象声明接口，在适当的情况下，实现所有类共有接口的默认行为。声明一个接口用于访问和管理component的子部件
 *
 * @author roll
 * created on 2019-07-28 22:27
 */
abstract class Component {
    protected String name;

    public Component(String name) {
        this.name = name;
    }

    abstract void add(Component component);

    abstract void remove(Component component);

    abstract void display(int depth);
}
