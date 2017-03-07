package cn.songm.acc.service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConcurrentTest {

    static String on = "13412987a5e044d984789c03b7a31bcb";

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "application-acc.xml");
        Tab01Service tService = (Tab01Service) context.getBean("tab01Service");

        // for (int i = 0; i < 100; i++) {
        // Thread t = new Thread(new CountThread(tService));
        // t.start();
        // }
        
        int n = 100;
        CountDownLatch countDown = new CountDownLatch(n);
        ExecutorService exe = Executors.newFixedThreadPool(10);
        for (int i = 0; i < n; i++) {
            exe.execute(new CountThread(tService, countDown));
        }

        countDown.await();
        context.close();
    }

    public static class CountThread implements Runnable {

        private Tab01Service tService;
        private CountDownLatch countDown;

        public CountThread(Tab01Service ts, CountDownLatch countDown) {
            this.tService = ts;
            this.countDown = countDown;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.tService.updateCount(on, 1);
            countDown.countDown();
        }

    }
}
