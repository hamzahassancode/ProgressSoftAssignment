import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String content = "ID,from Currency ,to Currency ,deal Timestamp,deal Amount\n" +
                        "1,EUR,USD,2024-05-17 10:30:00,500\n" +
                        "2,GBP,USD,2024-05-17 10:30:00,1500\n" +
                        "3,JPY,USD,2024-05-17 10:30:00,300\n" +
                        "4,CHF,USD,2024-05-17 10:30:00,400\n" +
                        "5,CAD,USD,2024-05-17 10:30:00,600\n" +
                        "6,AUD,USD,2024-05-17 10:30:00,700\n" +
                        "7,NZD,USD,2024-05-17 10:30:00,800\n" +
                        "7,SEK,USD,2024-05-17 10:30:00,900\n" +
                        "9,NOK,USD,2024-05-17 10:30:00,1000\n" +
                        "10,DKK,USD,2024-05-17 10:30:00,1100\n" +
                        "11,ZAR,USD,2024-05-17 10:30:00,1200\n" +
                        "12,HKD,USD,2024-05-17 10:30:00,1300\n" +
                        "13,SGD,USD,2024-05-17 10:30:00,1400\n" +
                        "14,THB,USD,2024-05-17 10:30:00,1500\n" +
                        "15,MYR,USD,2024-05-17 10:30:00,1600\n" +
                        "15,IDR,USD,2024-05-17 10:30:00,1700\n" +
                        "16,PHP,USD,2024-05-17 10:30:00,1800\n" +
                        "17,VND,USD,2024-05-17 10:30:00,1900\n";
        
        // Specify the file path where you want to save the CSV file
        String filePath = "test.csv";
        
        // Save the content to the CSV file
        saveToFile(filePath, content);
    }

    private static void saveToFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            System.out.println("File saved successfully: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
