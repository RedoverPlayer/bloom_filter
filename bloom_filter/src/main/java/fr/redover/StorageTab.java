package fr.redover;

/**
 * StorageTab class, uses a tab to store data
 */
public class StorageTab implements IStorage {
    private final int[] storage;

    public StorageTab(int size) {
        storage = new int[size];
    }

    public void set(int index, int value) {
        storage[index] = value;
    }

    public int get(int index) {
        return storage[index];
    }
}
