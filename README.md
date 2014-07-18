Sorting and selection
=======================================

John Paul Smith

Implementations of several common sorting and selection algorithms. Included 
as well are heaps to facilitate the heap sorts and a linked list for several
sorts that are capable of sorting linked lists efficiently (mergesort does
best but bucket sort can sort linked lists as well provided the range of keys
is small enough to make it efficient and the linear auxiliary space complexity
is not an issue). The big three n log n sorts (quicksort, mergesort and heap
sort) are included in several variations. Bubble sort is not included but I
found a sort in my notes that was labeled as "simple sort" which is actually
even more inefficient than bubble sort but effectively does the same thing. I
do not know where I found this sort but I included it as a curiosity as it is
the  only single-pointer sort I've seen. For linear-time sorts, bucket sort and
counting sort are included but I left radix sort out of the code as I find it
clumsy to implement in high-level languages like Java. The counting sort
implementation is actually somewhat of an abuse of the sort though; the
algorithm truly works best when the input is an array of primitive integers
and not key-value records. Still, in cases where the range of keys is 
relatively small, counting sort will handedly best even the most intelligently
implemented quicksort. Quicksort's counterpart, quickselect, is an excellent
algorithm for finding the median key in an unsorted dataset; this can be 
accomplished in linear time. 