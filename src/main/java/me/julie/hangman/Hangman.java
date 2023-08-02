package me.julie.hangman;

import java.util.*;

public class Hangman {
    public static void main(String[] args) {
        System.out.println("""

                Let's play Hangman!
                Pick a category:
                [1] 50 States
                [2] Animals
                [3] Random Words""");
        final Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        String fileName;
        String category;
        switch (choice) {
            case "1":
                fileName = "states.txt";
                category = "50 States";
                break;
            case "2":
                fileName = "animals.txt";
                category = "Animals";
                break;
            case "3":
                fileName = "random.txt";
                category = "Random Words";
                break;
            default:
                System.err.println("Choose a category.");
                return;
        }

        final Scanner fileScanner = new Scanner(Objects.requireNonNull(Hangman.class.getClassLoader().getResourceAsStream(fileName)));
        ArrayList<String> words = new ArrayList<>();
        while (fileScanner.hasNext()) {
            words.add(fileScanner.nextLine());
        }

        Random random = new Random();
        int num = random.nextInt(words.size());
        String answer = words.get(num).toLowerCase();
        char[] answerLetters = answer.toCharArray(); // answer to char array
        char[] inputs = new char[answerLetters.length]; // empty array for user's answer inputs

        System.out.println("[Category: " + category + "] \nInput one letter at a time!");
        printCurrentLetters(inputs);
        HashSet<Character> incorrect = new HashSet<>(); // no duplicates

        while (!Arrays.equals(inputs, answerLetters) && incorrect.size() != 7) {
            char guess = Character.toLowerCase(scanner.nextLine().toCharArray()[0]);
            for (int i = 0; i < inputs.length; i++) { // updating inputs array with correct guess
                if (guess == answerLetters[i]) {
                    inputs[i] = guess;
                }
            }

            if (answer.contains(String.valueOf(guess))) {
                System.out.println("Correct!");
            } else {
                incorrect.add(guess);
            }

            printHangman(incorrect.size());
            printCurrentLetters(inputs);

            ArrayList<Character> incorrectList = new ArrayList<>(incorrect);
            if (incorrectList.size() == 0) {
                System.out.println("Incorrect letters: none");
                continue;
            }
            System.out.print("Incorrect letters: ");
            for (int i = 0; i < incorrect.size(); i++) { // has incorrect numbers
                System.out.print(incorrectList.get(i));
                if (i < incorrectList.size() - 1) {
                    System.out.print(" "); // space in between each letter
                } else {
                    System.out.println(); // new line
                }
            }
        }
        if (incorrect.size() < 7) {
            System.out.println("\nCongrats! You win!");
        } else {
            System.out.println("\nGame over! The state was " + answer + "!");
        }
    }

    // printing out the updates in string form not array
    public static void printCurrentLetters(char[] inputs) {
        System.out.println();
        for (int i = 0; i < inputs.length; i++) {
            char letter = inputs[i];
            if (letter == Character.MIN_VALUE) { // empty
                System.out.print("_");
            } else {
                System.out.print(letter);
            }
            if (i < inputs.length - 1) { // last one doesn't have a space after
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    private static void printHangman(int incorrect) {
        switch (incorrect) {
            case 0: System.out.println(
                    "  ┌─────┬\n" +
                            "  │\n" +
                            "  │\n" +
                            "  │\n" +
                            "  │\n" +
                            "──┴──"
            );
                break;
            case 1: System.out.println(
                    "  ┌─────┬\n" +
                            "  │     ◯\n" +
                            "  │\n" +
                            "  │\n" +
                            "  │\n" +
                            "──┴──"
            );
                break;
            case 2: System.out.println(
                    "  ┌─────┬\n" +
                            "  │     ◯\n" +
                            "  │     │\n" +
                            "  │\n" +
                            "  │\n" +
                            "──┴──"
            );
                break;
            case 3: System.out.println(
                    "  ┌─────┬\n" +
                            "  │     ◯\n" +
                            "  │    \\│\n" +
                            "  │\n" +
                            "  │\n" +
                            "──┴──"
            );
                break;
            case 4: System.out.println(
                    "  ┌─────┬\n" +
                            "  │     ◯\n" +
                            "  │    \\│/\n" +
                            "  │\n" +
                            "  │\n" +
                            "──┴──"
            );
                break;
            case 5: System.out.println(
                    "  ┌─────┬\n" +
                            "  │     ◯\n" +
                            "  │    \\│/\n" +
                            "  │     │\n" +
                            "  │\n" +
                            "──┴──"
            );
                break;
            case 6: System.out.println(
                    "  ┌─────┬\n" +
                            "  │     ◯\n" +
                            "  │    \\│/\n" +
                            "  │     │\n" +
                            "  │    /\n" +
                            "──┴──"
            );
                break;
            case 7: System.out.println(
                    "  ┌─────┬\n" +
                            "  │     ◯\n" +
                            "  │    \\│/\n" +
                            "  │     │\n" +
                            "  │    / \\\n" +
                            "──┴──"
            );
                break;

        }
    }
}