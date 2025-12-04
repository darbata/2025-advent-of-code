package io.darbata;

/*
987654321111111
811111111111119
234234234234278
818181911112111

For each bank:
    1. find the greatest number (creates sub string)
    2. find the greatest number from created substring
    3. create a 2-digit integer
    4. return
Sum returned
return sum

*/

import java.io.File;
import java.util.*;

public class Main {

    private static int findMax(int[] data) {
        int maxVal = data[0];
        int maxValIdx = 0;

        for (int i = 1; i < data.length; i++) {
            if (data[i] > maxVal) {
                maxVal = data[i];
                maxValIdx = i;
            }
        }

        return maxValIdx;
    }

    private static int findMaxBankVoltage(int[] data) {

        // i don't want to be returned the last index, so chop it off
        // if I get the last index I can only create a single digit integer, when I can create a 2 digit
        int greatestNumberIndex = findMax(Arrays.copyOfRange(data, 0, data.length - 1));

        int nextGreatestNumberIndex = 1 + greatestNumberIndex + findMax(Arrays.copyOfRange(data, greatestNumberIndex + 1, data.length));

        return Integer.parseInt("" + data[greatestNumberIndex] + data[nextGreatestNumberIndex]);
    }

    public static void main(String[] args) {
        String filepath = "src/main/resources/input";
        File input = new File(filepath);

        int sum = 0;
        try (Scanner reader = new Scanner(input)) {
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                int maxVoltage = findMaxBankVoltage(data.chars().map(c -> c - '0').toArray());
                sum += maxVoltage;
                System.out.println("+ " + maxVoltage + " = " + sum);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(sum);

    }

}