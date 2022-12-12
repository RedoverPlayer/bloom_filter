package fr.redover;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hash class, handles all hashing operations
 */
public class Hash {
    private final MessageDigest mdSHA256;
    private final MessageDigest mdSHA512;
    private final MessageDigest mdMD5;

    public Hash() throws NoSuchAlgorithmException {
        mdSHA256 = MessageDigest.getInstance("SHA-256");
        mdSHA512 = MessageDigest.getInstance("SHA-512");
        mdMD5 = MessageDigest.getInstance("MD5");
    }

    public int[] hash(String word, int hashFunctions, int storageSize) {
        int[] index = new int[hashFunctions];

        // SHA-256 hash
        byte[] hash256 = mdSHA256.digest(word.getBytes(StandardCharsets.UTF_8));
        index[0] = Integer.parseInt(String.format("%02x", hash256[0]), 16);

        int hashTmp = 0;
        for (int i = 0; i < hash256.length; i++) {
            hashTmp = (hashTmp << 8) | (hash256[i] & 0xff);
        }
        index[0] = Math.abs(hashTmp % storageSize);

        if (hashFunctions > 1) {
            // SHA-512 hash
            byte[] hash512 = mdSHA512.digest(word.getBytes(StandardCharsets.UTF_8));
            index[1] = Integer.parseInt(String.format("%02x", hash512[0]), 16);

            hashTmp = 0;
            for (int i = 0; i < hash512.length; i++) {
                hashTmp = (hashTmp << 8) | (hash512[i] & 0xff);
            }
            index[1] = Math.abs(hashTmp % storageSize);
        }

        if (hashFunctions > 2) {
            // MD5 hash
            byte[] hashMD5 = mdMD5.digest(word.getBytes(StandardCharsets.UTF_8));
            index[2] = Integer.parseInt(String.format("%02x", hashMD5[0]), 16);

            hashTmp = 0;
            for (int i = 0; i < hashMD5.length; i++) {
                hashTmp = (hashTmp << 8) | (hashMD5[i] & 0xff);
            }
            index[2] = Math.abs(hashTmp % storageSize);
        }

        return index;
    }
}
