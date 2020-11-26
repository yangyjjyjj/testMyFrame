package com.wtwd.ldl.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ManyThreadSql {

    private static int time = 150;


    private final CountDownLatch latch = new CountDownLatch(2);//final对所有线程都可见无不可见问题

    public void excuteSql() throws InterruptedException {
        ExecutorService es1 =  Executors.newCachedThreadPool();
        ExecutorService es2 =  Executors.newCachedThreadPool();
//        ExecutorService es3 =  Executors.newCachedThreadPool();
//        ExecutorService es4 =  Executors.newCachedThreadPool();
//        ExecutorService es5 =  Executors.newCachedThreadPool();

        es1.execute(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("test1");
                int temp = getRandomTime(time);
                try {
                    Thread.sleep(temp);//假如执行sql平均需0.5秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("test1线程耗时"+temp+"ms"+"\n");
                latch.countDown();
                           }
        });
        es1.shutdown();
        es2.execute(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("test2");
                int temp = getRandomTime(time);
                try {
                    Thread.sleep(temp);//假如执行sql平均需0.5秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("test2线程耗时"+temp+"ms"+"\n");
                latch.countDown();
            }
        });
        es2.shutdown();
//        es3.execute(new Runnable() {
//            @Override
//            public void run() {
//                Thread.currentThread().setName("test3");
//                int temp = getRandomTime(time);
//                try {
//                    Thread.sleep(temp);//假如执行sql平均需0.5秒
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.print("test3线程耗时"+temp+"ms"+"\n");
//                latch.countDown();
//            }
//        });
//        es3.shutdown();
//        es4.execute(new Runnable() {
//            @Override
//            public void run() {
//                Thread.currentThread().setName("test4");
//                int temp = getRandomTime(time);
//                try {
//                    Thread.sleep(temp);//假如执行sql平均需0.5秒
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.print("test4线程耗时"+temp+"ms"+"\n");
//                latch.countDown();
//            }
//        });
//        es4.shutdown();
//        es5.execute(new Runnable() {
//            @Override
//            public void run() {
//                Thread.currentThread().setName("test5");
//                int temp = getRandomTime(time);
//                try {
//                    Thread.sleep(temp);//假如执行sql平均需0.5秒
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.print("test5线程耗时"+temp+"ms"+"\n");
//                latch.countDown();
//            }
//        });
//        es5.shutdown();
        latch.await();
    }

    /**
     * 获取50ms的浮动模拟
     * @param time
     * @return
     */
    public int getRandomTime(int time){
        int offset = (int)(Math.random()*100)-50;
        return time - offset;
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        new ManyThreadSql().excuteSql();

        long end = System.currentTimeMillis();
        long consumingTime = end - start;
        System.out.print("假设每条sql耗时"+time+"ms,多线程执行sql需耗时"+consumingTime+"ms");
    }

}
