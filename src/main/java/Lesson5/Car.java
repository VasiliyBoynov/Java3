package Lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;

    private static CyclicBarrier cb;
    private static CountDownLatch cdl;
    private static Semaphore smp ;
    private static Lock lock =  new ReentrantLock();
    private static volatile boolean init = false;
    private static AtomicBoolean win = new AtomicBoolean(false);

    public static CountDownLatch getCdl() {
        return cdl;
    }

    public static void setCdl(CountDownLatch cdl) {
        Car.cdl = cdl;
    }

    public static Semaphore getSmp() {
        return smp;
    }

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        //this.cb=cb;
        //this.cdl = cdl;

    }
    @Override
    public void run() {
        try {
            lock.lock();
                if (!init){
                    //System.out.println(this.name+"run initialisation");
                    init=true;
                    cb = new CyclicBarrier(CARS_COUNT);
                    smp = new Semaphore((int) (CARS_COUNT/2)) ;
                }
            lock.unlock();
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cdl.countDown();
            cb.await();

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if (win.compareAndSet(false,true)){
            System.out.println(this.name + " WIN!!");
        };
        cdl.countDown();
    }
}

