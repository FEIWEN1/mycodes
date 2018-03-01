package com.primeton.wfei.threadcontrol;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Business{
	private int tag=1;
	private ReentrantLock lock=new ReentrantLock();
	private Condition aCondition=lock.newCondition();
	private Condition bCondition=lock.newCondition();
	private Condition cCondition=lock.newCondition();
	public void a(){
		lock.lock();
		while(tag!=1){
			try {
				aCondition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=0;i<10;i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"  循环次数："+i);
		}
		tag=2;
		bCondition.signal();
		lock.unlock();
	}
	public void b(){
		lock.lock();
		while(tag!=2){
			try {
				bCondition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName()+"  循环次数："+i);
		}
		tag=3;
		cCondition.signal();
		lock.unlock();
	}
	public void c(){
		lock.lock();
		while(tag!=3){
			try {
				cCondition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName()+"  循环次数："+i);
		}
		tag=1;
		aCondition.signal();
		lock.unlock();
	}
}
