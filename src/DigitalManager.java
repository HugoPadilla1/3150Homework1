/**Class: DigitalManager
 * @author Hugo
 * @version 1.0
 * Course: ITEC 3150 Fall 2024
 * Written: August 24, 2024
 *
 * This class – This is our main DigitalManager class, in which our program runs.
 * This class allows you to use several methods, listItems, findItem, deleteItem, addItem, and saveItemstoFile.
 * There are also several smaller methods that are required for the program to run - parseLineToDigitalItem, and itemToFileString.
 * There is a dummy DigitalItem, in order to demonstrate the ability to delete an item.
 */
import java.util.*;
import java.io.*;

public class DigitalManager {
    private List<DigitalItem> items = new ArrayList<>();

    public static void main(String[] args) {
        DigitalManager manager = new DigitalManager();
        manager.loadItemsFromFile("DigitalItems.txt"); // loads preliminary file

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("What are we doing today?"); // creating a user friendly interface
            System.out.println("1. List all items" +
                    "\n2. Find an item by name" +
                    "\n3. Delete an item" +
                    "\n4. Add a new item" +
                    "\n5. Write list to a file" +
                    "\n6. Exit");
            int option = scanner.nextInt();
            int num;
            scanner.nextLine();
            try {
                num = Integer.parseInt(String.valueOf(option));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                continue;
            }

            switch(option) {
                case 1 -> manager.listItems();
                case 2 -> manager.findItem(scanner);
                case 3 -> manager.deleteItem(scanner);
                case 4 -> manager.addItem(scanner);
                case 5 -> manager.saveItemsToFile("DigitalItems.txt");
                case 6 -> {
                    manager.saveItemsToFile("DigitalItems.txt"); // save before exiting
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("This is an invalid option.");

            }
        }
    }

    public void loadItemsFromFile(String fileName) { // this will allow us to read the input file and find the given files
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File not found. Starting with an empty list.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                try {
                    DigitalItem item = parseLineToDigitalItem(line);
                    items.add(item);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error parsing line " + lineNumber + ": " + e.getMessage());
                }
                lineNumber++;
            }
            System.out.println("Successfully loaded items from file.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    private DigitalItem parseLineToDigitalItem(String line) { // this  allows the program to read the txt file in a way that it understands
        String[] parts = line.split(",", -1);
        if (parts.length < 5) {
            throw new IllegalArgumentException("Insufficient data fields.");
        }

        String type = parts[0].trim();
        String name = parts[1].trim();
        String producer = parts[2].trim();
        int size;
        try {
            size = Integer.parseInt(parts[3].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Size must be an integer.");
        }

        switch (type.toLowerCase()) {
            case "audio" -> {
                String audioType = parts[4].trim();
                return new AudioFile(name, producer, size, audioType);
            }
            case "video" -> {
                int length;
                try {
                    length = Integer.parseInt(parts[4].trim());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Length must be an integer.");
                }
                return new VideoFile(name, producer, size, length);
            }
            default -> throw new IllegalArgumentException("Unknown item type: " + type);
        }
    }

    public void saveItemsToFile(String fileName){ // this will write any new items to our list file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (DigitalItem item : items) {
                writer.write(itemToFileString(item));
                writer.newLine();
            }
            System.out.println("Items successfully saved to file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    private String itemToFileString(DigitalItem item) { // this will write our new items to our file in thee same format
        if (item instanceof AudioFile audio) {
            return String.join(",",
                    "Audio",
                    audio.getName(),
                    audio.getProducer(),
                    String.valueOf(audio.getSize()),
                    audio.getAudioType()
            );
        } else if (item instanceof VideoFile video) {
            return String.join(",",
                    "Video",
                    video.getName(),
                    video.getProducer(),
                    String.valueOf(video.getSize()),
                    String.valueOf(video.getLength())
            );
        } else {
            throw new IllegalArgumentException("Unknown DigitalItem subclass.");
        }
    }

    public void listItems(){ // this will allow us to present the items in the list
        Iterator<DigitalItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public void findItem(Scanner scanner){ // attempts to find an item from the  list, if not returns item not found.
        System.out.println("What item are you finding?: ");
        String itemName = scanner.nextLine();

        for (DigitalItem item : items){
            if(item.getName().equalsIgnoreCase(itemName)){
                System.out.println(item);
                return;
            }
        }
        System.out.println("Item not found.");
    }

    public void deleteItem(Scanner scanner) { // attemps to delete an item from the list, if not returns error
        System.out.println("What item would you like to delete?");
        String itemName = scanner.nextLine();

        Iterator<DigitalItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            DigitalItem item = iterator.next();
            if (item.getName().equalsIgnoreCase(itemName)) {
                System.out.println(item);
                System.out.println("Do you want to delete this item? (y/n)");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("y")) {
                    iterator.remove();
                    System.out.println("Item deleted.");
                }
                return;
            }
        }
        System.out.println("An item with this name was not found.");
    }

    public void addItem(Scanner scanner) {
        System.out.println("What type of item are you adding?: "); // 'audio' or 'video'
        String type = scanner.nextLine();

        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        System.out.println("Enter producer: ");
        String producer = scanner.nextLine();

        System.out.println("Enter size: ");
        int size = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (type.equalsIgnoreCase("audio")) {
            System.out.println("Enter audio type (MP3/Other): ");
            String audioType = scanner.nextLine();
            items.add(new AudioFile(name, producer, size, audioType));
        } else if (type.equalsIgnoreCase("video")) {
            System.out.println("Enter length in minutes: ");
            int length = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            items.add(new VideoFile(name, producer, size, length));
        } else {
            System.out.println("Invalid type specified.");
        }
    }
}
