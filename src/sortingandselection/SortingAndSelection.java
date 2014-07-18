package sortingandselection;

/**
 * @author John Paul Smith
 *
 * A repository for implementations of sorting and selection algorithms. All
 * methods perform on Objects of the Record class, which contain an integer
 * field to key on.
 */
import java.util.Random;

public class SortingAndSelection {

    /**
     *
     * Private constructor to prevent instantiation of Objects of this class
     */
    private SortingAndSelection() {
    }

    /**
     *
     * Insertion sort on arrays.
     *
     * In place and stable. The ideal quadratic time worst-case sort.
     *
     * @param a the Record array to be sorted
     */
    public static void insertionSort(Record[] a) {

        Record r;
        int y;

        for (int x = 0; x < a.length; ++x) {

            r = a[x];
            y = x;

            while (y > 0 && a[y - 1].compareTo(r) > 0) {
                a[y] = a[--y];
            }

            a[y] = r;
        }
    }

    /**
     *
     * Selection sort on arrays.
     *
     * Stable and in-place.
     *
     * Stability is gained by using a sentinel that keeps track of the smallest
     * key found on each pass. This eliminates the swaps that would normally
     * make the sort not stable. The reduction of swaps also makes this
     * implementation ideal in contrived situations where time complexity is
     * less of a factor than the cost of swapping data; this implementation
     * performs exactly (n - 1) swaps in the worst case.
     *
     * @param a the Record array to be sorted
     */
    public static void selectionSort(Record[] a) {

        int z;

        for (int x = 0; x < a.length - 1; ++x) {

            /*'z' is a sentinel that simply stores of the index of the minimum 
             * element found at each pass of the inner loop.
             */
            z = x;

            for (int y = (x + 1); y < a.length; ++y) {

                if (a[y].compareTo(a[z]) < 0) {
                    z = y;
                }
            }

            if (z != x) {

                Record t = a[z];
                a[z] = a[x];
                a[x] = t;
            }
        }
    }

    /**
     *
     * A very simple sort for arrays. In-place and stable.
     *
     * This algorithm is similar to insertion sort except that instead of
     * efficiently keeping track of the place in the array on each pass, it
     * blindly advances a single pointer forward in the array until an inversion
     * is found, bubbles the inversion towards the beginning of the array, then
     * advances again to the next inversion until there are no more inversions
     * and the pointer is allowed to advance to the end of the array. This is
     * both spectacularly simple and inefficient. I found this code in some of
     * my notes and I have no recollection of where it came from.
     *
     * @param a the Record array to be sorted
     */
    public static void simpleSort(Record[] a) {

        Record t;

        int i = 1;

        while (i < a.length) {

            if (a[i].compareTo(a[i - 1]) < 0) {

                t = a[i];
                a[i] = a[i - 1];
                a[i - 1] = t;

                if (i > 1) {
                    --i;
                }
            } else {
                ++i;
            }
        }
    }

    /**
     *
     * Quicksort on arrays.
     *
     * In-place but not stable.
     *
     * @param a the Record array to be sorted
     */
    public static void quickSort(Record[] a) {
        quickSort(a, 0, a.length - 1);
    }

