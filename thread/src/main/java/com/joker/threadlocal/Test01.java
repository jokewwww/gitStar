package com.joker.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal
 * 就是一个map.key->Thread.getCurrentThread().value->线程需要保存的变量
 * ThreadLocal.set(value)->map.put(Thread.getCurrentThread(),value)
 * ThreadLocal.get()->map.get(Thread.getCurrentThread());
 * 内存问题:在并发量高的时候,可能有内存溢出
 * 使用ThreadLocal时,一定要注意回收资源,每个线程结束之前,将当前线程保存的线程变量一定要删除
 * ThreadLocal.remove();
 */
public class Test01 {
    static ThreadLocal<String> tl=new ThreadLocal<>();
    volatile static String name="zhangsan";

  public static void main(String[] args) {
    new Thread(new Runnable() {
        @Override
        public void run() {
            try{
                TimeUnit.SECONDS.sleep(2);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(name);
            System.out.println(tl.get());
        }
    }).start();

    new Thread(new Runnable() {
        @Override
        public void run() {
            try{}catch(Exception e){
                e.printStackTrace();
            }
            name="liss";
            tl.set("wangwu");
        }
    }).start();
  }
}
