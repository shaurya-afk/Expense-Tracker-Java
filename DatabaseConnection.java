import java.sql.*;
import java.util.Scanner;

public class DatabaseConnection {
    String amount, category, date, name;
    Scanner scanner = new Scanner(System.in);
    static float budget = 10000;
    DatabaseConnection(){}
    DatabaseConnection(String amount, String category, String date, String name)
    {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.name = name;
    }
    private static final String url = "jdbc:mysql://localhost:3306/TrackerDataBase", username = "root", password = "niam_admin";
    public void InsertConnection(int code, String name, String amount, String date, String category) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "insert into Info(code, name, amount, date, category) values (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,code);
            statement.setString(2,name);
            statement.setString(3,amount);
            statement.setString(4,date);
            statement.setString(5,category);

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected > 0) System.out.println("Data Inserted!");
            else System.out.println("Data not Inserted!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void DisplayConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.print("Enter the code you wanna pull: ");
            int code = scanner.nextInt();

            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "select * from Info where code = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,code);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                System.out.println("Name: "+resultSet.getString("name"));
                System.out.println("Amount: "+resultSet.getString("amount"));
                System.out.println("Category: "+resultSet.getString("category"));
                System.out.println("Date: "+resultSet.getString("date"));
            }else{
                System.out.println("data not found!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void UpdateConnection()
    {
        System.out.println("+" + "-".repeat(45) + "+");
        System.out.println("|        What you wanna Update?           |");
        System.out.println("+" + "-".repeat(45) + "+");
        String[] options = {"Name", "Amount", "Category", "Date"};
        for (int i = 0; i < options.length; i++) {
            System.out.println(String.format("|  %d) %-10s  |", i + 1, options[i]));
        }
        System.out.println("+" + "-".repeat(45) + "+");
        System.out.print("> ");
        int choice = scanner.nextInt();

        System.out.print("Enter the unique code: ");
        int code = scanner.nextInt();

        int rowsAffected = -1;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            switch (choice)
            {
                case 1:
                    System.out.print("Enter new name: ");
                    String name = scanner.next();
                    String query = "update info set name = ? where code = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,name);
                    preparedStatement.setInt(2,code);

                    rowsAffected = preparedStatement.executeUpdate();
                    if(rowsAffected > 0) System.out.println("Data Updated!");
                    else System.out.println("Data not Updated!");

                    break;
                case 2:
                    System.out.print("Enter new amount: ");
                    String amount = scanner.next();
                    query = "update info set amount = ? where code = ?";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,amount);
                    preparedStatement.setInt(2,code);

                    rowsAffected = preparedStatement.executeUpdate();
                    if(rowsAffected > 0) System.out.println("Data Updated!");
                    else System.out.println("Data not Updated!");

                    break;
                case 3:
                    System.out.print("Enter new category: ");
                    String category = scanner.next();
                    query = "update info set category = ? where code = ?";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,category);
                    preparedStatement.setInt(2,code);

                    rowsAffected = preparedStatement.executeUpdate();
                    if(rowsAffected > 0) System.out.println("Data Updated!");
                    else System.out.println("Data not Updated!");

                    break;
                case 4:
                    System.out.print("Enter new date: ");
                    String date = scanner.next();
                    query = "update info set date = ? where code = ?";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,date);
                    preparedStatement.setInt(2,code);

                    rowsAffected = preparedStatement.executeUpdate();
                    if(rowsAffected > 0) System.out.println("Data Updated!");
                    else System.out.println("Data not Updated!");

                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void DeleteConnection()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.print("Enter the code you wanna delete: ");
            int code = scanner.nextInt();

            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "delete from Info where code = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,code);

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected > 0) System.out.println("Data Deleted!");
            else System.out.println("Data not Deleted!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
