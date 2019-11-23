package sample;

/**
 * Items on this product line have basic media controls.
 *
 * @author Darian Colon
 */
public class AudioPlayer extends Product implements MultimediaControl {
  protected String supportedAudioFormats;
  protected String supportedPlaylistFormats;
  protected ItemType mediaType;

  /** This part of the program give items on this product line have basic media controls.
     * @param name is the name of the product that is being added to the program(TableView)
     * @param manufacturer is the brand of the product (who makes it)
     * @param audio glgkgkt.
     * @param playlist gktmlt.
     */
  public AudioPlayer(String name, String manufacturer, String audio, String playlist) {
    super(name, manufacturer);
    supportedAudioFormats = audio;
    supportedPlaylistFormats = playlist;
    mediaType = ItemType.AUDIO;
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

  /**s returns the toString.
     * @return
     */
  public String toString() {
    String s = super.toString() + "\n";
    s += "Audio Formats: " + "Playlist Formats: " + "Type: " + mediaType;
    return s;
  }
}
