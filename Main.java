import java.util.Scanner;

class Banner
{
    Banner()
    {
        String banner = "Expense Tracker";
        int bannerLength = banner.length();

        System.out.print("+");
        for (int i = 0; i < bannerLength + 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");

        System.out.println("| " + banner + " |");

        System.out.print("+");
        for (int i = 0; i < bannerLength + 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
}

class MainMenuNaive
{
    static private int call()
    {
        System.out.println("+" + "-".repeat(36) + "+");
        System.out.println("|        Select an option            |");
        System.out.println("+" + "-".repeat(36) + "+");
        System.out.println("| 1) Insert Expense                  |");
        System.out.println("| 2) Update Expense                  |");
        System.out.println("| 3) Display Expense                 |");
        System.out.println("| 4) Delete Expense                  |");
        System.out.println("| 5) Display from Database           |");
        System.out.println("| 6) Update from Database            |");
        System.out.println("| 7) Delete from Database            |");
        System.out.println("| 8) Default Demo                    |");
        System.out.println("| 9) Display All Database Expenses   |");
        System.out.println("| 10) Exit                           |");
        System.out.println("+" + "-".repeat(36) + "+");
        System.out.print("> ");
        Scanner sc = new Scanner(System.in);

        return sc.nextInt();
    }
    static void mainMenuDriver()
    {
        Operations ops = new Operations();
        DatabaseConnection dBCon = new DatabaseConnection();
        Scanner sc = new Scanner(System.in);

        if(DatabaseConnection.getBudget() <= 0)
        {
            System.out.print("Enter your budget: ");
            sc.nextInt();
        }

        new Banner();

        int ch = call();
        while (ch <= 10)
        {
            if(DatabaseConnection.budget < 0)
            {
                System.out.println("Your budget is too low!\nCan't add more expenses! "+DatabaseConnection.budget);
                break;
            }
            try{
                switch (ch) {
                    case 1:
                        System.out.print("Expense Code(should be unique for each expense entered): ");
                        Integer code = sc.nextInt();

                        if (ops.codes.contains(code)){
                            throw new CodeException();
                        }

                        System.out.print("Expense name: ");
                        String name = sc.next();

                        System.out.print("Expense Amount: ");
                        String amount = sc.next();

                        System.out.print("Expense Category: ");
                        String cat = sc.next();

                        System.out.print("Expense Date: ");
                        String date = sc.next();

                        ops.InsertItem(name, cat, date, amount, code);

                        ch = call();
                        break;
                    case 2:
                        ops.UpdateItem();
                        ch = call();
                        break;
                    case 3:
                        ops.Display();
                        ch = call();
                        break;
                    case 4:
                        ops.Delete();
                        ch = call();
                        break;
                    case 5:
                        dBCon.DisplayConnection();
                        ch = call();
                        break;
                    case 6:
                        dBCon.UpdateConnection();
                        ch = call();
                        break;
                    case 7:
                        dBCon.DeleteConnection();
                        ch = call();
                        break;
                    case 8:
                        ops.DEFAULT_DEMO();
                        ch = call();
                        break;
                    case 9:
                        dBCon.DisplayAllConnection();
                        ch = call();
                        break;
                    case 10:
                        System.out.println("+" + "-".repeat(45) + "+");
                        System.out.println("|    Bye! See You Later.     |");
                        System.out.println("+" + "-".repeat(45) + "+");
                        ch = 11;
                        break;
                }
            }catch (Exception e)
            {
                System.out.println("+------------------------------------+");
                System.out.println("|          Error: Invalid Entry      |");
                System.out.println("|                                    |");
                System.out.println("| " + e.getMessage() + " |");
                System.out.println("+------------------------------------+");
                ch = call();
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if(!DatabaseConnection.isFirstEntry())
        {
            System.out.print("Enter a username: ");
            String uname = sc.next();
            System.out.print("Enter a password: ");
            String pass = sc.next();

            DatabaseConnection.setUsernamePassword(uname,pass);
            MainMenuNaive.mainMenuDriver();
        }else{
            System.out.print("username: ");
            String uname = sc.nextLine();
            System.out.print("password: ");
            String pwd = sc.nextLine();

            Authenticator authenticator = new Authenticator(uname, pwd);

            try{
                authenticator.CheckCredentials();
            }catch (AuthenticationException aue)
            {
                System.out.println(aue.getMessage());
                System.exit(-69);
            }

        }
    }
}
