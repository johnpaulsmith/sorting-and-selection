package sortingandselection;

/**
 *
 * @author John Paul Smith
 *
 * A max heap implementation inheriting from a more generic outline.
 */
public class MaxHeap extends Heap {

    public MaxHeap() {
        this(DEFAULT_CAPACITY);
    }

    public MaxHeap(int initialCapacity) {

        heap = new Record[initialCapacity + 1];

        count = 0;
    }

    public MaxHeap(Record[] a) {

        heap = new Record[a.length + 1];

        count = a.length;

        for (int i = 0; i < a.length; ++i) {
            heap[i + 1] = a[i];
        }

        heapify();
    }

    /**
     * Used by the add method to fix the heap after adding a record.
     *
     * @param i move the Record at the specified index of the heap array to its
     * correct position in the heap.
     */
    @Override
    void bubbleUp(int i) {

        Record t;

        while (i > 1 && heap[i].compareTo(heap[i >> 1]) > 0) {

            t = heap[i];
            heap[i] = heap[i >> 1];
            heap[i >> 1] = t;

            i >>= 1;
        }
    }

    /**
     * Used by the remove method to fix the heap after removal of the top
     * element.
     *
     * @param i move the Record at the specified index of the heap array to its
     * correct position in the heap.
     */
    @Override
    void bubbleDown(int i) {

        Record t;

        int j;

        while (2 * i <= count) {

            /*'j' denotes the maximum child of element at index i*/
            j = 2 * i;

            if (j < count && heap[j].compareTo(heap[j + 1]) < 0) {

                ++j;
            }

            if (heap[i].compareTo(heap[j]) >= 0) { /*the correct place had been found*/

                return;
            }

            t = heap[i];
            heap[i] = heap[j];
            heap[j] = t;

            i = j;
        }
    }
}