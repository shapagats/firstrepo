import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Sentiment {
    public static void main(String[] args) {
        Sentiment analyzer = new Sentiment();
        analyzer.analyzeSentiment("secretdocc.txt");
    }
    
    public void analyzeSentiment(String filename) {
        String[] positiveWords = {"funny", "beautiful", "adorable", "cool","amazing"};
        String[] negativeWords = {"selfish", "poor", "terrible", "scary","lazy"};
        
        Map<String, Integer> wordCounts = new HashMap<>();
    
        int positiveCount = 0;
        int negativeCount = 0;
        int neutralCount = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                   
                    wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                    
                    if (isPositive(word, positiveWords)) {
                        positiveCount++;
                    } else if (isNegative(word, negativeWords)) {
                        negativeCount++;
                    } else {
                        neutralCount++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        System.out.println("Neutral Words: ");
        for (String word : wordCounts.keySet()) {
            if (!isPositive(word, positiveWords) && !isNegative(word, negativeWords)) {
                System.out.print(word + ", ");
            }
        }
        System.out.println("\nWord Count: ");
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " ");
        }
    }
    
    private boolean isPositive(String word, String[] positiveWords) {
        for (String positiveWord : positiveWords) {
            if (word.equalsIgnoreCase(positiveWord)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isNegative(String word, String[] negativeWords) {
        for (String negativeWord : negativeWords) {
            if (word.equalsIgnoreCase(negativeWord)) {
                return true;
            }
        }
        return false;
    }
    
    private void printWords(String[] words) {
        for (String word : words) {
            System.out.print(word + ", ");
        }
        System.out.println();
    }
}
