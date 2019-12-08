package edison.io;

import edison.io.metric.MetricApplication;
import edison.io.metric.autoconfig.ThreadPoolRegistrar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MetricApplication.class})
public class ThreadPoolBuilderTest {

    @Autowired
    @Qualifier("httpThreadPool")
    ExecutorService executorService;

    @Test
    public void threadPoolBuilder() {

        CompletableFuture.runAsync(() -> System.out.println("fork success"), executorService);
        CompletableFuture.runAsync(() -> System.out.println("fork success"), executorService);
        CompletableFuture.runAsync(() -> System.out.println("fork success"), executorService);

        ThreadPoolExecutor threadPoolExecutor = ThreadPoolRegistrar.getInstance("httpPool");
        System.out.println(threadPoolExecutor.getPoolSize());
        System.out.println(threadPoolExecutor.getActiveCount());
        System.out.println(threadPoolExecutor.getCorePoolSize());
        System.out.println(threadPoolExecutor.getMaximumPoolSize());
        System.out.println(threadPoolExecutor.getTaskCount());
    }
}
