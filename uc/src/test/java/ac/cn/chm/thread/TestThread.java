package ac.cn.chm.thread;

public class TestThread {
    public static void main(String[] args) {
        runnable();
    }

    public static void thread(){
//        for (int i = 0; i < 10; i++) {
//
//            MyThread mt = new MyThread();
//            mt.start();
//        }
            System.out.println("主线程id："+Thread.currentThread().getId());
            MyThread mt = new MyThread("thread1");
            mt.start();
            MyThread mt1 = new MyThread("thread2");
            mt1.run();
    }
    public static void runnable(){
        System.out.println("主线程id："+Thread.currentThread().getId());
        MyRunnable mr = new MyRunnable();
        Thread t = new Thread(mr);
        t.start();
    }
}

class MyRunnable implements  Runnable{

    public MyRunnable(){

    }
    @Override
    public void run() {

        System.out.println("子线程id："+Thread.currentThread().getId());
    }
}