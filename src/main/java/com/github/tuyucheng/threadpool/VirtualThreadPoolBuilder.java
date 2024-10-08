package com.github.tuyucheng.threadpool;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;

import javax.annotation.Nullable;
import java.util.List;

public final class VirtualThreadPoolBuilder extends AbstractThreadPoolBuilder<VirtualThreadPoolBuilder> {

   VirtualThreadPoolBuilder() {
   }

   @Override
   ThreadPool build(TaskSubmissionHandler submissionHandler, TaskExceptionHandler exceptionHandler,
                    long taskTimeoutNanos, long watchdogIntervalNanos, MeterRegistry meterRegistry,
                    @Nullable String metricPrefix, List<Tag> metricTags) {

      return new VirtualThreadPool(submissionHandler, exceptionHandler,
            taskTimeoutNanos, watchdogIntervalNanos,
            meterRegistry, metricPrefix, metricTags);
   }
}