package com.joker.threadPool;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * 固定容量线程池
 * FixedThreadPool -固定容量线程池.创建线程池的时候,容量固定.
 * 构造的时候,提供线程池最大容量
 * ExecutorServcice-线程池服务类型.所有的线程池类型都实现这个接口.
 * 实现这个接口,代表可以提供线程池能力
 * shutdown 优雅关闭,不是强行关闭线程池回收线程池的资源,而是不再处理新的任务,将已接受的任务处理完毕再关闭
 * Executors - Executor的工具类.类似Collection和Collections的关系
 * 可以更简洁的创建若干线程池
 */
public class FixedThreadPool {

  public static void main(String[] args) {
      ExecutorService service = java.util.concurrent.Executors.newFixedThreadPool(5);
    for (int i = 0; i < 6; i++) {
      service.execute(new Runnable() {
          @Override
          public void run() {
              try {
                  TimeUnit.SECONDS.sleep(500);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }

              System.out.println(Thread.currentThread().getName()+"- test executor");
          }
      });
    }
    System.out.println(service);

    service.shutdown();
    //是否已经结束,相当于回收了资源
      System.out.println(service.isTerminated());
      //是否已经关闭,是否调用过shuadown方法
      System.out.println(service.isShutdown());
      System.out.println(service);

      try {
          TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }

      System.out.println(service.isTerminated());
      System.out.println(service.isShutdown());
      System.out.println(service);

  }
}
