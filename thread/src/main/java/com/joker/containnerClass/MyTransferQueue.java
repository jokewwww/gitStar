package com.joker.containnerClass;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * 并发容器-LinkedTransferQueue
 * 转移队列
 * add():队列会保存数据,不做阻塞等待
 * transfer:是TransferQueue的特有方法,必须有消费者(take()方法的调用者)
 * 如果没有任意线程消费数据,transfer方法阻塞,一般用于处理即时消息
 */
public class MyTransferQueue {

  TransferQueue<String> queue = new LinkedTransferQueue();

  public static void main(String[] args) {
    final MyTransferQueue t=new MyTransferQueue();
    new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"Thread begin");
            try {
                System.out.println(Thread.currentThread().getName()+"-"+t.queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    },"output Thread").start();

      try {
          TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }

      try {
          t.queue.transfer("test String");
      } catch (InterruptedException e) {
          e.printStackTrace();
      }

      new Thread(new Runnable() {
          @Override
          public void run() {
              try {
                  t.queue.transfer("test String");
                  t.queue.add("test String");
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }).start();

      try{
          TimeUnit.SECONDS.sleep(2);
      }catch (Exception e){
          e.printStackTrace();
      }

      new Thread(new Runnable() {
          @Override
          public void run() {
              System.out.println(Thread.currentThread().getName()+"thread begin");
              try {
                  System.out.println(Thread.currentThread().getName()+"-"+t.queue.take());
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      },"output thread").start();
  }
}
