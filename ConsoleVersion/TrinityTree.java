
/**
 * Implementation of a tree using a node-based, linked structure.
 * This implementation is based off of the structure given during CS351.
 * @author Jacob Dyer
 */

class TrinityTree<E> {

  //---------------- nested Node class ----------------
  /** Nested static class for a binary tree node. */
  protected static class Node<E> implements Position<E> {
    private E element;          // an element stored at this node
    private Node<E> parent;     // a reference to the parent node (if any)
    private Node<E> left;       // a reference to the left child (if any)
    private Node<E> right;      // a reference to the right child (if any)
    private Node<E> middle;      // a reference to the bottom child (if any)

    /**
     * Constructs a node with the given element and neighbors.
     *
     * @param e  the element to be stored
     * @param above       reference to a parent node
     * @param leftChild   reference to a left child node
     * @param rightChild  reference to a right child node
     * @param middleChild   reference to a center child node
     */
    public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild, Node<E> middleChild) {
        element = e;
        parent = above;
        left = leftChild;
        right = rightChild;
        middle = middleChild;
    }

    // accessor methods
    public E getElement() { return element; }
    public Node<E> getParent() { return parent; }
    public Node<E> getLeft() { return left; }
    public Node<E> getRight() { return right; }
    public Node<E> getMiddle() { return middle; }

