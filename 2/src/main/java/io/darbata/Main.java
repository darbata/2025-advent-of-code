package io.darbata;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Range {
        private final long start;
        private final long end;

        public Range(long start, long end) {
            this.start = start;
            this.end = end;
        }

        public long getEnd() {
            return end;
        }

        public long getStart() {
            return start;
        }
    }

    private static Range getRange(String data) {
        String[] split = data.split("-");
        long start = Long.parseLong(split[0]);
        long end = Long.parseLong(split[1]);
        return new Range(start, end);
    }

    private static List<Long> findInvalids(Range range) {

        List<Long> invalids = new ArrayList<>();

        for (long i = range.getStart(); i <= range.getEnd(); i++) {
            if (checkInvalid(i)) {
                invalids.add(i);
            }
        }

        return invalids;
    }

    private static boolean checkInvalid(Long id) {
        String sequence = id.toString();

        if (sequence.length() % 2 != 0) {
            return false;
        }

        int mid = sequence.length() / 2;
        String firstHalf = sequence.substring(0, mid);
        String secondHalf = sequence.substring(mid);

        return firstHalf.equals(secondHalf);
    }

    public static void main(String[] args) {


        String inputPath = "src/main/resources/input";
        File input = new File(inputPath);

        try (Scanner scanner = new Scanner(input).useDelimiter(",")) {
            String data;
            List<Long> invalids = new ArrayList<>();
            while (scanner.hasNext()) {
                data = scanner.next();
                Range range = getRange(data.strip());
                invalids.addAll(findInvalids(range));
            }

            Long total = 0L;
            for (Long invalid: invalids) {
                total += invalid;
            }

            System.out.println(total);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}