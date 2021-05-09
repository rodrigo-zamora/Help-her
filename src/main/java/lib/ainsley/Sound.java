package lib.ainsley;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {

    /**
     * Method to play a .wav sound file
     *
     * @param soundFile  receives an URL with the direction of the file
     * @param volume     receives a float from 0 to 1
     * @param shouldLoop receives a boolean
     * @throws LineUnavailableException      if the file can't be opened
     * @throws IOException                   if an I/O exception occurs
     * @throws UnsupportedAudioFileException if the audio file isn't valid
     */
    public static void playSound(URL soundFile, float volume, boolean shouldLoop) throws LineUnavailableException, IOException, UnsupportedAudioFileException {

        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

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
}
