import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Sentiment1{
    public static void main(String[] args) {
        Sentiment1 analyzer = new Sentiment1();
        analyzer.analyzeTopics("secretdocc.txt");
    }

    public void analyzeTopics(String filename) {
 
        String[] positiveWords = {"funny", "beautiful", "adorable", "cool","amazing"};
        String[] negativeWords = {"selfish", "poor", "terrible", "scary","lazy"};

        Map<String, String[]> topics = new LinkedHashMap<>();
        topics.put("Topic 1: food", new String[]{"soup", "doner", "burger","chocolate"});
        topics.put("Topic 2: sdu", new String[]{"top", "retake", "sdu","urbo"});
        topics.put("Topic 3: phone", new String[]{"iPhone", "ipad", "xiomi"});

 
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

                    for (Map.Entry<String, String[]> entry : topics.entrySet()) {
                        String topic = entry.getKey();
                        String[] themeWords = entry.getValue();
                        for (String themeWord : themeWords) {
                            if (word.equalsIgnoreCase(themeWord)) {
                                System.out.println(topic + ": " + themeWord);
                            }
                        }
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
