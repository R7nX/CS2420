package assign08;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {
  BinarySearchTree<Integer> bst;

  @BeforeEach
  void setUp() throws Exception {
    BinarySearchTree<Integer> bst = new BinarySearchTree<>();
    this.bst = bst;
    // Add some elements to the tree
    bst.add(10);
    bst.add(5);
    bst.add(15);
    bst.add(3);
    bst.add(7);
    bst.add(12);
    bst.add(18);
  }

  // normal test cases
  @Test
  void testContains() {
    assertTrue(bst.contains(10)); // Tree contains 10
    assertTrue(bst.contains(5)); // Tree contains 5
    assertTrue(bst.contains(15)); // Tree contains 15
    assertTrue(bst.contains(3)); // Tree contains 3
    assertTrue(bst.contains(7)); // Tree contains 7
    assertTrue(bst.contains(12)); // Tree contains 12
    assertTrue(bst.contains(18)); // Tree contains 18

    assertFalse(bst.contains(0)); // Tree does not contain 0
    assertFalse(bst.contains(20)); // Tree does not contain 20
    assertFalse(bst.contains(8)); // Tree does not contain 8
    assertFalse(bst.contains(13)); // Tree does not contain 13
  }

  @Test
  void testContainsAll() {
    List<Integer> collection1 = Arrays.asList(10, 5, 15); // All elements exist in the tree
    List<Integer> collection2 = Arrays.asList(10, 5, 20); // Some elements exist in the tree
    List<Integer> collection3 = Arrays.asList(11, 20, 30); // No elements exist in the tree
    List<Integer> emptyCollection = Arrays.asList(); // No elements exist in the tree

    // Test containsAll method
    assertTrue(bst.containsAll(collection1)); // All elements in collection1 are in the tree
    assertFalse(bst.containsAll(collection2)); // Not all elements in collection2 are in the tree
    assertFalse(bst.containsAll(collection3)); // No elements in collection3 are in the tree
    assertFalse(bst.containsAll(emptyCollection)); // No elements in collection3 are in the tree

  }

  @Test
  void testClear() {
    bst.clear();
    assertTrue(bst.isEmpty(), "Clear method does not work as expected");
  }

  @Test
  void testFirst() {
    assertEquals(3, bst.first(), "Expected return the smallest number in the set");
  }

  @Test
  void testLast() {
    assertEquals(18, bst.last(), "Expected return the largest number in the set");
  }

  // focus on add
  @Test
  void testAddNavigateLeft() {
    bst.clear();
    // Add elements to the left side of the tree
    assertTrue(bst.add(10)); // Adding the root
    assertTrue(bst.add(5)); // Adding a left child of 10
    assertTrue(bst.add(3)); // Adding a left child of 5
    assertTrue(bst.add(7)); // Adding a right child of 5

    // Verify if elements are correctly positioned
    assertTrue(bst.contains(10)); // Root should be 10
    assertTrue(bst.contains(5)); // Left child of 10 should be 5
    assertTrue(bst.contains(3)); // Left child of 5 should be 3
    assertTrue(bst.contains(7)); // Right child of 5 should be 7

    assertFalse(bst.contains(15)); // 15 shouldn't be in the tree
    assertFalse(bst.contains(4)); // 4 shouldn't be in the tree
    assertFalse(bst.contains(8)); // 8 shouldn't be in the tree
  }

  @Test
  void testAddNavigateRight() {
    bst.clear();
    // Add elements to the left side of the tree
    assertTrue(bst.add(10)); // Adding the root
    assertTrue(bst.add(15)); // Adding a right child of 10
    assertTrue(bst.add(12)); // Adding a left child of 15
    assertTrue(bst.add(16)); // Adding a right child of 15

    // Verify if elements are correctly positioned
    assertTrue(bst.contains(10)); // Root should be 10
    assertTrue(bst.contains(15)); // right child of 10 should be 15
    assertTrue(bst.contains(12)); // Left child of 15 should be 12
    assertTrue(bst.contains(16)); // Right child of 15 should be 16

    assertFalse(bst.contains(9)); // 9 shouldn't be in the tree
    assertFalse(bst.contains(4)); // 4 shouldn't be in the tree
    assertFalse(bst.contains(8)); // 8 shouldn't be in the tree
  }

  @Test
  void testAddFalse() {
    bst.clear();
    assertFalse(bst.add(null));
    assertTrue(bst.isEmpty()); // The tree should still be empty

  }

  @Test
  void testAddAll() {
    bst.clear();
    List<Integer> elementsToAdd = Arrays.asList(10, 5, 15, 3, 7);
    assertTrue(bst.addAll(elementsToAdd));
    assertTrue(bst.contains(10)); // 10 should be in the tree
    assertTrue(bst.contains(5)); // 5 should be in the tree
    assertTrue(bst.contains(15)); // 15 should be in the tree
    assertTrue(bst.contains(3)); // 3 should be in the tree
    assertTrue(bst.contains(7)); // 7 should be in the tree

    // Ensure that other elements are not added to the tree
    assertFalse(bst.contains(20)); // 20 should not be in the tree
    assertFalse(bst.contains(1)); // 1 should not be in the tree
    assertFalse(bst.contains(12)); // 12 should not be in the tree
  }

  // focus on remove
  @Test
  void testRemoveOneElement() {
    bst.clear();
    bst.add(10);
    bst.remove(10);
    assertTrue(bst.isEmpty(), "10 has not been removed");
  }

  @Test
  void testRemoveNoChildren() {
    bst.clear();
    bst.add(10);
    bst.add(5);
    bst.add(15);
    bst.add(3);
    bst.add(7);

    // Remove a leaf node (node containing 3)
    assertTrue(bst.remove(3)); // Removing a leaf node should return true

    // Verify if the leaf node has been removed and other nodes are still present
    assertFalse(bst.contains(3)); // 3 should be removed from the tree
    assertTrue(bst.contains(10)); // 10 should still be in the tree
    assertTrue(bst.contains(5)); // 5 should still be in the tree
    assertTrue(bst.contains(15)); // 15 should still be in the tree
    assertTrue(bst.contains(7)); // 7 should still be in the tree
  }

  @Test
  void testRemoveOnlyOneChild() {
    bst.clear();
    // Add elements to the tree
    bst.add(10);
    bst.add(5);
    bst.add(15);
    bst.add(3);
    bst.add(7);
    bst.add(12);
    bst.add(18);

    // Remove a node with one child (node containing 15)
    assertTrue(bst.remove(15)); // Removing a node with one child should return true

    // Verify if the node with one child has been removed and other nodes are still
    // present
    assertFalse(bst.contains(15)); // 15 should be removed from the tree
    assertTrue(bst.contains(10)); // 10 should still be in the tree
    assertTrue(bst.contains(5)); // 5 should still be in the tree
    assertTrue(bst.contains(3)); // 3 should still be in the tree
    assertTrue(bst.contains(7)); // 7 should still be in the tree
    assertTrue(bst.contains(12)); // 12 should still be in the tree
    assertTrue(bst.contains(18)); // 18 should still be in the tree
  }

  @Test
  void testRemoveTwoChildren() {
    bst.clear();
    // Add elements to the tree
    bst.add(10);
    bst.add(5);
    bst.add(15);
    bst.add(3);
    bst.add(7);
    bst.add(12);
    bst.add(18);

    // Remove a node with one child (node containing 15)
    assertTrue(bst.remove(10)); // Removing a node with one child should return true

    // Verify if the node with one child has been removed and other nodes are still
    // present
    assertFalse(bst.contains(10)); // 15 should be removed from the tree
    assertTrue(bst.contains(15)); // 10 should still be in the tree
    assertTrue(bst.contains(5)); // 5 should still be in the tree
    assertTrue(bst.contains(3)); // 3 should still be in the tree
    assertTrue(bst.contains(7)); // 7 should still be in the tree
    assertTrue(bst.contains(12)); // 12 should still be in the tree
    assertTrue(bst.contains(18)); // 18 should still be in the tree
  }

  @Test
  void testRemoveAll() {
    List<Integer> elementsToRemove = Arrays.asList(5, 15, 7);

    // Remove elements from the tree using removeAll method
    assertTrue(bst.removeAll(elementsToRemove)); // Removing elements should return true

    // Verify if the elements are removed from the tree
    // assertFalse(bst.contains(5)); // 5 should be removed from the tree
    // assertFalse(bst.contains(15)); // 15 should be removed from the tree
    // assertFalse(bst.contains(7)); // 7 should be removed from the tree
    // assertTrue(bst.contains(10)); // 10 should still be in the tree
    // assertTrue(bst.contains(3)); // 3 should still be in the tree
    // assertTrue(bst.contains(12)); // 12 should still be in the tree
    // assertTrue(bst.contains(18)); // 18 should still be in the tree
  }

  // focus on toArray
  @Test
  void testToArr() {
    bst.clear();
    // Add elements to the tree
    bst.add(10);
    bst.add(5);
    bst.add(15);
    bst.add(3);
    bst.add(7);
    bst.add(12);
    bst.add(18);

    ArrayList<Integer> arrayList = bst.toArrayList();

    // Create a List containing the same elements for comparison
    List<Integer> expectedList = Arrays.asList(3, 5, 7, 10, 12, 15, 18);

    // Verify if the ArrayList contains all elements from the tree and if the order
    // is correct
    assertEquals(expectedList, arrayList);
    assertEquals(7, arrayList.size());
  }

  // focus on Throws
  @Test
  void testFirstThrows() {
    bst.clear();
    bst.add(10);
    bst.add(5);
    bst.add(3);
    assertThrows(NoSuchElementException.class, () -> bst.first());
  }

  @Test
  void testLastThrows() {
    bst.clear();
    bst.add(10);
    bst.add(15);
    bst.add(13);
    assertThrows(NoSuchElementException.class, () -> bst.last());
  }

  // focus on edge (Large data set, duplication, ... )
}
