package com.roll.casserole.design.wrapper;

/**
 * 装饰角色：持有一个构建对象的实例，并定义一个与抽象接口一致的接口
 *
 * @author haozq
 * Date: 2018/8/16 下午4:34
 */
public class Decorator implements Component {
	private Component component;

	public Decorator(Component component) {
		this.component = component;
	}

	@Override
	public void ampleOperator() {
		//委派给构件
		component.ampleOperator();
	}
}
