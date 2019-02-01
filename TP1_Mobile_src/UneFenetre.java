import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class UneFenetre extends JFrame implements ActionListener
{
    UnMobile sonMobile;
    JButton controlMobile;
    Thread task;
    boolean moving = true;

    private final int LARG=400, HAUT=250;

    public UneFenetre()
    {
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.sonMobile = new UnMobile(LARG, HAUT);
        this.add(this.sonMobile);
        controlMobile = new JButton("Arrêt / Relance");
        this.add(controlMobile);
        controlMobile.addActionListener(this);

        task = new Thread(this.sonMobile);
        task.start();

        this.setVisible(true);
        this.setSize(500,500);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == controlMobile)
        {
            if (moving)
                task.suspend();
            else
                task.resume();
            moving = !moving;
        }
    }
}
