package com.roll.casserole.event;

import java.util.EventListener;

/**
 * 事件监听接口首先继承了EventListener，然后针对不同的事件发布了时机提供乡音的处理方法定义，最主要的就是
 * 这些放啊所接受的参数就是MethodExecutionEvent类型。也就是说，我们的自定义事件监听器类只负责监听其对于的自定义事件
 * 并进行处理。
 * @author haozq
 * Date: 2018/8/23 下午3:43
 */
public interface MethodExecutionEventListener extends EventListener {
	/**
	 * 处理方法开始执行的时候发布的MethodExecutionEvent事件
	 */
	void onMethodBegin(MethodExecutionEvent executionEvent);

	/**
	 * 处理方法执行将结束的时候发布的MethodExecutionEvent事件
	 */
	void onMethodEnd(MethodExecutionEvent executionEvent);
}
