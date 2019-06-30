package com.roll.casserole.ioc;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author haozq
 * Date: 2018/8/14 下午3:33
 */
@Component
public class BasePojo implements InitializingBean {
	private int a;
	private int b;

	/*public BasePojo(int a, int b) {
		this.a = a;
		this.b = b;
	}*/

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("BasePojo{");
		sb.append("a=").append(a);
		sb.append(", b=").append(b);
		sb.append('}');
		return sb.toString();
	}

	@PostConstruct
	public void init() {
		System.out.println("init ,a: " + a);
	}

	@PreDestroy
	public void destory() {
		System.out.println("destory, a: " + a);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet ,a: " + a);
	}
}
