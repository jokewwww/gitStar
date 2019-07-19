package com.joker.thread;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicXXX
 * 原子操作类型:其中的每个方法都是源自操作,可以保证线程安全
 */
public class Current07 {

  AtomicInteger count = new AtomicInteger(0);

  void m() {
    for (int i = 0; i < 10000; i++) {
      count.incrementAndGet();
    }
  }

  public static void main(String[] args) {
      final Current07 c=new Current07();

      List<Thread> threads=new ArrayList();

    for (int i = 0; i < 10; i++){
      threads.add(new Thread(new Runnable() {
          @Override
          public void run() {
              c.m();
          }
      }));
    }

    for (Thread thread :threads ) {
        thread.start();
    }

    for (Thread thread :threads ) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
      System.out.println(c.count.intValue());

  }
}
