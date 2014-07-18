package sortingandselection;

/**
 *
 * @author John Paul Smith
 *
 * A basic linked list implementation.
 */
public class RecordLinkedList {

    private Record head, tail;
    private int count;

    public RecordLinkedList() {

        head = tail = null;
        count = 0;
    }

    /**
     *
     * Add a Record to this list. Records are added to the end of the list by
     * default. Calling this method is identical to calling addToEnd.
     *
     * @param r the Record to be added to this list.
     */
    public void add(Record r) {
        addToEnd(r); /*add to end of list by default*/
    }

    /**
     *
     * Add a Record to the front of this list
     *
     * @param r the Record to be added to the front of this list
     */
    public void addToFront(Record r) {

        if (count == 0) {
            head = tail = r;
        } else {
            r.next = head;
            head.prev = r;
            head = r;
        }

        ++count;
    }

    /**
     *
     * Add a Record to the end of this list
     *
     * @param r the Record to be added to the end of this list
     */
    public void addToEnd(Record r) {

        if (count == 0) {
            head = tail = r;
        } else {
            r.prev = tail;
            tail.next = r;
            tail = r;
        }

        ++count;
    }

    /**
     *
     * Search this list for the first sequential Record containing the key k and
     * delete this Record if it exists.
     *
     * @param k the key value to be deleted from this list
     */
    public void delete(int k) {

        if (!isEmpty()) {

            Record r = head;

            while (r.next != null && r.key != k) {
                r = r.next;
            }

            if (r.key == k) { /*If a Record with a matching key was found in the list*/

                if (r.prev != null) {
                    r.prev.next = r.next;
                } else {
                    head = r.next;
                }

                if (r.next != null) {
                    r.next.prev = r.prev;
                } else {
                    tail = r.next;
                }

                --count;
            }
        }
    }

    /**
     *
     * @return the "head" Record of this list
     */
    public Record getFirstRecord() {
        return head;
    }

    /**
     *
     * @return the "tail" Record of this list
     */
    public Record getLastRecord() {
        return tail;
    }

    /**
     * Append another {@code RecordLinkedList} to the end of this list
     *
     * @param l the RecordLinkedList to be appended
     */
    public void append(RecordLinkedList l) {

        if (!isEmpty() && !l.isEmpty()) {

            tail.next = l.head;

            l.head.prev = tail;

            tail = l.getLastRecord();

        } else if (!l.isEmpty()) {

            head = l.getFirstRecord();

            tail = l.getLastRecord();

        } else {
            /*nothing to append*/
        }

        count += l.size();
    }

    /**
     *
     * @return A copy of this RecordLinkedList with newly created Record objects
     * identical to the Record objects found in this RecordLinkedList. The order
     * of Record objects will be identical.
     */
    public RecordLinkedList copy() {

        RecordLinkedList b = new RecordLinkedList();

        Record r = head;

        while (r != null) {

            b.add(new Record(r.getKey(), r.value));

            r = r.next;
        }

        return b;
    }

    /**
     *
     * @return an int representing the size of the list
     */
    public int size() {
        return count;
    }

    /**
     *
     * @return {@code true} if this list is empty
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     *
     * @return a String representation of this list
     */
    @Override
    public String toString() {

        String s = "";

        Record r = head;

        while (r != null) {
            s += r.toString() + " ";
            r = r.next;
        }

        return s;
    }
}