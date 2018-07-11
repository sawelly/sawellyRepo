package com.sawelly.fpog.learn.thread;

public class Product implements Runnable{
	
	private Stack lanzi;
	
	public Product(Stack lanzi){
		this.lanzi = lanzi ;
	}
	@Override
	public void run() {
		if(lanzi != null){
			for (int i = 0; i < 100; i++) {
				System.out.println("producting mantou "+i);
				Mantou mantou = new Mantou(i);
				lanzi.addMantou(mantou);
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
			
		}
		
	}
	
}
