//        1. Сформировать таблицу товаров (id, prodid, title, cost) запросом из Java-приложения.
//        id – порядковый номер записи, первичный ключ;
//        prodid – уникальный номер товара;
//        title – название товара;
//        cost – стоимость.
//
//        2. При запуске приложения очистить таблицу и заполнить 10000 товаров вида:
//        id_товара 1 товар1 10
//        id_товара 2 товар2 20
//        id_товара 3 товар3 30
//        …
//        id_товара 10000 товар10000 100000
//
//        3. Написать консольное приложение, которое позволяет узнать цену товара по его имени,
//                либо вывести сообщение «Такого товара нет», если товар отсутствует в базе.
//                Консольная команда: "/цена товар545".
//        4. Добавить возможность изменения цены товара (указываем имя товара и новую цену).
//                Консольная команда: "/сменитьцену товар10 10000".
//        5. Вывести товары в заданном ценовом диапазоне.
//                Консольная команда: "/товарыпоцене 100 600".


import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Connection connection;
    private static Statement statement;
    private static Scanner scanner;
    private static boolean flag;

    public static void main(String[] args){
        scanner = new Scanner(System.in);
        flag = true;
        try {
            connect();
            clearTable();
            fillTable();
            while (flag) {
                String userstr = scanner.nextLine();
                if (!userstr.startsWith("/")) continue;
                String[] token = userstr.split("\\s");
                switch (token[0]) {
                    case  "/создатьтаблицу":
                        createTable();
                        break;
                    case  "/заполнитьтаблицу":
                        clearTable();
                        fillTable();
                        break;
                    case  "/цена":                   // "/цена товар545"
//                        String substr = token[1].substring(5, token[1].length()-1);
//                        int index = Integer.getInteger(substr);
//                        if (getPrice(index) != -1)
//                            System.out.println(getPrice(index));
                          int i = getPriceOf(token[1]);
                          if (i != -1)
                              System.out.println(i);
                          else System.out.println("Такого товара нет");
                        break;
                    case  "/сменитьцену":           // "/сменитьцену товар10 10000"
                        try {
                            changePrice(token[1], Integer.parseInt(token[2]));
                        } catch (NumberFormatException e) {
                            System.out.println("Цена введена некорректно");
                        }
                        break;
                    case  "/товарыпоцене":          // "/товарыпоцене 100 600"
                        try {
                            System.out.println(getTitlesByPrices(Integer.parseInt(token[1]), Integer.parseInt(token[2])));
                        } catch (NumberFormatException e) {
                            System.out.println("Цена введена некорректно");
                        }
                        break;
                    case "/end":
                        flag = false;
                        break;
                    default:
                        System.out.println("Команда не опознана");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:jc2dbsqlite.db");
        statement = connection.createStatement();
    }

    public static void disconnect() throws SQLException {
        statement.close();
        connection.close();
    }

    //Task1
    public static void createTable() throws SQLException{
        statement.execute("CREATE TABLE IF NOT EXISTS Goods (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "prodid INTEGER UNIQUE NOT NULL, " +
                "title TEXT UNIQUE NOT NULL, " +
                "cost INTEGER NOT NULL)");
    }

    //Task2
    public static void fillTable() throws SQLException{
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Goods (prodid, title, cost) VALUES (?, ?, ?)");
        for (int i = 1; i<=10000; i++){
            ps.setInt(1, i);
            ps.setString(2, "товар" + i);
            ps.setInt(3, i*10);
            ps.addBatch();
        }
        ps.executeBatch();
    }

    public static void clearTable() throws SQLException{
        statement.execute("DELETE FROM Goods");
    }

    //Task3
    public static int getPriceOf(String productTitle) throws SQLException{
        ResultSet rs = statement.executeQuery("SELECT cost FROM Goods WHERE title = '" + productTitle + "'");
        if (rs.next())
            return rs.getInt(1);
        else return -1;
    }

    //Task4
    public static void changePrice(String productTitle, int newPrice) throws SQLException{
        statement.execute("UPDATE Goods SET cost = " + newPrice + " WHERE title = '" + productTitle + "'");
    }

    //Task5
    public static ArrayList<String> getTitlesByPrices(int lowPrice, int highPrice) throws SQLException{
        ArrayList<String> resultAl = new ArrayList<>();
        ResultSet rs = statement.executeQuery("SELECT title FROM Goods WHERE cost >= " + lowPrice + " AND cost <=" + highPrice);
        while (rs.next())
            resultAl.add(rs.getString(1));
        return resultAl;
    }
}