    // update methods
    public void setElement(E e) { element = e; }
    public void setParent(Node<E> parentNode) { parent = parentNode; }
    public void setLeft(Node<E> leftChild) { left = leftChild; }
    public void setRight(Node<E> rightChild) { right = rightChild; }
    public void setMiddle(Node<E> middleChild) { middle = middleChild; }
  } //----------- end of nested Node class -----------

  /** Factory function to create a new node storing element e. */
  protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right, Node<E> middle) {
    return new Node<E>(e, parent, left, right, middle);
  }

  // instance variables
  /** The root of the tree */
  protected Node<E> root = null;     // root of the tree

  /** The number of nodes in the tree */
  private int size = 0;              // number of nodes in the tree

  // constructor
  /** Construts an empty tree. */
  public TrinityTree() { }      // constructs an empty tree

  // nonpublic utility
  /**
   * Verifies that a Position belongs to the appropriate class, and is
   * not one that has been previously removed. Note that our current
   * implementation does not actually verify that the position belongs
   * to this particular list instance.
   *
   * @param p   a Position (that should belong to this tree)
   * @return    the underlying Node instance for the position
   * @throws IllegalArgumentException if an invalid position is detected
   */
  protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
    if (!(p instanceof Node))
      throw new IllegalArgumentException("Not valid position type");
    Node<E> node = (Node<E>) p;       // safe cast
    if (node.getParent() == node)     // our convention for defunct node
      throw new IllegalArgumentException("p is no longer in the tree");
    return node;
  }

  // accessor methods (not already implemented in AbstractBinaryTree)
  /**
   * Returns the number of nodes in the tree.
   * @return number of nodes in the tree
   */
  public int size() {
    return size;
  }

  /**
   * Returns the root Position of the tree (or null if tree is empty).
   * @return root Position of the tree (or null if tree is empty)
   */
  public Position<E> root() {
    return root;
  }

  /**
   * Returns the Position of p's parent (or null if p is root).
   *
   * @param p    A valid Position within the tree
   * @return Position of p's parent (or null if p is root)
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   */
  public Position<E> parent(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.getParent();
  }

  /**
   * Returns the Position of p's left child (or null if no child exists).
   *
   * @param p A valid Position within the tree
   * @return the Position of the left child (or null if no child exists)
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   */

  public Position<E> left(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.getLeft();
  }

  /**
   * Returns the Position of p's right child (or null if no child exists).
   *
   * @param p A valid Position within the tree
   * @return the Position of the right child (or null if no child exists)
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   */

  public Position<E> right(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.getRight();
  }

  /**
   * Returns the Position of p's middle child (or null if no child exists).
   *
   * @param p A valid Position within the tree
   * @return the Position of the right child (or null if no child exists)
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   */

  public Position<E> middle(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return node.getMiddle();
  }

  // update methods supported by this class
  /**
   * Places element e at the root of an empty tree and returns its new Position.
   *
   * @param e   the new element
   * @return the Position of the new element
   * @throws IllegalStateException if the tree is not empty
   */
  public Position<E> addRoot(E e) throws IllegalStateException {
    if (size != 0) throw new IllegalStateException("Tree is not empty");
    root = createNode(e, null, null, null, null);
    size = 1;
    return root;
  }

  /**
   * Creates a new left child of Position p storing element e and returns its Position.
   *
   * @param p   the Position to the left of which the new element is inserted
   * @param e   the new element
   * @return the Position of the new element
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   * @throws IllegalArgumentException if p already has a left child
   */
  public Position<E> addLeft(Position<E> p, E e)
                          throws IllegalArgumentException {
    Node<E> parent = validate(p);
    if (parent.getLeft() != null)
      throw new IllegalArgumentException("p already has a left child");
    Node<E> child = createNode(e, parent, null, null, null);
    parent.setLeft(child);
    size++;
    return child;
  }

  /**
   * Creates a new right child of Position p storing element e and returns its Position.
   *
   * @param p   the Position to the right of which the new element is inserted
   * @param e   the new element
   * @return the Position of the new element
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   * @throws IllegalArgumentException if p already has a right child
   */
  public Position<E> addRight(Position<E> p, E e)
                          throws IllegalArgumentException {
    Node<E> parent = validate(p);
    if (parent.getRight() != null)
      throw new IllegalArgumentException("p already has a right child");
    Node<E> child = createNode(e, parent, null, null, null);
    parent.setRight(child);
    size++;
    return child;
  }

  /**
   * Creates a new middle child of Position p storing element e and returns its Position.
   *
   * @param p   the Position to the right of which the new element is inserted
   * @param e   the new element
   * @return the Position of the new element
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   * @throws IllegalArgumentException if p already has a middle child
   */
  public Position<E> addMiddle(Position<E> p, E e)
                          throws IllegalArgumentException {
    Node<E> parent = validate(p);
    if (parent.getMiddle() != null)
      throw new IllegalArgumentException("p already has a right child");
    Node<E> child = createNode(e, parent, null, null, null);
    parent.setMiddle(child);
    size++;
    return child;
  }

  /**
   * Replaces the element at Position p with element e and returns the replaced element.
   *
   * @param p   the relevant Position
   * @param e   the new element
   * @return the replaced element
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   */
  public E set(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> node = validate(p);
    E temp = node.getElement();
    node.setElement(e);
    return temp;
  }

  /**
   * @param toAttach the tree you would like to attach in the left nodes position - left will become the root
   */

  public void attachLeft(TrinityTree<E> toAttach){
    if(root != null){
      if(this.root.getLeft() == null && toAttach.root != null){
        this.root.setLeft(toAttach.root);
        this.size += toAttach.size;
      }
    }
  }

  /**
   * @param toAttach the tree you would like to attach in the right nodes position - right will become the root
   */

  public void attachRight(TrinityTree<E> toAttach){
    if(root != null){
      if(this.root.getRight() == null && toAttach.root != null){
        this.root.setRight(toAttach.root);
        this.size += toAttach.size;
      }
    }
  }

  /**
   * @param toAttach the tree you would like to attach in the middle nodes position - middle will become the root
   */

  public void attachMiddle(TrinityTree<E> toAttach){
    if(root != null){
      if(this.root.getMiddle() == null && toAttach.root != null){
        this.root.setMiddle(toAttach.root);
        this.size += toAttach.size;
      }
    }
  }

  public boolean isInternal(){
    return root.getLeft() == null && root.getRight() == null && root.getMiddle() == null;
  }

} //----------- end of LinkedTree class -----------
