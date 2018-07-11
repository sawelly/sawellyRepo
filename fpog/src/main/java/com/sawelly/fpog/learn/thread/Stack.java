package com.sawelly.fpog.learn.thread;

public class Stack{
	public static int index=0;
	public static Mantou array[] = new Mantou[10];
	
	public synchronized void addMantou(Mantou mantou){
			if(index == array.length){
				this.notify();
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			array[index] = mantou;
			index++;
	}

	public synchronized Mantou delMantou() {
		if(index == 0){
			this.notify();
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		index--;
		return array[index];
	}

}
