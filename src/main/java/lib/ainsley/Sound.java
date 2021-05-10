package lib.ainsley;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Objects;

public class Sound {

    private static final ArrayList<Sound> instances = new ArrayList<>();

    private final MediaPlayer mediaPlayer;

    /**
     * Constructor for a sound object
     * @param soundFile gets the path to the sound file
     */
    public Sound(String soundFile)  {
        Media audio = new Media(Objects.requireNonNull(getClass().getClassLoader().getResource(soundFile)).toExternalForm());
        mediaPlayer = new MediaPlayer(audio);
        instances.add(this);
    }

    /**
     * Static method to stop all sounds that are currently playing
     */
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
        if (shouldLoop) {
            mediaPlayer.setOnEndOfMedia(() -> {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            });
        }
    }

    /**
     * Method to stop a sound
     */
    public void stopSound() {
        mediaPlayer.stop();
    }

    public static ArrayList<Sound> getInstances() {
        return instances;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}