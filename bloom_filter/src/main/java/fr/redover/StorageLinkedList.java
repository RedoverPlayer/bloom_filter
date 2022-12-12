package fr.redover;

import java.util.LinkedList;

/**
 * StorageLinkedList class, uses LinkedList to store data
 */
public class StorageLinkedList implements IStorage {
    private final LinkedList<Integer> storage;

    public StorageLinkedList(int size) {
        storage = new LinkedList<>();
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
