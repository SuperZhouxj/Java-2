package comac;

import com.sun.deploy.util.StringUtils;

import javax.rmi.CORBA.Util;
import java.util.*;
import java.util.stream.Collectors;

public class ContainerNotSafeDemo {
    public static void main(String args[]){
        //List<String> list = Arrays.asList("a","b","c");
        //list.forEach(System.out::println);

        //List<String> list = new ArrayList<>();
        //List<String> list = new Vector<>();
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        for(int i =0;i<20;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
            //使用List<String> list = new ArrayList<>()会出现java.util.ConcurrentModificationException

            /**
             * 1.故障现象
             *  java.util.ConcurrentModificationException
             *  2.故障原因
             *
             *  3.解决方法
             *    3.1 new Vector<>()
             *    3.2 Collections.synchronizedList(new ArrayList<>())
             *
             *  4.优化建议（同样的错误不犯第二次）
             */
        }
    }
}
