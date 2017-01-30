package migl.util;

import java.util.Iterator;
import java.util.function.Function;

/**
 * Implementation of the interface {@link migl.util.ConsList}
 * 
 * @author xavier
 *
 * @param <E>
 *            Type contains by the ConsList
 */
public class ConsListImpl<E> implements ConsList<E> {

    private Cons<E, ConsListImpl<E>> head;

    /**
     * Default Constructor
     */
    public ConsListImpl() {
        this.head = null;
    }

    /**
     * Construct a ConsList with an element inside of it
     * 
     * @param e
     *            The element to add into the list
     */
    public ConsListImpl(E e) {
        this.head = new Cons<>(e, new ConsListImpl<E>());
    }

    /**
     * Constructor with a head field
     * 
     * @param head
     */
    private ConsListImpl(Cons<E, ConsListImpl<E>> head) {
        assert head != null;
        this.head = head;
    }

    @Override
    public Iterator<E> iterator() {
        return new ConsListIterator<>(this);
    }

    @Override
    public ConsList<E> prepend(E e) {
        return new ConsListImpl<>(new Cons<>(e, this));
    }

    @Override
    public ConsList<E> append(E e) {
        if (this.head == null)
            return prepend(e);
        if (this.head.cdr().head != null) {
            this.head.cdr().append(e);
            return this;
        }
        ConsListImpl<E> newElt = new ConsListImpl<>(e);
        newElt = (ConsListImpl<E>) newElt.prepend(car());
        this.head = newElt.head;
        return this;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public E car() {
        return this.head == null ? null : this.head.car();
    }

    @Override
    public ConsList<E> cdr() {
        return (this.head == null) ? null : ((this.head.cdr() == null) ? this : this.head.cdr());
    }

    private static int count(ConsListImpl consList) {
        if (consList.head == null)
            return 0;
        return 1 + count((ConsListImpl) consList.head.cdr());
    }

    @Override
    public int size() {
        if (head == null)
            return 0;
        return 1 + count(head.cdr());
    }

    @Override
    public <T> ConsList<T> map(Function<E, T> f) {
        ConsListImpl<T> convertedList;

        if (f == null)
            throw new IllegalArgumentException("The function must be initialized");

        convertedList = new ConsListImpl<>();

        for (E e : this) {
            convertedList = (ConsListImpl<T>) convertedList.append(f.apply(e));
        }

        return convertedList;
    }

    private String getContent(boolean first) {
        if (this.head == null)
            return "";
        return (first ? this.car() : (" " + this.car())) + ((ConsListImpl<E>) this.cdr()).getContent(false);
    }

    @Override
    public String toString() {
        return "(" + getContent(true) + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((head == null) ? 0 : head.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof ConsList))
            return false;
        ConsListImpl<E> other = (ConsListImpl<E>) obj;

        if (this.head == null) {
            if (other.head == null) {
                return true;
            } else {
                return false;
            }
        }

        return this.head.equals(other.head);
    }

}
