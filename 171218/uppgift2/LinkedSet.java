/// OBS!!! There are lots of TODO annotations below. These capture
/// most of the things you need to do, BUT NOT ALL.

import java.util.Iterator;

public class LinkedSet /* TODO!!! */ {
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
    /* TODO! */ Link first = new Link(null); /// Sets up a sentinel
    /* TODO! */ Link last = first;

    /// TODO: add a constructor!

    /// TODO: add public boolean equals...

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
            /// TODO
        }
        public boolean hasNext() {
            /// TODO
        }
        public void remove() {
            /// TODO
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
