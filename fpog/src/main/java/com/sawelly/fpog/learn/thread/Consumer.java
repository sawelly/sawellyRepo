package com.sawelly.fpog.learn.thread;

public class Consumer implements Runnable{
	
	private Stack lanzi;
	
	public Consumer(Stack lanzi){
		this.lanzi = lanzi ;
	}
	@Override
	public void run() {
		if(lanzi != null){
			for (int i = 0; i < 100; i++) {
				Mantou m = lanzi.delMantou();
				System.out.println("del mantou "+m.getId());
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
