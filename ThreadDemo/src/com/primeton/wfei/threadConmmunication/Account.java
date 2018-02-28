package com.primeton.wfei.threadConmmunication;

public class Account {
	private double balance;
	private boolean flag=false;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	public synchronized void huaqian(double amount){
		while(!flag){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		balance-=amount;
		System.out.println("支付  ¥"+amount+"   余额："+balance);
		flag=false;
		notifyAll();
		
	}
	public synchronized void cunqian(double amount){
		while(flag){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		balance+=amount;
		System.out.println("转入  ¥"+amount+"   余额："+balance);
		flag=true;
		notifyAll();
	}

}
