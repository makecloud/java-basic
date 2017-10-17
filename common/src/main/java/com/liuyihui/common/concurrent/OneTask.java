package com.liuyihui.common.concurrent;

public class OneTask implements Runnable {
	/**
	 * 操作本线程外的对象： 利用构造方法，在new OneTask时，传入外部参数对象并赋值给OneTask类的属性，然后在run()中操作这些对象
	 */
	public OneTask() {

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
