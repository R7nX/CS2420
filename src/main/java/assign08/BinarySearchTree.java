package assign08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 *
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {
    private BinaryNode<Type> root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    @Override
    public boolean add(Type item) {
        if (item == null)
            return false;

        if (root == null) {
            root = new BinaryNode<>(item);
            size++;
            return true;
        }

        return add(root, item);
    }

    public boolean add(BinaryNode<Type> node, Type item) {
        int cmp = item.compareTo(node.element);

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

    private boolean contains(BinaryNode<Type> node, Type item) {
        if (node == null)
            return false;

        int cmp = item.compareTo(node.element);

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

        for (Type item : items) {
            if (!this.contains(item))
                return false;
        }
        return true;
    }

    @Override
    public Type first() throws NoSuchElementException {
        if (root == null)
            throw new NoSuchElementException("The tree is empty bro");
        return first(root);
    }

    public Type first(BinaryNode<Type> root) {
        if (root.leftChild == null)
            return root.element;
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
            return root.element;

        return last(root.rightChild);
    }

    @Override
    public boolean remove(Type item) {
        if (item == null || root == null)
            return false;

        int originalSize = size;
        root = remove(root, item);
        return size < originalSize;
    }

    public BinaryNode<Type> remove(BinaryNode<Type> node, Type item) {
        if (node == null)
            return null;

        int cmp = item.compareTo(node.element);

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
            // Node to remove only has right children
            if (node.leftChild == null) {
                size--;
                return node.rightChild;
            }

            // Node to remove only has left children
            else if (node.rightChild == null) {
                size--;
                return node.leftChild;
            }

             // Node to remove has 2 children
            else {
                Type successor = first(node.rightChild);
                node.element = successor;

                node.rightChild = remove(node.rightChild, successor);
            }
        }
        return node;
    }

    @Override
    public boolean removeAll(Collection<? extends Type> items) {
        if (items.isEmpty())
            return false;

        int originalSize = size;
        for (Type item : items) {
            remove(item);
        }

        return size < originalSize;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public ArrayList<Type> toArrayList() {
        ArrayList<Type> result = new ArrayList<>();
        toArrayList(root, result);

        return result;
    }

    private void toArrayList(BinaryNode<Type> node, ArrayList<Type> result) {
        if (node == null)
            return;

        if (node.leftChild != null)
            toArrayList(node.leftChild, result);

        result.add(node.element);

        if (node.rightChild != null)
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
