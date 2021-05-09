package lib.ainsley;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Sound {

    private static final ArrayList<Sound> instances = new ArrayList<>();

    private static Toolkit toolkit;

    private final MediaPlayer mediaPlayer;

    /**
     *
     */
    public Sound(String soundFile)  {
        Media audio = new Media(Objects.requireNonNull(getClass().getClassLoader().getResource(soundFile)).toExternalForm());
        mediaPlayer = new MediaPlayer(audio);
        instances.add(this);
    }

    public static void stopAllSounds() {
        for (Sound instance : instances) {
            instance.stopSound();
        }
    }

    /**
     * Method to play a .mp3 sound file
     *
     * @param volume     receives a float from 0 to 1
     * @param shouldLoop receives a boolean
     */
    public void playSound(float volume, boolean shouldLoop) {
        mediaPlayer.setAutoPlay(shouldLoop);
        mediaPlayer.setVolume(volume);
        mediaPlayer.play();
    }

    /**
     *
     */
    public void stopSound() {
        try {
            mediaPlayer.stop();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}