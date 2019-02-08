import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable
{
    int saLargeur, saHauteur, sonDebDessin;
    final int sonPas = 10, sonTemps=50, sonCote=40;
    static private semaphoreGeneral mutex = new semaphoreGeneral(1);

    UnMobile(int telleLargeur, int telleHauteur)
    {
        super();
        saLargeur = telleLargeur;
        saHauteur = telleHauteur;
        setSize(telleLargeur, telleHauteur);
    }

    public void run()
    {
        sonDebDessin = 0;

        mutex.syncWait();
        for (; sonDebDessin < saLargeur * 0.33; sonDebDessin++)
        {
            repaint();
            try {Thread.sleep(sonTemps);}
            catch (InterruptedException telleExcp)
                {telleExcp.printStackTrace();}

        }// for()

        mutex.syncSignal();
        for (; sonDebDessin < saLargeur * 0.66; sonDebDessin++)
        {
            repaint();
            try {Thread.sleep(sonTemps);}
            catch (InterruptedException telleExcp)
                {telleExcp.printStackTrace();}

        }// for()
    }

    public void paintComponent(Graphics telCG)
    {
        super.paintComponent(telCG);
        telCG.fillRect(sonDebDessin, saHauteur/2, sonCote, sonCote);
    }
}
