package com.joker.threadPool;


import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class MyForkJoinPool {
  static final int[] numbers = new int[10000];
  static final int MAX_SIZE = 500000;
  static final Random random = new Random();

  static {
    for (int i = 0; i < numbers.length; i++) {
      numbers[i] = random.nextInt(1000);
    }
  }

  static class AddTask extends RecursiveTask<Long> {

    int begin, end;

    public AddTask(int begin, int end) {
      this.begin = begin;
      this.end = end;
    }

    @Override
    protected Long compute() {
      if ((end - begin) < MAX_SIZE) {
        long sum = 0;
        for (int i = begin; i < end; i++) {
            sum+=numbers[i];
        }
        System.out.println("form "+begin+"to "+end+",sum is"+sum);
        return sum;
      } else{
          int middle=begin+(end-begin/2);
          AddTask addTask1 = new AddTask(begin, middle);
          AddTask addTask2 = new AddTask(middle,end);

          addTask1.fork();//开启一个新的线程任务
          addTask2.fork();
          return addTask1.join()+addTask2.join();

      }


    }
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
      int result=0;
    for (int i = 0; i < numbers.length; i++) {
        result=numbers[i];
    }
    System.out.println(result);

      ForkJoinPool pool = new ForkJoinPool();

      AddTask task = new AddTask(0, numbers.length);
      Future<Long> submit = pool.submit(task);

      System.out.println(submit.get());
  }
}
