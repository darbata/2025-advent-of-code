package io.darbata;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static int turnDial(int dial, char dir, int num) {

        System.out.println("Rotating dial from " + dial + " " + dir +  " by " + num);

        if (dir == 'R') {
            dial += num;
        } else {
            dial -= num;
        }

        // + 100 to handle to negative
        // modulos again to handle out of range (greater than 100)
        return (dial % 100 + 100) % 100;
    }


    public static void main(String[] args) {

        int dial = 50;

        File input = new File(args[0]);

        int count = 0;

        try (Scanner r = new Scanner(input)) {
            while (r.hasNextLine()) {
                String turn = r.nextLine(); // L54

                char dir = turn.charAt(0);
                int num = Integer.parseInt(turn.substring(1));

                // pass dial, the direction to turn, and spin clicks
                dial = turnDial(dial, dir, num);

                if (dial == 0) {
                    count++;
                }

                if (dial >= 100 || dial < 0) {
                    throw new IllegalArgumentException("Invalid dial: " + dial);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("no file at path");
        }

        System.out.println(count);

    }

}