package fr.redover;

/**
 * StorageTab class, uses a tab to store data
 */
public class StorageTab implements IStorage {
    private final boolean[] storage;

    public StorageTab(int size) {
        storage = new boolean[size];
    }

    public void set(int index, boolean value) {
        storage[index] = value;
    }

    public boolean get(int index) {
        return storage[index];
    }
}
