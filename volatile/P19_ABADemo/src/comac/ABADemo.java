package comac;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {
    static AtomicInteger atomicInteger = new AtomicInteger(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);
    public static void main(String args[]){
        System.out.println("=============以下是ABA问题的产生============");
        new Thread(()->{
            atomicInteger.compareAndSet(100,101);
            atomicInteger.compareAndSet(101,100);
        },"t1").start();
        new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){e.printStackTrace();};
            System.out.println(atomicInteger.compareAndSet(100,2019)+"\t"+atomicInteger.get());
        },"t2").start();

        System.out.println("=============以下是ABA问题的解决============");
        new Thread(()->{
            atomicInteger.compareAndSet(100,101);
            atomicInteger.compareAndSet(101,100);
        },"t3").start();

        new Thread(()->{
            atomicInteger.compareAndSet(100,101);
            atomicInteger.compareAndSet(101,100);
        },"t4").start();
    }
}
