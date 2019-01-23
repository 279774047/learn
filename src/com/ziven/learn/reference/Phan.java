package com.ziven.learn.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;

public class Phan {

	private static boolean isflag = true;
	private static ReferenceQueue<String> queue = new ReferenceQueue();

	public static void main(String[] args) throws InterruptedException {
		//对象分配在常量池，gc的时候并不会被回收
		String s1 = "s";
		//对象存在于堆上（常量池的拷贝副本，常量池上也有一份）
		String s = new String("s");
		Thread t = new Thread(()->{
			while(isflag){
				Reference r = queue.poll();
				if(r != null){
					try {
						Field f = Reference.class.getDeclaredField("referent");
						Field f2 = String.class.getDeclaredField("hash");
						f.setAccessible(true);
						f2.setAccessible(true);
						Object o = f.get(r);
						Object o1 = f2.get(o);
						System.out.println(o.getClass());
						System.out.println(o.hashCode());
						isflag = false;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}


			}
		});
		t.start();
		PhantomReference<String> p = new PhantomReference<>(s, queue);
		System.out.println(p);
		s = null;
		System.gc();
		t.join();
	}
}
