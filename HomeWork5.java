/**
 * Java. Level 1. Lesson 5. Homework
 *
 * Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст;
 * Конструктор класса должен заполнять эти поля при создании объекта;
 * Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль;
 * Создать массив из 5 сотрудников. С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
 *
 * @author Roman Stepanyuk
 * @version dated Feb 11, 2018
 * @link https://github.com/Tzill
 * @
 */

public class HomeWork5 {
    public static void main(String[] args){
        Person[] persArray = new Person[5];
        persArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 42);
        persArray[1] = new Person("Petrov Petr", "Programmer", "pepetr@mailbox.com", "892312313", 60000, 30);
        persArray[2] = new Person("Alexandrov Alex", "Barmen", "alalex@mailbox.com", "892312314", 50000, 50);
        persArray[3] = new Person("Sergeev Sergey", "Janitor", "sesergey@mailbox.com", "892312315", 20000, 20);
        persArray[4] = new Person("Vasiliev Vasiliy", "Manager", "vavasiliy@mailbox.com", "892312316", 80000, 25);

        for(Person p: persArray){
            if(p.getAge()>40) System.out.println(p);
        }
    }
}

class Person {
    private String name;
    private String position;
    private String email;
    private String tel;
    private int salary;
    private int age;

    public Person(String name, String position, String email, String tel, int salary, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.tel = tel;
        this.salary = salary;
        this.age = age;
    }

    int getAge(){
        return age;
    }

    @Override
    public String toString(){
        return "Name: " + name + ", Position: " + position + ", Email: " + email + ", Tel: " + tel + ", Salary: " + salary + ", Age: " + age;
    }
}