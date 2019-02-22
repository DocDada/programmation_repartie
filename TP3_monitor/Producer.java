import java.lang.Thread;
import java.util.Scanner;

public class Producer extends Thread
{
    private Bal bal;

    public Producer(Bal bal)
    {
        this.bal = bal;
    }

    public void run()
    {
        //String lettre = "MaLettre";

        /*System.out.println("Ecrivez votre lettre :");
        Scanner scanner = new Scanner(System.in);
        String lettre = scanner.nextLine();
        this.bal.DEPOSER(lettre);*/

        boolean quit = false;
        Scanner scanner = new Scanner(System.in);

        while (!quit)
        {
            quit = this.bal.DEPOSER(scanner.next().charAt(0));
        }

    }
}

