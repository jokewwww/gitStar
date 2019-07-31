package com.joker.threadPool;

import java.util.concurrent.*;

/**
 * ScheduledThreadPool
 * 计划任务线程池,可以根据计划自动执行任务的线程池
 *
 */
public class MyScheduledThreadPool {

  public static void main(String[] args) {
    ScheduledExecutorService service = Executors.newScheduledThreadPool(3);
    //定时完成任务:scheduleAtFixedRate(Runnable,start_limit,limit,TimeUnit)
    //Runnable-要执行的任务
    service.scheduleAtFixedRate(new Runnable() {
        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    },0,300,TimeUnit.MILLISECONDS);
  }
}
