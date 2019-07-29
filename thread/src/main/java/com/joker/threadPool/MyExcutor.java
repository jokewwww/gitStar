package com.joker.threadPool;

import java.util.concurrent.Executor;

/**
 * 线程池
 * Executor-线程池底层处理机制
 * 在使用`线程池的时候,底层如何调用线程中的逻辑
 */
public class MyExcutor implements Executor {
  public static void main(String[] args) {
    new MyExcutor().execute(new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"- test executor");
        }
    });
  }

    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
