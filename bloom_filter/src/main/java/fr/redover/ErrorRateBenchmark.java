package fr.redover;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
/**
 * Benchmark class for BloomFilter
 */

public class ErrorRateBenchmark {
    public static double testErrorRate(int elemsNum, int hashNum, int filterSize) {
        BloomFilter bloomFilterArrayList = new BloomFilter(0, hashNum, filterSize);
        Random random = new Random();

        int falsePositives = 0;
        int truePositives = 0;

        // Test elements that are not in the bloom filter
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < elemsNum; i++) {
            String element = String.valueOf(random.nextInt());
            bloomFilterArrayList.add(element);
            arrayList.add(element);
        }

        for (int i = 0; i < elemsNum; i++) {
            String element = String.valueOf(random.nextInt());
            if (bloomFilterArrayList.contains(element)) {
                if (!arrayList.contains(element)) {
                    falsePositives++;
                }
            }
        }

        for (int i = 0; i < arrayList.size(); i++) {
            String element = arrayList.get(i);
            if (bloomFilterArrayList.contains(element)) {
                if (arrayList.contains(element)) {
                    truePositives++;
                }
            }
        }

        return (double) falsePositives / (double) (falsePositives + truePositives);
    }

    public static void main(String[] args) {
        int elemsNum = 2000;
        System.out.println(testErrorRate(elemsNum, 1, elemsNum/100));
        System.out.println(testErrorRate(elemsNum, 3, elemsNum/20));
        System.out.println(testErrorRate(elemsNum, 5, elemsNum/10));
    }
}
