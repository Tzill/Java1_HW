import java.util.*;

public class JavaCore1Hw3 {
    public static void main(String[] args) {
        task1FindDuplicates();

        //task2
        TelephoneMap telMap = new TelephoneMap();
        telMap.add("Smith", "A", 4848443);
        telMap.add("Brown", "X", 3433382);
        telMap.add("Heisenberg", "U", 989898);
        telMap.add("Smith", "E", 11122122);
        telMap.print();
        System.out.println(telMap.get("Smith"));
    }

    static void task1FindDuplicates() {
        ArrayList<String> al = new ArrayList<>(Arrays.asList("apple", "peach", "cherry",
                "tomato", "apple", "orange", "melon", "grapes", "melon", "apple"));
        HashSet<String> hs = new HashSet<>(al); // избавляемся от дубликатов
        System.out.println(hs);

        ArrayList<String> alCopy = new ArrayList<>(al); // коллекция из которой будем удалять
        HashMap<String, Integer> hm = new HashMap<String, Integer>(); // hm хранит слово ключ и количество дубликатов

        for (String s : hs) {
            int i = 0;
            while (alCopy.remove(s)) i++;
            hm.put(s, i);
        }
        for (Map.Entry<String, Integer> e : hm.entrySet()){  // выводим содержимое HashMap
            System.out.print(e.getKey() + ": " + e.getValue() + " ");
        }
    }
}

class TelephoneMap {
    Comparator comparator = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            return o1.hashCode()-o2.hashCode();
        }
    };
    TreeMap<Person, Integer> tm = new TreeMap<>(comparator);

    public void add(String surname, String name, Integer i){
        tm.put(new Person(surname, name), i);
    }
    public String get(String surname) {
        String s = surname + ": ";
        for(Map.Entry<Person, Integer> e : tm.entrySet()) {
            if (e.getKey().getSurname().equals(surname)) {
                s += e.getValue() + ", ";
            }
        }
        return s;
    }
    public void print(){
        for(Map.Entry<Person, Integer> e : tm.entrySet()){
            System.out.print(e.getKey().getSurname() + ": " + e.getValue() + " ");
            System.out.println();
        }
    }
}

class Person {
    String surname;
    String name;
    public Person(String surname, String name){
        this.surname = surname;
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof Person)) return false;
        Person another = (Person)obj;
        return this.surname.equals(another.surname) && this.name.equals(another.name);
    }
    @Override
    public int hashCode(){
        return surname.hashCode()+name.hashCode();
    }
}