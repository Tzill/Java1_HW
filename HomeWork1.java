public class HomeWork1(
    public static void main(String[] args){
        // Задание 2
        // целочисленные
        byte bt=127;
        short sht=32767;
        int nt=3000;
        long lng=40000L;
        // дробные
        float flt=23.5f;
        double dbl=45.5532;
        // символьный
        char chr='D';
        // булев
        boolean bl=true;
        
        //3
        System.out.println(calc(4.4,5.5,4.9,3.7);
        //4
        System.out.println(compareSum(4, 9);
        //5
        printPosOrNeg(7);
        //6
        isNegative(-9);
        //7
        printHelloName("Борис");
        //8
        isLeapYear(200);
        isLeapYear(204);
        isLeapYear(400);
        isLeapYear(404);
        
    
    // Задание 3
    static double calc(double a, double b, double c, double d){
        return a*(b+(c/d));
    }
    
    // Задание 4
    static boolean compareSum(int a, int b){
        if ((a+b)>=10 && (a+b)<=20) return true;
        else return false;
    }
    
    // Задание 5
    static void printPosOrNeg(int a){
        if (a>=0) System.out.println("положительное");
        else System.out.println("отрицательное");
    }
    
    // Задание 6
    static boolean isNegative(int a){
        if (a<0) return true;
    }
    
    // Задание 7
    static void printHelloName(String s){
        System.out.println("Привет, " + s + "!");
    }
    
    // Задание 8*
    static void isLeapYear(int year){
        if (year%400=0) System.out.println(year + "- високосный");
        else if ((year%100!=0) && (year%4==0)) System.out.println(year +"- високосный");
        else System.out.println(year + "- не високосный");
    }
    
    
    
    
    
    
    
)