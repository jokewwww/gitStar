package com.joker.thread;

import java.util.concurrent.TimeUnit;

/**
 * volatile关键字
 * volatile的可见性:通知OS底层操作系统,在CPU计算过程中,都要检查内存中那个数据的有效性,保证最新的内存数据被使用.
 */
public class Current05 {

    volatile Boolean b=true;

    void m(){
        System.out.println("start");
        while(b){

        }
            System.out.println("end");
    }

  public static void main(String[] args) {
    final Current05 c=new Current05();

    new Thread(new Runnable() {
        public void run() {
            c.m();
        }
    }).start();

    try{
        TimeUnit.SECONDS.sleep(1);
    }catch (Exception e){
        e.printStackTrace();
    }
    c.b=false;
  }
}
