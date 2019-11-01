package sample;
import static java.util.Calendar.AM;
import static javax.sound.sampled.AudioFileFormat.Type.AU;

public enum ItemType {
    AUDIO ("AU"),
    VISUAL ("VI"),
    AUDIO_MOBILE ("AM"),
    VISUAL_MOBILE ("VM");

    public String code;

    ItemType(String c){
        code = c;
    }
}
