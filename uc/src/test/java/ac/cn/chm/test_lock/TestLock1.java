package ac.cn.chm.test_lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock1 {

    private ArrayList<Integer> arrayList = new ArrayList<>();
    Lock lock = new ReentrantLock();//注意这个地方？

    public static void main(String[] args) {
//        final TestLock1 tl = new TestLock1();
//
//        for (int i = 0; i < 10; i++) {
//            new Thread() {
//                public void run() {
//                    try {
//                        tl.insert(Thread.currentThread());
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }.start();
//        }

        TestLock1 tt = new TestLock1();
        MyThread1 thread1 = new MyThread1(tt);
        MyThread1 thread2 = new MyThread1(tt);
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }
    public void insert(Thread thread) throws InterruptedException{
        lock.lockInterruptibly();   //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        try {
            System.out.println(thread.getName()+"得到了锁");
            long startTime = System.currentTimeMillis();
            for(    ;     ;) {
                if(System.currentTimeMillis() - startTime >= Integer.MAX_VALUE){
                    System.out.println(111);
                    break;
                    //插入数据
                }
            }
        }
        finally {
            System.out.println(Thread.currentThread().getName()+"执行finally");
            lock.unlock();
            System.out.println(thread.getName()+"释放了锁");
        }
    }
    public void insert2(Thread t){
        if(lock.tryLock()){
            try {
                System.out.println(t.getName() + "得到了锁");
                for (int i = 0; i < 5; i++) {
                    arrayList.add(i);
                }
            }catch (Exception e) {

            }finally {
                System.out.println(t.getName()+"释放了锁");
                lock.unlock();
            }
        }else{

            System.out.println(t.getName()+"获取锁失败");
        }
    }

    public void insert1(Thread t){
        lock.lock();//获取锁
        try {
            System.out.println(t.getName() + "得到了锁");
            for (int i = 0; i < 5; i++) {
                arrayList.add(i);
            }
        }catch (Exception e) {

        }finally {
            System.out.println(t.getName()+"释放了锁");
            lock.unlock();
        }
    }
}
class MyThread1 extends Thread {
    private TestLock1 test = null;
    public MyThread1(TestLock1 test) {
        this.test = test;
    }
    @Override
    public void run() {

        try {
            test.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"被中断");
        }
    }
}
