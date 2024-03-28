import java.sql.*;
import java.util.Scanner;

public class DatabaseConnection {
    Scanner scanner = new Scanner(System.in);
    protected static float budget = 0.0f;
    DatabaseConnection(){}
    private static final String url = "jdbc:mysql://localhost:3306/TrackerDataBase", username = "root", password = "niam_admin";

    protected static void setUsernamePassword(String username1,String password1)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            String query = "insert into Credentials(username,password) values (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1,username1);
            statement.setString(2,Authenticator.HashPassword(password1));
            int rowsAffected = statement.executeUpdate();
            if(rowsAffected > 0) System.out.println("Username and Password Added!");
            else System.out.println("Username and Password not Added!!");

        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    protected static boolean isFirstEntry()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            String query = "select count(*) as total from Credentials";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next())
            {
                int row_count = resultSet.getInt("total");
                return row_count > 0;
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return false;
    }
    protected static String getUsername()
    {
        String user = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            String query = "SELECT username FROM Credentials";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
               user = resultSet.getString("username");
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return user;
    }
    protected static String getPassword(String username_)
    {
        String pass = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            String query = "SELECT password FROM Credentials where username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,username_);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next())
            {
               pass = resultSet.getString("password");
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return pass;
    }

    protected static float getBudget()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            String query = "SELECT * FROM Info ORDER BY budget DESC LIMIT 1;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                budget = resultSet.getInt("budget");
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return budget;
    }
    public void InsertConnection(int code, String name, String amount, String date, String category) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "insert into Info(code, name, amount, date, category,budget) values (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,code);
            statement.setString(2,name);
            statement.setString(3,amount);
            statement.setString(4,date);
            statement.setString(5,category);
            statement.setFloat(6,budget);

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
    public void DisplayAllConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "select * from Info";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                System.out.println("Name: "+resultSet.getString("name"));
                System.out.println("Amount: "+resultSet.getString("amount"));
                System.out.println("Category: "+resultSet.getString("category"));
                System.out.println("Date: "+resultSet.getString("date"));
                System.out.println("Code: "+resultSet.getInt("code"));
                System.out.println("||-------------------------------------------------------------||");
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
