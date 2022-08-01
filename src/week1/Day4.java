package week1;


import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Day4 {
}
/**
 * volatile + cas compareAndSet
 *      volatile int i +1
 *      t1  t2          t1    t2
 *      0   0     expect 0    0
 *                       1
 *                          e  1
 *                             2
 *   compareAndSet(object, offset, v, v+1)
 */
class Test14{

    static AtomicInteger ai;
    public static void main(String[] args) throws Exception{

        ai = new AtomicInteger(0);
        Thread t1 = new Thread(()->{
            for(int i=0;i<10000;i++) {
                ai.getAndIncrement();
            }
        });
        Thread t2 = new Thread(()->{
            for(int i=0;i<10000;i++) {
                ai.getAndIncrement();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(ai.get());

    }

}
/**
 * Threadsafe colleciton: atomicInteger hashtable concurrenthashmap
 */
/**
 * synchronized
 *      slower performance
 *      no try lock
 *      no fair
 *      one waiting list
 *
 *     blocking
 *     producer offer() ->  [][x][x][x][x] -> consumer poll()
 *      100                                  100
 *    producerList                            consumerList
 */
class TestSycn {
    public static void main(String[] args) throws Exception{
        synchronized (TestSycn.class) {
            System.out.println("here");
        }
        //....
        System.out.println("other work");
        Thread producer = new Thread(()->{
            synchronized (TestSycn.class){
                try{
                    Thread.currentThread().wait();
                }catch(Exception ex){

                }

            }
        });
        Thread consumer = new Thread(()->{
            synchronized (TestSycn.class){
                try{
                    Thread.currentThread().wait();
                }catch(Exception ex){

                }

            }
        });
        TestSycn.class.notifyAll();



    }
}
/**
 * ReentrantLock
 *      lock()
 *      lock()
 *      unlock()
 *      unlock()
 *      try lock
 *      fair
 *      multiple waiting list
 */
class MyBlockingQueue {
    Queue<Integer> queue = new LinkedList<>();
    ReentrantLock lock = new ReentrantLock(true);
    Condition producerList = lock.newCondition();
    Condition consumerList = lock.newCondition();
    int capacity =16;

    void offer(int ele) throws Exception{
        lock.lock();
        while(capacity==queue.size()){
            producerList.await();
        }
        queue.offer(ele);
        lock.unlock();
        consumerList.signalAll();
    }

    int poll() throws Exception {
        lock.lock();
        while(queue.isEmpty()){
            consumerList.await();
        }
        int ele = queue.poll();
        lock.unlock();
        producerList.signalAll();
        return ele;
    }
}

/**
 *  DeadLock
 *      Thread1    monitor 1 -> monitor 2
 *      t2         monitor 2 -> monitor 1
 *      Solution:
 *          1. release the resource after some time
 *          2. lock in order
 *
 */

class TestDeadLock {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        Thread t1 = new Thread (()->{
            synchronized (o1){
                try{
                    Thread.currentThread().sleep(3000);
                }catch (Exception ex){}

                synchronized (o2){
                    System.out.println("in the thread1");
                }
            }
        });
        Thread t2 = new Thread (()->{
            synchronized (o1){
                try{
                    Thread.currentThread().sleep(3000);
                }catch (Exception ex){}
                synchronized (o2){
                    System.out.println("in the thread2");
                }
            }
        });
        t1.start();
        t2.start();


    }
}

/**
 * ThreadPool
 *      reuse thread
 *      [t6][t5][t4][t3][t2][t1] worker thread
 *   Executor, ExecutorService
 *
 *   Executors
 *   3 core thread   3 extra worker thread
 *   ThreadPoolExecutor(int corePoolSize,
 *                               int maximumPoolSize,
 *                               long keepAliveTime,
 *                               TimeUnit unit,
 *                               BlockingQueue<Runnable> workQueue)
 *    Diff type of thread pool
 *          FixedThreadPool  core pool size = max
 *          CachedThreadPool core < max submit the task
 *
 *          ScheduledThreadPool    delay, fix rate
 *
 *          ForkJoinPool        submit
 *              [1,2,3...10]->[1,2,3] t1  6 -> sum
 *                          ->[4,5,6] t2 15
 *                          ->[7,8,9] t3 24
 *                          ->[10]    t4  10
 *          parellel stream
 *          stream
 */
class TestThreadPool {
    public static void main(String[] args) throws Exception{
//        Executor ex;
//        ExecutorService es;
//        ex = Executors.newFixedThreadPool(3);
//        ex.execute(()->{
//            System.out.println("i am a task");
//        });
//
//        es = Executors.newCachedThreadPool();
//        Future<Integer> future = es.submit(()->{
//            Thread.currentThread().sleep(3000);
//            return 1;
//        });
        ScheduledExecutorService es2 = Executors.newScheduledThreadPool(3);
        es2.schedule(()->{
            System.out.println("shcheduled task");
        },3000,TimeUnit.MILLISECONDS);

//        Thread t1 = new Thread();
//        t1.start();
//        //blocking you thread invoke the join() wait for target thread t1
//        t1.join();
        //block
//        System.out.println("i am waiting");
//        //Completable future
//        int x=future.get();
        ///otherlogic

    }
}