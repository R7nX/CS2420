package assign08;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TempTest {
    BinarySearchTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new BinarySearchTree<>();
        // Add some elements to the tree
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(3);
        tree.add(8);
        tree.add(12);
        tree.add(18);
    }

    @Test
    void testRemoveAllExistingElements() {
        assertTrue(tree.removeAll(Arrays.asList(3, 5, 8)));
        assertFalse(tree.contains(3));
        assertFalse(tree.contains(5));
        assertFalse(tree.contains(8));
        assertEquals(4, tree.size());
    }

    @Test
    void testRemoveAllNonExistingElements() {
        assertFalse(tree.removeAll(Arrays.asList(1, 7, 20)));
        assertEquals(7, tree.size());
    }

    @Test
    void testRemoveAllFromEmptyTree() {
        tree.clear();
        assertFalse(tree.removeAll(Arrays.asList(3, 5, 8)));
        assertEquals(0, tree.size());
    }

    @Test
    void testRemoveAllMixedElements() {
        assertTrue(tree.removeAll(Arrays.asList(5, 10, 15)));
        assertFalse(tree.contains(5));
        assertFalse(tree.contains(10));
        assertFalse(tree.contains(15));
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(8));
        assertTrue(tree.contains(12));
        assertTrue(tree.contains(18));
        assertEquals(4, tree.size());
    }

    @Test
    void testRemoveAllEmptyCollection() {
        assertFalse(tree.removeAll(Collections.emptyList()));
        assertEquals(7, tree.size());
    }

    @Test
    void testRemoveAllEntireTree() {
        assertTrue(tree.removeAll(Arrays.asList(3, 5, 8, 10, 12, 15, 18)));
        assertTrue(tree.isEmpty());
    }
}
