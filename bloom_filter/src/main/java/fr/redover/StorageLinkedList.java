package fr.redover;

import java.util.LinkedList;

/**
 * StorageLinkedList class, uses LinkedList to store data
 */
public class StorageLinkedList implements IStorage {
    private final LinkedList<Boolean> storage;

    public StorageLinkedList(int size) {
        storage = new LinkedList<>();
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
