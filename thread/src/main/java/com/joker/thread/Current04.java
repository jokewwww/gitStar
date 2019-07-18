package com.joker.thread;

import java.util.concurrent.TimeUnit;

public class Current04 {
    int i=0;
    synchronized void m(){
        System.out.println(Thread.currentThread().getName()+"-start");
        while (true){
            i++;
            System.out.println(Thread.currentThread().getName()+"-"+i);
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){
                e.printStackTrace();
            }
            if(i==5){
                i=1/0;
            }
        }


    }

  public static void main(String[] args) {
    final Current04 t=new Current04();

    new Thread(new Runnable() {
        public void run() {
            t.m();
        }
    });
  }
}
