import java.util.*;

public class LearningCollectionsClass {
    public static void main(String[] args) {
        //testArrayList();
        //testArrayListIntegers();
        //testLinkedList();
        //testHashSet();
        //testLinkedHashSet();
        //testTreeSet();
        //testHashMap();


    }

    static void testHashMap() {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("Russia", "Moscow");
        hm.put("France", "Paris");
        hm.put("Germany", "Berlin");
        hm.put("Norway", "Oslo");
        //Set<Map.Entry<String, String>> set = hm.entrySet();
        for (Map.Entry<String, String> o : hm.entrySet()) {
            System.out.print(o.getKey() + ": ");
            System.out.println(o.getValue());
        }
        hm.put("Germany", "Berlin2");
        System.out.println("New Germany Entry: " + hm.get("Germany"));
    }

    static void testTreeSet() {
        TreeSet<String> ts = new TreeSet<>();
        ts.add("C");
        ts.add("A");
        ts.add("B");
        ts.add("E");
        ts.add("F");
        ts.add("D");
        System.out.println(ts);
    }

    static void testLinkedHashSet() {
        LinkedHashSet<String> hs = new LinkedHashSet<String>();
        hs.add("Бета");
        hs.add("Aльфa") ;
        hs.add("Этa");
        hs.add("Гaммa") ;
        hs.add("Эпсилон");
        hs.add("Oмeгa");
        System.out.println(hs);
    }

    static void testHashSet() {
        HashSet<String> hs = new HashSet<String>();
        hs.add("Бета");
        hs.add("Aльфa") ;
        hs.add("Этa");
        hs.add("Гaммa") ;
        hs.add("Эпсилон");
        hs.add("Oмeгa");
        System.out.println(hs);
    }

    static void testLinkedList() {
        LinkedList<String> w = new LinkedList<>();
        w.add("F");
        w.add("B");
        w.add("D");
        w.add("Е");
        w.add("C");
        w.addLast("Z");
        w.addFirst("A");
        w.add(1, "А2");
        System.out.println("1. LinkedList w: " + w);
        w.remove("F");
        w.remove(2);
        System.out.println("2. LinkedList w: " + w);
        w.removeFirst();
        String s = w.removeLast();
        System.out.println(s);
        System.out.println("3. LinkedList w: " + w);
        String val = w.get(2);
        w.set(2, val + " изменено");
        System.out.println("4. LinkedList w: " + w);
    }

    static void testArrayListIntegers() {
        ArrayList<Integer> al = new ArrayList<>();
        al.add(1);
        al.add(2);
        al.add(3);
        Integer[] ia = new Integer[al.size()];
        al.toArray(ia);
        System.out.println(Arrays.asList(ia));
    }

    static void testArrayList() {
        ArrayList<String> al = new ArrayList<>();
        al.add("A");
        al.add("B");
        al.add("C");
        al.add("D");
        al.add("E");
        System.out.println(al);
        al.remove("C");
        System.out.println(al);
        al.remove(1);
        System.out.println(al);
        System.out.println(al.size());
        System.out.println(al.contains("D"));
        al.set(0, "F");
        System.out.println(al);
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return o1.toString().compareTo(o2.toString());
            }
        };
        al.sort(comparator);
        System.out.println(al);
    }
}
