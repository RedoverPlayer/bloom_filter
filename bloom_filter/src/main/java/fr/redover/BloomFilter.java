package fr.redover;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class BloomFilter {
    private final IStorage storage;
    private final int hashFunctions;
    private final int size;

    public BloomFilter(int elemsNum, int storageType, int hashFunctions, double desiredFalsePositiveRate) throws NoSuchAlgorithmException{
        this.hashFunctions = hashFunctions;
        this.size = this.optimalSize(elemsNum, desiredFalsePositiveRate);
        // System.out.println("Size: " + this.size);

        // initialize bloom filter
        switch (storageType) {
            case 1 -> storage = new StorageArrayList(size);
            case 2 -> storage = new StorageLinkedList(size);
            default -> storage = new StorageTab(size);
        }
    }

    public void add(String word) {
        int[] hash = Hash.hash(word, hashFunctions, size);
        storage.set(hash[0], 1);
        storage.set(hash[1], 1);
    }

    public boolean contains(String word) {
        int[] hash = Hash.hash(word, hashFunctions, size);
        return storage.get(hash[0]) == 1 && storage.get(hash[1]) == 1;
    }

    private int optimalSize(int n, double desiredFalsePositiveRate) {
        return (int) Math.ceil((n * Math.log(desiredFalsePositiveRate)) / Math.log(1.0 / (Math.pow(2.0, Math.log(2.0)))));
    }
}
