package test;

import fr.redover.BloomFilter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * BloomTest class, tests the BloomFilter classes
 */
public class BloomTest {
    private BloomFilter bloomFilterArrayList;
    private BloomFilter bloomFilterLinkedList;
    private BloomFilter bloomFilterTab;

    @Before
    public void setUp() throws Exception {
        bloomFilterArrayList = new BloomFilter(1000, 1, 3, 0.01);
        bloomFilterLinkedList = new BloomFilter(1000, 2, 3, 0.01);
        bloomFilterTab = new BloomFilter(1000, 3, 3, 0.01);
    }

    @Test
    public void containsTestArrayList() {
        bloomFilterArrayList.add("Hello");
        bloomFilterArrayList.add("World");
        assertTrue(bloomFilterArrayList.contains("Hello"));
        assertTrue(bloomFilterArrayList.contains("World"));
        assertFalse(bloomFilterArrayList.contains("Foo"));
        assertFalse(bloomFilterArrayList.contains("Bar"));
    }

    @Test
    public void containsTestLinkedList() {
        bloomFilterLinkedList.add("Hello");
        bloomFilterLinkedList.add("World");
        assertTrue(bloomFilterLinkedList.contains("Hello"));
        assertTrue(bloomFilterLinkedList.contains("World"));
        assertFalse(bloomFilterLinkedList.contains("Foo"));
        assertFalse(bloomFilterLinkedList.contains("Bar"));
    }

    @Test
    public void containsTestTab() {
        bloomFilterTab.add("Hello");
        bloomFilterTab.add("World");
        assertTrue(bloomFilterTab.contains("Hello"));
        assertTrue(bloomFilterTab.contains("World"));
        assertFalse(bloomFilterTab.contains("Foo"));
        assertFalse(bloomFilterTab.contains("Bar"));
    }
}
