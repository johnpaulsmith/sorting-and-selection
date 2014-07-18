package sortingandselection;

/**
 *
 * @author John Paul Smith
 *
 * A basic binary heap data structure. This class is abstract so that inheriting
 * classes {@link MinHeap} and {@link MaxHeap} can implement their own versions
 * of the bubbleUp and bubbleDown methods.
 */
public abstract class Heap {

    static final int DEFAULT_CAPACITY = 16;
    static final double THRESHOLD = .5;
    static final double OPTIMAL_RATIO = .8;
    Record[] heap;
    int count;

    /**
     *
     * @param r The Record being added to the heap
     */
    public void add(Record r) {

        if (count == heap.length - 1) {
            growHeap();
        }

        heap[++count] = r;

        if (count > 1) {
            bubbleUp(count);
        }
    }

    /**
     *
     * @return the Record removed from the heap, or null if the Record was not
     * found.
     */
    public Record remove() { /*return null if heap is empty*/

        if (count > 0) {

            Record top = heap[1];

            heap[1] = heap[count];

            heap[count--] = null;

            if (count > 0) {
                bubbleDown(1);
            }

            double r = (double) count / (double) (heap.length - 1);

            if (r < THRESHOLD && heap.length - 1 > DEFAULT_CAPACITY) {
                shrinkHeap();
            }

            return top;
        }

        return null;
    }

    abstract void bubbleUp(int i);

    abstract void bubbleDown(int i);

    /**
     *
     * Increase the size of the heap
     */
    void growHeap() {

        int newCapacity = (count > 1024) ? count + (count >> 2) : count << 1;

        Record[] newHeap = new Record[newCapacity + 1];

        System.arraycopy(heap, 0, newHeap, 0, heap.length);

        heap = newHeap;
    }

    /**
     *
     * Decrease the size of the heap
     */
    void shrinkHeap() {

        int newCapacity = (int) (count / OPTIMAL_RATIO);

        Record[] newHeap = new Record[newCapacity + 1];

        System.arraycopy(heap, 0, newHeap, 0, newHeap.length);

        heap = newHeap;
    }

    /**
     *
     * Transform the internal array into a heap. Used by the overloaded
     * constructor to take an array as a parameter, copy it, and turn it into a
     * heap.
     */
    void heapify() {

        for (int i = count >> 1; i > 0; --i) {
            bubbleDown(i);
        }
    }

    /**
     *
     * @return {@code true} if this heap is empty
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     *
     * @return the number of elements in this heap
     */
    public int size() {
        return count;
    }

    /**
     *
     * @return a String representation of the heap array
     */
    @Override
    public String toString() {

        if (count == 0) {
            return "{}";
        }

        StringBuilder s = new StringBuilder();

        for (int i = 1;; ++i) {

            s.append(heap[i]);

            if (i == count) {
                return s.toString();
            }

            s.append(", ");
        }
    }
}