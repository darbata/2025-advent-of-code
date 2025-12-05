package io.darbata;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static class Coords {
        private final int x;
        private final int y;

        public Coords(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
    };

    public static boolean isReachable(List<List<Character>> factory, int n, int m, Coords coords) {

        int adjacentRolls = 0;

        for (int[] dir : directions) {
            int x = coords.getX() + dir[0];
            int y = coords.getY() + dir[1];

            if (x >= 0 && y >= 0 && x < n && y < m) {
                char space = factory.get(x).get(y);
                if (space == '@') {
                    adjacentRolls++;
                }
            }

        }

        return adjacentRolls < 4;
    }

    public static void main(String[] args) {

        String filePath = "src/main/resources/input";
        File input = new File(filePath);

        List<List<Character>> factory = new ArrayList<>();
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNext()) {
                List<Character> line = scanner.nextLine().chars().mapToObj(c -> (char) c).collect(Collectors.toList());
                factory.add(line);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        int n = factory.size();
        int m = factory.getFirst().size();

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (factory.get(i).get(j) == '@') {
                    if (isReachable(factory, n, m, new Coords(i, j))) {
                        count++;
                    }
                }
            }
        }

        System.out.println(count);

    }

}