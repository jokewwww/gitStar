package com.joker.thread;

/**
 * 常量问题
 * 在定义同步代码块时,不要使用常量对象作为锁对象
 */
public class Current10 {

    String s1="hello";
    String s2=new String("hello");
    Integer i1=1;
    Integer i2=1;

    void m1(){
        synchronized (s1){
            System.out.println("m1() execute");
            while (true){

            }
        }

    }

    void m2(){
        synchronized (s2){
            System.out.println("m2() execute");
            while (true){

            }
        }
    }

  public static void main(String[] args) {
    final Current10 c=new Current10();
    new Thread(new Runnable() {
        @Override
        public void run() {
            c.m1();
        }
    }).start();

    new Thread(new Runnable() {
        @Override
        public void run() {
            c.m2();
        }
    }).start();
  }
}
