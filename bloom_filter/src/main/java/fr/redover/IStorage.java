package fr.redover;

/**
 * IStorage interface
 */
public interface IStorage {
    /**
     * Set a value at a specific index
     * @param index Index to set
     * @param value Value to set
     */
    void set(int index, int value);

    /**
     * Get a value at a specific index
     * @param index Index to get
     * @return Value at index
     */
    int get(int index);
}
