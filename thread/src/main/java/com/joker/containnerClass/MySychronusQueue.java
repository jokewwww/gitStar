package com.joker.containnerClass;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * SynchronusQueue
 * 同步队列,是一个线程为0的队列,是一个特殊的TransferQueue
 * 必须有消费线程等待,才能使用的队列
 * add方法,无阻塞,若没有消费线程阻塞等待数据,则抛出异常
 * put方法,有阻塞,托没有消费县城阻塞至等待数据,则阻塞
 */
public class MySychronusQueue {

  BlockingQueue<String> queue = new SynchronousQueue();

  public static void main(String[] args) {
      final MySychronusQueue t=new MySychronusQueue();

      new Thread(new Runnable() {
          @Override
          public void run() {
              System.out.println(Thread.currentThread().getName()+"thread begin");
              try {
                  TimeUnit.SECONDS.sleep(2);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              try {
                  System.out.println(Thread.currentThread().getName()+"-"+t.queue.take());
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      },"output thread").start();

      try {
          TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      t.queue.add("test add");


      /*try {
          t.queue.put("test put");
      } catch (InterruptedException e) {
          e.printStackTrace();
      }*/

      System.out.println(Thread.currentThread().getName()+"queue size: "+t.queue.size());
  }
}
