import java.io.*;

public class FileEncryption {

    public static void main(String[] args) {
        String sourceFilePath = "secretdocc.txt";
        String encryptedFilePath = "text2.txt";
        String decryptedFilePath = "text1.txt";
        int encryptionKey = 3;

       
        encryptFile(sourceFilePath, encryptedFilePath, encryptionKey);
        System.out.println("File encrypted successfully.");

        
        decryptFile(encryptedFilePath, decryptedFilePath, encryptionKey);
        System.out.println("File decrypted successfully.");
    }

    public static void encryptFile(String sourceFilePath, String encryptedFilePath, int encryptionKey) {
        try (FileInputStream fis = new FileInputStream(sourceFilePath);
             FileOutputStream fos = new FileOutputStream(encryptedFilePath)) {
            int data;
            while ((data = fis.read()) != -1) {
                // Apply Caesar cipher encryption
                data = (data + encryptionKey) % 256;
                fos.write(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decryptFile(String encryptedFilePath, String decryptedFilePath, int encryptionKey) {
        try (FileInputStream fis = new FileInputStream(encryptedFilePath);
             FileOutputStream fos = new FileOutputStream(decryptedFilePath)) {
            int data;
            while ((data = fis.read()) != -1) {
               
                data = (data - encryptionKey + 256) % 256;
                fos.write(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
