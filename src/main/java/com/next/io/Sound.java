package com.next.io;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    private final String BASE_PATH = "/sound";

    public enum Track {
        SONG, WIND, WALK, STEP, SPELL_UP, PICK_UP, EQUIP, PLUCK, FANFARE, POLKA, BUY_UPGRADE, DOOR
    }

    private Clip clip;
    private URL[] url;

    public Sound() {
        url = new URL[30];
        url[0] = FileReader.getResource(BASE_PATH + "/song.wav");
        url[1] = FileReader.getResource(BASE_PATH + "/wind.wav");
        url[2] = FileReader.getResource(BASE_PATH + "/walk.wav");
        url[3] = FileReader.getResource(BASE_PATH + "/step.wav");
        url[4] = FileReader.getResource(BASE_PATH + "/spell_up.wav");
        url[5] = FileReader.getResource(BASE_PATH + "/pick_up.wav");
        url[6] = FileReader.getResource(BASE_PATH + "/equip.wav");
        url[7] = FileReader.getResource(BASE_PATH + "/pluck.wav");
        url[8] = FileReader.getResource(BASE_PATH + "/end_sound.wav");
        url[9] = FileReader.getResource(BASE_PATH + "/polka.wav");
        url[10] = FileReader.getResource(BASE_PATH + "/buy_upgrade.wav");
        url[11] = FileReader.getResource(BASE_PATH + "/door.wav");
    }

    public void setFile(Track track) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(url[track.ordinal()]);
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
