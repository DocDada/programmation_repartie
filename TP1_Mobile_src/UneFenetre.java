import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class UneFenetre extends JFrame implements ActionListener
{
    private final int ROWS = 3, COLS = 1, MOBILES = ROWS;
    UnMobile[] mobiles;
    Thread tasks[];
    private String buttonText = "Arrêt / Relance";

    private final int LARG=400, HAUT=250;
    private final int LARGEUR_FENETRE = 900, LONGUEUR_FENETRE = 700;

    public UneFenetre()
    {
        super();

        Container container = getContentPane();
        container.setLayout(new GridLayout(ROWS, COLS));

        mobiles = new UnMobile[MOBILES];
        tasks = new Thread[MOBILES];

        for (int r = 0; r != MOBILES; r++)
        {
            mobiles[r] = new UnMobile(LARG, HAUT);

            tasks[r] = new Thread(this.mobiles[r]);

            tasks[r].start();

            this.add(mobiles[r]);
        }

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);

        this.setSize(LARGEUR_FENETRE, LONGUEUR_FENETRE);
    }

    public void actionPerformed(ActionEvent e) { }
}
