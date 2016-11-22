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
import java.time.Instant;
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

    public PlayArea(int delay, String img, String music)
    {
        TIMER_DELAY = delay;
        init(img, music, 1);
    }

    public PlayArea(int delay, String img, String music, int diff)
    {
        TIMER_DELAY = delay;
        init(img, music, diff);
    }

    private void init(String img, String music, int diff) {
        setBG(img);
        addMouseListener(this);
        creatures = new Creature[diff];
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
            URL url = getClass().getResource("/Sounds/" + music);
            Clip clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
            System.out.println(ex);
        }
        scoreLabel.setText(String.format("Score: %.0f", score));
        scoreMLabel.setText(String.format("Multi: %.2f", scoreM));
        scoreM = 1;
    }

    private void setBG(String img)
    {
        try {
            background = ImageIO.read(new File(img));
        } catch (java.io.IOException ex) {

        }
    }

    private BufferedImage getBG() {
        return background;
    }

    private void populateCreatures()
    {
        for (int i = 0; i < creatures.length; i++)
        {
            creatures[i] = new Hawk(random.nextInt(50), random.nextInt(50));
        }
        Timer timer = new Timer(TIMER_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                for (Creature c : creatures)
                    if (c.isState()) {
                        c.setPoint(random.nextInt(10 + PlayArea.super.getWidth() - 20 - c.getWidth()),
                                random.nextInt(10 + PlayArea.super.getHeight() - 20 - c.getHeight()));
                        if (scoreM > 1)
                            scoreM += scoreM * (TIMER_DELAY / 10000) - c.timeBetween(Instant.now()) * 1.25;
                        else
                            scoreM = 1;
                        c.setCreated(Instant.now());
                    } else {
                        c.setState(true);
                        c.setPoint(random.nextInt(10 + PlayArea.super.getWidth() - 20 - c.getWidth()),
                                random.nextInt(10 + PlayArea.super.getHeight() - 20 - c.getHeight()));
                        c.setCreated(Instant.now());
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
        for (Creature c: creatures)
            if (c.contains(e)) {
                if (c.isState()) {
                    c.setState(false);
                    c.death();
                    scoreM -= scoreM * (TIMER_DELAY / 10000) - c.timeBetween(Instant.now()) / 2;
                    setScore(getScore() + (1 * scoreM));
                    scoreLabel.setText(String.format("Score: %.0f", score));
                    scoreMLabel.setText(String.format("Multi: %.2f", scoreM));
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
