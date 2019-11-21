package sample;

import java.util.ArrayList;

/**
 * @author Darian Colon
 */
public class MoviePlayer extends Product implements MultimediaControl {
    private MonitorType monitorType;
    private Screen screen;
    protected ItemType mediaType;

    /**
     * @param name
     * @param manufacturer
     * @param screen
     * @param t
     */

    public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType t) {
        super(name, manufacturer);

        screen = new Screen("1024x768", 60, 10);

        monitorType = MonitorType.LCD;

        this.screen = screen;

        this.monitorType = t;

        mediaType = ItemType.VISUAL;
    }

    @Override
    public void play() {
        System.out.println("Playing");
    }

    @Override
    public void stop() {
        System.out.println("Stopped");
    }

    @Override
    public void previous() {
        System.out.println("Previous");
    }

    @Override
    public void next() {
        System.out.println("Next");
    }

    /**
     * @return
     */
    public String toString() {
        return super.toString() + "\n" + screen.toString() + "\n" +
                "Monitor Type: " + monitorType;
    }

}
