import java.io.*;

public class FileCopy {
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("the source file path:");
        String sourceFilePath = "";
        try {
            sourceFilePath = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading source file path: " + e.getMessage());
            return;
        }

        System.out.println("the destination file path:");
        String destFilePath = "";
        try {
            destFilePath = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error destination file path: " + e.getMessage());
            return;
        }

        try (FileInputStream fis = new FileInputStream(sourceFilePath);
             FileOutputStream fos = new FileOutputStream(destFilePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            System.out.println("File copied.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
        }
    }
}
