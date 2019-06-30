package com.roll.casserole.design.observer;

/**
 * @author haozq
 * Date: 2018/8/15 下午6:01
 */
public class Observer {
	/**
	 * 在被观察者状态发生变化时，这个方法被调用
	 */
	public void update(){
		System.out.println("收到通知");
	}
}
