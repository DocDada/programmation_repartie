
public class Bal
{
    private String letter;
    private boolean empty = true;

    public Bal()
    {
    }

    public synchronized void DEPOSER(String letter)
    {
        while (!empty)
        {
        try
        {
            wait();
        }
        catch (InterruptedException e) {;}
        }

        //System.out.println("LETTRE DEPOSE");
        this.letter = letter;

        this.empty = false;
        notifyAll();

        return;
    }

    public synchronized String RETIRER()
    {
        while (empty)
        {
        try
        {
            wait();
        }
        catch (InterruptedException e) {;}
        }

        //System.out.println("LETTRE LUE");
        String letter = this.letter;
        this.letter = new String();
        this.empty = true;
        notifyAll();

        return letter;
    }

}

