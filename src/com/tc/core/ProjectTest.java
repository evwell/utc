package com.tc.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ProjectTest {

	public static void main(String[] args) {
		// Spring test
		ApplicationContext ac = new FileSystemXmlApplicationContext(
				"G:/eclipseworkspace/tc/configs/applicationContext.xml","G:/eclipseworkspace/tc/configs/bean/applicationContext_system.xml");
		System.out.println(ac.getBean("system"));

	}

}
