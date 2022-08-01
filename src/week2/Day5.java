package week2;

import javax.xml.validation.Schema;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day5 {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        Optional<Integer> op = Optional.ofNullable(null);
        //Integer x=null;
        if(op.isPresent()){
            System.out.println("there is value");
        }else {
            System.out.println("there is null");
        }
        System.out.println(op.orElseGet(()->{
            System.out.println("i will provide a defualt value");
            //...
            return 10;
        }));
    }
}

/**
 * Java 8
 *      new feature
 *          static default method in interface
 *      Functional interface
 *      Lambda expression
 *      stream api (for loop)   stream().map().filter()....forEach()/reduce/collect()
 *      parallelStream (forkJoinPool)
 *      Optional: wrapper
 */


/**
 * Functional interface
 *      only contains one abstract method
 *
 *      comparator
 *      comparable
 *      runnable
 *      callable
 *      supplier
 *      Bi/Function
 *      Bi/Consumer
 *      predicate
 *
 *
 *
 *
 *
 */

/**
 * Q: runnable vs        callable
 *      void               <T>
 *      cannot resolve     can
 *      checked exceptions
 */
@FunctionalInterface
interface TestInter {
    int dummy();
    default int dummy2(){return 1;};
    //...
}
class TestFunctionInter{
    public static void main(String[] args) throws Exception{
//        Function<Integer, Integer> function = (a)->a+1;
//        int a =1;
//        System.out.println("pre: "+ a);
//        System.out.println("after: "+ function.apply(a));

//        Callable callable = ()->{
//            throw new Exception("i am dummy except");
//        };
//        ExecutorService es = Executors.newFixedThreadPool(1);
//        Future<Void> future =  es.submit(callable);
//        System.out.println(future.get());

//        TestInter ti = ()->1;
//        int asc= (int)'a';//97
//        int asc2= (int)'A';//65
//        System.out.println(asc2);
//        System.out.println("B".compareTo("ADUsASDFASDFA"));
//        Comparator<Integer> comparator = (a, b)-> {
//            return b-a;
//        };
//        List<Integer> list = Arrays.asList(1,5,2,7,8);
//        Collections.sort(list, comparator);
//        System.out.println(list);

    }
}
//ASCII
//"FnZd" and "EMoUs"
//"abcd" and "abc"



/**
 * ()->{
 *     logic
 *     return ...;
 * }
 * ()-> value
 */
class TestLambdaEx{
    public static void main(String[] args) {
        Predicate<Integer> predicate = (y)-> {
            System.out.println("other logic");
            return true;
        };
        System.out.println(predicate.test(10));
    }
}


/**
 *  stream()
 *      replace for loop
 *      chain operation
 *      List,Set,array -> stream()  [ele1, ]
 *                                 .map()
 *                                 .forEach()
 *
 *      intermediate step
 *          map()
 *          mapToInt()
 *
 *      terminate step (only when apply this step, the chain op will run)
 *          collect()
 *          forEach()
 *          reduce()
 *          ...
 * parallel stream
 */
class TestStream {
    static int index=0;
    public static void main(String[] args) {
        //[1,2,3,4,4,5] *10;
        List<Integer> list = Arrays.asList(1,2,3,4,4,5);
        //list.remove(0);
//        for(int i=0; i<list.size();i++) {
//            list.set(i,list.get(i)*10);
//        }
//        System.out.println(list);

        list.stream().map(x->{
            list.set(index, x*10);
            index++;
            return x*10;
        }).collect(Collectors.toList());
        System.out.println(list);
        // int[] -> List<Integer>
        //Stream<Integer>
//        int[] arr = {1,3,4,5,5};
        //boxed ->(int->Integer)

//        List<Integer> list = Arrays.stream(arr).boxed().collect(()->new LinkedList<>(),(resList, ele)->{
//            arr[index] = arr[index]*10;
//            index++;
//            resList.add(ele);
//        },(l1, l2)->{});
//        System.out.println(list);
//        Arrays.stream(arr).forEach(System.out::println);
        //leetcode 49

    }
}
class Employee {
    int age;
    String name;
}
//Q: filter the employee with age larger 18
class Filter {
    public static void main(String[] args) {
        List<Employee> employeeList = new LinkedList<>();
        employeeList.stream().filter(emp-> emp.age>18).collect(Collectors.toList());
    }
}
