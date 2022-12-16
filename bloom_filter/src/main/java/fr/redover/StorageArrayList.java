package fr.redover;

import java.util.ArrayList;

/**
 * StorageArrayList class, uses ArrayList to store data
 */
public class StorageArrayList implements IStorage {
    private final ArrayList<Boolean> storage;

    public StorageArrayList(int size) {
        storage = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            storage.add(false);
        }
    }

    public void set(int index, boolean value) {
        storage.set(index, value);
    }

    public boolean get(int index) {
        return storage.get(index);
    }
}
