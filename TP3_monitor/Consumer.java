
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
        String lettre = this.bal.RETIRER();
        System.out.println("LECTURE : " + lettre);
    }
}

