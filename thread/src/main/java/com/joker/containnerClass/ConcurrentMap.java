package com.joker.containnerClass;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * 并发容器
 * Hashtable和ConcurrentSkipListMap都是线程安全的容器
 * 但是ConcurrentSkipListMap比HashTable效率高得多\
 *
 * ConcurrentHashMap底层是哈希实现的同步Map(Set).效率高,线程安全,轻量级
 *
 * ConcurrentSkipListMap这个效率是最慢的,但是内部是排序的
 */
public class ConcurrentMap {

  public static void main(String[] args) {
    //final Map<String,String> map=new Hashtable<>();
    final Map<String,String> map=new ConcurrentHashMap();
    //final Map<String,String> map=new ConcurrentSkipListMap();

    final Random random=new Random();

    Thread []array=new Thread[100];

    final CountDownLatch latch=new CountDownLatch(array.length);

    long begin=System.currentTimeMillis();
    for (int i = 0; i < array.length; i++) {
        array[i]= new Thread(new Runnable() {
          @Override
          public void run() {
            for(int j = 0; j <10000 ; j++) {
               map.put("key"+random.nextInt(100000),"value"+random.nextInt(100000));
            }
            latch.countDown();//门闩-1
          }
        });
    }
    for (Thread t :array ) {
        t.start();
    }
    try{
      latch.await();//等待门闩开放
    }catch(Exception e){
      e.printStackTrace();
    }
    long end=System.currentTimeMillis();
    System.out.println("执行时间为:"+(end-begin)+"毫秒!");
  }
}
