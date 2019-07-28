package com.joker.containnerClass;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 并发容器之无界容器:DelayQueue
 * 延时队列,根据比较机制.实现自定义处理顺序的队列
 * 常用于定时任务.如:定时关机
 */
public class MyDelayQueue {
    static BlockingQueue<MyDelay> queue=new DelayQueue();

  public static void main(String[] args) throws InterruptedException {
    long value=System.currentTimeMillis();
     MyDelay task1=new MyDelay(value+2000);
     MyDelay task2=new MyDelay(value+4000);
     MyDelay task3=new MyDelay(value+5000);
     MyDelay task4=new MyDelay(value+3000);
     MyDelay task5=new MyDelay(value+6000);
     MyDelay task6=new MyDelay(value+1000);

     queue.put(task1);
     queue.put(task2);
     queue.put(task3);
     queue.put(task4);
     queue.put(task5);
     queue.put(task6);

     System.out.println(queue);
     System.out.println(value);

    for (int i = 0; i < 6; i++) {
      System.out.println(queue.take());
    }
  }
}

class MyDelay implements Delayed{

    private long compareValue;

    public MyDelay(long compareValue) {
        this.compareValue = compareValue;
    }

    /**
     *获取计划时常的方法]\
     * 根据参数TimeUnit来决定
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(compareValue-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    /**
     *比较大小,实现自动升序
     *建议和getDelay方法配合完成
     * 如果在DelayQueue是需要按时完成的计划任务,必须配合GetDEelay方法
     *
     */
    @Override
    public int compareTo(Delayed o) {
        return (int)compareValue;
    }
}
