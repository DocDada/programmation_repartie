
import java.lang.Thread;

public class Consumer extends Thread
{
    private Bal bal;

    public Consumer(Bal bal)
    {
        this.bal = bal;
    }

    public void run()
    {
        char lettre = 'a';
        try
        {
            while (lettre != 'q')
            {
                lettre = bal.retirer();
                System.out.println("Lettre " + lettre + " lue");
            }
        }
        catch (InterruptedException e) {}
    }
}

