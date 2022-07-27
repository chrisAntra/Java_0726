package week1;

import java.util.LinkedList;
import java.util.List;

public class Day1 {
   static protected int x;
}


/**
 * 1. OOP object oriented programming
 *      class: blue print
 *          attribute
 *          method
 *      object
 *
 *      Polymorphism
 *          upcast:
 *          method overriding: runtime
 *          method overloading: compile in one class
 *      Inheritance
 *          reuse
 *      Abstraction
 *          hide detail
 *          abstract class:
 *          interface:
 *              attribute final static
 *              before java8:
 *                  only allow public abstract method w/o method body
 *              after java8: static& default
 *              after java9: private
 *
 *
 *      Encapsulation
 *          private
 *          public
 *          default
 *          protected
 *
 *
 *
 *
 */

class Parent {
    // default
    private int x;

//    public int getX() {
//        return x;
//    }
//
    public void setX(int x) {
        this.x = x;
    }

    void dummy(){
        System.out.println("i am parent");
    }
}
class Child extends Parent {
    int x;
    Child(){}
    Child(int x) {
        this.x=x;
    }
//
    @Override
    void dummy(){
        System.out.println("i am child");
    }
//    void dummy(int x) {
//        System.out.println("i am overload method");
//    }
}
class Polymorphism {
    public static void main(String[] args) {
//        Parent p1 = new Parent(); //reference variable will be store in the stack, object store in heap
        //Parent p2 = new Child(); //upcasting
        Child c1 = new Child(1);
        c1.dummy();
        Abstract abs = new Abstract() {
            @Override
            void dummy() {
                System.out.println("iam anonymous");
            }
        };
        abs.dummy();
    }

}
abstract class Abstract {
    Abstract(){
        System.out.println("i am abstract");
    }
    //abstract
    abstract void dummy();
    int add(int x, int y) {
        return x+y;
    }
}
class DemoAbs extends Abstract {
    @Override
    void dummy(){
    }
}
interface  i1{
    final static int a=1;
}
interface  i2{
    static int a=2;
}
class A implements i1, i2{
    static void print () {
        System.out.println(i1.a);
    }

}
interface inter {

    void dummy();
//    default int add(int x, int y) {
//        minus(x, y);
//        return x+y;
//    }
//    private int minus(int x, int y) {
//        return x-y;
//    }

}

/**
 * 2.static
 *      make attribute/ method directly belongs to class, no need instance
 *      cannot inherit
 *
 */
class Test2 {
    public static void main(String[] args)  throws Exception{
        Class clzz = Class.forName("week1.Test1");
        Test1 t1 = new Test1();
        Test1 t2 = new Test1();
    }
}
class Test1 {
    static {
        System.out.println("i am static block");
    }
    {
        System.out.println("i am non static");
    }
    public static void main(String[] args) {
//        //no this
//        palindrome();
    }

    void dummy() {
        //this
        this.palindrome();
    }
    static boolean palindrome() {
        return false;
    }
}
class Child2 extends Test1{
    public static void main(String[] args) {
        System.out.println(palindrome());

    }

}

/**
 * 3. Java pass by value
 *      primitive                                                           object (reference->object)
 *      boolean , byte , char , short , int , long , float and double       LinkedList, Integer, String
 */
class Test3{
    int x;
    public static void main(String[] args) {
        //r v
//        Test3 t3 = new Test3();
//        //r v
//        int i1 =10;
        Test3 t3 = new Test3();
        t3.x=1;
        //
        dummy(t3);
        //1 or 10?
        System.out.println(t3.x);

    }
    static void dummy(Test3 t3) {
        //t3 reference value -> old object
        //t3
//        t3= new Test3();
        t3.x=10;
//        x=10;

    }


}

/**
 * 4. final
 *      attributes: cannot change reference value
 *      method: not allow to override
 *      class: cannot be inherited
 */
class Test5 {
    final LinkedList<Integer> list2= new LinkedList<>();
//    Test5 (){
//        list2= new LinkedList<>();
//    }
    public static void main(String[] args) {
        final List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        //list = new LinkedList<>();
        System.out.println(list);
    }
}
/**
 * 5. Immutable
 *      String
 *      Integer
 *      Boolean
 *      ..
 */
class Test6 {
    public static void main(String[] args) {

        Integer i1= 1;
        i1 =2;
        String s1= "abdbdbd";
        s1 ="abc";
        StringBuilder res =new StringBuilder();

        for(int i= s1.length()-1;i>=0;i--) {
            res.append(s1.charAt(i));
        }
        System.out.println(res);
    }
}
final class MyList {
    private final List<Integer> innerList;
    public MyList(List<Integer> list) {
        //deep copy
        this.innerList = new LinkedList<>(list);//this just for explain
    }
    public List<Integer> getList() {
        //deep
        return new LinkedList<>(innerList);
    }
}

class Test7 {
    public static void main(String[] args) {

        List<List> nestedList = new LinkedList<>();
        List<List> copyList = new LinkedList<>(nestedList);
        //  n[l1[1, 2,3], l2 ]    c[l1*[1,2],   l2]
        List<Integer> list = new LinkedList<>();
        list.add(1);
        //list-> list obj
        MyList myList = new MyList(list);
        //list.add(2);
        myList.getList().add(3);
        System.out.println(myList.getList());

    }
}
class Test8{
    public static void main(String[] args) {
        Integer i1 = 128;
        Integer i2 = 128;
        // integer pool  -128- 127
        String str ="abc";
        String str2=new String("abc").intern();
        System.out.println(str==str2);
        //System.out.println(i1.equals(i2));
    }
}
