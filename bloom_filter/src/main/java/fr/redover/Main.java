package fr.redover;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        BloomFilter bloomFilter = new BloomFilter(256, 0, 3, 0.01);
        bloomFilter.add("Hello");
        bloomFilter.add("World");
        System.out.println(bloomFilter.contains("Hello"));
        System.out.println(bloomFilter.contains("World"));
        System.out.println(bloomFilter.contains("Foo"));
        System.out.println(bloomFilter.contains("Bar"));
    }
}
