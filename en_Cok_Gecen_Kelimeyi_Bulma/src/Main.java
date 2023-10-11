import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Get input from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the text: ");
        String text = scanner.nextLine();

        // Split the text into words using regular expressions and create a HashMap to store word frequencies
        String[] words = text.split("\\s+");

        Map<String, Integer> wordCount = new HashMap<>();

        // Add words to the HashMap and update their counts
        for (String word : words) {
            word = word.toLowerCase(); // Convert words to lowercase to avoid case sensitivity
            if (wordCount.containsKey(word)) {
                wordCount.put(word, wordCount.get(word) + 1);
            } else {
                wordCount.put(word, 1);
            }
        }

        // Find the highest frequency
        int maxFrequency = 0;
        for (int count : wordCount.values()) {
            if (count > maxFrequency) {
                maxFrequency = count;
            }
        }

        // Find words with the highest frequency
        System.out.print("Most common word(s): ");

        for (String key : wordCount.keySet()) {
            if (wordCount.get(key)== maxFrequency) {

                System.out.print(key+" ");

            }
        }
        System.out.println(", Count: " + maxFrequency);
    }
}