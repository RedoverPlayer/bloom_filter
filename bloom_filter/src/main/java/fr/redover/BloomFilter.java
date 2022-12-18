package fr.redover;

import java.awt.desktop.SystemSleepEvent;

public class BloomFilter {
    private final IStorage storage;
    private final int hashFunctions;
    private final int size;

    /**
     * BloomFilter constructor
     * @param elemsNum Number of elements to store
     * @param storageType Storage type to use (0 = ArrayList, 1 = LinkedList, 2 = Tab)
     * @param desiredFalsePositiveRate Desired false positive rate
     */
    public BloomFilter(int elemsNum, int storageType, double desiredFalsePositiveRate) {
        this.size = this.optimalSize(elemsNum, desiredFalsePositiveRate);
        this.hashFunctions = this.optimalHashFunctions(elemsNum, this.size);

        // initialize bloom filter
        switch (storageType) {
            case 1 -> storage = new StorageArrayList(size);
            case 2 -> storage = new StorageLinkedList(size);
            default -> storage = new StorageTab(size);
        }
    }

    public BloomFilter(int storageType, int hashFunctions, int size) {
        this.size = size;
        this.hashFunctions = hashFunctions;

        // initialize bloom filter
        switch (storageType) {
            case 1 -> storage = new StorageArrayList(size);
            case 2 -> storage = new StorageLinkedList(size);
            default -> storage = new StorageTab(size);
        }
    }

    /**
     * Add an element to the bloom filter
     * @param word Word to add
     */
    public void add(String word) {
        int[] hash = Hash.hash(word, hashFunctions, size);

        for (int i = 0; i < hashFunctions; i++) {
            storage.set(hash[i], true);
        }
    }

    /**
     * Check if an element is in the bloom filter
     * @param word Word to check
     * @return True if the element is in the bloom filter, false otherwise
     */
    public boolean contains(String word) {
        int[] hash = Hash.hash(word, hashFunctions, size);

        for (int i = 0; i < hashFunctions; i++) {
            if (!storage.get(hash[i])) {
                return false;
            }
        }

        return true;
    }

    /**
     * Optimal size of the bloom filter
     * @param n Number of elements to store
     * @param desiredFalsePositiveRate Desired false positive rate
     * @return Optimal size of the bloom filter
     */
    private int optimalSize(int n, double desiredFalsePositiveRate) {
        return (int) Math.ceil((n * Math.log(desiredFalsePositiveRate)) / Math.log(1.0 / (Math.pow(2.0, Math.log(2.0)))));
    }

    /**
     * Optimal number of hash functions
     * @param n Number of elements to store
     * @param m Size of the bloom filter
     * @return Optimal number of hash functions
     */
    private int optimalHashFunctions(int n, int m) {
        return (int) Math.ceil((m / n) * Math.log(2.0));
    }
}
