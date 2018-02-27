public class JavaCore1Hw2 {
    public static void main(String[] args) {
        String[][] array1 = {{"1","1","1","1"}, {"2","2","2","2"}, {"3","3","3","3"}, {"4","4","4","4"}};
        String[][] array2 = {{"1","1","1","1"}, {"2","2","2","2"}, {"3","3","3"}, {"4","4","4","4"}};
        String[][] array3 = {{"1","1","1","1"}, {"2","2","2","2"}, {"3","3","3","3"}};
        String[][] array4 = {{"1","1","1","1"}, {"2","2","puu","2"}, {"3","3","3","3"}, {"4","4","4","4"}};
        String[][][] arrays = {array1, array2, array3, array4};

        // Task 3
        for (String[][] array : arrays) {
            try{
                int sumArray = JavaCore1Hw2.sum2DArray(array);
                System.out.println("Sum array: " + sumArray);
            } catch (MyArraySizeException ae) {
                //ae.printStackTrace();
                String[][] defaultArray = {{"0","0","0","0"}, {"0","0","0","0"}, {"0","0","0","0"}, {"0","0","0","0"}};
                int sumArray = JavaCore1Hw2.sum2DArray(defaultArray);
                System.out.println("Error. Array is not 4x4. Used default array." + " It's Sum: " + sumArray);
            } catch (MyArrayDataException de) {
               System.out.println("Not a number. Error in: " + (de.getRow() + 1) + "-row " + (de.getCol() + 1) + "-col");
            //} catch (RuntimeException re) {
            //    System.out.println(re.getMessage());
            }
        }
    }

    public static int sum2DArray(String array[][]) throws MyArrayDataException, MyArraySizeException {
        // Task 1
        if (array.length != 4) throw new MyArraySizeException();
        for(String[] a : array){
            if (a.length !=4) throw new MyArraySizeException();
        }
        // Task 2
        int sum = 0;
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                try {
                    sum += Integer.valueOf(array[i][j]);
                } catch (NumberFormatException ne) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }
}


class MyArraySizeException extends RuntimeException {
}

class MyArrayDataException extends RuntimeException {
    private int row;
    private int col;

    public  MyArrayDataException(int row, int col){
        super("RuntimeException. Error in: " + row + " row " + col + " col");
        this.row = row;
        this.col = col;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
}