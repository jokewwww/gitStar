package com.joker.threadPool;

import java.util.concurrent.*;


/**\
 * SingleThreadExecutor
 * 单一容量线程池:容量上限永远为1,执行完一个线程返回来执行第二个线程
 * 保证任务顺序
 */
public class MySingleThreadPool {

  public static void main(String[] args) {
      ExecutorService service = Executors.newSingleThreadExecutor();
      System.out.println(service);

    for (int i = 0; i < 5; i++){
      service.execute(new Runnable() {
          @Override
          public void run() {
              try {
                  TimeUnit.MILLISECONDS.sleep(500);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.println(Thread.currentThread().getName()+"-test executor");
          }
      });
    }
  }
}
