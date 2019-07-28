package com.joker.containnerClass;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 并发容器:-ArrayBlockingQueue
 * add():容量不足的时候抛出异常
 * put():在容量不足的时候,阻塞队列
 * offer()
 *    单参数方法:不阻塞,容量不足的时候返回false,放弃当前新增数据
 *    三参数方法:offer(value,times,timeunit),容量不足的时候阻塞times时常,如果在阻塞时常内有容量空闲,新增数据返回true,
 *       如果阻塞时常内无容量空闲,放弃新增数据,返回false.
 */
public class MyArrayBlockingQueue {
    
    final BlockingQueue queue=new ArrayBlockingQueue(3);

  public static void main(String[] args) {

      final MyArrayBlockingQueue t=new MyArrayBlockingQueue();
    for (int i = 0; i <5; i++) {
        //add方法
        //System.out.println("add method : "+t.queue.add("value"+i));
        //put方法
        /*try{
            t.queue.put("put"+i);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("put emthod : "+i);*/
        //offer单参数方法
        //System.out.println("offer method : "+t.queue.offer("offer"+i));

        //offer三参数方法
        try {
            System.out.println("offer method : "+t.queue.offer("value"+i,1, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //
    }
      System.out.println(t.queue);
  }
}
