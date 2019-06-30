package com.roll.casserole.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 注解处理器，读取PasswordUtils类，并使用反射机制查找@UserCase标记。我们为其提供了一组Id值，然后它会列出在
 * PasswordUtils中找到的用例，已经缺失的用例。
 *
 * @author haozq
 * Date: 2018/8/19 下午12:16
 */
public class UseCaseTracker {
	public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
		for (Method m : cl.getDeclaredMethods()) {
			UserCase uc = m.getAnnotation(UserCase.class);
			if (uc != null) {
				System.out.println("Found use case: " + uc.id() + " " + uc.description());
				useCases.remove(new Integer(uc.id()));
			}
		}

		for (int i : useCases) {
			System.out.println("Waring : missing user case-" + i);
		}
	}

	public static void main(String args[]) {
		List<Integer> useCase = new ArrayList<>();
		Collections.addAll(useCase, 47, 48, 49, 50);
		trackUseCases(useCase, PasswordUtils.class);
	}
}
