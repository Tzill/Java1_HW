/**
 * Java 1. Lesson 2. Homework
 *
 * @author Roman Stepanyuk
 * @version dated Feb 1, 2018
 */

import java.util.Arrays;

public class HomeWork2 {
    public static void main(String[] args) {
        //test for Task 1
        System.out.println("____Task 1____");
        int[] testArray1 = {0, 1, 1, 0, 1, 0, 0, 1, 0, 1};
        System.out.println("TestArray: " + Arrays.toString(testArray1));
        System.out.println("Inverted:  " + Arrays.toString(invertArray(testArray1)));
        int[] testArray2 = {0, 1, 4, 0, 1, 0, 0, 1, 0, 1};
        System.out.println("TestArray: " + Arrays.toString(testArray2));
        System.out.println("Inverted:  " + Arrays.toString(invertArray(testArray2)));

        //test for Task 2
        System.out.println("____Task 2____");
        int[] testArray3 = new int[8];
        System.out.println(Arrays.toString(fillArrayPlus3(testArray3)));

        //test for Task 3
        System.out.println("____Task 3____");
        int[] testArray4 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Array: " + Arrays.toString(testArray4));
        System.out.println("Result:" + Arrays.toString(doubleAboveSix(testArray4)));

        //test for Task 4
        System.out.println("____Task 4____");
        int[][] testArray5 = new int[5][5];
        int[][] testArray6 = new int[4][4];
        System.out.println("Array:");
        printTwoDimArray(testArray5);
        System.out.println("Result:");
        printTwoDimArray(fillMatrixDiagonalsByOnes(testArray5));
        System.out.println("Array:");
        printTwoDimArray(testArray6);
        System.out.println("Result:");
        printTwoDimArray(fillMatrixDiagonalsByOnes(testArray6));

        //test for Task 5
        System.out.println("____Task 5____");
        int[] testArray7 = {34, 8, 65, 557, 0, -97, 9, -6758764, 67587, 8, 0};
        System.out.println("Array: " + Arrays.toString(testArray7));
        System.out.println("Min: " + findMinValue(testArray7) + "\nMax: " + findMaxValue(testArray7));

        //test for Task 6
        System.out.println("____Task 6____");
        int[] testArray8 = {1, 1, 1, 2, 1};
        int[] testArray9 = {2, 1, 1, 2, 1};
        int[] testArray10 = {5, 6, 5, 6};
        System.out.println("Array: " + Arrays.toString(testArray8) + "\nResult: " + checkArrayForSumSymmetry(testArray8));
        System.out.println("Array: " + Arrays.toString(testArray9) + "\nResult: " + checkArrayForSumSymmetry(testArray9));
        System.out.println("Array: " + Arrays.toString(testArray10) + "\nResult: " + checkArrayForSumSymmetry(testArray10));

        //test for Task 7
        System.out.println("____Task 7____");
        int[] testArray11 = {34, 8, 65, 557, 0, -97, 9, 8, 7, 3};
        int shift = 44;
        System.out.println("Array: " + Arrays.toString(testArray11) + "\nShift: " + shift
                + "\nResult: " + Arrays.toString(shiftArray(testArray11, shift)));
        shift = -55;
        System.out.println("Array: " + Arrays.toString(testArray11) + "\nShift: " + shift
                + "\nResult: " + Arrays.toString(shiftArray(testArray11, shift)));
    }

    /*
    Task 1. Задать целочисленный массив, состоящий из элементов 0 и 1.
    Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, ​​0​]​.​​
    С​​помощью​​ цикла ​​и ​​условия ​з​аменить ​​0 ​​на ​​1,​​1 ​​на ​​0;
     */
    public static int[] invertArray(int[] arr){
        for(int i=0; i<arr.length; i++){
            switch(arr[i]){
                case 1: arr[i]=0;
                break;
                case 0: arr[i]=1;
                break;
                default: System.out.println("Array is wrong! Only 1 and 0 integers are acceptable");
                return null;
            }
        }
        return arr;
    }

    /*
    Task 2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
     */
    public static int[] fillArrayPlus3(int[] arr){
        for(int i=0, j=0; i<arr.length; i++, j+=3) arr[i]=j;
        return arr;
    }

    /*
    Task 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
     */
    public static int[] doubleAboveSix(int[] arr){
        for(int i=0; i<arr.length; i++){
            if(arr[i]<6) arr[i]*=2;
        }
        return arr;
    }

    /*
    Task 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
          и с помощью цикла(-ов) заполнить его диагональные элементы единицами;Java. Level 1. Lesson 1. Example of homework
     */
    public static int[][] fillMatrixDiagonalsByOnes(int[][] arr){
         for(int i=0, j=arr[i].length-1; i<arr.length; i++, j-- ){
             arr[i][i]=arr[i][j]=1;
         }
         return arr;
    }

    /*
     1 0 0 1   11   14    1 0 0 0 1       11    15
     0 1 1 0    22 23     0 1 0 1 0        22  24
     0 1 1 0    32 33     0 0 1 0 0          33
     1 0 0 1   41   44    0 1 0 1 0        41  44
                          1 0 0 0 1       51    55
     */

    //Вывод двухмерного массива в консоль
    public static void printTwoDimArray(int[][] arr){
        for(int i=0; i<arr.length; i++) System.out.println(Arrays.toString(arr[i]));
    }

    /*
    Task 5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
     */
    public static int findMinValue(int[] arr){
        int min=Integer.MAX_VALUE;
        for(int i: arr){
            if (i<min) min=i;
        }
        return min;
    }
    public static int findMaxValue(int[] arr){
        int max=Integer.MIN_VALUE;
        for(int i: arr){
            if (i>max) max=i;
        }
        return max;
    }

    /*
    Task 6. ** Написать метод, в который передается не пустой одномерный целочисленный массив,
    метод должен вернуть true если в массиве есть место, в котором сумма левой и правой части массива равны.
    Примеры: checkBalance([1, 1, 1, || 2, 1]) → true, checkBalance ([2, 1, 1, 2, 1]) → false,
    checkBalance ([10, || 10]) → true, граница показана символами ||, эти символы в массив не входят.
     */
    public static boolean checkArrayForSumSymmetry(int[] arr){
        int sumLeft=0, sumRight=0;
        for(int i=0; i<arr.length; i++){
            sumLeft+=arr[i];
            for(int j=i+1; j<arr.length; j++){
                sumRight+=arr[j];
            }
            if (sumLeft==sumRight) return true;
            sumRight=0;
        }
        return false;
    }

    /*
    Task 7. **** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным,
    или отрицательным), при этом метод должен сместить все элементы массива на n позиций. Для усложнения задачи
    нельзя пользоваться вспомогательными массивами.
     */
    public static int[] shiftArray(int[] arr, int shift){
        if(shift/arr.length!=0) shift%=arr.length;  //избавление от лишних действий, если модуль смещения больше длины массива
        if(shift>0){ //ветка для положительного смещения
            for(int i=0; i<shift; i++){
                int buf=arr[arr.length-1];
                for(int j=arr.length-1; j>0; j--){
                    arr[j]=arr[j-1];
                }
                arr[0]=buf;
            }
        }else if(shift<0){ //ветка для отрицательного смещения
            for(int i=shift; i<0; i++){
                int buf=arr[0];
                for(int j=0; j<arr.length-1; j++){
                    arr[j]=arr[j+1];
                }
                arr[arr.length-1]=buf;
            }
        }
        return arr; //если смещение равно нулю - просто возврат массива без изменений
    }
}