package assign08;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * 
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

  private class BinaryNode<Type> {
    private Type element;
    private BinaryNode<Type> leftChild;
    private BinaryNode<Type> rightChild;

    public int dotLabel;

    public BinaryNode(Type element, BinaryNode<Type> lNode, BinaryNode<Type> rNode) {
      this.element = element;
      this.leftChild = lNode;
      this.rightChild = rNode;
      this.dotLabel = dotLabelCount++;
    }

    public BinaryNode(Type element) {
      this.element = element;
      this.leftChild = null;
      this.rightChild = null;
    }

    public Type getElement() {
      return element;
    }

    public String generateDot() {
      String ret = "\tnode" + dotLabel + " [label = \"<f0> |<f1> " + element + "|<f2> \"]\n";
      if (leftChild != null) {
        ret += "\tnode" + dotLabel + ":f0 -> node" + leftChild.dotLabel + ":f1\n" + leftChild.generateDot();
      }
      if (rightChild != null) {
        ret += "\tnode" + dotLabel + ":f2 -> node" + rightChild.dotLabel + ":f1\n" + rightChild.generateDot();
      }

      return ret;
    }
  }

  private BinaryNode<Type> root;
  private int size;
  private int dotLabelCount;

  public BinarySearchTree() {
    root = null;
    size = 0;
    dotLabelCount = 0;
  }

  @Override
  public boolean add(Type item) {
    // TODO Auto-generated method stub
    if (item == null)
      return false;

    if (root == null) {
      root = new BinaryNode<Type>(item);
      size++;
      return true;
    }

    return add(root, item);
  }

  public boolean add(BinaryNode<Type> node, Type item) {
    int cmp = item.compareTo(node.getElement());

    // Navigating down the left side (If the item is smaller)
    if (cmp < 0) {
      if (node.leftChild == null) {
        node.leftChild = new BinaryNode<>(item);
        size++;
        return true;
      } else {
        return add(node.leftChild, item);
      }
    }

    // Navigating the right side (If the item is bigger)
    else if (cmp > 0) {
      if (node.rightChild == null) {
        node.rightChild = new BinaryNode<>(item);
        size++;
        return true;
      } else {
        return add(node.rightChild, item);
      }
    }
    return false;
  }

  @Override
  public boolean addAll(Collection<? extends Type> items) {
    if (items.isEmpty())
      return false;

    boolean finished = false;
    for (Type item : items) {
      if (this.add(item))
        finished = true;
    }

    return finished;
  }

  @Override
  public void clear() {
    this.root = null;
    this.size = 0;
  }

  @Override
  public boolean contains(Type item) {
    return contains(root, item);
  }

  public boolean contains(BinaryNode<Type> node, Type item) {
    if (node == null)
      return false;

    int cmp = item.compareTo(node.getElement());

    if (cmp < 0)
      return contains(node.leftChild, item);
    else if (cmp > 0)
      return contains(node.rightChild, item);
    return true;
  }

  @Override
  public boolean containsAll(Collection<? extends Type> items) {
    if (items.isEmpty())
      return false;

    boolean finished = false;
    for (Type item : items) {
      if (this.contains(item))
        finished = true;
    }
    return finished;
  }

  @Override
  public Type first() throws NoSuchElementException {
    if (root == null)
      throw new NoSuchElementException("The tree is empty bro");
    return first(root);
  }

  public Type first(BinaryNode<Type> root) {
    if (root.leftChild == null)
      return root.leftChild.getElement();
    return first(root.leftChild);
  }

  @Override
  public boolean isEmpty() {
    return root == null;
  }

  @Override
  public Type last() throws NoSuchElementException {
    if (root == null)
      throw new NoSuchElementException("The tree is empty bro");
    return last(root);
  }

  public Type last(BinaryNode<Type> root) {
    if (root.rightChild == null)
      return root.rightChild.getElement();

    return last(root.rightChild);
  }

  @Override
  public boolean remove(Type item) {
    if (item == null || root == null)
      return false;

    root = remove(root, item);
    if (root == null)
      return false;
    return true;
  }

  public BinaryNode<Type> remove(BinaryNode<Type> node, Type item) {
    if (node == null)
      return null;

    int cmp = item.compareTo(node.getElement());

    // Navigating to the left if item is smaller
    if (cmp < 0) {
      node.leftChild = remove(node.leftChild, item);
    }

    // Navigating to the right if item is bigger
    else if (cmp > 0) {
      node.rightChild = remove(node.rightChild, item);
    }

    // Node to remove
    else {
      // Node to remove has no children
      if (node.rightChild == null && node.rightChild == null) {
        return null;
      }

      // Node to remove only has right children
      else if (node.leftChild == null)
        return node.rightChild;

      // Node to remove only has left children
      else if (node.rightChild == null)
        return node.leftChild;

      // Node to remove has 2 fking chidlren
      else {
        // TODO: Fill this shit in (but how?)
      }
    }
    return null;
  }

  @Override
  public boolean removeAll(Collection<? extends Type> items) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public ArrayList<Type> toArrayList() {
    ArrayList<Type> result = new ArrayList<>();
    toArrayList(root, result);

    return result;
  }

  public void toArrayList(BinaryNode<Type> node, ArrayList<Type> result) {
    if (node == null)
      return;

    toArrayList(node.leftChild, result);

    result.add(node.getElement());

    toArrayList(node.rightChild, result);
  }

  public void generateDotFile(String filename) {
    try {
      PrintWriter out = new PrintWriter(filename);
      out.println("digraph Tree {\n\tnode [shape=record]\n");

      if (root == null)
        out.println("");
      else
        out.print(root.generateDot());

      out.println("}");
      out.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }

}
