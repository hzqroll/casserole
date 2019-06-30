package com.roll.casserole.event;

import java.util.EventObject;

/**
 * 我们想对改方法的执行情况进行发布和监听，当该类型的时间发布之后，相应的监听器即可对稿爱类型的事件进行
 * 处理。如果需要，自定义事件类可以根据情况提供更多信息，不用担心自定义时间类的“承受力”
 *
 * @author haozq
 * Date: 2018/8/23 下午3:40
 */
public class MethodExecutionEvent extends EventObject {
	private String methodName;

	/**
	 * Constructs a prototypical Event.
	 *
	 * @param source The object on which the Event initially occurred.
	 * @throws IllegalArgumentException if source is null.
	 */
	public MethodExecutionEvent(Object source) {
		super(source);
	}

	public MethodExecutionEvent(Object source, String methodName) {
		super(source);
		this.methodName = methodName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
}
