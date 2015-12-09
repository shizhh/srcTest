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
public class BinaryTree<E> {

    protected int btSize;
    protected int btHeight;
    protected Node<E> root;

    public BinaryTree() {
        this.btSize = 0;
        this.btHeight = 0;
        this.root = null;
    }

    public void setBTHeight(int i) {
        this.btHeight = i;
    }

    public void setBTSize(int i) {
        this.btSize = i;
    }

    public Node<E> setRoot(E e) throws TreeNotEmptyException {
        if (!isEmpty()) {
            throw new TreeNotEmptyException("Tree is not empty");
        }
        btSize = 1;
        btHeight = 1;
        root = new Node<E>(e, null, null, null);
        root.setLevel(1);
        return root;
    }

    public int getBTSize() throws InvalidNodeException, EmptyTreeException,
        EmptyListException, BoundaryException {
        this.updateHeightAndSize();
        return btSize;
    }

    public int getBTHeight() throws InvalidNodeException, EmptyTreeException,
        EmptyListException, BoundaryException {
        this.updateHeightAndSize();
        return btHeight;
    }

    public Node<E> getRoot() throws EmptyTreeException {
        if (root == null) {
            throw new EmptyTreeException("Empty tree");
        }
        return root;
    }

    public Node<E> getParent(Node<E> n) throws BoundaryException {
        Node<E> p = n.getParent();
        if (p == null) {
            throw new BoundaryException("No parent");
        }
        return p;
    }

    public Node<E> getLeft(Node<E> n) {
        Node<E> l = n.getLeft();
        return l;
    }

    public Node<E> getRight(Node<E> n) {
        Node<E> r = n.getRight();
        return r;
    }

    /**
     * 获取兄弟节点
     */
    public Node<E> getSibling(Node<E> n) throws BoundaryException {
        Node<E> p = getParent(n);
        if (p != null) {
            Node<E> s;
            Node<E> l = getLeft(p);
            if (l == n) {
                s = getRight(p);
            } else {
                s = l;
            }
            if (s != null) {
                return s;
            }
        }
        // s == null
        throw new BoundaryException("Node has no sibling");
    }

    public boolean hasLeft(Node<E> n) throws InvalidNodeException {
        if (n == null) {
            throw new InvalidNodeException("Node is null");
        }
        return (n.getLeft() != null);
    }

    public boolean hasRight(Node<E> n) throws InvalidNodeException {
        if (n == null) {
            throw new InvalidNodeException("Node is null");
        }
        return (n.getRight() != null);
    }

    public boolean isEmpty() {
        return btSize == 0;
    }

    public boolean isRoot(Node<E> n)
        throws InvalidNodeException, EmptyTreeException {
        if (n == null) {
            throw new InvalidNodeException("Node is null");
        }
        return (n == getRoot());
    }

    public boolean isInternal(Node<E> n) throws InvalidNodeException {
        if (n == null) {
            throw new InvalidNodeException("Node is null");
        }
        return (hasLeft(n) || hasRight(n));
    }

    public NodeList<E> getChildList(Node<E> n) throws InvalidNodeException {
        NodeList<E> childList = new NodeList<E>();
        if (hasLeft(n)) {
            childList.addLast(n.getLeft().getElement());  // Recursive
        }
        if (hasRight(n)) {
            childList.addLast(n.getRight().getElement()); // Recursive
        }
        return childList;
    }

    // N.B. different signature to class Node method setLeft
    public Node<E> setLeft(Node<E> n, E e) throws InvalidNodeException {
        Node<E> test = getLeft(n);
        if (test != null) {
            throw new InvalidNodeException("Node already has left child");
        }
        Node<E> l = new Node<E>(e, n, null, null);
        n.setLeft(l);
        btSize++;
        if (n.getLevel() == btHeight) {
            btHeight++;
            l.setLevel(btHeight);
        } else {
            l.setLevel(n.getLevel() + 1);
        }
        return l;
    }

    public Node<E> setRight(Node<E> n, E e) throws InvalidNodeException {
        Node<E> test = getRight(n);
        if (test != null) {
            throw new InvalidNodeException("Node already has left child");
        }
        Node<E> r = new Node<E>(e, n, null, null);
        n.setRight(r);
        btSize++;
        if (n.getLevel() == btHeight) {
            btHeight++;
            r.setLevel(btHeight);
        } else {
            r.setLevel(n.getLevel() + 1);
        }
        return r;
    }

