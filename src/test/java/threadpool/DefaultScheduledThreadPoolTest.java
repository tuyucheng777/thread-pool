package threadpool;

import com.github.tuyucheng.threadpool.ThreadPool;
import io.micrometer.core.instrument.Tag;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

class DefaultScheduledThreadPoolTest {

    private static final Logger logger = LoggerFactory.getLogger(DefaultScheduledThreadPoolTest.class);

    @Test
    void scheduledTasks() throws Exception {
        final PrometheusMeterRegistry meterRegistry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
        final ThreadPool executor = ThreadPool.builder(3)
                .metrics(meterRegistry, "mytest.threadpool", Tag.of("foo", "bar"))
                .build();
        final CountDownLatch latch = new CountDownLatch(1);
//        final CompletableFuture<String> f = executor.scheduleAtFixedRate(() -> {
//            logger.info("Task is run. {}", System.currentTimeMillis());
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }, 3, 1, TimeUnit.SECONDS).handle((value, cause) -> "Cancelled!");
//        Thread.sleep(4300);
//        logger.info("\n{}", meterRegistry.scrape());
//        Thread.sleep(5700);
//        executor.shutdown();
//        logger.info(f.get());
        executor.execute(() -> System.err.println("EXECUTED!"));
        executor.shutdown();
        executor.awaitTermination();
        logger.info("\n{}", meterRegistry.scrape());
    }
}
