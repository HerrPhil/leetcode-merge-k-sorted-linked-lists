# Leetcode Merge K Sorted Linked Lists

Given k sorted linked lists of different sizes,
the task is to merge them all maintaining their cumulative sorted order.

This solution employs a minimum heap to achieve log(n) performance.

In Java, the minimum head data structure is known as the PriorityQueue.

When an element is added to a minimum heap,
then it re-balances the b-tree so that the minimum value is always on top.

Corrollary: the minimum value pops off the end of the queue,
since it has priority.

## The algorithm

Send a list of the head nodes of the linked lists.
Seed the minimum heap with the head nodes.
Track a dummy node (of -1, for example).
Point the the tail node to the dummy node.


On iteration #1:
Pop the minimum heap, assign to top (of the minimum heap).

That value is one of the head nodes.

Assign tail.next = top (aka assign dummy.next = top).
Move the tail "pointer"; assign tail = top.
If top has a next node, then add this node to the minimum heap.
The heap, by design, re-balances the nodes so that the minimum value is on top.


On iteration #2:
Pop the minimum heap, assign to top (of the minimum heap).

Note, now the top might be a head from one of the other k-1 lists,
or the next value of the head value processed in iteration #1.

Assign tail.next = top (of the heap, the smallest value from k lists).
Move the tail "pointer"; assign tail = top.
If top has a next node, then add this node to the minimum heap.
The heap, by design, re-balances the nodes so that the minimum value is on top.

On iteration #3:
Pop the minimum heap, assign to the top (of the minimum heap).

Note, no the top might be a head from one of the other k-1 lists,
when the top from iteration #2 was from the first list encountered,
Otherwise the top might be a head from one of the other k-2 lists,
when the top is the head of another list in iteration #2.

Assign tail.next = top (of the heap, the smallest value from k lists).
Move the tail "pointer"; assign tail = top.
If top has a next node, then add this node to the minimum heap.
The heap, by design, re-balances the node so that the minimum value is on top.

On subsequent iterations, we see the min heap is linked list agnostic.
It just sees the next smallest node value it knows about from k merged lists.

Finally, when the minimum heap is empty,
then return the next value of the dummy pointer.
This is the head node of the merged list.
