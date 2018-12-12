/// OBS!!! There are lots of TODO annotations below. These capture
/// most of the things you need to do, BUT NOT ALL.

import java.util.Iterator;

public class LinkedSet<A> extends Collection<A> implements Set<A> {
    /// Don't change the Link class!
    private class Link {
        Link next;
        final T element;
        Link(T element) {
            this(element, null);
        }
        Link(T element, Link next) {
            this.element = element;
            this.next = next;
        }
    }

    /// Write your code below this line! You may change the below but you must use first and last.
    private Link first = new Link(null); /// Sets up a sentinel
    private Link last = first;

    /// TODO: add a constructor!
    public LinkedSet(int maxCapacity) {
      super(maxCapacity)
    }

    /// TODO: add public boolean equals...
    public boolean equals(LinkedSet<T> o) {

    }

    /// TODO: do we need our own newEmptyCollection?

    /// TODO: implement
    /// public Object[] asArray() {
    ///     ...
    /// }

    /// TODO: finish three methods below!
    private class SetIterator implements Iterator<T> {
        Link current = null;
        Link prev = null;

        public SetIterator(Link first) {
            this.current = first;
        }

        public T next() {
            this.prev = this.current;
            this.current = this.current.next;
            return this.current.element;
        }
        public boolean hasNext() {
            if (this.current.next != null) {
              return true;
            }
            return false;
        }
        public void remove() {
            this.prev.next = this.current.next;
            this.current = this.prev.next;
        }
    }

    /// Returns iterator for this class -- you are allowed to change this if you want
    public Iterator<T> iterator() {
        return new SetIterator(this.first);
    }

    /// If you implement your iterator correctly -- you get this one for free!
    public int removeAll(Collection<T> other) {
        int removed = 0;
        for (T e : other) {
            this.remove(e);
            ++removed;
        }
        return removed;
    }

    /// If you implement your iterator correctly -- you get this one for free!
    public int addAll(Collection<T> other) {
        int added = 0;
        for (T e : other) {
            this.add(e);
            ++added;
        }
        return added;
    }

    /// If you implement your iterator correctly -- you get this one for free!
    public int size() {
        int result = 0;
        for (T e : this) {
            ++result;
        }
        return result;
    }

    /// If you implement your iterator correctly -- you get this one for free!
    public boolean contains(T elem) {
        for (T e : this) {
            if (e.equals(elem)) return true;
        }
        return false;
    }

    /// If you implement your iterator correctly -- you get this one for free!
    public boolean remove(T elem) {
        Iterator<T> iter = this.iterator();
        while (iter.hasNext()) {
            if (iter.next().equals(elem)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }
}
