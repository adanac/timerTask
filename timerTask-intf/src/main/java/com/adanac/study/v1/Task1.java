package com.adanac.study.v1;

/**
 * 普通thread
 * 这是最常见的，创建一个thread，然后让它在while循环里一直运行着，
 * 通过sleep方法来达到定时任务的效果。这样可以快速简单的实现，代码如下：
 * @author allen
 *
 */
public class Task1 {
    public static void main(String[] args) {
        // run in a second
        final long timeInterval = 1000;
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    // ------- code for task to run
                    System.out.println("Hello !!");
                    // ------- ends here
                    try {
                        Thread.sleep(timeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}

