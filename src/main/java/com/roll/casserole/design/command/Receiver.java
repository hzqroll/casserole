package com.roll.casserole.design.command;

/**
 * receiver：负责具体实施和执行一个请求。任何一个类都可以成为接收者，实施和执行请求的方法叫做行动方法。
 *
 * @author haozq
 * Date: 2018/8/15 下午5:05
 */
public class Receiver {
	/**
	 * 真正执行命令的相应操作
	 */
	public void action() {
		System.out.println("执行操作");
	}
}
