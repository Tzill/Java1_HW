/**
 * Java. Level 1. Lesson 3. Homework
 *
 * @author Roman Stepanyuk
 * @version dated Feb 4, 2018
 */

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

public class HomeWork3 {
    public static void main(String[] args){
        int chTask;
        do {
            System.out.println("Please choose number of task (1, 2) or 0 for exit");
            chTask = getNewUserAnswerFromTo(0, 2);
            switch (chTask) {
                case 1:
                    //Test for Task 1
                    testUserAnswer(0, 9, 3); // от 0 до 9 включительно, 3 попытки
                    break;
                case 2:
                    //Test for Task 2
                    String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
                            "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
                            "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
                    guessWord(words);
                    break;
                case 0: System.out.println("Good bye!");
                    break;
            }
        }while (chTask!=0);
    }


    /*
    1. Написать программу, которая загадывает случайное число от 0 до 9, и пользователю дается 3 попытки угадать это число.
    При каждой попытке компьютер должен сообщить больше ли указанное пользователем число чем загаданное, или меньше.
    После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
    */

    public static int getNewUserAnswerFromTo(int min, int max){ // получить от пользователя integer в переданном диапазоне
        int answer = max+1;
        do {
            try{
                Scanner sc = new Scanner(System.in);
                System.out.print("Please enter integer (" + min + ".." + max + "): ");
                answer = sc.nextInt();
            }
            catch (InputMismatchException ex){
                //ex.printStackTrace();
                System.out.println("Not integer!");
            }
        }while (answer<min || answer>max);
        return answer;
    }

    public static int getRandomZeroTillX(int max){ // генерируем число от 0 до max включительно
        Random rand = new Random();
        return rand.nextInt(max+1); // прибавляем единицу для корректной генерации в нужном диапазоне
    }

    public static void testUserAnswer(int min, int max, int trials){ // игра. угадай число
        boolean repeat;
        do{
            int rightAnswr = getRandomZeroTillX(max);
            System.out.println("I have a nubmer (0..9) Guess what?");
            //System.out.println(rightAnswr);
            repeat = false;
            for(int i=trials; i>=1; i--){
                int userTry = getNewUserAnswerFromTo(min, max);
                if(userTry<rightAnswr) System.out.printf("Your answer is lower. You have %d trial(s)\n", i-1);
                else if(userTry>rightAnswr) System.out.printf("Your answer is bigger. You have %d trial(s)\n", i-1);
                else{
                    System.out.println("Bingo!");
                    break;
                }
            }
            System.out.println("Do you wanna play again? (1 - Yes/ 0 - No):");
            if(getNewUserAnswerFromTo(0, 1)==1) repeat = true;
        }while(repeat==true);
        System.out.println("Closing task 1");
    }

    /*
    2 * Создать массив из слов String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
     "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
     "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
     При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя, сравнивает его с загаданным
     и сообщает правильно ли ответил пользователь. Если слово не угадано, компьютер показывает буквы которые стоят на своих местах.

     apple – загаданное
     apricot - ответ игрока
     ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
     Для сравнения двух слов посимвольно, можно пользоваться:
     String str = "apple";
     str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
     Играем до тех пор, пока игрок не отгадает слово
     Используем только маленькие буквы
     */

    public static void guessWord(String[] words){
        int correctWordNumber = getRandomZeroTillX(words.length-1);
        String correctWord = words[correctWordNumber];
        int t = 12; // количество попыток пользователя
        String userWord;
        boolean isNotCorrect = true;
        do {
            System.out.println("Guess a word: ");
            Scanner sc = new Scanner(System.in);
            userWord = sc.next();
            if(!userWord.equals(correctWord)){
                int i, len;
                len = correctWord.length()<userWord.length() ?  correctWord.length() : userWord.length();
                for(i = 0; i<len; i++){
                    System.out.print((correctWord.charAt(i)==userWord.charAt(i))? userWord.charAt(i) : '#');
                }
                for(int j = i; j<15; j++) System.out.print('#');
                t++;
                System.out.println();
            } else {
                System.out.printf("Your answer is correct! You done it by %d trials\n", t);
                isNotCorrect = false;
            }
        }while (isNotCorrect);
        System.out.println("Closing task 2");
    }
}
