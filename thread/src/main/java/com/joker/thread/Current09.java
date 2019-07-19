package com.joker.thread;

import java.util.concurrent.TimeUnit;

/**
 * 锁对象变更问题
 *
 * 同步代码一旦加锁后,会有一个临时的锁引用执行锁对象,和真实的引用无直接关联.
 * 在锁未释放之前,修改锁对象引用,不会影响同步代码的执行
 */
public class Current09 {

    Object o=new Object();
    int i=0;

    int m(){
        System.out.println(Thread.currentThread().getName()+" start");

        synchronized (o){
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

  public static void main(String[] args) {
    final Current09 c=new Current09();
    new Thread(new Runnable() {
        @Override
        public void run() {
            c.m();
        }
    }).start();

      try {
          TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }

      Thread thread=new Thread(new Runnable() {
          @Override
          public void run() {
              c.m();
          }
      },"thread2");
      c.o = new Object();
      thread.start();
  }


}
