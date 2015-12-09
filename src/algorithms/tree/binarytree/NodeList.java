/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms.tree.binarytree;

/**
 *
 * @author zhenhui
 */
public class NodeList<E> {

    private int nlSize;
    private ListNode<E> head, tail;

    public NodeList() {
        nlSize = 0;
        head = new ListNode<E>(null, null, null);
        tail = new ListNode<E>(null, head, null);
        head.setNext(tail);
    }

    public int getNLSize() {
        return nlSize;
    }

    public ListNode<E> getFirst() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException("Empty list");
        }
        return head.getNext();
    }

    public ListNode<E> getLast() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException("Empty list");
        }
        return tail.getPrevious();
    }

    public ListNode<E> getPrevious(ListNode<E> n) throws BoundaryException {
        ListNode<E> prv = n.getPrevious();
        if (prv == head) {
            throw new BoundaryException("attempt to access before start of list");
        }
        return prv;
    }

    public ListNode<E> getNext(ListNode<E> n) throws BoundaryException {
        ListNode<E> nxt = n.getNext();
        if (nxt == tail) {
            throw new BoundaryException("attempt to access after end of list");
        }
        return nxt;
    }

    public void addFirst(E e) {
        nlSize++;
        ListNode<E> n = new ListNode<E>(e, head, head.getNext());
        head.getNext().setPrevious(n);
        head.setNext(n);
    }

    //Set new element at given node, return old
    public E setElement(ListNode<E> ln, E e) throws InvalidNodeException {
        E oldElement = ln.getElement();
        ln.setElement(e);
        return oldElement;
    }

    public void addLast(E e) {
        nlSize++;
        ListNode<E> n = new ListNode<E>(e, tail.getPrevious(), tail);
        tail.getPrevious().setNext(n);
        tail.setPrevious(n);
    }

    public void addBeforeNode(ListNode<E> ln, E e) {
        nlSize++;
        ListNode<E> n = new ListNode<E>(e, ln.getPrevious(), ln);
        ln.getPrevious().setNext(n);
        ln.setPrevious(n);
    }

    public void addAfterNode(ListNode<E> ln, E e) {
        nlSize++;
        ListNode<E> n = new ListNode<E>(e, ln, ln.getNext());
        ln.getNext().setPrevious(n);
        ln.setNext(n);
    }

    //Remove node from list and return its element
    public E removeNode(ListNode<E> ln) throws InvalidNodeException {
        nlSize--;
        ListNode<E> previous = ln.getPrevious();
        ListNode<E> next = ln.getNext();
        previous.setNext(next);
        next.setPrevious(previous);
        E e = ln.getElement();
        ln.setPrevious(null);
        ln.setNext(null);
        return e;
    }

    public boolean isEmpty() {
        return (nlSize == 0);
    }

    public int size() {
        return nlSize;
    }
}
