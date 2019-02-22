import java.lang.Thread;

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

        System.out.println("Ecrivez votre lettre :");
        Scanner scanner = new Scanner(System.in);
        String lettre = scanner.nextLine();

        this.bal.DEPOSER(lettre);
    }
}

