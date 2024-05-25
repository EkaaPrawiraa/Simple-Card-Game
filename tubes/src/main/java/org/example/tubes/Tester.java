package org.example.tubes;

public class Tester {
    public static void main(String[] args) {
        String input1 = "A01 HIU DARAT 5 3 ACCELERATE DELAY PROTECT";
        String input2 = "A01 DOMBA 5 3 ACCELERATE DELAY PROTECT";

        // Split the input strings
        String[] parts1 = splitInput(input1);
        String[] parts2 = splitInput(input2);

        // Print the resulting parts for input1
        System.out.println("Input 1:");
        for (String part : parts1) {
            System.out.println(part);
        }

        // Print the resulting parts for input2
        System.out.println("\nInput 2:");
        for (String part : parts2) {
            System.out.println(part);
        }
    }

    public static String[] splitInput(String input) {
        // Split the input into parts based on spaces
        String[] parts = input.split(" ");

        // Check if we need to combine the second and third elements
        if (parts.length > 6) {
            // Combine the second and third parts
            String[] result = new String[6];
            result[0] = parts[0];
            result[1] = parts[1] + " " + parts[2];
            result[2] = parts[3];
            result[3] = parts[4];
            result[4] = parts[5];
            result[5] = parts[6];
            return result;
        } else {
            return parts;
        }
    }

}
