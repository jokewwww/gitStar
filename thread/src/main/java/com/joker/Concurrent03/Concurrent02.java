package com.joker.Concurrent03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 尝试锁
 */
public class Concurrent02 {
  Lock lock = new ReentrantLock();

  void m1() {
    try {
      lock.lock(); // 加锁
      for (int i = 0; i < 10; i++) {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("m1 () method"+i);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }finally{
        //尝试锁在接触锁标记的时候,一定要判断是否获取到所标记
        //如果当前吸纳成没有获取到锁标记,会抛出异常
        lock.unlock();
    }
  }

  void m2(){
      boolean isLocked=false;
      try{
          //尝试锁:如果有所,无法获取锁标记,返回false
          //如果获取所标记,返回true
          isLocked=lock.tryLock();//非阻塞

          //阻塞尝试锁,阻塞参数代表的时长,尝试获取锁标记
          //如果超时,不等待,直接返回

          //isLocked=lock.tryLock(5,TimeUnit.SECONDS);

          if(isLocked){
              System.out.println("m2() method synchronized");
          }else{
              System.out.println("m2() method unsynchronized");
          }
      }catch(Exception e){
          e.printStackTrace();
      }finally{
          if (isLocked){
              lock.unlock();
          }
      }
  }

  public static void main(String[] args) {
      final Concurrent02 t=new Concurrent02();
      new Thread(new Runnable() {
          @Override
          public void run() {
              t.m1();
          }
      }).start();

      try{
          TimeUnit.SECONDS.sleep(1);
      }catch(Exception e){
          e.printStackTrace();
      }

      new Thread(new Runnable() {
          @Override
          public void run() {
              t.m2();
          }
      }).start();
  }
}
