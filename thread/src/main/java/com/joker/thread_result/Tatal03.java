package com.joker.thread_result;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 门闩fan方法监听容器元素个数
 */
public class Tatal03 {

  public static void main(String[] args) {
    final Total03_Container t=new Total03_Container();

    final CountDownLatch latch=new CountDownLatch(1);

    new Thread(new Runnable() {
        @Override
        public void run() {
            if(t.size()!=5){
                try{
                    latch.await();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }).start();

    new Thread(
            new Runnable() {
              @Override
              public void run() {
               for (int i = 0; i < 10; i++) {
                  System.out.println("add Object to Container"+i);
                  t.add(new Object());

                  if(t.size()==5){
                      latch.countDown();//门闩-1
                  }
                  try{
                      TimeUnit.SECONDS.sleep(1);
                  }catch (Exception e){
                      e.printStackTrace();
                  }
                }
              }
            })
        .start();
  }
}

class Total03_Container{
    List<Object> container=new ArrayList<>();

    public void add(Object o){
        container.add(o);
    }

    public int size(){
        return container.size();
    }
}
