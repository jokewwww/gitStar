package com.joker.Concurrent03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁的可打断机制 阻塞状态:包括普通阻塞,等待队列,锁池队列 普通阻塞:sleep(11),可以被打断,调用thread.interrupt()可以打断阻塞状态,抛出异常
 * 等待队列:wait()方法被调用,也是一种阻塞状态,只能由notify唤醒,无法打断 锁池队列:无法获取所标记,不是所有的锁池队列都可被打断
 * 使用ReentrantLock的lock方法,获取所标记的时候,如果需要阻塞等待锁标记,无法被打断
 * 使用ReentrantLock的lockInterruptibly方法,获取锁标记的时候,如果需要阻塞等待,可以被打断
 */
public class Concurrent03 {
  Lock lock = new ReentrantLock();

  void m1() {
    try {
      lock.lock();
      for (int i = 0; i < 5; i++) {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("m1)( method " + i);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  void m2() {
    try {
      lock.lockInterruptibly(); // 可尝试打断,阻塞等待锁,可以被其他吸纳还曾打断阻塞状态
    } catch (Exception e) {
      System.out.println("me() merhod 打断阻塞锁状态 interrupt");
    } finally {
      try {
        lock.unlock();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    final Concurrent03 t = new Concurrent03();
    new Thread(
            new Runnable() {
              @Override
              public void run() {
                t.m1();
              }
            })
        .start();

    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Thread thread =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                t.m2();
              }
            });

    thread.start();

    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (Exception e) {
    }

    thread.interrupt(); // 打断线程秀敏啊,非正常结束阻塞状态的线程,都会抛出异常
  }
}