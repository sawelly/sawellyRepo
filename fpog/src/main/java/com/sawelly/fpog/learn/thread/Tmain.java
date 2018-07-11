package com.sawelly.fpog.learn.thread;

public class Tmain{
	public static void main(String args[]){
		Stack  lanzi = new Stack();
		Product t1 = new Product(lanzi);
		new Thread(t1).start();
		Consumer t2 = new Consumer(lanzi);
		new Thread(t2).start();
	}

}
