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
        //boolean b;
        try
        {
            for (char a = 'a'; a!='z';a++)
            {
                bal.deposer(a);
                System.out.println("Lettre " + a + " déposé");
                /*if (b)
                    System.out.println("Lettre " + a + " déposé");
                else
                    System.out.println("Boîte à lettre pleine");*/
            }
        }
        catch (InterruptedException e) {}

    }
}