    /**
     * Quicksort using randomized pivot selection.
     *
     * Recursively arrange increasingly smaller sub-arrays around a randomly
     * chosen pivot value until all method calls have operated on one-element
     * arrays. By selecting pivots at random and swapping equal elements on
     * either side of the pivot, there is no specific input array that can
     * elicit the worst-case behavior (degrading to quadratic time) from this
     * sort. Datasets that cause other implementations of quicksort to degrade
     * to the worst-case are already sorted or nearly-sorted arrays, arrays with
     * a Gaussian distribution, or arrays of many non-unique elements.
     *
     * @param a the Record array to be sorted
     * @param start the index of the first element in the array or sub-array
     * @param end the index of the last element in the array or sub-array
     */
    private static void quickSort(Record[] a, int start, int end) {

        /*If the sub-array a contains more than one element*/
        if (start < end) {

            int i = start - 1,
                    j = end,
                    p = Math.max(start, new Random().nextInt(end));

            Record t, pivot;

            pivot = a[p];

            a[p] = a[end];

            /*Temporarily store the pivot element at the end of this sub-array 
             * while the swapping happens*/
            a[end] = pivot;

            do {
                do {/*Increment 'i' until an element >= pivot is found at index 'i'*/
                    ++i;
                } while (a[i].compareTo(pivot) < 0);

                do {/*Decrement 'j' until an element <= pivot is found at index 'j'*/
                    --j;
                } while (a[j].compareTo(pivot) > 0 && j > start);

                if (i < j) {

                    t = a[i];

                    a[i] = a[j];

                    a[j] = t;
                }
            } while (i < j);

            a[end] = a[i];

            a[i] = pivot;/*Index 'i' is the final destination of pivot*/

            /*Recurse on both sub-arrays to the left and right of the pivot 
             * index. The pivot is now in its final place.
             */
            quickSort(a, start, i - 1);
            quickSort(a, i + 1, end);
        }
    }

    /**
     *
     * Mergesort on arrays.
     *
     * Stable but not in-place.
     *
     * @param a the Record array to be sorted
     */
    public static void mergeSort(Record[] a) {
        mergeSort(a, 0, a.length - 1);
    }

    /**
     *
     * @param a the Record array to be sorted
     * @param start the first index of the Record array to be sorted
     * @param end the last index of the Record array to be sorted
     * @return the sorted Record array
     */
    private static Record[] mergeSort(Record[] a, int start, int end) {

        if (start < end) {

            int m = end >> 1;

            Record[] leftSub = new Record[(m - start) + 1];
            Record[] rightSub = new Record[end - m];

            System.arraycopy(a, 0, leftSub, 0, leftSub.length);
            System.arraycopy(a, m + 1, rightSub, 0, rightSub.length);

            leftSub = mergeSort(leftSub, 0, (leftSub.length - 1));
            rightSub = mergeSort(rightSub, 0, (rightSub.length - 1));

            merge(a, leftSub, rightSub);
        }

        return a;
    }

    /**
     *
     * Merge two Record arrays together by placing their respective elements
     * into a third array.
     *
     * @param a the Record array where left and right will be merged
     * @param left left array to be merged
     * @param right right array to be merged
     */
    private static void merge(Record[] a, Record[] left, Record[] right) {

        for (int i = 0, j = 0, k = 0; k < left.length + right.length; ++k) {

            if (i == left.length) {
                a[k] = right[j++];
            } else if (j == right.length) {
                a[k] = left[i++];
            } else {
                a[k] = left[i].compareTo(right[j]) <= 0 ? left[i++] : right[j++];
            }
        }
    }

    /**
     *
     * Mergesort on linked lists. In-place and stable. An ideal algorithm for
     * sorting linked lists due to stability, a small extra space factor being
     * used, and being fast even compared to quicksort adapted for linked lists.
     *
     * @param c the "head" node (Record) of the list to be sorted
     * @return the sorted linked list
     */
    public static Record mergeSortLinkedList(Record c) {

        if (c == null || c.next == null) {
            return c; /*return a one-element or empty list*/
        }

        Record a = c, b = c.next;

        while (b != null && b.next != null) {

            c = c.next; /*Move forward one record*/

            b = b.next.next; /*Move forward two records ahead*/
        }

        b = c.next;
        c.next = null;/*Sever the end of list 'a'*/
        b.prev = null;/*Sever the beginning of list 'b'*/

        return listMerge(mergeSortLinkedList(a), mergeSortLinkedList(b));
    }

    /**
     *
     * Merge two sorted linked lists together.
     *
     * @param a the "head" node (Record) first list to merge
     * @param b the "head" node (Record) second list to merge
     *
     * @return the "head" node (Record) in the merged list
     */
    private static Record listMerge(Record a, Record b) {

        Record d = new Record(0, "DUMMY"), c = d;

        while (a != null && b != null) {

            if (a.compareTo(b) <= 0) {
                c.next = a;
                a.prev = c;
                c = a;
                a = a.next;
            } else {
                c.next = b;
                b.prev = c;
                c = b;
                b = b.next;
            }
        }

        c.next = (a == null ? b : a);
        c.next.prev = c;

        Record r = d.next;
        r.prev = null;
        return r;
    }

