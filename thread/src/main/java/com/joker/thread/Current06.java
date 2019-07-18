package com.joker.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile只能保证可见性,不能保证原子性,只是内存数据可见
 */
public class Current06 {

  volatile int count = 0;

  synchronized void m() {
    for (int i = 0; i < 10000; i++) {
      count++;
    }
  }

  public static void main(String[] args) {
    final Current06 c = new Current06();
    List<Thread> threads = new ArrayList<Thread>();
    for (int i = 0; i < 10; i++) {
      threads.add(new Thread(new Runnable() {
          public void run() {
              c.m();
          }
      }));
    }

    for (Thread thread :threads ) {
        thread.start();
    }

    for (Thread thread : threads ) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    System.out.println(c.count);
  }
}
