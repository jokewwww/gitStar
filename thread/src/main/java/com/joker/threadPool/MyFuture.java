package com.joker.threadPool;

import java.util.concurrent.*;

public class MyFuture {

  public static void main(String[] args) {
      ExecutorService service = Executors.newFixedThreadPool(1);
      Future<String> future = service.submit(new Callable<String>() {
          @Override
          public String call(){
              try {
                  TimeUnit.SECONDS.sleep(5);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.println("aaa");

              return Thread.currentThread().getName()+" - test executor";
          }

      });

      System.out.println(future);

      System.out.println(future.isDone());//查看call方法是否执行完毕

      try {
          System.out.println(future.get());//获取call方法的返回值
      } catch (InterruptedException e) {
          e.printStackTrace();
      } catch (ExecutionException e) {
          e.printStackTrace();
      }
      System.out.println(future.isDone());

  }
}
