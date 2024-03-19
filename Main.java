import java.util.Scanner;

class MainMenuNaive
{
    static private int call()
    {
        int ch;
        System.out.print("1) Insert Expense\n2)Update Expense\n3)Display Expense\n4)Delete Expense\n5)Quit\n> ");
        Scanner sc = new Scanner(System.in);
        ch = sc.nextInt();

        return ch;
    }
    static void mainMenuDriver()
    {
        Operations ops = new Operations();
        Scanner sc = new Scanner(System.in);
        int ch = call();
        while (ch != 5)
        {
            try{
                switch (ch) {
                    case 1:
                        System.out.print("Expense name: ");
                        String name = sc.nextLine();

                        System.out.print("Expense Amount: ");
                        String amount = sc.nextLine();

                        System.out.print("Expense Category: ");
                        String cat = sc.nextLine();

                        System.out.print("Expense Date: ");
                        String date = sc.nextLine();

                        System.out.print("Expense Code(should be unique for each expense entered): ");
                        String code = sc.nextLine();

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
                    default:
                        break;
                }
            }catch (Exception e)
            {
                System.out.println("invalid entry" + e.getMessage());
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
