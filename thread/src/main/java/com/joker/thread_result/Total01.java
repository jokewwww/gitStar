package com.joker.thread_result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Total01 {

  public static void main(String[] args) {
    final Total01_Container t=new Total01_Container();
    new Thread(
            new Runnable() {
              @Override
              public void run() {
                for (int i = 0; i < 10; i++) {
                  System.out.println("add Object to Container"+i);
                  t.add(new Object());
                  try{
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("容器内元素"+t.container.size());
                  }catch (Exception e){
                    e.printStackTrace();
                  }
                }
              }
            })
        .start();
  }
}


class Total01_Container{
  volatile List<Object> container=new ArrayList();

  public void add(Object o){
    container.add(o);
  }

  public int size(){
    return container.size();
  }
}