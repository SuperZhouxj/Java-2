package comac;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    static class User{
      String name;
      Integer age;
      public User(String name, Integer age) {
          this.name = name;
          this.age = age;
      }
    }

    public static void main(String args[]){
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User zh3 =new User("zhangsan",10);
        User li4 = new User("Lisi",11);
        atomicReference.set(zh3);
        System.out.println(atomicReference.compareAndSet(zh3,li4) +"\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(zh3,li4) +"\t" + atomicReference.get().toString());
    }

}
