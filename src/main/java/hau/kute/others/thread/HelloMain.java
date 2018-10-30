package hau.kute.others.thread;

import java.util.*;

/**
 * Created by HauKute on 9/30/2018.
 */
public class HelloMain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("----------> Main thread started with id " + Thread.currentThread().getId());

        HelloThread helloThread = new HelloThread();
        helloThread.start();

        Thread thread = new Thread(new RunnableDemo());
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("#Thread: " + t);
                System.out.println("#Thread exception message: " + e.getMessage());
            }
        });
        thread.start();
        helloThread.join();
        int idx = 1;
        for(int i = 0; i < 10; i ++){
            System.out.println("Main thread running " + idx++);
            Thread.sleep(1000);
        }

        List l = new ArrayList<>();
        l.add(1);
        l.add(1);
        l.add(2);
        Set s = new HashSet<>();
        s.add(1);
        s.add(1);
        s.add(2);

        System.out.println("Stooped  ");
    }
}
