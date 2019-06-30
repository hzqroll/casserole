package com.roll.casserole.event;

/**
 * @author haozq
 * Date: 2018/8/23 下午3:48
 */
public class SimpleMethodExecutionEventListener implements MethodExecutionEventListener {
	/**
	 * 处理方法开始执行的时候发布的MethodExecutionEvent事件
	 *
	 * @param executionEvent
	 */
	@Override
	public void onMethodBegin(MethodExecutionEvent executionEvent) {
		String methodName = executionEvent.getMethodName();
		System.out.println("start to execute the method: " + methodName);
	}

	/**
	 * 处理方法执行将结束的时候发布的MethodExecutionEvent事件
	 *
	 * @param executionEvent
	 */
	@Override
	public void onMethodEnd(MethodExecutionEvent executionEvent) {
		String methodName = executionEvent.getMethodName();
		System.out.println("end to execute the method: " + methodName);
	}
}
