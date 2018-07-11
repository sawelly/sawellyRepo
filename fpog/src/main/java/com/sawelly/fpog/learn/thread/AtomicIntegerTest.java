package com.sawelly.fpog.learn.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程安全自增类 AtomicInteger
 */
public class AtomicIntegerTest {

	public static  final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);
	public static int value = 0;
	public static void main(String[] args) throws InterruptedException {

//		atomicIntegerTest();
//
//		Thread.sleep(3000);
//		System.out.println("最终结果是" + ATOMIC_INTEGER.get());

		integerTest();

		Thread.sleep(3000);
		System.out.println("最终结果是" + value);
	}

	private static void integerTest() {
		ExecutorService executorService = Executors.newFixedThreadPool(10000);
		for (int i = 0; i < 10000; i++) {
			executorService.execute(() -> {
				for (int j = 0; j < 4; j++) {
					System.out.println(value++);
				}
			});
		}
		executorService.shutdown();
//		Thread.sleep(3000);
//		System.out.println("最终结果是" + value);
	}


	private static void atomicIntegerTest() {
		ExecutorService executorService = Executors.newFixedThreadPool(10000);
		for (int i = 0; i < 10000; i++) {
			executorService.execute(() -> {
				for (int j = 0; j < 4; j++) {
					ATOMIC_INTEGER.getAndIncrement();
//					System.out.println(ATOMIC_INTEGER.getAndIncrement());
				}
			});
		}
		executorService.shutdown();
	}


}
