package me.julie.hangman;

import java.util.Scanner;

public class Learning {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Input a number:");
            final String input = scanner.nextLine();
            final double num;
            try {
                num = Double.parseDouble(input);
            } catch (final NumberFormatException ignored) {
                System.out.println("Not a number.");
                continue;
            }
            System.out.println(doubleToString(num) + " * 2 = " + doubleToString(num * 2));
        }
    }
    private static String doubleToString(final double number) {
        final String numStr = Double.toString(number);
        if (numStr.endsWith(".0")) {
            final long nums = (long) number;
            return Long.toString(nums);
        }
        return numStr;
    }
}
