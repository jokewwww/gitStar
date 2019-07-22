package com.joker.thread_result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 同步锁方法监听容器元素个数
 */
public class Total02 {

  public static void main(String[] args) {
    final Object lock=new Object();
    final Total02_Container t=new Total02_Container();

    new Thread(new Runnable() {
        @Override
        public void run() {
            synchronized (lock){
                if (t.size()!=5){
                    try{
                        lock.wait();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                System.out.println("size==5");
                lock.notifyAll();//唤醒其他等待线程
            }
        }
    }).start();

    new Thread(
            new Runnable() {
              @Override
              public void run() {
                synchronized (lock) {
                 for (int i = 0; i < 10; i++) {
                    System.out.println("add Object to container");
                    t.add(new Object());

                    if(t.size()==5){
                        System.out.println("监听到容器有5个元素");
                        lock.notifyAll();//唤醒监听线程
                        try{
                            lock.wait();
                        }catch (Exception e ){
                            e.printStackTrace();
                        }
                    }
                    try{
                        TimeUnit.SECONDS.sleep(1);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                  }
                }
              }
            })
        .start();
  }
}

class Total02_Container{

    List<Object> container=new ArrayList();
    public void add(Object o){
        container.add(o);
    }

    public int size(){
        return container.size();
    }
}