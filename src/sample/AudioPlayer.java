package sample;

public class AudioPlayer extends Product implements MultimediaControl {
    protected String supportedAudioFormats;
    protected String supportedPlaylistFormats;
    protected ItemType mediaType;

    public AudioPlayer(String name, String manufacturer, String Audio, String Playlist){
        super(name, manufacturer);
        supportedAudioFormats = Audio;
        supportedPlaylistFormats = Playlist;
        mediaType = ItemType.AUDIO;
    }
    @Override
    public void play(){
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

    public String toString(){
       String s = super.toString()+ "\n";
               s += "Audio Formats: " + "Playlist Formats: " + "Type: " + mediaType;
       return s;
    }
}
