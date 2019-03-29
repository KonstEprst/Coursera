import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    int nodesCount = 0;

    public Deque(){}

    public boolean isEmpty() {
        return first == null && last == null;
    }
    public int size() {
        return nodesCount;
    }

    public void addFirst(Item item) {
        if(item == null) throw new IllegalArgumentException();

        Node newFirst = new Node();
        newFirst.value  = item;
        newFirst.nextNode = this.first;

        if(this.first != null) this.first.previousNode = newFirst;
        if(this.last == null) this.last = newFirst;

        this.first = newFirst;
        nodesCount++;
    }


    public void addLast(Item item) {
        if(item == null) throw new IllegalArgumentException();

        Node newLast = new Node();
        newLast.value = item;
        newLast.previousNode = this.last;

        if(this.last != null) this.last.nextNode = newLast;
        if(this.first == null) this.first = newLast;

        this.last = newLast;
        nodesCount++;
    }

    public Item removeFirst() {
        if(isEmpty()) throw new NoSuchElementException();

        Node retVal = first;
        if(retVal.nextNode != null) {
            first = retVal.nextNode;
        } else {
            first = null;
        }

        nodesCount--;
        return retVal.value;
    }

    public Item removeLast() {
        if(isEmpty()) throw new NoSuchElementException();

        Node retVal = last;
        if(retVal.previousNode != null){
            last = retVal.previousNode;
        } else {
            last = null;
        }

        nodesCount--;
        return retVal.value;
    }

    public Iterator<Item> iterator(){
        return new DequeIterator();
    }

    private class Node{
        private Item value;
        private Node nextNode;
        private Node previousNode;
    }

    private class DequeIterator implements Iterator<Item>{

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current.nextNode != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if(current.nextNode == null) throw new NoSuchElementException();

            current = current.nextNode;
            return current.value;
        }
    }

    public static void main(String[] args){}   // unit testing (optional)
}
