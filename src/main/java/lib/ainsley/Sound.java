package lib.ainsley;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Sound {

    private static final ArrayList<Sound> instances = new ArrayList<>();

    private static Toolkit toolkit;

    private final MediaPlayer mediaPlayer;

    /**
     *
     * @param soundFile
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public Sound(String soundFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
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
     * Method to play a .wav sound file
     *
     * @param volume     receives a float from 0 to 1
     * @param shouldLoop receives a boolean
     * @throws LineUnavailableException      if the file can't be opened
     * @throws IOException                   if an I/O exception occurs
     * @throws UnsupportedAudioFileException if the audio file isn't valid
     */
    public void playSound(float volume, boolean shouldLoop) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
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