package com.eltosevenz.collections.LinkedList.basics;

/*
Reverse Both Linked Lists: This simplifies addition since the least significant digits align at the head of the reversed lists.

Perform Addition: Add the digits node by node, keeping track of any carry.

Create a Result Linked List: Store each digit of the result in a new linked list.

Reverse the Result List: The final result should be in forward order.
 */
public class AddLinkedLists {

    // Reverse a linked list
    private static Node reverse(Node head) {
        Node prev = null, current = head;
        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    // Add two numbers represented by linked lists
    public static Node addTwoLists(Node l1, Node l2) {
        // Reverse both linked lists
        l1 = reverse(l1);
        l2 = reverse(l2);

        Node dummy = new Node(0);
        Node current = dummy;
        int carry = 0;

        // Add digits and carry
        while (l1 != null || l2 != null || carry > 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.data;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.data;
                l2 = l2.next;
            }

            carry = sum / 10; // Calculate carry
            current.next = new Node(sum % 10); // Create a new node for the result
            current = current.next;
        }

        // Reverse the result linked list to restore forward order
        return reverse(dummy.next);
    }

    // Print the linked list
    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        // Create LinkedList 1: 4 -> 5 -> 6
        Node l1 = new Node(4);
        l1.next = new Node(5);
        l1.next.next = new Node(6);

        // Create LinkedList 2: 2 -> 5 -> 6
        Node l2 = new Node(2);
        l2.next = new Node(5);
        l2.next.next = new Node(6);

        System.out.println("Linked List 1:");
        printList(l1);

        System.out.println("Linked List 2:");
        printList(l2);

        // Add the two linked lists
        Node result = addTwoLists(l1, l2);

        System.out.println("Result Linked List:");
        printList(result); // Output: 7 -> 1 -> 2
    }
}
