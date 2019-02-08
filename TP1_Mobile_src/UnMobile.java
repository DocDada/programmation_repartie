import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable
{
    int saLargeur, saHauteur, sonDebDessin;
    final int sonPas = 10, sonTemps=10, sonCote=40;
    static private int p = 2;
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
        double partie = 0.33;
        sonDebDessin = 0;

        for (int j = 0; j != p-1; j++)
            mutex.syncWait();

        for (; ; sonDebDessin++)
        {
            repaint();
            try {Thread.sleep(sonTemps);}
            catch (InterruptedException telleExcp)
                {telleExcp.printStackTrace();}

            if (sonDebDessin > saLargeur * partie)
            {
                for (int j = 0; j != 3; j++)
                    mutex.syncSignal();
                if (partie == 0.33)
                    partie = 0.66;
                else
                    partie = 0.33;
                for (int j = 0; j != p; j++)
                    mutex.syncWait();
            }

        }// for()

        //mutex.syncSignal();
        /*for (; sonDebDessin < saLargeur * 0.66; sonDebDessin++)
        {
            repaint();
            try {Thread.sleep(sonTemps);}
            catch (InterruptedException telleExcp)
                {telleExcp.printStackTrace();}

        }// for()
        */
    }

    public void paintComponent(Graphics telCG)
    {
        super.paintComponent(telCG);
        telCG.fillRect(sonDebDessin, saHauteur/2, sonCote, sonCote);
    }
}
