# Bloom Filter in Java

## Context
The goal of this project was to implement a Bloom filter in Java and evaluate its performance using a benchmark.

## What is a Bloom Filter?
A Bloom filter is a space-efficient probabilistic data structure that is used to test whether an element is a member of a set. It is faster than checking if the data is present in the set, but it has a small probability of false positives. It can be used when the small probability of false positives is not a problem / can be double checked, to match an url pattern, for example.
A Bloom Filter uses a byte array to store the data. Each element is hashed and the corresponding bits are set to 1. When checking if an element is present in the set, the element is hashed and the corresponding bits are checked. If all the bits are set to 1, the element is probably present in the set. If at least one bit is set to 0, the element is definitely not present in the set.

## Benchmark
To test the performance of the Bloom Filter, I used a Benchark class. The benchmark uses jmh to measure the average time for adding elements to the Bloom Filter as well as for testing if an element is present in the Bloom Filter. The benchmark has a parameter for the number of elements to add to it to compare the performance with different sizes.

## Results
![bench1](img/bench1.png)  
```
BloomBenchmark.arrayListAdd              7000  avgt    5     409,776 ±    396,579  us/op
BloomBenchmark.arrayListContains         7000  avgt    5     393,195 ±    302,983  us/op
BloomBenchmark.linkedListAdd             7000  avgt    5  279928,060 ±  11075,822  us/op
BloomBenchmark.linkedListContains        7000  avgt    5  140827,180 ±   5994,355  us/op
BloomBenchmark.tabAdd                    7000  avgt    5     493,989 ±   1454,171  us/op
BloomBenchmark.tabContains               7000  avgt    5     379,203 ±    256,367  us/op
```
Here is the result of the benchmark with larger and larger number of elements. We can see that the arrayList is the slowest storage type, with a complexity that seems exponential, while the ArrayList and table storage types seems to have a linear complexity. There is already a function to calculate the optimal size of the Bloom Filter. It is calculated as 
```java
Math.ceil((n * Math.log(desiredFalsePositiveRate)) / Math.log(1.0 / (Math.pow(2.0, Math.log(2.0)))))
```
where `n` is the number of elements to add to the Bloom Filter and `desiredFalsePositiveRate` is the desired false positive rate (here 0.01).

----
![bench2](img/bench2.png)  
```
BloomBenchmark.arrayListAdd              7000  avgt    5     613,887 ±   440,327  us/op
BloomBenchmark.arrayListContains         7000  avgt    5     540,762 ±   415,092  us/op
BloomBenchmark.linkedListAdd             7000  avgt    5  280871,820 ±  5605,365  us/op
BloomBenchmark.linkedListContains        7000  avgt    5  141705,320 ±  2929,759  us/op
BloomBenchmark.tabAdd                    7000  avgt    5     522,676 ±   409,755  us/op
BloomBenchmark.tabContains               7000  avgt    5     527,860 ±   352,732  us/op
```
I then added a function to calculate the optimal number of hashes as per wikipedia (https://en.wikipedia.org/wiki/Bloom_filter#Optimal_number_of_hash_functions). The optimal number of hashes is calculated as
```java
Math.ceil((m / n) * Math.log(2.0))
```
where `m` is the size of the Bloom Filter and `n` is the number of elements to add to it. I then ran the benchmark again with the optimal number of hashes. We can see that the difference is not very significant, it is within the error margin.


## Why does the results differ
The results differ because the linkedList has a complexity of O(n) for adding elements, while the ArrayList and table have a complexity of O(1). 

## What can we learn from this
Using the linkedList for a BloomFilter is suboptimal and should be avoided. The algorithmic complexity of the LinkedList is orders of magnitude worse than the complexity of the ArrayList and the table.
