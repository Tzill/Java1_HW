/**
 * Java. Level 1. Lesson 4. Homework
 *
 * @author Roman Stepanyuk
 * @version dated Feb 09, 2018
 */
import java.util.Random;
import java.util.Scanner;

class TicTacToeHW {

    final int SIZE = 9;
    final int WINSEQ = 4;
    final char DOT_X = 'x';
    final char DOT_O = 'o';
    final char DOT_EMPTY = '.';
    char[][] map = new char[SIZE][SIZE];
	char[][] mapBuffer = new char[SIZE][SIZE];
    int lastUserTurnX, lastUserTurnY, lastAiTurnX, lastAiTurnY, testX, testY;
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();
    int iBuf = 0;

    public static void main(String[] args) {
        TicTacToeHW ttt = new TicTacToeHW();
        System.out.println("Input '1' for testing: ");
        Scanner s = new Scanner(System.in);
        int ch = s.nextInt();
        if (ch==1) ttt.test();
    }

    TicTacToeHW() {
        initMap(map);
        initMap(mapBuffer);
        generateWinSeq();
        while (true) {
            humanTurn();
            if (checkWin(DOT_X, lastUserTurnX, lastUserTurnY, map)) {
                System.out.println("YOU WON!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Sorry, DRAW!");
                break;
            }
            if(!aiCheckUserMove(lastUserTurnX, lastUserTurnY)){
				// проверить последний ход пользователя и заблокировать его если требуется
				while(!isWinMapFit()){
				    generateWinSeq(); // сгенерировать новую потенциально выигрышную позицию, если предыдущая уже не подходит
				} 
				putNextChar(); // добавить следующий символ в таблицу
			}
				
            printMap();
			
            if (checkWin(DOT_O, lastAiTurnX, lastAiTurnY, map)) {
                System.out.println("AI WON!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Sorry, DRAW!");
                break;
            }
        }
        System.out.println("GAME OVER.");
        printMap();
    }

    void initMap(char[][] m) {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                m[i][j] = DOT_EMPTY;
    }

