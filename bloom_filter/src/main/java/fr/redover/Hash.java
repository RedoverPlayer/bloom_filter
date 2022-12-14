package fr.redover;

/**
 * Hash class, handles all hashing operations
 */
public class Hash {
    public static int[] hash(String word, int hashFunctions, int storageSize) {
        int[] index = new int[hashFunctions];

        for (int i = 0; i < hashFunctions; i++) {
            index[i] = Math.abs((word.hashCode() + i) % storageSize);
        }
        return index;
    }
}
