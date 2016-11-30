package Sounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Created by cengen on 11/20/16.
 */
public class SoundFX {

    private Clip soundFx;

    public SoundFX(String fx) {
        AccessController.doPrivileged(
                new PrivilegedAction() {
                    @Override
                    public Object run() {
                        try {
                            URL url = getClass().getResource("/Sounds/SoundEffects/" + fx);
                            soundFx = AudioSystem.getClip();
                            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
                            soundFx.open(ais);
                        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                            System.out.println(ex);
                        }
                        return null;
                    }
                }
        );
    }

    public void makeSound () {
        if (soundFx.isActive())
            soundFx.stop();
        soundFx.setFramePosition(0);
        soundFx.start();
    }
}