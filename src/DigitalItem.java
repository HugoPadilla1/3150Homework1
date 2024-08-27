/**Class: DigitalItem
 * @author Hugo Padilla
 * @version 1.0
 * Course: ITEC 3150 Fall 2024
 * Written: August 23, 2024
 *
 * This class – This is our DigitalItem abstract class, this provides all of it's following subclasses
 * with a general guide and quality set. Contains 3 variables, name, producer, and size. Provides exceptions
 * if any of these are entered incorrectly.
 * Has two classes that directly inherit it, AudioFile and VideoFile.
 */

public abstract class DigitalItem {
    public String name;
    public String producer;
    public int size; // standardize size in mB

    public DigitalItem(String name, String producer, int size){
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (producer == null || producer.isBlank()) {
            throw new IllegalArgumentException("Producer cannot be blank.");
        }
        if (size < 5 || size > 10000000) {
            throw new IllegalArgumentException("Size must be between 5 and 10000000.");
        }
        this.name = name;
        this.producer = producer;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return "A DigitalItem titled " + name + ", with size " + size + "mB, by producer " + producer + ".";
    }
}
