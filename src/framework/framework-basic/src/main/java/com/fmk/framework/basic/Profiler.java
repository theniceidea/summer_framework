package com.fmk.framework.basic;

import java.util.concurrent.TimeUnit;

/**
 * Created by larry on 2018/7/5.
 * @author suqun
 */
public class Profiler {
  private static final ThreadLocal<Long> TIME_THREADLOCAL = ThreadLocal.withInitial(() -> System.currentTimeMillis());

  public static final void begin(){
    TIME_THREADLOCAL.set(System.currentTimeMillis());
  }

  public static final Long end(){
    return System.currentTimeMillis() - TIME_THREADLOCAL.get();
  }

//  public static void main(String[] args) throws InterruptedException {
//    Profiler.begin();
//    TimeUnit.SECONDS.sleep(1);
//    System.out.println("Cost:" + Profiler.end() + "mills");
//  }
}
