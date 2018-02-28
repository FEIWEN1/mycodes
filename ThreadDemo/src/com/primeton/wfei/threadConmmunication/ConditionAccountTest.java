package com.primeton.wfei.threadConmmunication;

public class ConditionAccountTest {
	public static void main(String[] args) {


		ConditionAccount account=new ConditionAccount();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<10;i++){
					account.cunqian(1000);
				}
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int j=0;j<5;j++){
					account.huaqian(200);
				}
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int k=0;k<5;k++){
					account.huaqian(200);
				}
			}
		}).start();



	}
}
