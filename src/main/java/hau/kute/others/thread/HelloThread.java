package hau.kute.others.thread;

/**
 * Created by HauKute on 9/30/2018.
 */
public class HelloThread extends Thread {
    @Override
    public void run() {
        System.out.println("----->Hello thread started with id " + Thread.currentThread().getId());
        int index = 1;
        for(int i = 0; i < 5; i ++){
            System.out.println("--Hello thread running" + index++);
            try{
                Thread.sleep(2000);
            }catch (Exception e){

            }
        }
        System.out.println("=====>Hello thread stopped.");
    }
}
