# Percolation



## Submission


### Submit 1 - First try
No idea why my code works....at least most of them.
```
Correctness:  23/26 tests passed
Memory:       5/8 tests passed
Timing:       0/9 tests passed

Aggregate score: 63.75%
```


### Submit 2 - Improvement
fixed the counting issue.

### Submit 3 - Two Virtual Sites

#### Problems
  * backwash

### Submit 4 - Use One Virtual Site
How about just use top virtual site. no backwash problem.
```
Correctness:  26/26 tests passed
Memory:       6/8 tests passed
Timing:       1/9 tests passed

Aggregate score: 75.28%
```
### Submit 5 - Passed
Merge Submit 3 & 4, use two `WeightedQuickUnionUF` instance.
  * first instance with two virtual sites used by `percolates` function to check whether the system  is percolate.
  * second instance with one top virtual site used by `isFull` function to check whether the specific site is full.

```
Correctness:  26/26 tests passed
Memory:       8/8 tests passed
Timing:       9/9 tests passed

Aggregate score: 100.00%
```
