/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree.binarytree;

/**
 *
 * @author zhenhui
 */
public class ListNode<E> {

    private E element;
    private ListNode<E> previous, next;

    public ListNode(E e, ListNode<E> p, ListNode<E> n) {
        element = e;
        previous = p;
        next = n;
    }

    public E getElement() {
        return element;
    }

    public ListNode<E> getPrevious() {
        return previous;
    }

    public ListNode<E> getNext() {
        return next;
    }

    public void setPrevious(ListNode<E> p) {
        previous = p;
    }

    public void setNext(ListNode<E> l) {
        next = l;
    }

    public void setElement(E e) {
        element = e;
    }
}
