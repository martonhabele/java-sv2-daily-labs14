package day04;

import java.util.Map;
import java.util.TreeMap;

public class PairFinder {
    public static void main(String[] args) {
        PairFinder pairFinder = new PairFinder();
        System.out.println(pairFinder.findPairs(new int[]{7, 1, 5, 7, 3, 3, 5, 7, 6, 7}));
        System.out.println(pairFinder.findPairs(new int[]{5, 1, 4, 5}));
    }

    public int findPairs(int[] array) {
        Map<Integer, Integer> pairs = new TreeMap<>();

        for (int i : array) {
            if(!pairs.containsKey(i)) {
                pairs.put(i, 1);
            } else {
                pairs.put(i, pairs.get(i) + 1);
            }
        }
        return pairs.values().stream()
                .mapToInt(i -> i / 2).sum();
    }
}