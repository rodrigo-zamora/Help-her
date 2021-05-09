package lib.ainsley;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.ArrayList;

public class Sound {

    private static final ArrayList<Sound> instances = new ArrayList<>();

    private final Clip clip;

    /**
     * @param soundFile
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public Sound(String soundFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(ClassLoader.getSystemResource(soundFile));
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        instances.add(this);
    }

    public static void stopAllSounds() {
        for (Sound instance : instances) {
            instance.stopSound();
        }
    }

    public static ArrayList<Sound> getInstances() {
        return instances;
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

        try {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) java.lang.Math.log10(volume));

            if (shouldLoop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     *
     */
    public void stopSound() {
        try {
            clip.stop();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}