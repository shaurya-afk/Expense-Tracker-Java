import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Operations {

    HashMap<String, Integer> names = new HashMap<String, Integer>();
    HashMap<String, Integer> categories = new HashMap<String, Integer>();
    HashMap<String, Integer> dates = new HashMap<String, Integer>();
    HashMap<String, Integer> amounts = new HashMap<String, Integer>();
    ArrayList<Integer> codes = new ArrayList<>();

    void InsertItem(String name, String category, String date, String amount, int code)
    {
//        Database db = new Database(amount,category,date,name);

        try{
            names.put(name,code);
            categories.put(category,code);
            amounts.put(amount,code);
            dates.put(date,code);
            codes.add(code);
        }catch (Exception e)
        {
            System.out.println(e.getMessage()+"Invalid Entries");
        }
        Database.budget -= Float.parseFloat(amount);
        System.out.println("Expense Added! \n Your current budget is "+Database.budget);

    }

    void UpdateItem()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("What you wanna Update?");
        System.out.println("1)Name\n2)Category3)Amount\n4)Date");
        int ch = sc.nextInt();
        System.out.println("Enter code: ");
        int code = sc.nextInt();
        if (codes.contains(code))
        {
            System.out.println("Invalid code: "+code);
        }else {
            switch (ch)
            {
                case 1:
                    System.out.println("Enter new name: ");
                    String new_name = sc.nextLine();
                    String old_name = String.valueOf(names.get(code));
                    names.replace(old_name, Integer.valueOf(new_name),code);
                    break;
//                    cont. from here...
            }
        }
    }
}
