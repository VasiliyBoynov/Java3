package Lesson1.src.main.java;

import java.util.ArrayList;
import java.util.Arrays;


public class Lesson1 {
    public static void main(String[] args) {
        doTask1();
        doTask2();
        doTask3();
    }

    public static void doTask1(){
        System.out.println("Task1");
        int num1=0;
        int num2=5;
        //System.out.println("Integer");
        Integer[] ints = new Integer[10];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = i*3;
        }
        Task1<Integer> var1 = new Task1<Integer>(ints);
        var1.myChange(num1,num2);
        var1.info();

        System.out.println("Double");
        Double[] doubles = new Double[10];
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = i*3.0;
        }
        Task1<Double> var2 = new Task1<Double>(doubles);
        var2.myChange(num1,num2);
        var2.info();
    }

    public static void doTask2(){
        System.out.println("Task2");
        Integer[] ints = new Integer[10];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = i*3;
        }
        Task1<Integer> var1 = new Task1<Integer>(ints);
        var1.info();

        ArrayList<Integer> list = var1.toList();
        System.out.println(list);


    }

    public static void doTask3(){

        Box box1 = new Box();
        for (int i = 0; i < 10; i++) {
            box1.addFruit(new Orange());

        }
        Box box2 = new Box();
        for (int i = 0; i < 15; i++) {
            box2.addFruit(new Apple());

        }

        System.out.println("test different types fruits for the Box");
        box1.addFruit(new Apple());
        System.out.println("test for metod compare(Box box)");
        System.out.println(box1.compare(box2));
        box1.addFruit(new Orange());
        System.out.println(box1.compare(box2));
        System.out.println("test for metod copyAllFruit(Box box)");
        Box box3 = new Box();
        Box box4 = new Box();
        box3.addFruit(new Orange());
        box1.copyAllFruit(box3);
        box1.addFruit(new Apple());
        box4.copyAllFruit(box3);
        box2.copyAllFruit(box3);



    }



}
