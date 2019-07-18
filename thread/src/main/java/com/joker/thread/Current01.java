package com.joker.thread;

public class Current01 {

    Object o=new Object();

    public synchronized void m1(){
        System.out.println("public synchronized void m1() start");
        try{
            Thread.sleep(3000);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("public synchronized void m1() end");
    }

    public void m2(){
        System.out.println("public synchronized void m2() start");
        try{
            Thread.sleep(1500);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("public synchronized void m2() end");
    }

    public void m3(){
        synchronized (o){
            System.out.println("public void m3() start");
            try{
                Thread.sleep(1500);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("public  void m3() end");
        }

    }

    public static class MyThread implements Runnable{

        int i;
        Current01 t;
        public MyThread(int i,Current01 t){
            this.i=i;
            this.t=t;
        }

        public void run() {
            if(i==0){
                t.m1();
            }else if (i>0){
                t.m2();
            }else{
                t.m3();
            }

        }
    }

    public static void main(String[] args){
        Current01 c=new Current01();
        new Thread(new MyThread(-1,c)).start();
        new Thread(new MyThread(0,c)).start();
        new Thread(new MyThread(1,c)).start();
    }

}
