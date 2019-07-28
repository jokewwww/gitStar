package com.joker.containnerClass;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * LinkedBlockingQueue
 * 阻塞容器
 * put & take -自动阻塞
 * put:自动阻塞,队列容量满后,自动阻塞
 * take:自动阻塞方法,队列容量为0后,自动阻塞
 *
 */
public class MyLinkedBlockingQueue {

  final BlockingQueue<String> queue = new LinkedBlockingQueue();
  final Random random = new Random();

  public static void main(String[] args) {
      final MyLinkedBlockingQueue t=new MyLinkedBlockingQueue();

      new Thread(new Runnable() {
          @Override
          public void run() {
              while (true){
                  try{
                      t.queue.put("value"+t.random.nextInt(10000));
                      TimeUnit.SECONDS.sleep(1);
                  }catch (Exception e){
                      e.printStackTrace();
                  }
              }
          }
      },"producer").start();

    for (int i = 0; i < 3; i++){
       new Thread(new Runnable() {
           @Override
           public void run() {
              try{
                  System.out.println(Thread.currentThread().getName()+
                          "-"+t.queue.take());

              }catch (Exception e){
                  e.printStackTrace();
              }
           }
       },"consumer"+i).start();
    }
  }
}
