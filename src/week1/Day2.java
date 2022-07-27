package week1;


//import java.util.Arrays;
import java.util.*;

public class Day2 {
    public static void main(String[] args) {
        //A1 a1 = new A1();
//        try{
//            dummy();
//        }catch (IllegalArgumentException illEx) {
//            System.out.println("ill");
//        }catch(ArithmeticException arEx){
//            System.out.println("arEx");
//
//        }catch (Exception ex) {
//            System.out.println("here all exc");
//        }finally {
//            System.out.println("in finally");
//        }


    }
    static void dummy() throws Exception {
        throw new ArithmeticException();
    }
 }
/**
 * 1. exceptions
 *             Throwable
 *      /                 \
 *     error              Exceptions
 *      stackOverFlow     checked exceptions resolve in compile
 *                          classNotFound
 *                        uncheck exceptions
 *                          ArrayIndexOutOfBoundsException
 *                          ArithmeticException
 *                          ..
 *Solutions:
 *  1. throws
 *  2. try catch finally block
 *
 */

/**
 * Java Collections
 */

/**
 * Array
 *  1.fix size
 *  2. contiguous memory access O(1)
 *  3. primitive arr vs object
 *
 */
class Test9 {
    static void dummy(int[] arr) {

    }
    static void dummy(int x){}

    public static void main(String[] args) {
        //int[] arr = {1,23,3};
        //int[] arr = {1,2,3};
        Integer[] arr1 = new Integer[10];
        //arr1 = new int[11];
        Arrays.stream(arr1).forEach(System.out::println);

    }
}

/**
 * List
 *      LinkedList
 *          node+ pointer
 *          memory allocation random
 *          access time O(n)
 *          n1->n3->n2 O(n) +O(1)
 *          adding ele at tail: n1->n2->n3 O(1)
 *      ArrayList
 *          Object[]
 *          add excess size of old arr, create arr O(n)
 *          O(1) access
 */
class Test10 {
    public static void main(String[] args) {
      List<Integer> list = new LinkedList<>();
      list = new ArrayList<>();
    }
}

/**
 * HashMap -> O(1)
 *      key -value
 *      Node[] -> TreeNode    50 100 100/50 = 2
 *      [][][kv1][]
 *           ->[kv2]->[][][][] O(N)->O(LOGN)
 *      hash collison
 *      hashcode(){return 0}
 *      key-> hashcode() -> int -> hash()  ->10%4=2 index
 *      key ----                           -> 6%4=2
 *
 *      method:
 *          put(key, value)
 *          get(key)
 *
 *      WorkFlow (mechanism of hm)
 *      put
 *          1. hash % size of node arr -> index
 *          2. target []
 *          3. check first ele
 *          4. if no, treeNode / node
 *          5. if it is not a tree, traverse the list until you find the target key
 *          6. or if there if no exist key, append the node at the tail
 *
 *
 */
class Test11{
    public static void main(String[] args) {
        HashMap<Integer, Integer> hm = new HashMap<>();
    }
}
/**
 * TreeMap-> binary search tree
 *      root
 *      / \
 *
 */
class Test12 {
    public static void main(String[] args) {
        TreeMap<Integer, Integer> tm =new TreeMap<>(Collections.reverseOrder());
        tm.put(1,null);
        tm.put(10,null);
        tm.put(5, null);
        tm.put(null, null);
        for(Map.Entry<Integer,Integer> entry: tm.entrySet()) {
            System.out.println(entry);
        }
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(1);
        System.out.println(list);
        list.add(1,1);

    }
}

/**
 * Set (Map -> dummy value field)
 *  HashSet ->
 *  List  vs Set
 *  dup        no dup
 *  matain inseriton   will not
 */


