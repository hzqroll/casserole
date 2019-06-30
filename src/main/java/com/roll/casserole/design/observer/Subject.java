package com.roll.casserole.design.observer;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author haozq
 * Date: 2018/8/15 下午5:59
 */
public class Subject {
	private CopyOnWriteArrayList<Observer> observers = new CopyOnWriteArrayList<>();

	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	protected void notifyObserver() {
		for (Observer observer : observers) {
			observer.update();
		}
	}
}
