package com.joker.container;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 多线程模拟生产者和消费者
 */
public class Container01<E> {
    private final LinkedList<E> list=new LinkedList();
    private final int MAX=10;
    private int count=0;

    public synchronized int getCount(){
        return count;
    }

    public synchronized void put(E e){
        while(list.size()==MAX){
            try{
                wait();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        list.add(e);
        count++;
        notifyAll();
    }

    public synchronized E get(){
        E e=null;
        while (list.size()==0){
            try{
                wait();
            }catch (Exception es){
                es.printStackTrace();
            }
        }
        e=list.removeFirst();
        count--;
        notifyAll();
        return e;
    }

  public static void main(String[] args) {
    final Container01<String> c=new Container01();
    for (int i = 0; i < 10; i++) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int j = 0; j < 5; j++) {
                    System.out.println("取出容器元素"+c.get());
                }
            }
        },"consumer"+i).start();
    }

    try{
        TimeUnit.SECONDS.sleep(2);
    }catch (Exception e){
        e.printStackTrace();
    }

    for (int i = 0; i <3 ; i++) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int j = 0; j <25 ; j++) {
                    c.put("container value"+j);
                    System.out.println("生产容器元素"+j);
                }
            }
        },"producer"+i).start();
    }
  }
}
