package com.roll.casserole.ioc;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @author haozq
 * Date: 2018/8/14 下午2:54
 */
public class BeanInitTest {
	public static void main(String args[]) {
		/*DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
		AbstractBeanDefinition abstractBeanDefinition = new RootBeanDefinition(BasePojo.class);
		defaultListableBeanFactory.registerBeanDefinition("base", abstractBeanDefinition);
		ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
		constructorArgumentValues.addIndexedArgumentValue(0, 10);
		constructorArgumentValues.addIndexedArgumentValue(1, 20);
		abstractBeanDefinition.setConstructorArgumentValues(constructorArgumentValues);

		BasePojo basePojo = (BasePojo) defaultListableBeanFactory.getBean("base");
		System.out.println(basePojo.toString());*/

		//AbstractApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		//applicationContext.refresh();
		//BasePojo basePojo = (BasePojo) applicationContext.getBean("base");
		System.out.println(determineRootDir("Class:asd/bsdf/csdf"));


	}

	protected static String determineRootDir(String location) {
		int prefixEnd = location.indexOf(':') + 1;
		int rootDirEnd = location.length();
		while (rootDirEnd > prefixEnd) {
			rootDirEnd = location.lastIndexOf('/', rootDirEnd - 2) + 1;
		}
		if (rootDirEnd == 0) {
			rootDirEnd = prefixEnd;
		}
		return location.substring(0, rootDirEnd);
	}
}
