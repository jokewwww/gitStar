package com.joker.container;

import java.util.LinkedList;
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

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
