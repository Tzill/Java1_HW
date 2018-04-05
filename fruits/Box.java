
import java.util.ArrayList;



public class Box<T extends Fruit> {
    //для создания ArrayList c фруктами используем правильное обобщение
    //не забываем про метод add (добавить фрукт в коробку) - -яблоко положить к апельсинам нельзя

    // Task 3
    private float fruitWeight;
    private ArrayList<T> boxFruits;
    private Class<T> genClassName;

    Box(Class<T> genClassName){
        boxFruits = new ArrayList<>();
        this.genClassName = genClassName;

        if(genClassName.getName().equals("Apple")) {
            fruitWeight = Main.WEIGHT_APPLE;
        }
        if(genClassName.getName().equals("Orange")) {
            fruitWeight = Main.WEIGHT_ORANGE;
        }
    }

    public void add(T fru){
        boxFruits.add(fru);
    }

    public float getWeight(){
        return boxFruits.size() * fruitWeight;
    }
    /*Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую
        подадут в compare в качестве параметра, true – если она равны по весу, false – в противном случае
                (коробки с яблоками мы можем сравнивать с коробками с апельсинами);*/
    public boolean compare(Box boxExt){
        return this.getWeight() == boxExt.getWeight();
    }

    /*Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую
    (помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами).
    Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты,
    которые были в этой коробке;*/
    public boolean fill(Box boxExt){
        if (this.genClassName.getName().equals(boxExt.genClassName.getName())){
            boxFruits.addAll(boxExt.boxFruits);
            boxExt.boxFruits.clear();
            return true;
        }
        return false;
    }
}
