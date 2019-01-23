package com.ziven.learn.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Lock {

	private final static java.util.concurrent.locks.Lock lock = new ReentrantLock();
	private  static Condition condition = lock.newCondition();



	public static void main(String[] args) {
		System.out.println("start");
		Thread a = new Thread(){
			public void run(){
				try {
					this.sleep(5000);
					lock.lock();
					System.out.println("get lock ----a");
					condition.signal();
					Thread.currentThread().sleep(1000);
					System.out.println("signal ----a");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					lock.unlock();
					System.out.println("unlock ----a");
				}
			}
		};
		a.start();
		try {
//			lock.lock();
			System.out.println("get lock");
			Thread.currentThread().sleep(10000);
			System.out.println("wake up");
			condition.await();
			System.out.println("await");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
//			lock.unlock();
			System.out.println("unlock");
		}


	}

}
