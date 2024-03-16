import java.util.Scanner;

class MainMenuNaive
{
    static int ch = -1;
    static String name;
    static String category;
    static String date;
    static String amount;

    static Operations ops = new Operations();

    MainMenuNaive(){}

    private static void call()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your choice: ");
        ch = sc.nextInt();

        System.out.print("Enter expense name: ");
        name = sc.nextLine();

        System.out.print("Enter expense category: ");
        category = sc.nextLine();

        System.out.print("Enter expense date: ");
        date = sc.nextLine();

        System.out.print("Enter expense amount: ");
        amount = sc.nextLine();
    }

    static void mainMenuDriver()
    {
        switch (ch)
        {
            case 1:
                call();
                ops.InsertItem(name,category,date,amount);
                break;
            case 2:
                call();
                System.out.println("display would work");
                break;
            default:
                break;
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
