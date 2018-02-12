/**
 * Java. Level 1. Lesson 5. Homework
 *
 * @author Roman Stepanyuk
 * @version dated Feb 11, 2018
 * @link https://github.com/Tzill
 */

import java.io.*;
 
public class HomeWork5 {
    public static void main(String[] args) throws IOException {
        Person[] persArray = new Person[5];
        persArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 42);
        persArray[1] = new Person("Petrov Petr", "Programmer", "pepetr@mailbox.com", "892312313", 60000, 30);
        persArray[2] = new Person("Alexandrov Alex", "Barmen", "alalex@mailbox.com", "892312314", 50000, 50);
        persArray[3] = new Person("Sergeev Sergey", "Janitor", "sesergey@mailbox.com", "892312315", 20000, 20);
        persArray[4] = new Person("Vasiliev Vasiliy", "Manager", "vavasiliy@mailbox.com", "892312316", 80000, 25);

        for (Person p : persArray) {
            if (p.getAge() > 40) System.out.println(p);
        }
        // Test for Task 2
        System.out.println();
        System.out.println("Writing persArrray to file and then reading from the file to console");
        writePersonArrayToFile("persArray1.txt", persArray);
        readFromFileToConsole("persArray1.txt");

        System.out.println();
        System.out.println("Reading from file into persReadArray, then write array to file and then print file to console");
        Person[] persReadArray = parsePersonArrayFromFile("persArray1.txt", 5);
        writePersonArrayToFile("persArray2.txt", persReadArray);
        readFromFileToConsole("persArray2.txt");
    }

    // Task 2
    public static void writePersonArrayToFile(String fileName, Person[] per) {  // Writing Person array to file
        try {
            File file = new File(fileName);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for (Person p : per) writer.write(p.toString() + "\r\n");
            writer.flush();
            writer.close();
        } catch (IOException exc) {
            System.out.println("Ошибка ввода-вывода: " + exc);
        }
    }

    public static void readFromFileToConsole(String fileName) { // Reading from file to console
        try {
            FileReader fr = new FileReader(fileName);
            char[] a = new char[200000];
            fr.read(a);
            for (char ch : a) {
                System.out.print(ch);
            }
        } catch (IOException exc) {
            System.out.println("Ошибка ввода-вывода: " + exc);
        }
    }

    public static Person[] parsePersonArrayFromFile(String fileName, int perNum) { // parsing Person array from file
        Person[] per = new Person[perNum];
        int i = 0, n = 0;
        String buf = "";
        String name = "", position = "", email = "", tel = "";
        int salary = 0, age = 0;
        try {
            FileReader fr = new FileReader(fileName);
            char[] a = new char[2000];   // Количество символов, которое будем считывать
            fr.read(a);   // Чтение содержимого в массив
            for(int k = 0; k<a.length; k++) {
                if (a[k] == ',') {
                    if (n == 0) name = buf;
                    else if (n == 1) position = buf;
                    else if (n == 2) email = buf;
                    else if (n == 3) tel = buf;
                    else if (n == 4) salary = Integer.parseInt(buf);
                    else if (n == 5) age = Integer.parseInt(buf);
                    buf = "";
                    n++;
                    continue;
                } else if (a[k] == '\n' && n==6) {
                    per[i] = new Person(name, position, email, tel, salary, age);
                    i++;
                    n=0;
                    continue;
                } else if (a[k] == '\r') {
                    buf = "";
                    continue;
                }
                buf += a[k];
            }
        } catch (IOException exc) {
            System.out.println("Ошибка ввода-вывода: " + exc);
        }
        return per;
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
	void setName(String name){
		this.name=name;
	}
	void setPosition(String position){
		this.position=position;
	}
	void setEmail(String email){
		this.email=email;
	}
	void setTel(String tel){
		this.tel=tel;
	}
	void setSalary(int salary){
		this.salary=salary;
	}
	void setAge(int age){
		this.age=age;
	}

	@Override
    public String toString(){
        return name + "," + position + "," + email + "," + tel + "," + salary + "," + age + ",";
    }
}