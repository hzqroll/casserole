package com.roll.casserole.event;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haozq
 * Date: 2018/8/23 下午3:50
 */
public class MethodExecutionEventPublisher {
	private List<MethodExecutionEventListener> listeners = new ArrayList<>();

	public void methodToMonitor() {
		MethodExecutionEvent executionEvent = new MethodExecutionEvent(this, "methodToMonitor");
		publishEvent(MethodExecutionStatus.BEGIN, executionEvent);
		System.out.println("----------");
		publishEvent(MethodExecutionStatus.END, executionEvent);
	}

	protected void publishEvent(int status, MethodExecutionEvent methodExecutionEvent) {
		List<MethodExecutionEventListener> copyListener = new ArrayList<>();
		copyListener.addAll(listeners);
		for (MethodExecutionEventListener listener : copyListener) {
			if (MethodExecutionStatus.BEGIN == status) {
				listener.onMethodBegin(methodExecutionEvent);
			} else {
				listener.onMethodEnd(methodExecutionEvent);
			}
		}
	}

	public void addMethodExecutionEventListener(MethodExecutionEventListener listener) {
		this.listeners.add(listener);
	}

	public void removeMethodExecutionEventListener(MethodExecutionEventListener listener) {
		this.listeners.remove(listener);
	}

	public void removeAll() {
		this.listeners.clear();
	}

	public static void main(String args[]) {
		MethodExecutionEventPublisher executionEventPublisher = new MethodExecutionEventPublisher();
		executionEventPublisher.addMethodExecutionEventListener(new SimpleMethodExecutionEventListener());
		executionEventPublisher.methodToMonitor();
	}
}
