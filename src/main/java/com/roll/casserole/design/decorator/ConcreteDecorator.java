package com.roll.casserole.design.decorator;

/**
 * @author zongqiang.hao
 * created on 2018/10/25 上午11:15.
 */
public class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void printComponent(String name) {
        super.printComponent(name);
        System.out.println("concrete decorator");
    }

    public static void main(String args[]){
        Component component = new ConcreteComponent();
        ConcreteDecorator concreteDecorator = new ConcreteDecorator(component);
        concreteDecorator.printComponent("adsf");
    }
}
