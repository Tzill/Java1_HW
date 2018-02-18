import java.util.Scanner;

/**
 * Java. Level 1. Lesson 7. Homework 1-6
 *
 * @author Roman Stepanyuk
 * @version dated Feb 18, 2018
 * @link https://github.com/Tzill
 */

/*
1. Расширить задачу про котов и тарелки с едой
2. Сделать так, чтобы в тарелке с едой не могло получиться отрицательного
   количества еды (например, в миске 10 еды, а кот пытается покушать 15-20)
3. Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны).
   Если коту удалось покушать (хватило еды), сытость = true
4. Считаем, что если коту мало еды в тарелке, то он ее просто не трогает, то
   есть не может быть наполовину сыт (это сделано для упрощения логики программы)
5. Создать массив котов и тарелку с едой, попросить всех котов покушать из этой
   тарелки и потом вывести информацию о сытости котов в консоль
6. Добавить в тарелку метод, с помощью которого можно было бы добавлять еду
   в тарелку
*/

public class HomeWork7 {
    final static int CAT_NUM = 9;
    public static void main(String args[]){
        Cat[] cats = new Cat[CAT_NUM];  // Task 5
        for (int i = 0; i<CAT_NUM; i++) {
            cats[i] = new Cat("cat_" + i, i*5+5);
        }
        Plate plate = new Plate(200);
        System.out.println("plate: " + plate.info());

        for(int i = 0; i<CAT_NUM; i++){ // feeding cats
            if (!cats[i].eat(plate)) {
                Scanner sc = new Scanner(System.in);
                System.out.println("plate: " + plate.info());
                System.out.print("Please add some food: ");
                plate.addFood(sc.nextInt());
                i--;
                //break;
                //System.out.println(cat.getName() + " is still hungry.");
            }
        }
        System.out.println("You fed all cats!");
        System.out.println("plate: " + plate.info());
    }
}

class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }
    public boolean decreaseFood(int n) {
        if (n<=food) {  // Task 2
            food -= n;
            return true;  // Task 3
        } else {
            return false; // Task 4
        }
    }
    public int info() {
        //System.out.println("plate: " + food);
        return food;
    }
    public void addFood(int food){ // Task 6
        this.food += food;
    }
}

class Cat {
    private String name;
    private int appetite;
    private boolean fullness;

    public String getName(){
        return name;
    }
    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.fullness = false; // Task 3
    }
    public boolean eat(Plate p) {
        fullness = p.decreaseFood(appetite); // Task 3
        if (fullness) {
            System.out.println(name + " has eaten " + appetite + " food. Belly is fully, Kitty is happy.");
            return true;
        } else {
            System.out.println("Plate has not enough food. " + name + " cannot eat " + appetite + " food.");
            return false;
        }
    }
}