    /**
     *
     * Heapsort on arrays. Create a min heap out of an arbitrary array of and
     * repeatedly select-and-remove the top (minimum) element of the heap.
     *
     * This implementation is neither in-place nor stable.
     *
     * @param a the Record array to be sorted
     */
    public static void heapSort(Record[] a) {

        MinHeap h = new MinHeap(a.length);

        for (Record r : a) {
            h.add(r);
        }

        int i = 0;

        while (!h.isEmpty()) {
            a[i++] = h.remove();
        }
    }

    /**
     *
     * Heapsort on arrays, performed in-place. Builds a max heap and sorts it
     * backwards within the same array.
     *
     * In-place but not stable.
     *
     * @param a the Record array to be sorted
     */
    public static void inPlaceHeapSort(Record[] a) {

        int end = a.length - 1;

        /*
         * Creates a heap out of an array of arbitrary size and permutation
         */
        for (int i = end >> 1; i >= 0; --i) {
            SortingAndSelection.maxHeapBubbleDown(a, i, end);
        }

        while (end > 0) {

            Record m = a[0];

            a[0] = a[end];

            a[end--] = m;

            SortingAndSelection.maxHeapBubbleDown(a, 0, end);
        }
    }

    /**
     * Adapted from the max heap implementation. Used to fix the heap after
     * removal of the top element. Continually swap the element at index i with
     * its maximum child until it is no longer less than either of its children.
     * In this version, the array is partitioned into two parts, the heap and
     * the sorted array.
     *
     * @param heap the Record array to perform on
     * @param i the index of the element in the array to be moved to its correct
     * position in the heap
     * @param end the index of the final element in the heap.
     */
    private static void maxHeapBubbleDown(Record[] heap, int i, int end) {

        Record t;
        int j;

        while ((2 * i) + 1 <= end) {

            j = (2 * i) + 1;

            if (j < end && heap[j].compareTo(heap[j + 1]) < 0) {
                ++j;
            }

            if (heap[i].compareTo(heap[j]) >= 0) {
                return;
            }

            t = heap[i];
            heap[i] = heap[j];
            heap[j] = t;

            i = j;
        }
    }

    /**
     * Bucket sort on arrays.
     *
     * Stable but not in place.
     *
     * @param a the Record array to be sorted
     * @param max the maximum value of the known range of keys in the array to
     * be sorted
     *
     */
    public static void bucketSort(Record[] a, int max) {

        RecordLinkedList[] buckets = new RecordLinkedList[max + 1];

        for (int i = 0; i < buckets.length; ++i) {
            buckets[i] = new RecordLinkedList();
        }

        /*Iterate through the input data set and place all elements in their appropriate bucket*/
        for (int i = 0; i < a.length; ++i) {
            buckets[a[i].getKey()].add(a[i]);
        }

        /*Concatenate all buckets together. Since they have been implemented as 
         * linked-lists, bucket chaining can be done in linear time on the 
         * number of buckets.
         */
        int i = 0;

        for (RecordLinkedList b : buckets) {

            Record r = b.getFirstRecord();

            while (r != null) {

                a[i++] = r;

                r = r.next;
            }
        }
    }

    /**
     * Bucket sort on a linked list. Stable but not in-place.
     *
     * @param a the "head" node (Record) of the list to be sorted
     * @param max the maximum value of the known range of keys in the array to
     * be sorted
     */
    public static Record bucketSortLinkedList(Record a, int max) {

        RecordLinkedList[] buckets = new RecordLinkedList[max + 1];

        for (int i = 0; i < buckets.length; ++i) {
            buckets[i] = new RecordLinkedList();
        }

        Record c = a;

        while (c != null) {

            buckets[c.getKey()].add(new Record(c.getKey(), c.value));

            c = c.next;
        }

        RecordLinkedList sortedList = new RecordLinkedList();

        for (RecordLinkedList b : buckets) {
            sortedList.append(b);
        }

        return sortedList.getFirstRecord();
    }

