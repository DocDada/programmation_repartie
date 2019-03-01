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
        char lettre = 'a';
        Scanner input = new Scanner(System.in);
        try
        {
            while (lettre != 'q')
            {
                lettre = input.next().charAt(0);
                bal.deposer(lettre);
                System.out.println("Lettre " + lettre + " déposé");
            }
        }
        catch (InterruptedException e) {}

    }
}

