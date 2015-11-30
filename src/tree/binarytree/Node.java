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
public class Node<E> {

    private E element;
    private int level;
    // Define node's position on its level, left to right
    private int position;
    // with some positions null for non-complete trees
    // 父亲，左儿子，右儿子
    private Node<E> parent, left, right;

    public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
        setElement(e);
        setParent(p);
        setLeft(l);
        setRight(r);
    }

    public E getElement() {
        return element;
    }

    public Node<E> getParent() {
        return parent;
    }

    public Node<E> getLeft() {
        return left;
    }

    public Node<E> getRight() {
        return right;
    }

    public int getLevel() {
        return level;
    }

    public int getPosition() {
        return position;
    }

    public void setParent(Node<E> p) {
        parent = p;
    }

    public void setLeft(Node<E> l) {
        left = l;
    } //different signature to BinaryTree's setLeft

    public void setRight(Node<E> r) {
        right = r;
    }

    public void setElement(E e) {
        element = e;
    }

    public void setLevel(int i) {
        level = i;
    }

    public void setPosition(int i) {
        position = i;
    }
}
