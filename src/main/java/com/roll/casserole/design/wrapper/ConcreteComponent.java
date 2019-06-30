package com.roll.casserole.design.wrapper;

/**
 * 具体构建角色：定义一个将要接受附加责任的类
 *
 * @author haozq
 * Date: 2018/8/16 下午4:33
 */
public class ConcreteComponent implements Component {

	@Override
	public void ampleOperator() {
		System.out.println("具体业务代码");
	}
}
