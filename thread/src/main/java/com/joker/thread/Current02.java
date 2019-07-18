package com.joker.thread;

import java.util.concurrent.TimeUnit;

/**
 * 可重入锁
 * 同步方法调用其他同步方法
 * 可重入:同一个线程,多次调用同步代码,锁定同一个锁对象
 * 多线程锁定同一个锁对象是不可重入的
 */
public class Current02 {

    synchronized void m1(){
        System.out.println("m1 start");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1 end");
    }

    synchronized void m2()  {
        System.out.println("m2 start");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("m2 end");
    }

  public static void main(String[] args) {
    new Current02().m1();
  }
}
