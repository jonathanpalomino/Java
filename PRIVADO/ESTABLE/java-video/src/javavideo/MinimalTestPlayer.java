/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javavideo;

/**
 *
 * @author JONATHAN
 */
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class MinimalTestPlayer {

    public static void main(String[] args) throws Exception {
        String cadena_video = "E:\\SMALLVILLE\\Smallville 10x01.avi";
        //System.setProperty("jna.library.path", "C:\\Users\\JONATHAN\\Desktop\\dlls\\");
        System.setProperty("jna.library.path", "C:\\Program Files\\VideoLAN\\VLC\\");
        //System.setProperty("VLC_PLUGIN_PATH", "C:\\Program Files\\VideoLAN\\VLC\\plugins\\");
        Frame f = new Frame("Test Player");
        f.setSize(800, 600);
        f.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setLayout(new BorderLayout());
        Canvas vs = new Canvas();
        f.add(vs, BorderLayout.CENTER);
        f.setVisible(true);

        MediaPlayerFactory factory = new MediaPlayerFactory();

        EmbeddedMediaPlayer mediaPlayer = factory.newEmbeddedMediaPlayer();
        mediaPlayer.setVideoSurface(factory.newVideoSurface(vs));

        mediaPlayer.setRepeat(true);
        mediaPlayer.playMedia(cadena_video);
        Thread.currentThread().join();
    }
}
