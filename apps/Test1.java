import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Test1 {
    public static void main(String[] args) {
        Set<String> keywords = new TreeSet<>(Arrays.asList(
            "break", "byte", "case", "catch", "char", "class", 
            "continue", "else", 
            "for", "if","import", "int","new", "public", 
            "return","static", "try", "void", "while", "true"
        ));
        
        java.util.Map<String, Integer> keywordCounts = new java.util.TreeMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("Test1.java"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\W+");
                for (String word : words) {
                    if (keywords.contains(word)) {
                        keywordCounts.put(word, keywordCounts.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String keyword : keywordCounts.keySet()) {
            System.out.println("Keyword: " + keyword + ", Count: " + keywordCounts.get(keyword));
        }
    }
}
