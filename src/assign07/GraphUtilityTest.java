package assign07;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphUtilityTest<T> {
	private List<Integer> sources;
	private List<Integer> destinations;

	@BeforeEach
	void setUp() {
		// Initialize test data
		sources = Arrays.asList(1, 2, 3);
		destinations = Arrays.asList(3, 4, 5);
	}

// test throw correctly
	@Test
	void testAreConnectedThrows() {

	}

	@Test
	void testShortestPathThrows() {
	}

	@Test
	void testGraphUtilityThrows() {
	}

	// test normal case areconnected
	@Test
	void testAreConnected() {
		Graph<Integer> graph = new Graph<>();

		// Initialize vertices
		Integer vertex1 = 1;
		Integer vertex2 = 2;
		Integer vertex3 = 3;
		Integer vertex4 = 4;
		Integer vertex5 = 5;

		// Add edges
		graph.addEdge(vertex1, vertex2);
		graph.addEdge(vertex1, vertex3);
		graph.addEdge(vertex2, vertex4);
		graph.addEdge(vertex3, vertex5);

		assertTrue(graph.dfs(1, 5)); // There's a path from 1 to 5
		assertTrue(graph.dfs(2, 4)); // There's a path from 3 to 4
		assertFalse(graph.dfs(5, 1)); // There's no path from 5 to 1
		assertFalse(graph.dfs(4, 1)); // There's no path from 4 to 1
	}


	@Test
	public void testTopologicalGraphUtilityWithoutCycle() {
		Graph<String> graph = new Graph<>();

		// Add vertices and edges to create a graph without a cycle
		graph.addEdge("A", "B");
		graph.addEdge("A", "C");
		graph.addEdge("B", "D");
		graph.addEdge("C", "D");

		try {
			List<String> result = graph.topologicalSort();
			// Assert statements to verify the correctness of the topological sort result
			assertEquals(4, result.size());
			assertTrue(result.indexOf("A") < result.indexOf("B"));
			assertTrue(result.indexOf("A") < result.indexOf("C"));
			assertTrue(result.indexOf("B") < result.indexOf("D"));
			assertTrue(result.indexOf("C") < result.indexOf("D"));
		} catch (IllegalArgumentException e) {
			fail("Unexpected IllegalArgumentException");
		}
	}		// test normal case sort
	@Test
	void testGraphUtility() {
		List<Integer> sortedList = GraphUtility.sort(sources, destinations);
		assertEquals(Arrays.asList(1, 2, 3, 4, 5), sortedList);
	}

	@Test
	void testTopologicalGraphUtility() {
		Graph<String> graph = new Graph<>();
		graph.addEdge("A", "B");
		graph.addEdge("A", "C");
		graph.addEdge("B", "D");
		graph.addEdge("C", "D");

		// Expected topological order: A, B, C, D
		assertEquals("[A, B, C, D]", graph.topologicalSort().toString());
	}

	@Test
	void testTopologicalGraphUtilityWithCycle() {
		Graph<String> graph = new Graph<>();
		graph.addEdge("A", "B");
		graph.addEdge("B", "C");
		graph.addEdge("C", "A"); // introducing a cycle

		// Expected: IllegalArgumentException thrown due to cycle
		assertThrows(IllegalArgumentException.class, () -> graph.topologicalSort());
	}

	@Test
	void testTopologicalGraphUtilityWithDisconnectedGraph() {
		Graph<String> graph = new Graph<>();
		graph.addEdge("A", "B");
		graph.addEdge("C", "D");

		// Expected topological order: A, B, C, D
		assertEquals("[A, C, B, D]", graph.topologicalSort().toString());
	}


	@Test
	void testTopologicalGraphUtilityWithEmptyGraph() {
		Graph<String> graph = new Graph<>();

		// Expected topological order: empty list
		assertEquals("[]", graph.topologicalSort().toString());
	}

	@Test
	void testLinearDAG() {
		Graph<String> graph = new Graph<>();
		graph.addEdge("A", "B");
		graph.addEdge("B", "C");
		graph.addEdge("C", "D");

		List<String> expectedOrder = Arrays.asList("A", "B", "C", "D");
		List<String> actualOrder = graph.topologicalSort();

		assertEquals(expectedOrder, actualOrder, "Linear DAG should result in a linear topological ordering");
	}
	@Test
	void testTreeDAG() {
		Graph<String> graph = new Graph<>();
		graph.addEdge("A", "B");
		graph.addEdge("A", "C");
		graph.addEdge("B", "D");
		graph.addEdge("B", "E");
		graph.addEdge("C", "F");

		List<String> expectedOrder = Arrays.asList("A", "B", "C", "D", "E", "F");
		List<String> actualOrder = graph.topologicalSort();

		assertEquals(expectedOrder, actualOrder, "Tree DAG should result in a hierarchical topological ordering");
	}

	@Test
	void testGraphUtility1() {
		List<String> sources = Arrays.asList("A", "B", "C");
		List<String> destinations = Arrays.asList("B", "C", "D");

		// Expected topological order: A, B, C, D
		assertEquals("[A, B, C, D]", GraphUtility.sort(sources, destinations).toString());
	}

	@Test
	void testGraphUtilityWithMismatchedSizes() {
		List<String> sources = Arrays.asList("A", "B");
		List<String> destinations = Arrays.asList("B", "C", "D");

		// Expected: IllegalArgumentException thrown due to mismatched sizes
		assertThrows(IllegalArgumentException.class, () -> GraphUtility.sort(sources, destinations));
	}

	@Test
	void testGraphUtilityWithEmptyLists() {
		List<String> sources = new ArrayList<>();
		List<String> destinations = new ArrayList<>();

		// Expected: IllegalArgumentException thrown due to empty lists
		assertThrows(IllegalArgumentException.class, () -> GraphUtility.sort(sources, destinations));
	}

	@Test
	void testGraphUtilityWithCyclicDependency() {
		List<String> sources = Arrays.asList("A", "B", "C");
		List<String> destinations = Arrays.asList("B", "C", "A");

		// Expected: IllegalArgumentException thrown due to cyclic dependency
		assertThrows(IllegalArgumentException.class, () -> GraphUtility.sort(sources, destinations));
	}

	@Test
	void testSortWithSingleElement() {
		List<String> sources = Collections.singletonList("A");
		List<String> destinations = Collections.singletonList("B");

		// Expected topological order: A, B
		assertEquals("[A, B]", GraphUtility.sort(sources, destinations).toString());
	}

	@Test
	void testSortWithDuplicateElements() {
		List<String> sources = Arrays.asList("A", "B", "C", "A");
		List<String> destinations = Arrays.asList("B", "C", "D", "B");

		// Expected topological order: A, B, C, D
		assertEquals("[A, B, C, D]", GraphUtility.sort(sources, destinations).toString());
	}

	@Test
	void testSortWithNullElements() {
		List<String> sources = Arrays.asList("A", null, "C");
		List<String> destinations = Arrays.asList("B", "C", "D");

		// Expected: IllegalArgumentException thrown due to null element
		assertThrows(IllegalArgumentException.class, () -> GraphUtility.sort(sources, destinations));
	}

	@Test
	void testSortWithEmptyList() {
		List<String> sources = Collections.emptyList();
		List<String> destinations = Collections.emptyList();

		// Expected: IllegalArgumentException thrown due to empty lists
		assertThrows(IllegalArgumentException.class, () -> GraphUtility.sort(sources, destinations));
	}

	@Test
	void testSortWithSameElements() {
		List<String> sources = Arrays.asList("A", "A", "A");
		List<String> destinations = Arrays.asList("B", "B", "B");

		// Expected topological order: A, B
		assertEquals("[A, B]", GraphUtility.sort(sources, destinations).toString());
	}

	@Test
	void testSortWithRepeatedDependency() {
		List<String> sources = Arrays.asList("A", "B", "C");
		List<String> destinations = Arrays.asList("B", "B", "C");

		// Expected: IllegalArgumentException thrown due to repeated dependency
		assertThrows(IllegalArgumentException.class, () -> GraphUtility.sort(sources, destinations));
	}

	@Test
	void testSortWithLargeCycle() {
		List<String> sources = Arrays.asList("A", "B", "C", "D");
		List<String> destinations = Arrays.asList("B", "C", "D", "A");

		// Expected: IllegalArgumentException thrown due to large cycle
		assertThrows(IllegalArgumentException.class, () -> GraphUtility.sort(sources, destinations));
	}

	@Test
	void testSortWithValidInput() {
		List<String> sources = Arrays.asList("A", "B", "C", "D");
		List<String> destinations = Arrays.asList("B", "C", "D", "E");

		// Expected topological order: A, B, C, D, E
		assertEquals("[A, B, C, D, E]", GraphUtility.sort(sources, destinations).toString());
	}

}
