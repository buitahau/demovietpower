package hau.kute.others.thread;

/**
 * Created by HauKute on 9/30/2018.
 */
public class RunnableDemo implements Runnable {
    @Override
    public void run() {
        System.out.println("----->RunnableDemo started with id " + Thread.currentThread().getId());
        for(int i = 0; i < 1000; i ++){
            System.out.println("Hello from RunnableDemo " + i);
            try{
                Thread.sleep(1500);
            }catch (Exception e){

            }
            if(i > 15){
//                throw new Exception("aaaaa");
                long t = i /0;
//                throw new RuntimeException("Problem ......");
            }
        }
        System.out.println("----->RunnableDemo stopped.");
    }
}
