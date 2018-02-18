/**
 * Java. Level 1. Lesson 7. Homework ***
 *
 * @author Roman Stepanyuk
 * @version dated Feb 18, 2018
 * @link https://github.com/Tzill
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeWork7GUI {
    public static void main(String args[]){
        GUI gui = new GUI();
    }
}

class GUI extends JFrame {
    final static int CAT_NUM = 10;
    int lastFedCounter = 0;
    public GUI() {
        Cat[] cats = new Cat[CAT_NUM];  // Task 5
        for (int i = 0; i<CAT_NUM; i++) {
            cats[i] = new Cat("cat_" + i, i*5+5);
        }
        Plate plate = new Plate(325);

        setTitle("Cats feeding");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(500, 300, 510, 400); // window position and size

        JPanel panel = new JPanel();
        panel.setLayout(null); // absolute layout

        JCheckBox checkBoxes[] = new JCheckBox[10];
        for (int i = 0; i < 10; i++) {
            checkBoxes[i] = new JCheckBox("Feed " + cats[i].getName());
            checkBoxes[i].setBounds(5, i*30+5, 100, 20);
            checkBoxes[i].setSelected(true);
            panel.add(checkBoxes[i]);
        }
        JLabel labelsCatAppetite[] = new JLabel[10];
        for (int i = 0; i < 10; i++) {
            labelsCatAppetite[i] = new JLabel(String.valueOf(cats[i].getAppetite()));
            labelsCatAppetite[i].setBounds(110, i*30+5, 80, 20);
            panel.add(labelsCatAppetite[i]);
        }
        JLabel labelsCatFullness[] = new JLabel[10];
        for (int i = 0; i < 10; i++) {
            labelsCatFullness[i] = new JLabel(String.valueOf(cats[i].getFullness()));
            labelsCatFullness[i].setBounds(150, i*30+5, 80, 20);
            panel.add(labelsCatFullness[i]);
        }
        JLabel labelPlate = new JLabel("Plate: " + String.valueOf(plate.info()));
        labelPlate.setBounds(200, 150, 200, 20);
        panel.add(labelPlate);

        JLabel labelInfo = new JLabel("Please feed cats");
        labelInfo.setBounds(200, 200, 200, 20);
        panel.add(labelInfo);

        JTextField textAddFood = new JTextField("Type integer to add food with next feeding");
        textAddFood.setBounds(200, 250, 270, 20);
        panel.add(textAddFood);
        textAddFood.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textAddFood.setText("");
            }
        });

        JButton hungryButton = new JButton("Make them hungry");
        hungryButton.setBounds(320,310, 150, 30);
        panel.add(hungryButton);

        JButton feedButton = new JButton("Feed cats");
        feedButton.setBounds(200,310, 100, 30);
        panel.add(feedButton);
        feedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String text = "";
                if (!textAddFood.getText().equals("Type integer to add food with next feeding")
                        && (!textAddFood.getText().equals("")))
                    try {
                        plate.addFood(Integer.parseInt(textAddFood.getText()));
                    } catch (NumberFormatException exc) {
                        labelInfo.setText("Only integers are acceptable");
                        textAddFood.setText("Type integer to add food with next feeding");
                        return;
                    }

                for(int i = lastFedCounter; i<CAT_NUM; i++){ // feeding cats
                    lastFedCounter = i;
                    if (checkBoxes[i].isSelected() && (cats[i].eat(plate))) {
                        labelsCatFullness[i].setText(String.valueOf(cats[i].getFullness()));
                        labelPlate.setText("Plate: " + String.valueOf(plate.info()));
                        //labelInfo.setText("Please add some food: ");
                        //lastFedCounter++;
                        textAddFood.setText("Type integer to add food with next feeding");
                        //return;
                    } else if (checkBoxes[i].isSelected() && !(cats[i].eat(plate))) {
                        labelInfo.setText("Not enough food");
                        return;
                    }
                }
                labelInfo.setText("You fed all selected cats!");
                labelPlate.setText("Plate: " + String.valueOf(plate.info()));
                feedButton.setEnabled(false);
            }
        });
        hungryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i<CAT_NUM; i++){
                    cats[i].makeHungry();
                    labelsCatFullness[i].setText(String.valueOf(cats[i].getFullness()));

                }
                feedButton.setEnabled(true);
                lastFedCounter = 0;
                labelInfo.setText("Please feed cats");
            }
        });
        getContentPane().add(panel);
        setVisible(true);
    }
}

class Plate {
    private int food;
    public Plate(int food) {
        this.food = food;
    }
    public boolean decreaseFood(int n) {
        if (n<=food) {
            food -= n;
            return true;
        } else return false;
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

    public int getAppetite() {
        return appetite;
    }
    public boolean getFullness(){
        return fullness;    }

    public void makeHungry(){
        this.fullness = false;
    }
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
            return true;
        } else {
            return false;
        }
    }
}