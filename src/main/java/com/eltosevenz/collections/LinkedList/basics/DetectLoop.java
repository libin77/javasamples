package com.eltosevenz.collections.LinkedList.basics;

/*
The algorithm uses two pointers:

Slow Pointer: Moves one step at a time.
Fast Pointer: Moves two steps at a time.
If there is a loop, the two pointers will eventually meet.
 */
public class DetectLoop {
    // Method to detect a loop in the linked list
    public static boolean hasLoop(Node head) {
        if (head == null) return false;

        Node slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next; // Move slow by 1
            fast = fast.next.next; // Move fast by 2

            if (slow == fast) {
                return true; // Loop detected
            }
        }

        return false; // No loop
    }

    public static void main(String[] args) {
        // Example Linked List with a loop
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = head.next; // Creates a loop

        System.out.println("Has Loop: " + hasLoop(head)); // Output: true

        // Example Linked List without a loop
        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        System.out.println("Has Loop: " + hasLoop(head2)); // Output: false
    }
}