    public E replaceElement(Node<E> n, E e) throws InvalidNodeException {
        E oldElement = n.getElement();
        n.setElement(e);
        return oldElement;
    }

    // Below areauxiliary methods for listing tree node properties, including 
    // for non-complete trees and subtrees. All lists may be printed out using
    // other methods, and some are used by the printDiagram method. 
    public NodeList<Node<E>> getTreeList()
        throws InvalidNodeException, EmptyTreeException {
        NodeList<Node<E>> treeList = new NodeList<>();
        if (btSize != 0) {
            getSubtreeList(getRoot(), treeList);
        }
        return treeList;
    }

    // Above method employs the one below  
    public void getSubtreeList(Node<E> n, NodeList<Node<E>> l)
        throws InvalidNodeException, EmptyTreeException {
        l.addLast(n);
        if (hasLeft(n)) {
            getSubtreeList(n.getLeft(), l);
        }
        if (hasRight(n)) {
            getSubtreeList(n.getRight(), l);
        }
    }

    // Lists the level at each node
    public void getSubtreeLevelsList(Node<E> n, NodeList<Integer> l)
        throws InvalidNodeException, EmptyTreeException {
        l.addLast(n.getLevel());
        if (hasLeft(n)) {
            getSubtreeLevelsList(n.getLeft(), l);
        }
        if (hasRight(n)) {
            getSubtreeLevelsList(n.getRight(), l);
        }
    }
    // For whole tree, from root

    public NodeList<Node<E>> getTreeDiagramList() throws InvalidNodeException,
        EmptyTreeException, EmptyListException, BoundaryException {
        NodeList<Node<E>> treeList = new NodeList<Node<E>>();
        if (btSize != 0) {
            getSubtreeDiagramList(getRoot(), treeList, 1);
        }
        return treeList;
    }
    // For subtree, defined from node

    public NodeList<Node<E>> getTreeDiagramListFromNode(Node<E> n)
        throws InvalidNodeException, EmptyTreeException, EmptyListException,
        BoundaryException {
        NodeList<Node<E>> treeList = new NodeList<Node<E>>();
        if (btSize != 0) {
            getSubtreeDiagramList(n, treeList, 1);
        }
        return treeList;
    }// Above two methods employs the one below  

    public void getSubtreeDiagramList(Node<E> n, NodeList<Node<E>> dl, int pos)
        throws InvalidNodeException, EmptyTreeException {
        n.setPosition(pos);
        dl.addLast(n);
        int posn;
        if (hasLeft(n)) {
            posn = pos * 2 - 1;
            getSubtreeDiagramList(n.getLeft(), dl, posn); // Recursive
        }
        if (hasRight(n)) {
            posn = pos * 2;
            getSubtreeDiagramList(n.getRight(), dl, posn); // Recursive   
        }
    }

    // Print the elements stored in each node
    public void printList(NodeList<Node<E>> nl) throws EmptyListException,
        InvalidNodeException, BoundaryException {
        ListNode<Node<E>> nextNode = nl.getFirst();
        for (int i = 0; i < nl.getNLSize() - 1; i++) {
            System.out.print(nextNode.getElement().getElement() + " ");
            nextNode = nl.getNext(nextNode);
        }
        System.out.print(nextNode.getElement().getElement() + " ");
        System.out.println();
    }

    // Main use is to print level of each node, when used after calling 
    // getSubtreeLevelsList, although could be used whenever argument of
    // NodeList<E> is applicable (compare to NodeList<Node<E>> for printList) 
    public void printLevelList(NodeList<E> nl) throws EmptyListException,
        InvalidNodeException, BoundaryException {
        ListNode<E> nextNode = nl.getFirst();
        for (int i = 0; i < nl.getNLSize() - 1; i++) { // Only operates for size > 1;    
            System.out.print(nextNode.getElement() + " ");
            nextNode = nl.getNext(nextNode);
        }
        System.out.print(nextNode.getElement() + " ");
        System.out.println();
    }

    public void updateHeightAndSize() throws InvalidNodeException,
        EmptyTreeException, EmptyListException, BoundaryException {
        int max = 0;
        int min = 0;
        int count = 0;
        NodeList<Integer> levelsList = new NodeList<>();
        this.getSubtreeLevelsList(this.getRoot(), levelsList);
        ListNode<Integer> nextNode = levelsList.getFirst();
        for (int i = 0; i < levelsList.getNLSize() - 1; i++) {
            count++;
            min = nextNode.getElement();
            if (min > max) {
                max = min;
            }
            nextNode = levelsList.getNext(nextNode);
        }
        count++;
        min = nextNode.getElement();
        if (min > max) {
            max = min;
        }
        setBTHeight(max);
        setBTSize(count);
    }

