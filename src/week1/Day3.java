package week1;

import jdk.swing.interop.SwingInterOpUtils;

public class Day3 {
}
/**
 * JVM Memory model
 *      heap + stack
 *      thread1  stack ([main][fib(10)][fib(9)][fib(8)]....[fib(1)])  last in first out
 *      t2       stack
 *      t3       stack
 *      stack:
 *          local reference variable
 *          method parameter reference variable
 *
 *      heap:
 *          object
 *          meta data
 *      heap share all the thread
 *
 *      heap:
 *          once created object-> eden -> after several gc promote -> survivor0/1 -> old
 *          [eden][survivor0][s1] young generation (serial GC/parallel GC/ ParallelNewGC)
 *          [                   ] old generation (serial GC,parallelOldGC, CMS concurrentMarkSweep)
 *          [] permanent meta data
 *      stop the world (STW)
 *      CMS:
 *          1.intial mark (STW)
 *          2.concurrent mark
 *          3. final mark(STW)
 *          4. concurrent sweep
 *      gc root(starting point)
 *          classLoader, class, monitor
 *      minor gc: young
 *      major gc: old
 *      full gc: young+old
 *      g1(full gc)
 *      [][E][][][S][][][X][][]
 *      [][][O][][][][O][][][]
 *
 *      garbage collection GC
 *          System.gc();
 */
/**
 * final finally finalize
 */
class ToRemove {
    //this finalize() will be invoked while the object be removed
    @Override
    protected void finalize(){
        System.out.println("i am removed");
    }
}
class GCTest {
    public static void main(String[] args) {
        new ToRemove();
        System.gc();
    }
}

/**
 * Thread
 *      create a new thread?
 *          1. extend thread class
 *          2. runnable throws checked excptions
 *      lifeCycle
 *          1.new xx() -> new
 *          2. start() -> active
 *          3. sleep() wait(), await()-> block wait
 *          4. Terminated
 */
class NewThread extends Thread {
    @Override
    public void run() {
        try{
            Thread.sleep(3000);
        }catch (Exception ex) {

        }

        System.out.println("My name: " + Thread.currentThread().getName());
    }
}

class TestThread {
    public static void main(String[] args) {
        //NewThread myThread = new NewThread();
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("i am runnable");
//            }
//        });
        //()->{}  -> create an object from the annonymous class
        Runnable runnable = ()->{
            System.out.println("i am r2");
        };
        Thread t1 = new Thread(runnable);
        //myThread.start();
        t1.start();
        //t1.start();
    }
}

/**
 * volatile
 *     volatile boolean flag f->t
 *      t1    t2
 *      cache cache
 *      memory
 *
 */
class TestFlag {
    static volatile boolean flag = false;
    public static void main(String[] args) throws Exception{

        Thread t1 = new Thread(()->{

            while(!flag) {

            }
            System.out.println("i am out!");
        });

        t1.start();

        Thread.sleep(3000);
        System.out.println("changed the flag");
        flag =true;




    }
}

/**
 *  volatile int i cannot secure threadsafe
 *  t1 t2   (+1)  right: 2
 *  0  0
 *  1  block
 *     2
 *
 *  0 0
 *  1 1
 *  synchronized threadsafe by itself
 *      resolve race condition
 *      monitor class
 */
class TestRaceCondition {
    static int i;

    public static void main(String[] args) throws Exception{
        TestRaceCondition trc1 = new TestRaceCondition(); // trc1 and trc2 will have their own monitor
        TestRaceCondition trc2 = new TestRaceCondition();
        Thread t1 = new Thread(()->{
            for(int j=0;j<10000;j++) {
                synchronized (trc1){
                    i++;
                }

            }
        });
        Thread t2 = new Thread(()->{
            for(int j=0;j<10000;j++) {
                synchronized (trc1){
                    i++;
                }
            }
        });
        t1.start();
        t2.start();
        //block here
        t1.join();
        t2.join();
        System.out.println(i);
    }
}