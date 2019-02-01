import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class UneFenetre extends JFrame implements ActionListener
{
    private final int ROWS=5, COLS=5;
    UnMobile[] mobiles;
    JButton controlMobiles[];
    Thread tasks[];
    private boolean moving[];
    private String buttonText = "Arrêt / Relance";

    private final int LARG=400, HAUT=250;

    public UneFenetre()
    {
        super();

        Container container = getContentPane();
        container.setLayout(new GridLayout(ROWS, COLS));

        controlMobiles = new JButton[ROWS];
        mobiles = new UnMobile[ROWS];
        tasks = new Thread[ROWS];
        moving = new boolean[ROWS];

        for (int r = 0; r != ROWS; r++)
        {
            controlMobiles[r] = new JButton(buttonText);
            controlMobiles[r].addActionListener(this);

            mobiles[r] = new UnMobile(LARG, HAUT);

            tasks[r] = new Thread(this.mobiles[r]);

            moving[r] = true;

            tasks[r].start();

            this.add(mobiles[r]);
            this.add(controlMobiles[r]);
        }

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);
        this.setSize(900,700);
    }

    public void actionPerformed(ActionEvent e)
    {
        int i;
        for (i = 0; e.getSource() != controlMobiles[i] && i != ROWS; i++);

        if (moving[i])
            tasks[i].suspend();
        else
            tasks[i].resume();
        moving[i] = !moving[i];
    }
}
