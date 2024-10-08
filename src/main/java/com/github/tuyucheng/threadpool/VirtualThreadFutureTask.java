package com.github.tuyucheng.threadpool;

import javax.annotation.Nullable;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

final class VirtualThreadFutureTask<V> extends ManagedVirtualThread<V> {

   VirtualThreadFutureTask(VirtualThreadPool parent, Runnable task, @Nullable V result) {
      this(parent, Executors.callable(task, result));
   }

   VirtualThreadFutureTask(VirtualThreadPool parent, Callable<V> task) {
      super(parent, task);
   }

   @Nullable
   @Override
   V call() throws Exception {
      if (setProcessed()) {
         return super.call();
      } else {
         return null;
      }
   }
}