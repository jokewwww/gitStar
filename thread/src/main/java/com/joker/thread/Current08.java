package com.joker.thread;

/**
 * 同步粒度问题
 *尽量在商业开发中避免同步方法,使用同步代码块
 * 细粒度解决问题,提高效率
 */
public class Current08 {

    //效率低下,不建议使用
    synchronized void m1(){
        //前置逻辑
        System.out.println("同步逻辑");
        //后置逻辑
    }

    //推荐使用
    void m2(){
        //前置逻辑
        synchronized (this){
            System.out.println("同步逻辑");
        }
        //后置逻辑
    }
}
