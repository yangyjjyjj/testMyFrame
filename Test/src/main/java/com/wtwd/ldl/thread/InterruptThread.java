package com.wtwd.ldl.thread;

/**
 * @Author ldaoliang
 * @Date 2019/10/9 0009 下午 5:26
 * @Description 如何安全地中断线程
 **/
public class InterruptThread extends Thread {

	public InterruptThread(String name){
		super(name);
	}

	/**
	 * //只有这个线程自己停止，才有效；即如果对中断不予理会，会继续执行该线程；这就说明java的线程是协作式开发;
	 * 所以可以根据线程的中断标志位的状态判断该线程是否被调用了中断命令，根据这个中断标志位判断线程是否要继续执行下去
	 */
	public void run(){
		//如果不属于中断状态  isInterrupted()-->true 中断状态
		while (!isInterrupted()){//如果是实现Runnable接口的类，获取线程状态就得使用 Thread.currentThread().isInterrupted才能中断;方式一也可以使用这个
			String threadName = Thread.currentThread().getName();//当前线程的名字
			System.out.println("current Thread name is "+threadName);
		}
		System.out.println("now is not zhe interrupted status");
	}

	public static void main(String[] args) {
		InterruptThread interruptThread = new InterruptThread("interruptThread");
		Long currentMills = System.currentTimeMillis();
		interruptThread.start();
		try {
			//线程休眠20毫秒，期间还是处于非中断状态，所以会一直判断是否处于中断状态，所以一直执行，打印次数跟线程执行速度有关
			interruptThread.sleep(20);
			Long endMills = System.currentTimeMillis();
			System.out.println(endMills - currentMills);
			//线程中断
			interruptThread.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