    //Remove a node, replace it with its one child if it exists, and return 
    //element stored at the removed node. All descendents of child also promoted.
    //Attempt to remove node with two children causes an error.
    //Note all promotions may be incorporated in updated diagram as a mathematical 
    //consequence of never removing nodes with 2 children.
    public E removeNode(Node<E> n) throws InvalidNodeException,
        BoundaryException {
        Node<E> l = getLeft(n);
        Node<E> r = getRight(n);
        int posn = n.getPosition();
        int lev = n.getLevel();
        if (l != null && r != null) {
            throw new InvalidNodeException("Node has two children");
        }
        Node<E> child;
        if (l != null) {
            child = l;
        } else if (r != null) {
            child = r;
        } else {
            child = null;  // Node to be removed is external    
        }
        if (child != null) {
            child.setPosition(posn);
            child.setLevel(lev);
        }
        if (n == root) {  // Node to be removed is the root
            if (child != null) {
                child.setParent(null);
            }
            root = child;
        } else {  // Node to be removed is not the root      
            Node<E> p = n.getParent();
            if (n == p.getLeft()) {
                p.setLeft(child); // N.B set to null if node is external
            } else {
                p.setRight(child);  // N.B set to null if node to be removed is external
            }
            if (child != null) { // Node removed was internal
                child.setParent(p);
                setDescendentLevels(n);
            }
        }
        return n.getElement();
    }// end removeNode

    // Recursive method to increment all levels in subtree below subtree root
    public void setDescendentLevels(Node<E> rootSub) throws InvalidNodeException {
        int parentLevel = rootSub.getParent().getLevel();
        rootSub.setLevel(parentLevel + 1);
        if (hasLeft(rootSub)) {
            this.setDescendentLevels(rootSub.getLeft());
        }
        if (hasRight(rootSub)) {
            this.setDescendentLevels(rootSub.getRight());
        }
    }

    //Remove entire subtree defined at the node entered as parameter from the
    //tree to which it belongs. Subtree may vary from a single external node
    //to the entire tree: in latter case the tree will be disestablished
    public void removeSubtree(Node<E> n) throws InvalidNodeException,
        BoundaryException, EmptyTreeException, EmptyListException {
        Node<E> l = getLeft(n);
        Node<E> r = getRight(n);
        if (l != null) {
            l.setParent(null);
        }
        if (r != null) {
            r.setParent(null);
        }
        if (n != root) {  // Subtree root is not the root of entire tree      
            Node<E> p = n.getParent(); // So its parent will become an external node
            if (n == p.getLeft()) {
                p.setLeft(null);
            } else {
                p.setRight(null);
            }
        }
    }// end removeSubtree

    //Remove all descendents of a node from its tree, but keep the node
    public void removeDescendents(Node<E> n) throws InvalidNodeException,
        BoundaryException, EmptyTreeException, EmptyListException {
        Node<E> l = getLeft(n);
        Node<E> r = getRight(n);
        if (l != null) {
            l.setParent(null);
            n.setLeft(null);
        }
        if (r != null) {
            r.setParent(null);
            n.setRight(null);
        }
    }// end removeDescendents

    // Attach two trees as subtrees of an external node
    // i.e. the roots of these trees become children of the node
    public void attachTrees(Node<E> n, BinaryTree<E> TLeft, BinaryTree<E> TRight)
        throws InvalidNodeException, EmptyTreeException {
        if (isInternal(n)) {
            throw new InvalidNodeException("Node is not external");
        }
        int attachmentNodeLevel = n.getLevel();
        if (!TLeft.isEmpty()) {
            Node<E> leftTreeRoot = TLeft.getRoot();
            n.setLeft(leftTreeRoot);
            leftTreeRoot.setParent(n);
            leftTreeRoot.setLevel(attachmentNodeLevel);
            setDescendentLevels(leftTreeRoot);
        }
        if (!TRight.isEmpty()) {
            Node<E> rightTreeRoot = TRight.getRoot();
            n.setRight(rightTreeRoot);
            rightTreeRoot.setParent(n);
            rightTreeRoot.setLevel(attachmentNodeLevel);
            setDescendentLevels(rightTreeRoot);
        }
    }// end attachTrees

