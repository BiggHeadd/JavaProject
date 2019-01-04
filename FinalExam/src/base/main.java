package base;

import java.util.Scanner;
import outside.testProtected;

class A{
    public void show(){
        System.out.println("A");
    }
}
class B extends A{
    public void show(){
        System.out.println("B");
    }
}

class C extends B{
    public void show(){
        super.show();
        System.out.println("C");
    }
}

public class main extends testProtected{

    public static int ddddddd = 0;

    public void test(){
        System.out.println(ddddddd);
    }

    public static void show(A a){
        a.show();
    }
    public static void main(String[] args){
        main a = new main();
        a.test();
    }
}
