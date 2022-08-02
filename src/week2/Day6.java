package week2;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day6 {
}
/**
 * parallel stream
 * [1,2,3,4,5,6] -> [1*2, 2*2....6*2] -> [1*2,2*2] -> filter(>5)
 *                                         [3,4]
 *                                         [5,6]
 */
class TestParallelStream{

    public static void main(String[] args) {
        List<Integer> list = IntStream.rangeClosed(1,6).parallel().map(x->{
            System.out.println("curr thread: "+ Thread.currentThread().getName()+" step 1");
            return 2*x;
        }).filter(x-> {
            System.out.println("curr thread: "+ Thread.currentThread().getName()+" step 2");
            return x>5;

        }).boxed().collect(Collectors.toList());
        System.out.println(list);
        //List<Integer> list  = Arrays.asList(1,2,3,54,5);
        //list.parallelStream().map().
    }
}
/**
 * CompletableFuture  (default forkJoinPool)
 *    Future get()  block
 *    fully asynchronized  ,  chain operation ,  combine multiple result... allOf(), anyOf()
 */
class TestCompletableFuture {
    public static void main(String[] args) {

//        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(()->{
//            try{
//                Thread.sleep(3000);
//            }catch (Exception ex){}
//            System.out.println("step 1");
//            return 1;
//        });
//        System.out.println("logic in main");
//        //int x=  future.get();
//        cf.thenAccept(x->{
//            System.out.println("step 2");
//            System.out.println("the value is: "+x);
//        });
//        System.out.println("other logic in main");
//        cf.join();
        List<CompletableFuture> completableFutureList = IntStream
                .rangeClosed(0,5)
                .collect(()->new LinkedList<>(),(l, i)->{

                    CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(()->{
                        try{
                            Thread.sleep(i*1000);
                        }catch (Exception ex){}
                        System.out.println("Step 1 This is: "+Thread.currentThread().getName());
                        return i;
                    });
                    l.add(cf);
                },(resList, l)->{});
        CompletableFuture cf = CompletableFuture.anyOf(completableFutureList.toArray(new CompletableFuture[0]));
        cf.thenRun(()->{
            completableFutureList.stream().forEach(cfEle->{
                cfEle.thenAccept(i->{
                    System.out.println("step 2 This is: "+Thread.currentThread().getName());
                    System.out.println((int)i*2);
                }).join();
            });

        });
        //..
        cf.join();
    }
}

class Solution {
    //[1,1,2,3]
    public static void main(String[] args) {
        int[] nums={1,1,2,3}; //{1,2,3,_}  {1,2,3,_}
        System.out.println(removeDuplicates(nums));
        Arrays.stream(nums).forEach(System.out::println);

    }
    public static int removeDuplicates(int[] nums) {
//        Set<Integer> set = new TreeSet<>();
//        for(int num: nums) {
//            set.add(num); n*log(k)
//        }
//        List<Integer> list = new LinkedList<>(set);
//        int sz = list.size();
//        for(int i=0;i<sz;i++) {
//            nums[i]= list.get(i);
//        }
//        return sz;
        int i=0;
        int j=0;
        for(j=0;j<nums.length;j++) {
            if(j==0) {
                i++;
                j++;
            }else{
                if(nums[j]!=nums[j-1]){
                    nums[i]=nums[j];
                    i++;
                }
            }
        }
        return i;


    }
}