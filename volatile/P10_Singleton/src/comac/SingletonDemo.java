package comac;


public class SingletonDemo {
    private static SingletonDemo instance;
    public SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我的构造方法SingletonDemo");
    }
    public static SingletonDemo getInstance(){
        if (instance == null){
            instance = new SingletonDemo();
        }
        return instance;
    }

    public static void main(String args[]){
        //单线程（main函数的操作动作。。。。）
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        //并发多线程下，情况发生了很大变化
        for(int i =0;i<10;i++){
            new Thread(()->{
               SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
