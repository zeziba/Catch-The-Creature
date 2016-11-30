package Game;

import Creature.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.sound.sampled.*;

/**
 * Created by cengen on 11/15/16.
 */

public class PlayArea extends JPanel implements MouseListener{

    private JLabel scoreLabel = new JLabel("Score: " + getScore(), JLabel.CENTER);
    private JLabel scoreMLabel = new JLabel("Multi: " + getScore(), JLabel.CENTER);
    private BufferedImage background;
    private Creature[] creatures;
    private Random random = new Random();
    private final int TIMER_DELAY;
    private double score = 0;
    private double scoreM = 1.0;
    private boolean created = false;
    private File iFile = new File("src/Images/Backgrounds");
    private String[] fNames = iFile.list();
    private File sFile = new File("src/Sounds/Music");
    private String[] sounds = sFile.list();
    private int hits, misses;

    public PlayArea(int delay)
    {
        TIMER_DELAY = delay;
        init(1);
    }

    public PlayArea(int delay, int diff)
    {
        TIMER_DELAY = delay;
        init(diff);
    }

    private void init(int diff) {
        setBG();
        addMouseListener(this);
        creatures = new Creature[diff];
        initCreatures();
        populateCreatures();
        scoreLabel.setFont(scoreLabel.getFont().deriveFont(Font.BOLD, 48));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setVerticalAlignment(JLabel.TOP);
        scoreLabel.setHorizontalAlignment(JLabel.LEFT);
        scoreMLabel.setFont(scoreLabel.getFont().deriveFont(Font.BOLD, 48));
        scoreMLabel.setForeground(Color.WHITE);
        scoreMLabel.setVerticalAlignment(JLabel.TOP);
        scoreMLabel.setHorizontalAlignment(JLabel.RIGHT);
        this.add(scoreLabel);
        this.add(scoreMLabel);
        try {
            int am = random.nextInt(sounds.length);
            URL url = getClass().getResource("/Sounds/Music/" + sounds[am]);
            Clip clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (javax.sound.sampled.LineUnavailableException |
                java.io.IOException |
                javax.sound.sampled.UnsupportedAudioFileException |
                java.lang.NullPointerException ex) {
            ex.printStackTrace();
        }
        scoreLabel.setText(String.format("Score: %.0f", score));
        scoreMLabel.setText(String.format("Multi: %.2f", scoreM));
        scoreM = 1;
    }

    private void setBG()
    {
        try {
            int fR;
            if (fNames.length > 0)
                fR = random.nextInt(fNames.length);
            else
                fR = 0;
            background = ImageIO.read(new File(iFile.toString() + "/" + fNames[fR]));
        } catch (java.io.IOException | java.lang.NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    private BufferedImage getBG() {
        return background;
    }

    private void initCreatures() {
        if (!created) {
            for (int i = 0; i < creatures.length; i++)
                creatures[i] = PickCreature.PickCreature(10 + random.nextInt(500), 10 + random.nextInt(500));
            for (Creature cre: creatures) {
                cre.setCreated(System.currentTimeMillis());
            }
            created = true;
        }
    }

    private void populateCreatures()
    {
        Timer timer = new Timer(TIMER_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                for (Creature cre : creatures) {
                    double timeX = cre.timeBetween(System.currentTimeMillis());
                    if (timeX > cre.getDuration()) {
                        if (cre.isState()) {
                            if (scoreM > 1) {
                                scoreM -= Math.pow(1.1, timeX / 10);
                            }
                            else
                                scoreM = 1;
                        } else {
                            cre.setState(true);
                        }
                    try {
                        cre.setPoint(random.nextInt(10 + PlayArea.super.getWidth() - 20 - cre.getWidth()),
                                random.nextInt(10 + PlayArea.super.getHeight() - 20 - cre.getHeight()));
                        cre.setCreated(System.currentTimeMillis());
                    }
                    catch (IllegalArgumentException e) {
                        cre.setPoint(10, 10);
                        cre.setCreated(System.currentTimeMillis());
                    }
                    }
                }
                scoreLabel.setText(String.format("Score: %.0f", score));
                scoreMLabel.setText(String.format("Multi: %.2f", scoreM));
                PlayArea.this.repaint();
            }
        });
        timer.start();
    }

    private void setScore(double score) {
        this.score = score;
    }

    private double getScore() {
        return score;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getBG(), 0, 0, super.getWidth(), super.getHeight(),  null);
        for (Creature creature : creatures) {
            creature.drawSelf(g, this);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Creature cre: creatures)
            if (cre.contains(e)) {
                if (cre.isState()) {
                    cre.setState(false);
                    cre.death();
                    scoreM += Math.pow(cre.timeBetween(System.currentTimeMillis()) / 10, 1/1.2);
                    setScore(getScore() + (1 * scoreM));
                    scoreLabel.setText(String.format("Score: %.0f", score));
                    scoreMLabel.setText(String.format("Multi: %.2f", scoreM));
                    if ((int) score % 10 == 0 && score > 0)
                        setBG();
                    hits++;
                }
                else {
                    misses++;
                }
            }
        super.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
