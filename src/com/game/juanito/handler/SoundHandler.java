package com.game.juanito.handler;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundHandler {
    public static void playSound(URL soundFile, float volume, boolean shouldLoop) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
        if (shouldLoop) {
            clip.loop(99);
        } else {
            clip.start();
        }
    }
}
