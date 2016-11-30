/**
 *  Created By; Charles Engen
 *  Instructor: Paul Novy
 *
 *  Assignment: 6.21 Catch-the-Creature
 *
 **/


import Game.PlayArea;

import java.awt.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import javax.swing.*;

public class App extends JFrame{

    private App()
    {
        init();
    }

    private void init()
    {
        PlayArea pa = new PlayArea(10);
        add(pa);

        setSize(800, 640);
        setMinimumSize(new Dimension(440, 220));
        setTitle("Catch-The-Creature");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args)
    {
        AccessController.doPrivileged(
                new PrivilegedAction() {
                    public Object run() {
                        EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                App ex = new App();
                                ex.setVisible(true);
                            }
                        });
                        return null;
                    }
                }
        );
    }
}
