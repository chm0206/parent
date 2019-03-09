package ac.cn.chm.test_lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriteLock {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final TestReadWriteLock test = new TestReadWriteLock();

        new Thread() {
            public void run() {
                test.get(Thread.currentThread());
            }

            ;
        }.start();

        new Thread() {
            public void run() {
                test.get(Thread.currentThread());
            }

            ;
        }.start();
    }

    public synchronized void get(Thread thread) {
        long start = System.currentTimeMillis();
        System.out.println(start);
        while (System.currentTimeMillis() - start <= 1) {
            System.out.println(thread.getName() + "正在进行读操作");
        }
        System.out.println(thread.getName() + "读操作完毕");
    }
}
