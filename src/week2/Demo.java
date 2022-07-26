package week2;

import week1.*;

public class Demo {
    public static void main(String[] args) {

        //Day1.x=1;
    }

}
class Demo1 extends Day1 {
    public static void main(String[] args) {
        Day1.x=1;
        System.out.println(Day1.x);
    }
    void dummy(){
        this.x=1;
    }
}