package sample;

import java.util.ArrayList;

/**
 * @author Darian Colon
 */
public interface MultimediaControl {
    public void play();
    public void stop();
    public void previous();
    public void next();

//--------------------------------------------------------------------------------
// GUI: Below is a driver that will demonstrate that any class that implements the
// MultimediaControl Interface would be able to be instantiated and use its methods
// used no matter if it was an audio or movie player.

    public static void testMultimedia() {
        AudioPlayer newAudioProduct = new AudioPlayer("DP-X1A", "Onkyo",
                "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
        Screen newScreen = new Screen("720x480", 40, 22);
        MoviePlayer newMovieProduct = new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen,
                MonitorType.LCD);
        ArrayList<MultimediaControl> productList = new ArrayList<MultimediaControl>();
        productList.add(newAudioProduct);
        productList.add(newMovieProduct);
        for (MultimediaControl p : productList) {
            System.out.println(p);
            p.play();
            p.stop();
            p.next();
            p.previous();
        }
    }
}
