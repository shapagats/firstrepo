import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        HashSet<String> keywords = new HashSet<>();


        keywords.add("catch");
        keywords.add("class");
        keywords.add("for");
        keywords.add("if");
        keywords.add("import");
        keywords.add("int");
        keywords.add("new");
        keywords.add("public");
        keywords.add("return");
        keywords.add("static");
        keywords.add("switch");
        keywords.add("this");
        keywords.add("try");
        keywords.add("void");
        keywords.add("while");

       
        HashMap<String, Integer> keywordCounts = new HashMap<>();

       
        try (BufferedReader br = new BufferedReader(new FileReader("Test.java"))) {
            String line;
            while ((line = br.readLine()) != null) {
              
                String[] words = line.split("\\s+");
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
            System.out.println(keyword + ": " + keywordCounts.get(keyword));
        }
    }
}
