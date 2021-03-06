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
        try {
            URL url = getClass().getResource("/Sounds/SoundEffects/" + fx);
            setSoundFx(AudioSystem.getClip());
            AudioInputStream AIS = AudioSystem.getAudioInputStream(url);
            addAudio(AIS);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
            System.out.println(ex);
        }
    }

    private Clip getSoundFx() {
        return soundFx;
    }

    private void setSoundFx(Clip soundFx) {
        this.soundFx = soundFx;
    }

    private void addAudio(AudioInputStream ais) {
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