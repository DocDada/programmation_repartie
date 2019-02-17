package TP1_Mobile;

import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable {
    int saLargeur, saHauteur, sonDebDessin;
    final int sonPas = 10, sonTemps = 10, sonCote = 40;
    static private int p = 2;
    static private semaphoreGeneral mutex = new semaphoreGeneral(p);

    UnMobile(int telleLargeur, int telleHauteur) {
        super();
        saLargeur = telleLargeur;
        saHauteur = telleHauteur;
        setSize(telleLargeur, telleHauteur);
    }

    public void run() {
        double partie = 0.33;
        sonDebDessin = 0;

        for (; sonDebDessin != saLargeur; sonDebDessin++) {
            repaint();
            try {
                Thread.sleep(sonTemps);
            } catch (InterruptedException telleExcp) {
                telleExcp.printStackTrace();
            }

            if (sonDebDessin == saLargeur * 0.33 - sonCote) {
                mutex.syncWait();

            }
            else if (sonDebDessin == saLargeur * 0.66 - sonCote) {
                mutex.syncSignal();

            }

        }

    }

    public void paintComponent(Graphics telCG) {
        super.paintComponent(telCG);
        telCG.drawLine((int) (0.33 * saLargeur), 0, (int) (0.33 * saLargeur),
                saHauteur);
        telCG.drawLine((int) (0.67 * saLargeur), 0, (int) (0.67 * saLargeur),
                saHauteur);

        if (sonDebDessin > (0.33 * saLargeur) - sonCote
                && sonDebDessin < (0.66 * saLargeur) - sonCote)
            telCG.setColor(Color.RED);

        telCG.fillRect(sonDebDessin, saHauteur / 2, sonCote, sonCote);
    }
}