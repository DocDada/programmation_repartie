import java.awt.*;
import javax.swing.*;

class UneFenetre extends JFrame
{
    UnMobile sonMobile;
    private final int LARG=400, HAUT=250;

    public UneFenetre()
    {
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.sonMobile = new UnMobile(LARG, HAUT);
        this.add(this.sonMobile);
        Thread task = new Thread(this.sonMobile);
        task.start();
        this.setVisible(true);
        this.setSize(200,200);
    }
}
