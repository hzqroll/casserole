package com.roll.casserole.design.command;

/**
 * 声明了一个给所有具体命令累的抽象接口
 *
 * @author haozq
 * Date: 2018/8/15 下午5:06
 */
public interface Command {
	/**
	 * 执行方法
	 */
	void execute();
}
