package com.joker.Concurrent03;

import java.util.concurrent.locks.ReentrantLock;

/** 公平锁 sychronized没有公平性 ReentrantLock有公平性 */
public class Concurrent04 {
  public static void main(String[] args) {
    //TestReentrantLock t=new TestReentrantLock();
    TestSyn t=new TestSyn();

      Thread thread1 = new Thread(t);
      Thread thread2=new Thread(t);
      thread1.start();
      thread2.start();
  }
}

class TestReentrantLock extends Thread {
  // 定义一个公平锁:true代表是一个公平锁
  private static ReentrantLock lock = new ReentrantLock(true);

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
        lock.lock();//加锁
        try{
            System.out.println(Thread.currentThread().getName()+"get lock--"+i);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
  }
}

class TestSyn extends Thread{
    @Override
    public void run() {
    for (int i = 0; i < 10; i++) {
      synchronized (this){
          System.out.println(Thread.currentThread().getName()+"get lock --"+i);

      }
    }
    }
}
