package com.joker.threadPool;

import java.util.concurrent.*;

/**
 * 缓存线程池.
 * 容量不限(int的最大值).自动扩容:每次有新任务无法处理的时候,都会创建新的线程
 *
 * 默认线程空闲60s,自动销毁
 */
public class MyCacheThreadPool {
  public static void main(String[] args) {
      ExecutorService service = Executors.newCachedThreadPool();

      System.out.println(service);

    for (int i = 0; i < 5; i++){
      service.execute(new Runnable() {
          @Override
          public void run() {
              try {
                  TimeUnit.MILLISECONDS.sleep(3);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }

              System.out.println(Thread.currentThread().getName()+"- test executor");
          }

      });
    }
    System.out.println(service);
      try {
          TimeUnit.SECONDS.sleep(65);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      System.out.println(service);
  }
}
