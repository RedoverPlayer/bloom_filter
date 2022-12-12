package fr.redover;

import java.util.ArrayList;

/**
 * StorageArrayList class, uses ArrayList to store data
 */
public class StorageArrayList implements IStorage {
    private final ArrayList<Integer> storage;

    public StorageArrayList(int size) {
        storage = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            storage.add(0);
        }
    }

    public void set(int index, int value) {
        storage.set(index, value);
    }

    public int get(int index) {
        return storage.get(index);
    }
}
