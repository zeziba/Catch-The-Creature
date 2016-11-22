package Sounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by cengen on 11/20/16.
 */
public class SoundFX {

    private Clip soundFx;

    public SoundFX(String fx) {
        String music = fx;
        try {
            URL url = getClass().getResource("/Sounds/SoundEffects/" + music);
            soundFx = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            soundFx.open(ais);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
            System.out.println(ex);
        }
    }

    public void makeSound () {
        if (soundFx.isActive())
            soundFx.stop();
        soundFx.setFramePosition(0);
        soundFx.start();
    }
}