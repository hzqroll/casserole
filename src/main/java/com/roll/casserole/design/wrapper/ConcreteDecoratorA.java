package com.roll.casserole.design.wrapper;

/**
 * @author haozq
 * Date: 2018/8/16 下午4:36
 */
public class ConcreteDecoratorA extends Decorator {

	public ConcreteDecoratorA(Component component) {
		super(component);
	}

	@Override
	public void ampleOperator() {
		super.ampleOperator();
		//其他业务代码
		System.out.println("相关代码");
	}
}
