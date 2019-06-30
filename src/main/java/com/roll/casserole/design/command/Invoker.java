package com.roll.casserole.design.command;

/**
 * 负责调用命令对象执行请求，相关方法叫做行动方法
 *
 * @author haozq
 * Date: 2018/8/15 下午5:09
 */
public class Invoker {
	/**
	 * 持有命令对象
	 */
	private Command command;

	public Invoker(Command command) {
		this.command = command;
	}

	/**
	 * 行动方法
	 */
	public void action() {
		command.execute();
	}
}
