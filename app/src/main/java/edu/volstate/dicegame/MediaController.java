package edu.volstate.dicegame;

import android.app.Activity;
import android.media.MediaPlayer;

public class MediaController {
    // This one just handles media
    public static void playMedia(Activity activity, int media) {
        MediaPlayer mediaPlayer = MediaPlayer.create(activity, media);
        mediaPlayer.start();
    }
}
