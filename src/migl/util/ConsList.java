package migl.util;

import java.util.function.Function;

/**
 * Implementation of a list using the {@link migl.util.Cons} data structure.
 * 
 * @author leberre
 *
 * @param <E>
 *            the type of the elements in the list
 */
public interface ConsList<E> extends Iterable<E> {

    /**
     * Insert a new element e in front of the list.
     * 
     * @param e
     *            an element.
     */
    ConsList<E> prepend(E e);

    /**
     * Insert a new element e at the end of the list
     * 
     * @param e
     *            an element
     */
    ConsList<E> append(E e);

    /**
     * Check if the list is empty or not.
     * 
     * @return true if the list is empty, else false.
     */
    boolean isEmpty();

    /**
     * Retrieve the first element of the list.
     * 
     * @return the first element of the list.
     */
    E car();

    /**
     * Return the sublist corresponding to all but the first element.
     * 
     * @return all but the first element of the list.
     */
    ConsList<E> cdr();

    /**
     * Returns the size of the list (the number of elements it contains).
     * 
     * @return the number of elements in the list.
     */
    int size();

    /**
     * Create a new list by applying a function to each element of the list.
     * 
     * @param f
     *            a function
     * @return a list where each element is the result of applying f to an
     *         element of the original list.
     */
    <T> ConsList<T> map(Function<E, T> f);

}