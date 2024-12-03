package com.eltosevenz.collections.LinkedList.basics;

/*
Traverse the list, and whenever two consecutive nodes have the same value, remove the duplicate node.
 */

public class RemoveDuplicates {
    public static Node removeDuplicates(Node head) {
        if (head == null) return null;

        Node current = head;

        while (current != null && current.next != null) {
            if (current.data == current.next.data) {
                current.next = current.next.next; // Remove duplicate
            } else {
                current = current.next; // Move to next node
            }
        }

        return head;
    }

    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        // Example Linked List
        Node head = new Node(1);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(3);

        System.out.println("Original List:");
        printList(head);

        head = removeDuplicates(head);

        System.out.println("List After Removing Duplicates:");
        printList(head);
    }
}

