package com.eltosevenz.collections.LinkedList.basics;

public class ReverseLinkedList {
    public static Node reverse(Node head) {
        Node prev = null;
        Node current = head;

        while (current != null) {
            Node next = current.next;
            System.out.print("Next: ");
            printList(next);
            current.next = prev;
            System.out.print("current.next: ");
            printList(current.next);
            prev = current;
            System.out.print("prev: ");
            printList(prev);
            current = next;
        }

        return prev; // New head
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
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);

        System.out.println("Original List:");
        printList(head);

        head = reverse(head);

        System.out.println("Reversed List:");
        printList(head);
    }
}

