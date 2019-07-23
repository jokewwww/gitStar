package com.joker.container;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者
 * 重入锁&条件
 * Condition,未Lock增加条件.当条件满足时,执行操作(加锁或解锁,等待或唤醒)
 */
public class Container02<E> {

    private final LinkedList<E> list=new LinkedList<>();
    private final int MAX=10;
    private int count=0;

    private Lock lock=new ReentrantLock();
    private Condition producer=lock.newCondition();
    private Condition consumer=lock.newCondition();

    public void put(E e){
        lock.lock();
        try{
            while (list.size()==MAX){
                System.out.println(Thread.currentThread().getName()+"等待....");
                //进入等待队列.释放锁标记
                //借助条件进入等待队列
                producer.await();
            }
            System.out.println(Thread.currentThread().getName()+"put ...");
            list.add(e);
            count++;
            consumer.signalAll();//借助条件唤醒所有消费者
        }catch (Exception ex){
            ex.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public E get(){
        E e=null;
        lock.lock();
        try{
            while (list.size()==0){
                System.out.println(Thread.currentThread().getName()+"等待...");
                consumer.await();
            }
            System.out.println(Thread.currentThread().getName()+"get...");
            e=list.removeFirst();
            count--;
            consumer.signalAll();


        }catch (Exception ec){
            ec.printStackTrace();
        }finally{
            lock.unlock();
        }
        return e;
    }

    public static void main(String[] args){
        final Container02<String> c=new Container02();
        for(int i = 0; i <10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0; j <5; j++) {
                        System.out.println("获取容器元素"+j);
                        System.out.println(c.get());
                    }
                }
            },"consumer"+i).start();
        }

        try{
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            e.printStackTrace();
        }

        for(int i = 0; i <2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0; j <25; j++) {
                        c.put("consumer value"+j);
                    }
                }
            },"producer"+i).start();

        }
    }
}
