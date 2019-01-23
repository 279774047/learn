package com.ziven.learn.classLoader;

import java.lang.ref.ReferenceQueue;

public class MyClassLoader extends ClassLoader {
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		return super.findClass(name);
	}


	public static void main(String[] args) throws InterruptedException {

	}
}
