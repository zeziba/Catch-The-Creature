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
                            setSoundFx(AudioSystem.getClip());
                            AudioInputStream AIS = AudioSystem.getAudioInputStream(url);
                            addAudio(AIS);
                        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                            System.out.println(ex);
                        }
                        return null;
                    }
                }
        );
    }

    public Clip getSoundFx() {
        return soundFx;
    }

    public void setSoundFx(Clip soundFx) {
        this.soundFx = soundFx;
    }

    public void addAudio(AudioInputStream ais) {
        try {
            getSoundFx().open(ais);
        } catch (java.io.IOException | javax.sound.sampled.LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    public void makeSound () {
        if (soundFx.isActive())
            soundFx.stop();
        soundFx.setFramePosition(0);
        soundFx.start();
    }
}