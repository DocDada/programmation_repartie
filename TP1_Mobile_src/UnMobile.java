import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable
{
    int saLargeur, saHauteur, sonDebDessin;
    final int sonPas = 10, sonTemps=50, sonCote=40;

    UnMobile(int telleLargeur, int telleHauteur)
    {
        super();
        saLargeur = telleLargeur;
        saHauteur = telleHauteur;
        setSize(telleLargeur, telleHauteur);
    }

    public void run()
    {
        boolean toTheRight = true;
        sonDebDessin = 0;
        while (true)
        {
            repaint();
            try {Thread.sleep(sonTemps);}
            catch (InterruptedException telleExcp)
                {telleExcp.printStackTrace();}

            if (toTheRight)
                if (sonDebDessin < saLargeur - sonPas)
                    sonDebDessin += sonPas;
                else
                {
                    toTheRight = false;
                    sonDebDessin -= sonPas;
                }
            else
                if (sonDebDessin > sonPas)
                    sonDebDessin -= sonPas;
                else
                {
                    toTheRight = true;
                    sonDebDessin += sonPas;
                }
        }
    }

    public void paintComponent(Graphics telCG)
    {
        super.paintComponent(telCG);
        telCG.fillRect(sonDebDessin, saHauteur/2, sonCote, sonCote);
    }
}
