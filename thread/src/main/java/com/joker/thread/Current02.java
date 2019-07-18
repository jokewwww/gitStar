package com.joker.thread;

import java.util.concurrent.TimeUnit;

public class Current02 {

    synchronized void m1(){
        System.out.println("m1 start");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1 end");
    }

    synchronized void m2()  {
        System.out.println("m2 start");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("m2 end");
    }

  public static void main(String[] args) {
    new Current02().m1();
  }
}
