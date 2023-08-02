package me.julie.hangman;

import java.util.*;

public class Hangman {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("""

                Let's play Hangman!
                Categories:
                [1] 50 States
                [2] Animals
                [3] Random Words
                Pick a category:\s""");
        String input = scanner.nextLine();
        while (Integer.parseInt(input) < 1 || Integer.parseInt(input) > 3) {
            System.err.println("Invalid input!");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print("Pick a category [1-3]: ");
            input = scanner.nextLine();
        }
        String fileName = "";
        String category = "";
        switch (input) {
            case "1" -> {
                fileName = "states.txt";
                category = "50 States";
            }
            case "2" -> {
                fileName = "animals.txt";
                category = "Animals";
            }
            case "3" -> {
                fileName = "random.txt";
                category = "Random Words";
            }
        }

        final Scanner fileScanner = new Scanner(Objects.requireNonNull(
                Hangman.class.getClassLoader().getResourceAsStream(fileName)));
        ArrayList<String> words = new ArrayList<>();
        while (fileScanner.hasNext()) {
            words.add(fileScanner.nextLine());
        }
        Random random = new Random();
        int num = random.nextInt(words.size());
        String answer = words.get(num).toLowerCase();
        char[] answerLetters = answer.toCharArray(); // answer to char array
        char[] inputs = new char[answerLetters.length]; // empty array for user's answer inputs

        System.out.println("\n[Category: " + category + "] \nInput one letter at a time!");
        printCurrentLetters(inputs);
        HashSet<Character> incorrect = new HashSet<>(); // no duplicates

        while (!Arrays.equals(inputs, answerLetters) && incorrect.size() != 7) {
            char guess = Character.toLowerCase(scanner.nextLine().toCharArray()[0]);
            for (int i = 0; i < inputs.length; i++) { // updating inputs array with correct guesses
                if (guess == answerLetters[i]) {
                    inputs[i] = guess;
                }
            }

            if (!answer.contains(String.valueOf(guess))) {
                incorrect.add(guess);
            }

            printHangman(incorrect.size());
            printCurrentLetters(inputs);

            ArrayList<Character> incorrectList = new ArrayList<>(incorrect);
            if (incorrectList.isEmpty()) {
                System.out.println("Incorrect letters: none");
            } else {
                System.out.print("Incorrect letters: ");
                for (int i = 0; i < incorrect.size(); i++) {
                    System.out.print(incorrectList.get(i));
                    if (i < incorrectList.size() - 1) {
                        System.out.print(" "); // space in between each letter
                    } else {
                        System.out.println(); // new line
                    }
                }
            }
        }

        // end result
        if (incorrect.size() < 7) {
            System.out.println("\nCongrats! You win!");
        } else {
            System.out.println("\nGame over! The answer was " + answer + "!");
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
            case 0 -> System.out.println(
                    """
                              ┌─────┬
                              │
                              │
                              │
                              │
                            ──┴──"""
            );
            case 1 -> System.out.println(
                    """
                              ┌─────┬
                              │     ◯
                              │
                              │
                              │
                            ──┴──"""
            );
            case 2 -> System.out.println(
                    """
                              ┌─────┬
                              │     ◯
                              │     │
                              │
                              │
                            ──┴──"""
            );
            case 3 -> System.out.println(
                    """
                              ┌─────┬
                              │     ◯
                              │    \\│
                              │
                              │
                            ──┴──"""
            );
            case 4 -> System.out.println(
                    """
                              ┌─────┬
                              │     ◯
                              │    \\│/
                              │
                              │
                            ──┴──"""
            );
            case 5 -> System.out.println(
                    """
                              ┌─────┬
                              │     ◯
                              │    \\│/
                              │     │
                              │
                            ──┴──"""
            );
            case 6 -> System.out.println(
                    """
                              ┌─────┬
                              │     ◯
                              │    \\│/
                              │     │
                              │    /
                            ──┴──"""
            );
            case 7 -> System.out.println(
                    """
                              ┌─────┬
                              │     ◯
                              │    \\│/
                              │     │
                              │    / \\
                            ──┴──"""
            );
        }
    }
}