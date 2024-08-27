/**Class: VideoFile
 * @author Hugo Padilla
 * @version 1.0
 * Course: ITEC 3150 Fall 2024
 * Written: August 23, 2024
 *
 * This class – This is the VideoFile class, which has a unique attribute length (minutes). This class
 * requires that the length be between 10 and 180 minutes, with exception handling if any other number is entered.
 * This class directly inherits DigitalItem.
 */
public class VideoFile extends DigitalItem{
    public int length; // standardized into mB

    public VideoFile(String name, String producer, int size, int length){
        super(name, producer, size);
        if (length < 10 || length > 180) {
            throw new IllegalArgumentException("Video length must be between 10 and 180 minutes");
        } // exception handling for a video less than 10 minutes or greater than 180
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String getType(){
        return "Video";
    }

    @Override
    public String toString() {
        return "A " + getType() + " file titled " + name + ", with size " + size + "mB, by producer " + producer + ".";
    }
}