    /**
     *
     * Counting sort on arrays.
     *
     * Stable but not in-place.
     *
     * @param a the Record array to be sorted
     * @param max the maximum value of the known range of keys in the array to
     * be sorted
     */
    public static void countingSort(Record[] a, int max) {

        /*Create an array of integers, ranging from 0 - max, to store the total 
         * counts of each key in the input array*/
        int[] counts = new int[max + 1];

        for (int i = 0; i < a.length; ++i) {
            ++counts[a[i].getKey()];
        }

        int total = 0, c;

        for (int i = 0; i < counts.length; ++i) {

            c = counts[i]; /*preserve the current value at this index*/
            counts[i] = total; /*this index now holds the count of the total number of keys less than i*/
            total += c;
        }

        Record[] b = new Record[a.length];/*new array to store to sorted list*/

        /*walk through input array again, using the key at each index to find 
         * the "counts" value, which in turn is the index in the output array 
         * that the element(s) with this key must be placed*/
        for (int i = 0; i < a.length; ++i) {
            b[counts[a[i].getKey()]++] = a[i];
        }

        for (int i = 0; i < a.length; ++i) {/*copy sorted list back into original array*/
            a[i] = b[i];
        }
    }

    /**
     * Binary search on an sorted array. This implementation is non-recursive.
     *
     * @param a the Record array to be searched. The array must be sorted in
     * order for the algorithm to perform the search correctly.
     * @param key the key to search for in the array.
     * @return the index in the array of the Record whose key matches the search
     * key k, or -1 if the key is not found.
     */
    public static int binarySearch(Record[] a, int key) {

        int i = 0,
                j = a.length - 1,
                k;

        while (i <= j) {

            /**
             * Using "k = (i + j) >> 1" also works but can potentially cause
             * integer overflow if (i + j) > Integer.MAX_VALUE
             */
            k = i + ((j - i) >> 1);

            if (key == a[k].getKey()) {
                return k;
            } else if (key < a[k].getKey()) {
                j = k - 1;
            } else {
                i = k + 1;
            }
        }

        return -1;
    }

    /**
     * Quickselect on an array. Select the element with the k-th smallest key in
     * an unsorted array in expected O(n) time. This algorithm actually selects
     * the element that would occupy index (k - 1) if the input array were
     * sorted, which would be the k-th smallest key if the input array is a set
     * of unique key values. A common application for this algorithm is finding
     * the median key in a set of unsorted keys.
     *
     * @param a the Record array from which to select from
     * @param k the index of the element such that the element would occupy
     * index (k - 1) in a sorted array
     * @return the Record with the k-th smallest key
     */
    public static Record quickSelect(Record[] a, int k) {
        return SortingAndSelection.quickSelect(a, k, 0, a.length - 1);
    }

    private static Record quickSelect(Record[] a, int k, int start, int end) {

        if (start >= end) {
            return a[start];
        }

        int i = start - 1,
                j = end,
                p = Math.max(start, new Random().nextInt(end));

        Record t, pivot;

        pivot = a[p];

        a[p] = a[end];

        /*Temporarily store the pivot element at the end of this sub-array 
         * while the swapping happens*/
        a[end] = pivot;

        do {

            do {/*Increment 'i' until an element >= pivot is found at index 'i'*/
                ++i;
            } while (a[i].compareTo(pivot) < 0);

            do {/*Decrement 'j' until an element <= pivot is found at index 'j'*/
                --j;
            } while (a[j].compareTo(pivot) > 0 && j > start);

            if (i < j) {

                t = a[i];

                a[i] = a[j];

                a[j] = t;
            }

        } while (i < j);

        a[end] = a[i];

        a[i] = pivot;/*Index 'i' is the final destination of pivot*/

        int offset = (i - start) + 1;

        if (k == offset) {
            return a[i];
        } else if (k < offset) {
            return quickSelect(a, k, start, i - 1);
        } else {
            return quickSelect(a, k - offset, i + 1, end);
        }
    }
}