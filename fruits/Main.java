

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class Main {
    public static final float WEIGHT_APPLE = 1.0f;
    public static final float WEIGHT_ORANGE = 1.5f;
    public static void main(String[] args){

        //Test of task 1
        String[] starr = {"one", "two", "three", "for", "five"};
        Integer[] intarr = {1,2,3,4,5};
        swapElements(starr, 2,4);
        swapElements(intarr, 0, 3);
        System.out.println(Arrays.toString(starr));
        System.out.println(Arrays.toString(intarr));

        //Test of task 2
        ArrayList<Integer> al = retArrayList(intarr);
        System.out.println(al);

        //Test of task 3
        Box<Apple> ba = new Box<>(Apple.class);
        Box<Apple> ba2 = new Box<>(Apple.class);
        Box<Orange> bo = new Box<>(Orange.class);
        for(int i = 0; i<4; i++){
            ba.add(new Apple(i));
        }

        for(int i = 0; i<8; i++){
            ba2.add(new Apple(i));
        }
        for(int i = 0; i<8; i++){
            bo.add(new Orange(i));
        }
        System.out.println(ba.getWeight());   //4
        System.out.println(ba2.getWeight());  //8
        System.out.println(bo.getWeight());   //12
        System.out.println(ba.fill(ba2));     //true
        System.out.println(ba.fill(bo));      //false
        System.out.println(ba.compare(bo));   //true  12==12
    }
    //super - класс должен быть не ниже по дереву наследования (такой же или выше)
    //extends - класс должен быть ниже по девеву наследования (такой же или ниже)
    public static <T> void copyElements(ArrayList<? extends T> src, ArrayList<? super T> dst){
        for(int i = 0; i < src.size(); i++){
            dst.add(src.get(i));
        }
        src.clear();
    }

    //Task 1
    public static <T> void swapElements(T[] array, int pos1, int pos2) {
        T buff = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = buff;
    }
    //Task 2
    public static <T> ArrayList<T> retArrayList(T[] array){
         ArrayList<T> al = new ArrayList<T>(Arrays.asList(array));
         return al;
    }
}
