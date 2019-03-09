package ac.cn.chm.thread;

public class MyThread extends Thread {

    private static int num = 0;
    private String name;
    public MyThread(){
        num++;
    }
    public MyThread(String name){
        this.name = name;
    }

    public void run(){
        System.out.println("需要重写run方法，主动创建的第"+num+"个线程,名字是"+name+"，子线程id："+Thread.currentThread().getId());
    }
}
