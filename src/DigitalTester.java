/**Class: DigitalTester
 * @author Hugo Padilla
 * @version 1.0
 * Course: ITEC 3150 Fall 2024
 * Written: August 23, 2024
 *
 * This class – This class is honestly a temporary Class that was utilized for troubleshooting and ensuring that
 * the proper classes could be utilized and initialized.
 */

public class DigitalTester {
    public static void main(String[] args) {
//        DigitalItem DVD = new DigitalItem("Donda", "Kanye West", 32);
//        System.out.println(DVD);
        AudioFile music = new AudioFile("Hotline Bling", "Drake", 16, "mp3");
        System.out.println(music);
        VideoFile video = new VideoFile("Get Out", "Jordan Peele", 1440, 124);
        System.out.println(video);
    }
}
