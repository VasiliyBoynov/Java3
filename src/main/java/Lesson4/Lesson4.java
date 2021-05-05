package Lesson4;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lesson4 {
    static final Object mon = new Object();
    static volatile char currentChar = 'A';
    static final int num = 5;

    static int count=0;
    static boolean run = true;



    public static void main(String[] args) {
        task1();
        testExecutorServiceFixedThreadPool();
        testExecutorServiceCachedThreadPool();

    }


    public static void task1(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i = 0; i < num; i++) {
                        synchronized (mon){
                            while(currentChar != 'A'){
                                mon.wait();
                            }
                            System.out.print("A");
                            currentChar = 'B';
                            mon.notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i = 0; i < num; i++) {
                        synchronized (mon){
                            while(currentChar != 'B'){
                                mon.wait();
                            }
                            System.out.print("B");
                            currentChar = 'C';
                            mon.notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for (int i = 0; i < num; i++) {
                        synchronized (mon){
                            while(currentChar != 'C'){
                                mon.wait();
                            }
                            System.out.print("C");
                            currentChar = 'A';
                            mon.notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //Использования Executors.newFixedThreadPool удобно, когда известно максимальное колличество участников в чате
    /*В примере создавался пул на 5 бесконечных нитей (аналог соединения с пользователем и ожидания сообщения от клиента)
    и при этом постарался запустить 10 нитей. В результате получился запуск 5 нитей, остальные нити просто ждут.
    * */
    public static void testExecutorServiceFixedThreadPool(){
        ExecutorService service = Executors.newFixedThreadPool(5);
        doThread(service);
        closeExecutor(service);
    }
    //Использования Executors.newCachedThreadPool удобно, когда не известно максимальное колличество участников в чате
    /*В примере создавался пул нитей CachedThreadPool. В результате каждая новая нить, запущенная с использованием executors, работала корректно.
    * */
    public static void testExecutorServiceCachedThreadPool(){
        ExecutorService service = Executors.newCachedThreadPool();
        doThread(service);
        closeExecutor(service);
    }

    public static void closeExecutor(ExecutorService service){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        run=false;
        System.out.println("closeAll");
        service.shutdownNow();
    }


    public static void doThread(ExecutorService service){
        run=true;
        count=0;
        System.out.println();
        for (int i = 0; i < 10; i++) {
            //System.out.println(i);
            service.execute(new Runnable() {
                @Override
                public void run() {
                    count++;
                        System.out.printf("Thread %d started%n", count);
                    while(run) {
                            //System.out.println("Thread " + count);
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                //e.printStackTrace();
                            }
                    }
                }
            });

        }
    }

}
