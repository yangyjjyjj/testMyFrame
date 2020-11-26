package com.wtwd.ldl.thread;

/**
 * @Author ldaoliang
 * @Date 2019/10/16 0016 下午 5:49
 * @Description 线程异常的处理
 **/
public class InterruptException {

	private static class UseThread extends Thread{

		public UseThread(String name){
			super(name);
		}

		//抛出异常后，中断标志位会改为false
		public void run(){
			String threadName = Thread.currentThread().getName();
			while (!isInterrupted()){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println(threadName+"中断标志位为"+isInterrupted());
					e.printStackTrace();
					//在处理异常时也中断一下；改变中断标志位
					interrupt();
				}
			}
			System.out.println("结束时"+threadName+"中断标志位为"+isInterrupted());
		}

		public static void main(String[] args)throws InterruptedException {
			Thread endThread = new Thread("这个UseThread的线程");
			endThread.start();
			Thread.sleep(500);
			endThread.interrupt();
		}


	}
}
