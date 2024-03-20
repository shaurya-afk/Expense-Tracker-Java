import java.util.Scanner;

class Banner
{
    Banner()
    {
        String banner = "Expense Tracker made by Shaurya Sharma";
        int bannerLength = banner.length();

        // Print top border
        System.out.print("+");
        for (int i = 0; i < bannerLength + 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");

        // Print banner content
        System.out.println("| " + banner + " |");

        // Print bottom border
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
        int ch;
        System.out.println("+" + "-".repeat(36) + "+");
        System.out.println("|        Select an option            |");
        System.out.println("+" + "-".repeat(36) + "+");
        System.out.println("| 1) Insert Expense                  |");
        System.out.println("| 2) Update Expense                  |");
        System.out.println("| 3) Display Expense                 |");
        System.out.println("| 4) Delete Expense                  |");
        System.out.println("| 5) Quit                            |");
        System.out.println("+" + "-".repeat(36) + "+");
        System.out.print("> ");
        Scanner sc = new Scanner(System.in);
        ch = sc.nextInt();

        return ch;
    }
    static void mainMenuDriver()
    {
        Operations ops = new Operations();
        Scanner sc = new Scanner(System.in);

        Banner banner = new Banner();

        int ch = call();
        while (ch != 9)
        {
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
                    case 7:
                        ops.DEFAULT_DEMO();
                        ch = call();
                        break;
                    case 5:
                        System.out.println("+" + "-".repeat(45) + "+");
                        System.out.println("|  Bye! See You Later.  |");
                        System.out.println("+" + "-".repeat(45) + "+");
                        ch = 9;
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
