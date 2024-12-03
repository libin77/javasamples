package com.eltosevenz.collections.LinkedList.basics;

/*
Approach: Slow and Fast Pointer Technique

Use two pointers:
The slow pointer moves one node at a time.
The fast pointer moves two nodes at a time.

By the time the fast pointer reaches the end of the list, the slow pointer will be at the middle of the list.
 */

public class FindMiddleElement {
    // Method to find the middle of the linked list
    public static Node findMiddle(Node head) {
        if (head == null) {
            return null; // If the list is empty
        }

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next; // Move slow pointer by one
            fast = fast.next.next; // Move fast pointer by two
        }

        return slow; // Slow pointer is at the middle
    }

    // Method to print the list
    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        // Creating the linked list
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        System.out.println("Linked List:");
        printList(head);

        // Find the middle element
        Node middle = findMiddle(head);
        System.out.println("Middle Element: " + (middle != null ? middle.data : "List is empty"));
    }
}

