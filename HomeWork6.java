/**
 * Java. Level 1. Lesson 6. Homework
 *
 * @author Roman Stepanyuk
 * @version dated Feb 16, 2018
 * @link https://github.com/Tzill
 */

/*
1. Создать классы Собака и Кот с наследованием от класса Животное.
2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие.
        В качестве параметра каждому методу передается величина, означающая или
        длину препятствия (для бега и плавания), или высоту (для прыжков).
3. У каждого животного есть ограничения на действия (бег: кот 200 м., собака
        500 м.; прыжок: кот 2 м., собака 0.5 м.; плавание: кот не умеет плавать,
        собака 10 м.).
4. При попытке животного выполнить одно из этих действий, оно должно сообщить
        результат в консоль. (Например, dog1.run(150); -> результат: run: true)
5. Записать объекты в массив и перебрать в цикле, проилюстрировав полиморфизм
*** Переписать крестики-нолики, используя все принципы ООП.
 */

abstract class Animal {
    protected int maxRunDist;
    protected int maxSwimDist;
    protected double maxJumpHeight;

    Animal(int maxRunDist, int maxSwimDist, double maxJumpHeight){
        this.maxRunDist = maxRunDist;
        this.maxSwimDist = maxSwimDist;
        this.maxJumpHeight = maxJumpHeight;
    }

   abstract String getInfo();

    boolean run(int distance, boolean echo) {
        if (distance > 0 && distance <= maxRunDist) {
            if (echo==true) System.out.println(this.getClass().getName()
                    + " ran " + distance);
            return true;
        } else {
            if (echo==true) System.out.println(this.getClass().getName()
                    + " cannot run " + distance);
            return false;
        }
    }

   boolean swim(int distance, boolean echo) {
        if (distance >= 0 && distance <= maxSwimDist) {
            if (echo==true) System.out.println(this.getClass().getName()
                    + " swam " + distance);
            return true;
        } else {
            if (echo==true) System.out.println(this.getClass().getName()
                    + " cannot swim " + distance);
            return false;
        }
    }

    boolean jump(double height, boolean echo) {
        if (height >= 0 && height <= maxJumpHeight) {
            if (echo==true) System.out.println(this.getClass().getName()
                    + " jumped " + height);
            return true;
        } else {
            if (echo==true) System.out.println(this.getClass().getName()
                    + " cannot jump " + height);
            return false;
        }
    }
}

class Cat extends Animal {
    Cat(){ super(200,0,2); }

    @Override
    String getInfo(){
        return "Cat can run " + maxRunDist + ", can smim " + maxSwimDist
                + ", can jump " + maxJumpHeight;
    }
}

class Dog extends Animal {
    Dog(){ super(500,10,0.5); }

    @Override
    String getInfo(){
        return "Dog can run " + maxRunDist + ", can smim " + maxSwimDist
                + ", can jump " + maxJumpHeight;
    }
}

public class HomeWork6 {
    public static void main(String args[]){
        Cat cat1 = new Cat();
        Dog dog1 = new Dog();

        cat1.run(100, true); // true
        cat1.run(300, true); // false
        System.out.println("Cat swim 300: " + cat1.swim(300, false)); // false

        System.out.println();
        Animal[] animals = {cat1, dog1};
        for(Animal animal : animals){
            animal.jump(1.0, true);
            animal.swim(5, true);
            animal.run(250, true);
            // полиморфизм. Можно было бы методы rum, swim,
            // jump определять в классах кота и собаки, в классе животного
            // создать абстрактные методы, но это бы привело к повторяемости кода
            System.out.println(animal.getInfo());
            System.out.println();
        }
    }
}