    // Return any node in a tree (except root) by specifying its path from the root
    // in an array holding only int 0 and 1, signaling left and right respectively
    // e.g {0,0,1,0} yielding left-left-right-left. 
    // If sequence exceeds depth of tree then last non-null node traversed is returned
    // (i.e. the external node reached by sequence is returned, and remainder of 
    // sequence is ignored). 
    public Node<E> accessNode(BinaryTree<E> bt, int[] path) throws EmptyTreeException {
        Node<E> n = bt.getRoot();
        Node<E> m;
        for (int i = 0; i < path.length; i++) {
            if (path[i] == 0) {
                m = getLeft(n);
                if (m != null) {
                    n = m;
                }
            }
            if (path[i] == 1) {
                m = getRight(n);
                if (m != null) {
                    n = m;
                }
            }
        }
        return n;
    }// end accessNode

    public BinaryTree<Integer> createPopulatedIntegerTree(Integer[][] a)
        throws EmptyTreeException, TreeNotEmptyException,
        InvalidNodeException, BoundaryException, EmptyListException {
        BinaryTree<Integer> t = new BinaryTree<Integer>();
        t.setBTHeight(a.length);
        Node<Integer> root = t.setRoot(a[0][0]);
        Node<Integer> parent = null;
        Node<Integer> currentNode = null;
        ListNode<Node<Integer>> ln = null;
        NodeList<Node<Integer>> parentList = new NodeList<Node<Integer>>();
        NodeList<Node<Integer>> levelList = null;
        parentList.addLast(root);
        int level, parentListSize, levelListSize;
        for (int i = 1; i < a.length; i++) { // process levels 2 onward
            level = i + 1;
            levelList = new NodeList<Node<Integer>>();
            parentListSize = parentList.getNLSize();
            levelListSize = a[i].length;
            for (int j = 0; j < levelListSize; j++) {
                if (j % 2 == 0) { // get one new parent for each consequetive node pair
                    if (j == 0) {
                        ln = parentList.getFirst();
                    } else {
                        ln = parentList.getNext(ln);
                    }
                    parent = ln.getElement();
                    currentNode = t.setLeft(parent, a[i][j]);
                    currentNode.setPosition(j + 1);
                    currentNode.setLevel(level);
                    levelList.addLast(currentNode);
                }// end if
                else {
                    currentNode = t.setRight(parent, a[i][j]);
                    currentNode.setPosition(j + 1);
                    currentNode.setLevel(level);
                    levelList.addLast(currentNode);
                }// end else
            }// end inner for
            parentList = levelList; //levelList becomes parent for next inner loop
        }// end outer for    
        return t;
    }// end createPopulatedIntegerTree

    // Prints a diagram of tree, or subtree, including non-complete trees.
    // Requires a full screen, and is tidiest for trees with only single digit 
    // elements, e.g. as in common usage of holding binary digits 1, 0.
    // Only shows 6 levels from root or declared node
    public void printDiagram(BinaryTree<E> t, Node<E> n) throws
        InvalidNodeException, EmptyTreeException, EmptyListException, EmptyListException,
        BoundaryException {
        System.out.println("This diagram is for full screen run only");
        System.out.println("Only displays the top 6 levels of tree or subtree");
        NodeList<Node<E>> nl = t.getTreeDiagramListFromNode(n);
        //t.printList(nl);
        int height = t.getBTHeight();
        int level = n.getLevel();
        int diagramHeight;
        if ((height < 7) || (height - level < 5)) {
            diagramHeight = height - level + 1;
        } else {
            diagramHeight = 6;  // Max no. diagram levels 6  
        }
        NodeList<Node<E>> level1 = new NodeList<Node<E>>();
        NodeList<Node<E>> level2 = new NodeList<Node<E>>();
        NodeList<Node<E>> level3 = new NodeList<Node<E>>();
        NodeList<Node<E>> level4 = new NodeList<Node<E>>();
        NodeList<Node<E>> level5 = new NodeList<Node<E>>();
        NodeList<Node<E>> level6 = new NodeList<Node<E>>();
        int[] a2 = {39, 3, 80, 80};
        int[] a3 = {19, 5, 40, 40};
        int[] a4 = {9, 9, 20, 20};
        int[] a5 = {4, 17, 10, 10};
        int[] a6 = {2, 33, 6, 4};
        int lev;
        int size = nl.getNLSize();
        ListNode<Node<E>> lndn = nl.getFirst(); // is root of printed tree (or subtree)
        Node<E> dn;
        for (int i = 1; i < size; i++) {
            lndn = nl.getNext(lndn);
            dn = lndn.getElement();
            lev = dn.getLevel() - level + 1;//e.g.root lev 10, next lev 11-10 + 1 =2       
            switch (lev) {
                case 2:
                    level2.addLast(dn);
                    break;
                case 3:
                    level3.addLast(dn);
                    break;
                case 4:
                    level4.addLast(dn);
                    break;
                case 5:
                    level5.addLast(dn);
                    break;
                case 6:
                    level6.addLast(dn);
                    break;
                default: ;
                    break;
            }
        }// end for           
        for (int proxyLev = 1; proxyLev < diagramHeight + 1; proxyLev++) {
            switch (proxyLev) {
                case 1:
                    processRoot(n);
                    break;
                case 2:
                    processLevel(level2, a2);
                    break;
                case 3:
                    processLevel(level3, a3);
                    break;
                case 4:
                    processLevel(level4, a4);
                    break;
                case 5:
                    processLevel(level5, a5);
                    break;
                case 6:
                    processLevel(level6, a6);
                    break;
                default: ;
                    break;
            }
        }
        System.out.println();
    }// end printDiagram

