package comac;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    private volatile Map<String,Object> map = new HashMap<>();
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public void set(String key,Object value){
        reentrantReadWriteLock.writeLock().lock();
        try {
            System.out.println("开始写入"+key);
            map.put(key,value);
            try {
                TimeUnit.SECONDS.sleep(1);}catch (Exception e){e.printStackTrace();}
            System.out.println("写入完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }
    public void get(Integer key){
        reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println("开始读入");
            Object result = map.get(key);
            System.out.println("读入完成"+result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }

    public static void main(String args[]){
        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();

        for (int i =1;i<=5;i++){
            Integer temp = 1;
            new Thread(()->{
                readWriteLockDemo.set(temp+"",temp+"");
                readWriteLockDemo.get(temp+"");
            },String.valueOf(temp)).start();

        }
    }
}
