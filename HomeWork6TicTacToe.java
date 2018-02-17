/**
 * Java. Level 1. Lesson 6. Homework Task ***.
 * Rewriting TicTacToe code from lesson 4 by OOP with polymorphism
 *
 * @author Roman Stepanyuk
 * @version dated Feb 16, 2018
 * @link https://github.com/Tzill
 */
import java.util.Random;
import java.util.Scanner;

class HomeWork6TicTacToe {
    public static void main(String[] args) {
        TableGame[] ttt = new TicTacToe[3];
        for (int i = 0; i<3; i++){
            ttt[i] = new TicTacToe(4+i,3+i);
            ttt[i].play();
        }
        int winsNumPlayer1 =0, winsNumPlayer2 = 0;
        for (int i = 0; i<3; i++){
           if(ttt[i].getWinner().equals("Human wins")) winsNumPlayer1++;
           else if(ttt[i].getWinner().equals("AI wins")) winsNumPlayer2++;
        }
        System.out.println("Human wins " + winsNumPlayer1 + " times");
        System.out.println("AI wins " + winsNumPlayer2 + " times");
    }
}

interface Playable{
    void play();
    void printMap();
    String getWinner();
}

abstract class TableGame implements Playable {
    protected Scanner sc;
    protected Random rand;
    final String MSG_FOR_HUMAN = "Enter X and Y (1..3):";
    final String MSG_YOU_WON = "YOU WON!";
    final String MSG_AI_WON = "AI WON!";
    final String MSG_DRAW = "Sorry, DRAW!";
    //final String MSG_GAME_OVER = "GAME OVER.";
    protected char[][] map;
    protected int tableSize;
    protected int winSize;
    protected String winPlayer;
}

class TicTacToe extends TableGame{
    final char DOT_X = 'x';     // sign of human
    final char DOT_O = 'o';     // sign of AI
    final char DOT_EMPTY = '.'; // sign of empty cell
    private char winPlayerChar;

    TicTacToe(int tSize, int wSize) {
        tableSize = tSize;
        winSize = wSize;
        map = new char[tableSize][tableSize];
        sc = new Scanner(System.in);
        rand = new Random();
        winPlayerChar = '_';
    }

    @Override
    public void play(){
        initMap();
        while (true) {
            printMap();
            turnHuman(DOT_X);
            if (checkWin(DOT_X)) {
                System.out.println(MSG_YOU_WON);
                break;
            }
            if (isMapFull()) {
                System.out.println(MSG_DRAW);
                break;
            }
            turnAI(DOT_O, DOT_X);
            //printMap();
            if (checkWin(DOT_O)) {
                System.out.println(MSG_AI_WON);
                break;
            }
            if (isMapFull()) {                      // this code doesn't matter
                System.out.println(MSG_DRAW);       // because a human always
                break;                              //  makes a move last in 3x3
            }
        }
        //System.out.println(MSG_GAME_OVER);
        printMap();
    }

    void initMap() {                                // init game's field
        for (int i = 0; i < tableSize; i++)
            for (int j = 0; j < tableSize; j++)
                map[i][j] = DOT_EMPTY;
    }

    @Override
    public void printMap() {                               // output game's field
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    void turnHuman(char dot) {                      // human action
        int x, y;
        do {
            System.out.println(MSG_FOR_HUMAN);
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = dot;
    }

    void turnAI(char dot, char enemyDot) {          // AI action
        int x, y;
        for (x = 0; x < tableSize; x++)                  // simple blocking
            for (y = 0; y < tableSize; y++)
                if (isCellValid(x, y)) {            // if cell empty
                    map[y][x] = enemyDot;           // try to be like enemy
                    if (checkWin(enemyDot)) {       // if win
                        map[y][x] = dot;            // block
                        return;                     // and exit
                    }
                    map[y][x] = DOT_EMPTY;          // restore cell
                }
        do {
            x = rand.nextInt(tableSize);
            y = rand.nextInt(tableSize);
        } while (!isCellValid(x, y));
        map[y][x] = dot;
    }

    boolean checkWin(char dot) {                    // check win condition
        for (int y = 0; y < tableSize; y++)
            for (int x = 0; x < tableSize; x++)
                for (int dy = -1; dy < 2; dy++)
                    for (int dx = -1; dx < 2; dx++)
                        if (checkLine(x, y, dx, dy, dot) == winSize) {
                            winPlayerChar = dot;
                            return true;
                        }
        return false;
    }

    int checkLine(int x, int y, int dx, int dy, char dot) {
        int count = 0;                              // check line for win
        if (dx == 0 && dy == 0)
            return 0;
        for (int i = 0, xi = x, yi = y;
             i < winSize; i++, xi += dx, yi += dy)
            if (xi >= 0 && yi >= 0 && xi < tableSize &&
                    yi < tableSize && map[yi][xi] == dot)
                count++;
        return count;
    }

    boolean isMapFull() {                           // check field filling
        for (int i = 0; i < tableSize; i++)
            for (int j = 0; j < tableSize; j++)
                if (map[i][j] == DOT_EMPTY)
                    return false;
        return true;
    }

    boolean isCellValid(int x, int y) {             // check cell
        if (x < 0 || y < 0 || x >= tableSize || y >= tableSize)
            return false;
        return map[y][x] == DOT_EMPTY;
    }

    @Override
    public String getWinner(){
        switch (winPlayerChar) {
            case 'x':
                return "Human wins";
            case 'o':
                return "AI wins";
            default :
                return "Winner is not defined";
        }
    }
}