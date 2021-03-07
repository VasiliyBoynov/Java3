package Lesson6;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;


public class Lesson6 {
    private static final Logger logger = LogManager.getLogger(Lesson6.class);

    public static void main(String[] args) {
        task1();
        int[] input={4,1,1,1,1,3,7,5};
        System.out.println(Arrays.toString(task2(input)));
        int[] input1={1,1,1,1,1,1,1,1};
        System.out.println(task3(input1));
    }
    /*
    Логирование log4j2
    настроен файл log4j2.xml на 2 логера:
    1)для класса Lesson6.Lesson6 данные перенаправляются в файл fileName="src/main/java/Lesson6/logs/app.log",уровень логирования trace
    для всех других классов запускается логер root с выводом данных в консоль,уровень логирования warn
    */
    public static void task1(){
        System.out.println(Lesson6.class);
        logger.info("Log4j2ExampleApp started.");
        logger.warn("Something to warn");
        logger.error("Something failed.");
        TestLogger.testLoggerOtherClass();
    }

    public static int[] task2(int[] input) {

        for (int i = input.length-1; i >=0; i--) {
            if (input[i]==4){
                int[] out = Arrays.copyOfRange(input,i+1,input.length);
                return out;
            }
        }
        throw new RuntimeException();
    }

    public static boolean task3(int[] input){
        boolean find4 = false;
        boolean find1 = false;
        for (int i : input) {
            if (i!=4&&i!=1){
                    return false;
            }
            if (i==4){
                find4 = true;
            }
            if (i==1){
                find1 = true;
            }

        }
        return find1&&find4;
    }

}




