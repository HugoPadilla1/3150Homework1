/**Class: AudioFile
 * @author Hugo Padilla
 * @version 1.0
 * Course: ITEC 3150 Fall 2024
 * Written: August 23, 2024
 *
 * This class – The AudioFile class contains a unique String audioType, while inheriting the 3 other
 * attributes from DigitalItem. Implements Exception Handling, This is useful in making sure that the
 * AudioFile is actually an audio file.
 */
public class AudioFile extends DigitalItem{
    public String audioType; // has to be defined as 'mp3' or 'other'

    public AudioFile(String name, String producer, int size, String audioType){
        super(name, producer, size);
        if (audioType == null || audioType.isBlank()) {
            throw new IllegalArgumentException("Audio type cannot be blank.");
        }
        if (!audioType.equalsIgnoreCase("MP3") && !audioType.equalsIgnoreCase("Other")) {
            throw new IllegalArgumentException("Invalid file type supported. Try 'mp3' or 'Other'");
        }
        this.audioType = audioType;
    }

    public String getAudioType() {
        return audioType;
    }

    public void setAudioType(String audioType) {
        this.audioType = audioType;
    }

    @Override
    public String getType(){
        return "Audio";
    }
    @Override
    public String toString() {
        return "A " + audioType + " audio file titled " + name + ", with size " + size + "mB, by producer " + producer + ".";
    }
}
