
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
        char quit = 'a';
        /*String lettre = this.bal.RETIRER();
        System.out.println("LECTURE : " + lettre);*/
        while (quit != 'Q')
        {
            quit = this.bal.RETIRER_char();
            System.out.println("LECTURE : " + quit);
        }
    }
}

