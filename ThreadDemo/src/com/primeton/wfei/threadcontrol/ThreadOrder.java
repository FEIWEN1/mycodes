package com.primeton.wfei.threadcontrol;


/**
 * 
 * @author feiwen
 * 任意启动若干个线程，让他们按照次序执行的实现
 *
 */
public class ThreadOrder {
	
	
	static Business business=new Business();
	public static void main(String[] args) {
		new cThread("c线程").start();
		new aThread("a线程").start();
		new bThread("b线程").start();
	}
	static class aThread extends Thread{
		 public aThread(String name) {
			 super(name);
		 }
		@Override
		public void run() {
			business.a();
		}
	}
	static class bThread extends Thread{
		 public bThread(String name) {
			 super(name);
		 }
		@Override
		public void run() {
			business.b();
		}
	}
	static class cThread extends Thread{
		 public cThread(String name) {
			 super(name);
		 }
		@Override
		public void run() {
			business.c();
		}
	}

}
