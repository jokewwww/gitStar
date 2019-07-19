package com.joker.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 门闩-CountDownLatch
 * 可以和锁混合使用,或代替锁的功能
 * 在门闩未完全开放之前等待,当门闩完全开放后执行
 * 可以避免锁的效率低下问题
 */
public class Current10 {

  CountDownLatch latch = new CountDownLatch(5);

  void m1() {
    try {
      latch.await(); // 等待门闩开放
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("m1() method excute");
  }

  void m2() {
    for (int i = 0; i < 10; i++) {
      if (latch.getCount() != 0) {
        System.out.println("latch count:" + latch.getCount());
        latch.countDown(); // 减门闩上的锁
      }
      /*try {
        TimeUnit.SECONDS.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }*/
      System.out.println("m2() method:"+i);
    }
  }

  public static void main(String[] args) {
    final Current10 c=new Current10();
    new Thread(new Runnable() {
      @Override
      public void run() {
        c.m1();
      }
    }).start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        c.m2();
      }
    }).start();
  }
}
