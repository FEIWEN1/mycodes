package com.primeton.wfei.threadConmmunication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionAccount {
	private double balance;
	private boolean flag=false;
	private ReentrantLock reentranLock=new ReentrantLock();
	private Condition condition=reentranLock.newCondition();
	public void huaqian(double amount){
		reentranLock.lock();
		while(!flag){
			try {
				condition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		balance-=amount;
		System.out.println("支付  ¥"+amount+"   余额："+balance);
		flag=false;
		condition.signalAll();
		reentranLock.unlock();
	}
	public void cunqian(double amount){
		reentranLock.lock();
		while(flag){
			try {
				condition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		balance+=amount;
		System.out.println("转入  ¥"+amount+"   余额："+balance);
		flag=true;
		condition.signalAll();
		reentranLock.unlock();
	}
}
