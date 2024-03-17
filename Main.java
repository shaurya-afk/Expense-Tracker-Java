import java.util.Scanner;

class MainMenuNaive
{
    static private int call()
    {
        int ch;
        System.out.println("1) Insert Expense\n2)Update Expense\n3)Delete Expense\n4)Display Expense\n5)Quit");
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
            switch (ch){
                case 1:
                    System.out.println("Expense name: ");
                    String name = sc.nextLine();

                    System.out.println("Expense Amount");
                    String amount = sc.nextLine();

                    System.out.println("Expense Category: ");
                    String cat = sc.nextLine();

                    System.out.println("Expense Date: ");
                    String date = sc.nextLine();

                    System.out.println("Expense Code(should be unique for each expense entered): ");
                    int code = sc.nextInt();

                    ops.InsertItem(name, cat, date, amount, code);

                    ch = call();
                default:
                    break;
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
