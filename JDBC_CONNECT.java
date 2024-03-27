import java.sql.*;

public class JDBC_CONNECT {
    private static final String url = "jdbc:mysql://localhost:3306/TrackerDataBase", username = "root", password = "niam_admin";
    public static void main(String[] args) {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        try
        {
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();

//            String query = "select * from Info";


            String query = String.format("insert into Info(code, name, amount, date, category) values (%o,'%s','%s','%s','%s')",254,"hello","120.98","12-02-21","greet");
            int rowsAffected = statement.executeUpdate(query);
            if (rowsAffected > 0)
            {
                System.out.println("Data inserted");
            }else {
                System.out.println("No data inserted");
            }

//            ResultSet resultSet = statement.executeQuery(query);
//            while (resultSet.next())
//            {
//                int code = resultSet.getInt("code");
//                String name = resultSet.getString("name");
//                String amount = resultSet.getString("amount");
//                String date = resultSet.getString("date");
//                String category = resultSet.getString("category");
//
//                System.out.println("code: "+code+"\nname: "+name+"\namount: "+amount+"\ndate: "+date+"\ncategory: "+category);
//                System.out.println("\n");
//            }

        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
