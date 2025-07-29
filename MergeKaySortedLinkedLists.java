import java.util.*;

class Node {
	int data;
	Node next;
	Node(int value) {
		data = value;
		next = null;
	}
}

class Solution {
	static Node mergeKLists(List<Node> headNodes) {
		// This declaration leverages the fact that the compiler is smart enough to infer
		// from a lambda expression that it must initialize an anonymous class that implements
		// the Comparator interface, which is one of the PriorityQueue constructor options.
		PriorityQueue<Node> minHeap = new PriorityQueue<>((node1, node2) -> node1.data - node2.data);

		// Insert the head nodes of K lists; O(k)
		for (Node head : headNodes) {
			if (head != null) minHeap.add(head);
		}

		// Initialize a dummy head; temporarily point tail to dummy head, too.
		Node dummy = new Node(-1);
		Node tail = dummy;

		// While nodes in any Kth list exist...
		while(!minHeap.isEmpty()) {

			// Pop the min heap node
			Node top = minHeap.poll();

			// Build up list with this value
			tail.next = top;
			// Track "new" tail of the list
			tail = top;

			// If the top node has a next node,
			// then add it to the min heap.
			if (top.next != null) {
				minHeap.add(top.next);
			}
		}

		return dummy.next;
	}

	// Here is a convenience method to show the merged list
	static void printList(Node node) {
		while (node != null) {
			System.out.printf("%d ", node.data);
			node = node.next; //  traverse to next linked node
		}
		System.out.printf("%n");
	}

	public static void main(String [] args) {

		int k = 3; // not used, present to remind us of number of lists

		List<Node> headNodes = new ArrayList<>();

		headNodes.add(new Node(1));
		headNodes.get(0).next = new Node(3);
		headNodes.get(0).next.next = new Node(5);
		headNodes.get(0).next.next.next = new Node(7);

		headNodes.add(new Node(2));
		headNodes.get(1).next = new Node(4);
		headNodes.get(1).next.next = new Node(6);
		headNodes.get(1).next.next.next = new Node(8);

		headNodes.add(new Node(0));
                headNodes.get(2).next = new Node(9);
                headNodes.get(2).next.next = new Node(10);
                headNodes.get(2).next.next.next = new Node(11);

		Node head = mergeKLists(headNodes);

		printList(head);
	}

}
