import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/* Monitor */
public class Bal
{
    private int nbLetters = 15;
    private BlockingQueue<Character> letters = new ArrayBlockingQueue<Character>(nbLetters);

    public Bal()
    {
    }

    public void deposer(char letter) throws InterruptedException
    {
        letters.put(letter);
    }

    public char retirer() throws InterruptedException
    {
        return letters.take();
    }

    public int getSize()
    {
        return letters.size();
    }
}

