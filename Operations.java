import java.util.ArrayList;

public class Operations {
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<String> categoryList = new ArrayList<>();
    ArrayList<String> dateList = new ArrayList<>();
    ArrayList<String> amountList = new ArrayList<>();
    void InsertItem(String name, String category, String date, String amount)
    {
        Database db = new Database(amount,category,date,name);

        try{
            nameList.add(name);
            categoryList.add(category);
            dateList.add(date);
            amountList.add(amount);
        }catch (Exception e)
        {
            System.out.println(e.getMessage()+"\nInvalid entries");
        }

        Database.budget -= Integer.parseInt(amount);
        System.out.println("Expense Added! \n Your current budget is "+Database.budget);
    }
}
