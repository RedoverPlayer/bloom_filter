package fr.redover;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

/**
 * Benchmark class for BloomFilter
 */
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
@Timeout(time = 10)
@Threads(1)
@Fork(1)
public class BloomBenchmark {
    @Param({"10", "100", "500", "1000", "2000", "3000", "4000", "5000", "6000", "7000", "8000", "9000", "10000"})
    int elemNums;

    // init random number generator
    Random random = new Random();
    BloomFilter bloomFilterArrayList;
    BloomFilter bloomFilterLinkedList;
    BloomFilter bloomFilterTab;

    // Init bloom filters with different storage types
    @Setup(Level.Invocation)
    public void setUp() {
        bloomFilterArrayList = new BloomFilter(elemNums, 1, 0.01);
        bloomFilterLinkedList = new BloomFilter(elemNums, 2, 0.01);
        bloomFilterTab = new BloomFilter(elemNums, 3, 0.01);
    }

    @Benchmark
    public void arrayListAdd(Blackhole bh) {
        for (int i = 0; i < elemNums; i++) {
            bloomFilterArrayList.add(String.valueOf(random.nextInt()));
        }
    }

    @Benchmark
    public void linkedListAdd(Blackhole bh) {
        for (int i = 0; i < elemNums; i++) {
            bloomFilterLinkedList.add(String.valueOf(random.nextInt()));
        }
    }

    @Benchmark
    public void tabAdd(Blackhole bh) {
        for (int i = 0; i < elemNums; i++) {
            bloomFilterTab.add(String.valueOf(random.nextInt()));
        }
    }

    @Benchmark
    public void arrayListContains(Blackhole bh) {
        for (int i = 0; i < elemNums; i++) {
            bh.consume(bloomFilterArrayList.contains(String.valueOf(random.nextInt())));
        }
    }

    @Benchmark
    public void linkedListContains(Blackhole bh) {
        for (int i = 0; i < elemNums; i++) {
            bh.consume(bloomFilterLinkedList.contains(String.valueOf(random.nextInt())));
        }
    }

    @Benchmark
    public void tabContains(Blackhole bh) {
        for (int i = 0; i < elemNums; i++) {
            bh.consume(bloomFilterTab.contains(String.valueOf(random.nextInt())));
        }
    }

    public void benchmark() throws Exception {
        org.openjdk.jmh.Main.main(new String[] {});
    }

    public static void main(String[] args) throws Exception {
        new BloomBenchmark().benchmark();
    }
}
