package com.test;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

 

public class SysUserTest {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring/applicationContext.xml","spring/spring-dubbox.xml" });
		context.start();
		System.out.println("服务启动成功！！！");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
