import java.util.Arrays;

public class Bal
{
    private int nbLetters = 28;
    private int head = 0;
    private int queue = 0;

    private char letters[];
    private String letter;
    private boolean empty = true;
    private boolean empty_slot[];

    private boolean quit = false;

    public Bal()
    {
        this.letters = new char[nbLetters];
        this.empty_slot = new boolean[nbLetters];
        Arrays.fill(this.empty_slot, Boolean.TRUE);
    }



    public synchronized boolean DEPOSER(char letter)
    {
        if (quit) return true;

        while (!empty_slot[head])
        {
            try
            {
                wait();
            }
            catch (InterruptedException e) {;}
        }

        System.out.println("LETTRE DEPOSE");
        this.letters[head] = letter;
        this.head = (head + 1)%nbLetters;

        this.empty_slot[head - 1] = false;
        notifyAll();

        if (letter == 'q') quit = true;

        return quit;
    }



    public synchronized char RETIRER_char()
    {
        if (quit) return 'Q';
        while (empty_slot[queue])
        {
            try
            {
                wait();
            }
            catch (InterruptedException e) {;}
        }

        char letter = this.letters[queue];
        this.letters[queue] = '\0';
        this.queue = (queue + 1)%nbLetters;

        this.empty_slot[queue - 1] = true;
        notifyAll();

        return letter;
    }

}

