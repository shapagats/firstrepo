import java.io.*;

public class copyimage {
    public static void main(String[] args) {
        String sourceFileName = "image1.png";

        String targetDir = "target/";

        File sourceFile = new File(sourceFileName);
        File targetFile = new File(targetDir + sourceFileName);

        try {
            // Copy the image
            long fileSize = copyImage(sourceFile, targetFile);
            System.out.println("For " + sourceFileName + ", " + fileSize + " bytes copied");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
        }
    }

    public static long copyImage(File sourceFile, File destFile) throws FileNotFoundException, IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            long totalBytesCopied = 0;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
                totalBytesCopied += bytesRead;
            }
            return totalBytesCopied;
        }
    }
}
