package sortingandselection;

/**
 *
 * @author John Paul Smith
 *
 * A simple class to represent a record with a key and an associated value.
 * Records also feature a next and prev reference to function as "nodes" in a
 * linked list.
 */
public class Record implements Comparable<Record> {

    int key;
    String value;
    public Record next, prev;

    public Record(int k, String v) {
        key = k;
        value = v;
        next = prev = null;
    }

    /**
     *
     * @return the integer key associated with this Record
     */
    public int getKey() {
        return key;
    }

    /**
     *
     * @return a String representation of this Record in form of [key:value]
     */
    @Override
    public String toString() {
        return "[" + key + ":" + value + "]";
    }

    /**
     *
     * @param r the Record to compare to this Record
     *
     * @return a negative integer, zero, or a positive integer as the specified
     * key of r is greater than, equal to, or less than this Record's key
     */
    @Override
    public int compareTo(Record r) {

        if (key > r.getKey()) {
            return 1;
        } else if (key == r.getKey()) {
            return 0;
        } else {
            return -1;
        }
    }
}