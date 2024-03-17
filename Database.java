import java.sql.*;

public class Database {
    String amount, category, date, name;
    static float budget = 10000;
    Database(){}
    Database(String amount, String category, String date, String name)
    {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.name = name;
    }
}