    public void processRoot(Node<E> n) throws InvalidNodeException {
        E e = n.getElement();
        int pos = n.getPosition();
        if (pos == 1) { // i.e. if pos is not null
            printSpaces(79);
            System.out.print(e);
            System.out.println();
        }
    }// end processRoot

    public void processLevel(NodeList<Node<E>> nl, int[] a)
        throws InvalidNodeException, EmptyListException, BoundaryException {
        int tally = 0;
        int size = nl.getNLSize();
        if (size == 0) {
            return;
        }
        int posDeficit = 0;
        int posDefPrev = 0;
        ListNode<Node<E>> ln = nl.getFirst();
        Node<E> node = ln.getElement();
        E e = node.getElement();
        int pos = node.getPosition();
        // process first node
        tally = iterateSpaces(tally, pos, a[0], a[1], a[2], a[3]);
        if (e != null) {
            System.out.print(e);
        } else {
            System.out.print(" ");
        }
        tally++;
        // process all nodes after first
        for (int i = 1; i < size; i++) {
            ln = nl.getNext(ln);
            node = ln.getElement();
            e = node.getElement();
            pos = node.getPosition();
            tally = iterateSpaces(tally, pos, a[0], a[1], a[2], a[3]);
            if (e != null) {
                System.out.print(e);
            } else {
                System.out.print(" ");
            }
            tally++;
        }// end for
        System.out.println();
    }// end processDiagramLevel

    public int printSpaces(int number) {
        int t = 0;
        for (int j = 0; j < number; j++) {
            System.out.print(" ");
            t++;
        }
        return t;
    }// end printSpaces

    public int iterateSpaces(int tally, int pos, int init, int upto, int even, int odd) {
        int spaces = init;
        for (int j = 1; j < upto; j++) {
            if (pos == j) {
                tally += printSpaces(spaces - tally);
            }
            if (j % 2 == 0) {
                spaces += even;
            } else {
                spaces += odd;
            }
        }
        return tally;
    }

    public void printDiagramNodeListE(NodeList<Node<E>> nl)
        throws EmptyListException, InvalidNodeException, BoundaryException {
        ListNode<Node<E>> nextListNode = nl.getFirst();
        Node<E> nextNode = nextListNode.getElement();
        if (nl.getNLSize() == 1) {
            System.out.print("El: " + nextNode.getElement() + " ");
            System.out.print("Lev: " + nextNode.getLevel() + " ");
            System.out.print("Pos: " + nextNode.getPosition() + " ");
            System.out.println();
        }
        for (int i = 0; i < nl.getNLSize() - 1; i++) { // Only operates for size > 1;    
            System.out.print("El: " + nextNode.getElement() + " ");
            System.out.print("Lev: " + nextNode.getLevel() + " ");
            System.out.print("Pos: " + nextNode.getPosition() + "   ");
            nextListNode = nl.getNext(nextListNode);
            nextNode = nextListNode.getElement();
        }
        if (nl.getNLSize() != 1) {
            System.out.print("El: " + nextNode.getElement() + " ");
            System.out.print("Lev: " + nextNode.getLevel() + " ");
            System.out.print("Pos: " + nextNode.getPosition() + " ");
            System.out.println();
        }
    }
}