    void printMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    boolean isMapFull() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (map[i][j] == DOT_EMPTY)
                    return false;
        return true;
    }

    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE)
            return false;
        return map[y][x] == DOT_EMPTY;
    }

    void humanTurn() {
        int x, y;
        do {
            System.out.printf("Enter X and Y (1..%d):", SIZE);
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
        lastUserTurnX = x;
        lastUserTurnY = y;
    }

    /*void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        map[y][x] = DOT_O;
        lastAiTurnX = x;
        lastAiTurnY = y;
    }*/

    // блокирование следующего хода пользователя, если последний ход создал опасную ситуацию
	boolean aiCheckUserMove(int lastX, int lastY) {
        for(int i = -1; i<=1; i++){
            for(int j = -1; j<=1; j++){
                if (isCellValid(lastX+j, lastY+i)) {
                    // значит выхода за рамки таблицы не произошло и на этом месте нет чужих ходов
                    map[lastY+i][lastX+j] = DOT_X;
					//printMap();
                    if(checkWin(DOT_X, lastX+j, lastY+i, map)){
                        map[lastY+i][lastX+j] = DOT_O;						
                        return true;
                    }else map[lastY+i][lastX+j] = DOT_EMPTY;
                }
            }
        }
		return false;
	}		

    /*boolean checkWin(char dot) {
        // check horizontals
        if (map[0][0] == dot && map[0][1] == dot && map[0][2] == dot) return true;
        if (map[1][0] == dot && map[1][1] == dot && map[1][2] == dot) return true;
        if (map[2][0] == dot && map[2][1] == dot && map[2][2] == dot) return true;
        // check verticals
        if (map[0][0] == dot && map[1][0] == dot && map[2][0] == dot) return true;
        if (map[0][1] == dot && map[1][1] == dot && map[2][1] == dot) return true;
        if (map[0][2] == dot && map[1][2] == dot && map[2][2] == dot) return true;
        // check diagonals
        if (map[0][0] == dot && map[1][1] == dot && map[2][2] == dot) return true;
        if (map[2][0] == dot && map[1][1] == dot && map[0][2] == dot) return true;
        return false;
    }*/

	// проверка таблицы на наличие выигрышной комбинации
    boolean checkWin(char dot, int lastTurnX, int lastTurnY, char[][] m){
        int numSeq = 0;
        int x = lastTurnX, y = lastTurnY;
        // check vertical
        for (int i = 0, j = lastTurnX; i < SIZE-1; i++) {
            if(m[i][j] == dot && m[i+1][j] == dot) numSeq++;
            else numSeq=0;
            if (numSeq == WINSEQ-1) return true;
            if(i==SIZE-2) numSeq=0;
        }
        // check horizontal
        for (int i = lastTurnY, j = 0; j < SIZE-1; j++) {
            if(m[i][j] == dot && m[i][j+1] == dot) numSeq++;
            else numSeq=0;
            if (numSeq == WINSEQ-1) return true;
            if(j==SIZE-2) numSeq=0;
        }
        // check falling diagonal
        x = lastTurnX; y = lastTurnY;
        while(x!=0 && y!=0) {
            x--; y--;
        }
        for (int i = y, j = x; y>x?i<SIZE-1:j<SIZE-1; i++, j++) {
            if(m[i][j] == dot && m[i+1][j+1] == dot) numSeq++;
            else numSeq=0;
            if (numSeq == WINSEQ-1) return true;
            if(i==SIZE-2 || j==SIZE-2) numSeq=0;
        }
        // check rising diagonal
        x = lastTurnX; y = lastTurnY;
        while(x!=0 && y!=SIZE-1) {
            x--; y++;
        }
        for (int i = y, j = x; x==0?i>0:j<SIZE-1; i--, j++) {
            if(m[i][j] == dot && m[i-1][j+1] == dot) numSeq++;
            else numSeq=0;
            if (numSeq == WINSEQ-1) return true;
            if(j==SIZE-2 || i==1) numSeq=0;
        }
        return false;
    }
    
	// сгенерировать рандомную последовательность в таблице mapBuffer
    void generateWinSeq(){
        initMap(mapBuffer);
        int x = 0, y = 0;
        int choice = rand.nextInt(4);
        switch (choice) {
            case 0: // horizontal case
                y = rand.nextInt(SIZE);
                x = rand.nextInt(SIZE - WINSEQ + 1);
                //System.out.println("horizontal " + y + " " + x);
                for (int n = 0; n < WINSEQ; n++) mapBuffer[y][x+n] = DOT_X;
                break;
            case 1: // vertical
                x = rand.nextInt(SIZE);
                y = rand.nextInt(SIZE - WINSEQ + 1);
                //System.out.println("vertical " + y + " "  + x);
                for (int n = 0; n < WINSEQ; n++) mapBuffer[y+n][x] = DOT_X;
                break;
            case 2: // falling
                x = rand.nextInt(SIZE - WINSEQ + 1);
                y = rand.nextInt(SIZE - WINSEQ + 1);
                //System.out.println("falling " + y + " "  + x);
                for (int n = 0; n < WINSEQ; n++) {
                    mapBuffer[y + n][x + n] = DOT_X;
                }
                break;
            case 3: // rising
                x = SIZE - rand.nextInt(SIZE - WINSEQ + 1) - 1;
                y = rand.nextInt(SIZE - WINSEQ + 1);
                //System.out.println("rising " + y + " " + x);
                for (int n = 0; n < WINSEQ; n++) {
                    mapBuffer[y + n][x - n] = DOT_X;
                }
                break;
        }
        testX = x; testY = y;
    }
	
	// возвращает true если последовательность в mapBuffer подходит для воплощения в текущей ситуации
	boolean isWinMapFit(){
		for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
				if(mapBuffer[i][j]==DOT_X && map[i][j]==DOT_X) return false;
			    return true;
	}
	
	// перенести следующий символ из потенциальной выйгрышной последовательности в таблицу игры
	void putNextChar() {
        boolean ext = false;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (mapBuffer[i][j] == DOT_X && map[i][j] == DOT_EMPTY) {
                    map[i][j] = DOT_O;
                    lastAiTurnX=j; lastAiTurnY=i;
                    ext = true;
                    break;
                }
            }
            if(ext){
                iBuf = i;
                break;
            }
        }
	}

    void test(){
        System.out.print("Number of test");
        for(int counter=0; counter<100; counter++) {
            if(counter%25==0) System.out.println();
            System.out.print(counter);
            generateWinSeq();
            if (checkWin(DOT_X, testX, testY, mapBuffer)) {
                System.out.print("+ ");
            }
            else {
                System.out.print("- \n");
                printMap();
                //break;
            }
        }
        System.out.println("\nTest is over");
    }
}