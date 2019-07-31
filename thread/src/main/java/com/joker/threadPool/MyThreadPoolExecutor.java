package com.joker.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolExecutor {
  public static void main(String[] args) {
      ExecutorService service=new ThreadPoolExecutor(5,5,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());

    for (int i = 0; i < 67; i++) {
      service.execute(new Runnable() {
          @Override
          public void run() {
              try {
                  TimeUnit.MILLISECONDS.sleep(500);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.println(Thread.currentThread().getName()+"- test esecutor");
          }
      });
    }

    System.out.println(service);

    service.shutdown();
    System.out.println(service.isTerminated());
    System.out.println(service.isShutdown());
    System.out.println(service);

      try {
          TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }

      service.shutdown();
      System.out.println(service.isTerminated());
      System.out.println(service.isShutdown());
      System.out.println(service);
  }
}
