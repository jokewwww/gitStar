package com.joker.containnerClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * Arraylist线程非同步效率高
 * Vector线程安全效率低
 */
public class CopyOnWriteList {
  public static void main(String[] args) {

      //final List<String> list=new ArrayList<>();
      //final List<String> list = new Vector<>();
      final List<String> list=new CopyOnWriteArrayList<>();

      final Random random=new Random();

      Thread []array=new Thread[100];
      final CountDownLatch latch=new CountDownLatch(array.length);

      long begin=System.currentTimeMillis();
    for (int i = 0; i < array.length; i++) {
      array[i]=new Thread(new Runnable() {
          @Override
          public void run() {
              for(int j = 0; j <1000; j++) {
                  list.add("value"+random.nextInt(100000));
              }
              latch.countDown();
          }
      });
    }
    for (Thread t :array ) {
        t.start();
    }

    try{
        latch.await();
    }catch(Exception e){
        e.printStackTrace();
    }

    long end=System.currentTimeMillis();
    System.out.println("执行时间为:"+(end-begin)+"毫秒!");
    System.out.println("List.size()"+list.size());
  }
}
