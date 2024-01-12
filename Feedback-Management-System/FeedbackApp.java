
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Feedback {
    private String text;

    public Feedback(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

class FeedbackSystem {
    private List<Feedback> feedbackList = new ArrayList<>();
    private static final String FILE_PATH = "feedback.txt";

    public FeedbackSystem() {
        loadFeedbackFromFile();
    }

    public void submitFeedback(String text) {
        Feedback feedback = new Feedback(text);
        feedbackList.add(feedback);
        System.out.println("Feedback submitted successfully!");
        saveFeedbackToFile();
    }

    public List<Feedback> getAllFeedback() {
        return feedbackList;
    }

    private void saveFeedbackToFile() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Feedback feedback : feedbackList) {
                writer.write(feedback.getText() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFeedbackFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                feedbackList.add(new Feedback(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class FeedbackApp {
    public static void main(String[] args) {
        FeedbackSystem feedbackSystem = new FeedbackSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Feedback Management System");
            System.out.println("1. Submit Feedback");
            System.out.println("2. View All Feedback");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter your feedback: ");
                    String text = scanner.nextLine();
                    feedbackSystem.submitFeedback(text);
                    break;
                case 2:
                    List<Feedback> feedbackList = feedbackSystem.getAllFeedback();
                    System.out.println("All Feedback:");
                    for (Feedback feedback : feedbackList) {
                        System.out.println(feedback.getText());
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
