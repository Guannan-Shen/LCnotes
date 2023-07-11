# Sorting

## Analyzing Computational Time in Random-access Machine (RAM) model
The RAM model contains instructions such as arithmetic(add, subtract, multiply, 
remainder, floor), data movement (load, store, copy), and control (conditional, 
unconditional branch, subroutine call and return). Data types in RAM model are 
integer, floating point, and character.  
There are special cases, such as the exponentiation calculation x^n, taking time 
as log(n), however, since 2^n equals shift left which takes constant time, we 
just always treat exponentiation as constant time.  
The RAM model does not account for memory hierarchy, it does not model caches nor 
virtual memory.  

Running time depends on input size, where input size depends on the problem. 
Input size can be **the number of items**, or total number of bits in two integer 
multiplication problem. In some case, if the input is a graph, the input size is 
characterized by **both the number of vertices and edges**.  

We'll usually concentrate on finding only the **worst-case running time**. In 
some particular cases, interested in the **average-case running time**.  

From now on, consider running time as **order of growth**, thus only focus on 
highest-order time.

## Insertion Sort (in-place sort)
Start with insertion sort, which is an efficient algorithm for sorting a small 
number of elements. Insertion sort works the way you might sort a hand of playing 
cards.  
When the following procedure is done, array A[1 : n] contains the original values,
but in sorted order, from smallest to greatest.  
Subarray means a contiguous portion of the array.  
```Pseudocode
for i = 2 to n
    key = A[i]
    // Insert the key to the sorted subarray from A[1] to A[i - 1]
    // compare the key and the element from right to left within the range
    j = i - 1
    while j > 0 and A[j] > key
        A[j + 1] = A[j]
        j = j - 1
    A[j + 1] = key
```

The subarray A[1 : i - 1] holds the condition formally called as **a loop 
invariant**. Loop invariants help us understand why an algorithm is correct. It 
is a property or condition that is true immediately before and immediately after 
each iteration of a loop (mathematical induction): 
- Initialization: it is true prior to the first iteration of the loop (base case).
- Maintenance: If it is true before an iteration of the loop, it remains true 
  before the next iteration.
- Termination: When the loop terminates, the loop invariant gives us a useful 
  property that helps show that the algorithm is correct.


As in insertion sort, the running time of an algorithm is **fixed** for a given 
input. In best case, running time is **linear**, worst case is **quadratic 
function** of **n**. In the order of growth form, using **Theta(n^2)**.

## Divide-and-conquer method
Many useful algorithm are **recursive**. These algorithms typically follow the 
**divide-and-conquer** method: they break the problem into several subproblems 
which are similar to the original problem but have size, solve subproblems 
recursively and then combine solutions. If the subproblem is small enough, makes 
it the **base case**.
Given the **merge sort** algorithm as the example, in each step, it sorts a 
subarray A[p:r], starting with the entire array A[1:n] and recursing down to 
smaller and smaller subarrays:
- Divide: divide the subarray A[p:r] to be sorted into two adjacent subarrays, 
  each of half the size, using midpoint q.
- Conquer: sorting each of the two subarrays recursively using merge sort.
- Combine: by merging the two sorted subarrays back together.

The recusion "bottom out", it reaches the base case, one element. The key 
operation is the "combine" step, using auxiliary procedure (helper function) 
"Merge". 

### Analysis of Merge Sort Running Time
The worst-case running time, on *n* input numbers.  
- Divide: the divide step just a one-step computation, taking constant time **D(n)
 = Theta(1) **.
- Conquer: two subproblems, contributes **2T(n/2)** running time, ignoring the 
  floors and ceilings.
- Combine: merge n elements takes linear time, **C(n) = Theta(n)**.
- Based on the **master theorem**, **T(n) = Theta(nlgn)**. Or understand this 
  with recursion tree, we have a **lgn + 1** levels recursion tree. At each level.
  there are **2^i** nodes, more nodes and less the size of the subproblems. 
  Therefore, the combine step/ each level always takes **Theta(n)** time, such as at 
  the lowest level, there are n nodes each has a size 1. As a result, total 
  running time is **no. levels multiply running time at each level**, that is 
  **nlgn**.

