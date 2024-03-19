import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Operations {
    HashMap<String, String> names = new HashMap<String, String>();
    HashMap<String, String> categories = new HashMap<String, String>();
    HashMap<String, String> dates = new HashMap<String, String>();
    HashMap<String, String> amounts = new HashMap<String, String>();
    ArrayList<String> codes = new ArrayList<>();

    private boolean VerifyCode(String code)
    {
        return codes.contains(code);
    }

    void InsertItem(String name, String category, String date, String amount, String code)
    {
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
        System.out.print("What you wanna Update?");
        System.out.print("\n1)Name\n2)Category\n3)Amount\n4)Date\n> ");
        int ch = sc.nextInt();
        System.out.println("called");
        System.out.print("Enter code: ");
        String code = sc.nextLine();
        if (VerifyCode(code))
        {
            System.out.println("Invalid code: "+code);
        }else {
            System.out.println("Enter your update choice!");
            switch (ch)
            {
                case 1:
                    System.out.print("Enter new name: ");
                    String new_name = sc.nextLine();
                    String old_name = String.valueOf(names.get(code));
                    names.replace(old_name, new_name,code);
                    System.out.println("name updated!");
                    break;
                case 2:
                    System.out.print("Enter new category: ");
                    String new_cat = sc.nextLine();
                    String old_cat = String.valueOf(categories.get(code));
                    names.replace(old_cat, new_cat,code);
                    System.out.println("category updated!");
                    break;
                case 3:
                    System.out.print("Enter new amount: ");
                    String new_amount = sc.nextLine();
                    String old_amount = String.valueOf(amounts.get(code));
                    names.replace(old_amount, new_amount,code);
                    System.out.println("amount updated!");
                    break;
                case 4:
                    System.out.print("Enter new date: ");
                    String new_date = sc.nextLine();
                    String old_date = String.valueOf(dates.get(code));
                    names.replace(old_date, new_date,code);
                    System.out.println("date updated!");
                    break;
                default:
                    break;
            }
        }
    }
    void Display()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter code: ");

        String code = sc.nextLine();
        if(VerifyCode(code))
        {
            System.out.println("code not found!");
        }else {
            String name = names.get(code);
            System.out.println("name: "+name);
        }

    }
}
