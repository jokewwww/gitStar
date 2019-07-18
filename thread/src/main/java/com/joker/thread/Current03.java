package com.joker.thread;

import java.util.concurrent.TimeUnit;

/**
 * 子类同步方法覆盖父类同步方法,可以指定调用父类得同步方法.相当于锁的重入
 */
public class Current03 {

    synchronized void m(){
        System.out.println("Super Class m() start");
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Super Class m() end");
    }

  public static void main(String[] args) {
    new Sub_Current().m();
  }
}
class Sub_Current extends Current03{
    @Override
    synchronized void m(){
        System.out.println("Sub Class m() start");
        super.m();
        System.out.println("Sub Class m() end");
    }
}
