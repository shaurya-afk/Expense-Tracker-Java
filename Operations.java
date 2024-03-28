import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class CodeException extends Exception
{
    CodeException()
    {
        super("code should be unique");
    }

}

public class Operations {
    HashMap<Integer, String> names = new HashMap<>();
    HashMap<Integer, String> categories = new HashMap<>();
    HashMap<Integer, String> dates = new HashMap<>();
    HashMap<Integer, String> amounts = new HashMap<>();
    ArrayList<Integer> codes = new ArrayList<>();

    public void DEFAULT_DEMO()
    {
        DatabaseConnection dbCon = new DatabaseConnection();

        codes.add(999);
        names.put(999,"books");
        categories.put(999,"self");
        dates.put(999,"12-02-23");
        amounts.put(999,"120");

        dbCon.InsertConnection(999,"books","120","12-02-23","self");
        codes.add(998);
        names.put(998,"party");
        categories.put(998,"enjoy");
        dates.put(998,"22-04-23");
        amounts.put(998,"2500");
        dbCon.InsertConnection(998,"party","2500","22-04-23","party");

        codes.add(997);
        names.put(997,"books");
        categories.put(997,"self");
        dates.put(997,"12-02-23");
        amounts.put(997,"120");
        dbCon.InsertConnection(997,"books","120","12-02-23","self");

        codes.add(996);
        names.put(996,"party");
        categories.put(996,"enjoy");
        dates.put(996,"22-04-23");
        amounts.put(996,"2500");
        dbCon.InsertConnection(996,"books","120","12-02-23","self");

        System.out.println("+" + "-".repeat(45) + "+");
        System.out.println("|  Default values have been set!  |");
        System.out.println("|  Codes are: [999,998,997,996]  |");
        System.out.println("+" + "-".repeat(45) + "+");

    }

    private boolean VerifyCode(Integer code)
    {
        return !codes.contains(code);
    }

    protected int SET_CODE(int code)
    {
        codes.add(code);
        return code;
    }
    protected void SET_CODE()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter code: ");
        Integer code = scanner.nextInt();
        codes.add(code);
    }

    DatabaseConnection dBCon = new DatabaseConnection();

    void InsertItem(String name, String category, String date, String amount, Integer code) {
        try{
            codes.add(code);
            names.put(code,name);
            categories.put(code,category);
            amounts.put(code,amount);
            dates.put(code,date);

            System.out.println("+" + "-".repeat(30) + "+");
            System.out.println("| Name: " + names.get(code) + " ".repeat(23 - names.get(code).length()) + "|");
            System.out.println("| Category: " + categories.get(code) + " ".repeat(18 - categories.get(code).length()) + "|");
            System.out.println("| Amount: " + amounts.get(code) + " ".repeat(20 - amounts.get(code).length()) + "|");
            System.out.println("| Dates: " + dates.get(code) + " ".repeat(21 - dates.get(code).length()) + "|");
            System.out.println("+" + "-".repeat(30) + "+");
        }catch (Exception e)
        {
            System.out.println(e.getMessage()+"Invalid Entries");
        }
        DatabaseConnection.budget -= Float.parseFloat(amount);
        System.out.println("+" + "-".repeat(32) + "+");
        System.out.println("|         Expense Added!          |");
        System.out.println("+" + "-".repeat(32) + "+");
        System.out.println("| Your current budget is " + DatabaseConnection.budget + " |");
        System.out.println("+" + "-".repeat(32) + "+");

        dBCon.InsertConnection(code,name,amount,date,category);
    }
    void UpdateItem()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        int ch = sc.nextInt();

        System.out.print("Enter the code: ");
        Integer code = sc.nextInt();

        if (VerifyCode(code))
        {
            System.out.println("Invalid code: "+code);
        }else if(codes.contains(code))
        {
            System.out.println(code+" is already present!");
        }
        else
        {
            System.out.println("Enter your update choice!");
            switch (ch)
            {
                case 1:
                    System.out.print("Enter new name: ");
                    String new_name = sc.next();

                    names.put(code,new_name);

                    System.out.println("name updated!");
                    break;
                case 2:
                    System.out.print("Enter new category: ");
                    String new_cat = sc.next();

                    categories.put(code,new_cat);

                    System.out.println("category updated!");
                    break;
                case 3:
                    System.out.print("Enter new amount: ");
                    String new_amount = sc.next();

                    amounts.put(code,new_amount);

                    System.out.println("amount updated!");
                    break;
                case 4:
                    System.out.print("Enter new date: ");
                    String new_date = sc.next();

                    dates.put(code,new_date);

                    System.out.println("date updated!");
                    break;
                default:
                    break;
            }
        }
    }
    void Display() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter code: ");

        Integer code = sc.nextInt();
        if(VerifyCode(code))
        {
            System.out.println("code not found!");
        }else {
            System.out.println("+" + "-".repeat(30) + "+");
            System.out.println("| Name: " + names.get(code) + " ".repeat(23 - names.get(code).length()) + "|");
            System.out.println("| Category: " + categories.get(code) + " ".repeat(18 - categories.get(code).length()) + "|");
            System.out.println("| Amount: " + amounts.get(code) + " ".repeat(20 - amounts.get(code).length()) + "|");
            System.out.println("| Dates: " + dates.get(code) + " ".repeat(21 - dates.get(code).length()) + "|");
            System.out.println("+" + "-".repeat(30) + "+");
        }
    }
    void Delete()
    {
        System.out.println("+" + "-".repeat(45) + "+");
        System.out.println("|  This is Danger Ilaaka!!  |");
        System.out.println("+" + "-".repeat(45) + "+");


        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the code: ");
        Integer code = sc.nextInt();

        if(VerifyCode(code))
        {
            System.out.println("code not found!");
        }else {
            System.out.println("+" + "-".repeat(45) + "+");
            System.out.println("|  This will delete your entire selected expense  |");
            System.out.println("+" + "-".repeat(45) + "+");

            System.out.print("[Y/n]> ");
            String ch = sc.next();

            if (ch.equalsIgnoreCase("y"))
            {
                names.remove(code);
                categories.remove(code);
                dates.remove(code);
                amounts.remove(code);
                codes.remove(code);

                System.out.println("+" + "-".repeat(45) + "+");
                System.out.println("|  Expense Deleted Successfully!  |");
                System.out.println("+" + "-".repeat(45) + "+");
            }
        }
    }
}
