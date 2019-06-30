package com.roll.casserole.design.command;

/**
 *  定义一个接收者和定位直接的弱耦合，实现execute方法，负责调用接收者的相应操作
 * @author haozq
 * Date: 2018/8/15 下午5:07
 */
public class ConcreteCommand implements Command {
	//持有相应的接收者对象
	private Receiver receiver;

	public ConcreteCommand(Receiver receiver) {
		this.receiver = receiver;
	}

	/**
	 * 执行方法
	 */
	@Override
	public void execute() {
		//通常会转调接收者对象相应的方法，让接收者来着正在执行功能
		receiver.action();
	}
}
