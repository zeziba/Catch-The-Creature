import Game.PlayArea;

import java.awt.*;
import javax.swing.*;


/**
 * Created by cengen on 11/15/16.
 */
public class App extends JFrame{

    public App()
    {
        init();
    }

    public void init()
    {
        PlayArea pa = new PlayArea(1000, "src/Images/background.jpeg", "Defense_Line.wav");
        add(pa);

        setSize(800, 640);
        setTitle("Catch-The-Creature");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            App ex = new App();
            ex.setVisible(true);
        });
    }
}