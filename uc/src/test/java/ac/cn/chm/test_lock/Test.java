package ac.cn.chm.test_lock;

import java.util.concurrent.locks.ReentrantLock;

class MyService {

    private ReentrantLock lock = new ReentrantLock();

    public void method() {
        try {
            lock.lock();
            //lock,tryLock,lockInterruptibly都是用来获取锁的,无法使用Thread.isInterrupted中断线程
            //lock，如果锁已被其它的线程获取，则进行等待，必须使用try{}catch{}finally{}进行锁的释放，否则易引起死锁
            //tryLock有返回值，一般用于判断，若未能获取到锁，则进行其它的操作（可按具体的业务进行修改）
            //tryLock还有一个带参方法，当使用该方法时，在一定时间内会等待锁，若时间到了未获取到锁，则返回false
            //lockInterruptibly与lock类似，但优先考虑中断线程，可以使用Thread.isInterrupted中断线程
            for (int i = 1; i <= 3; i++) {
                Thread.sleep(1000);
                System.out.println("ThreadName=" + Thread.currentThread().getName() + "  " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();//释放锁
        }
    }
}

class MyThread extends Thread {

    private MyService service;

    MyThread(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.method();
    }
}

public class Test {

    public static void main(String[] args) {
        MyService service = new MyService();
        MyThread myThread1 = new MyThread(service);
        MyThread myThread2 = new MyThread(service);

        myThread1.start();
        myThread2.start();
    }
}