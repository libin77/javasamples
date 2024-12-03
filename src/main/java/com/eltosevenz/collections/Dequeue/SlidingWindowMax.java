package com.eltosevenz.collections.Dequeue;

import java.util.*;

public class SlidingWindowMax {
    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>(); // Stores indices

        for (int i = 0; i < nums.length; i++) {
            // Remove indices that are out of the window
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // Remove indices of elements smaller than the current element
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // Add the current element's index
            deque.offerLast(i);

            // Add the max value to the result when the window is full
            if (i >= k - 1) {
                result.add(nums[deque.peekFirst()]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SlidingWindowMax swm = new SlidingWindowMax();
        int[] nums = {2, 5, 1, -2, 4, 9};
        int k = 3;
        List<Integer> result = swm.maxSlidingWindow(nums, k);
        System.out.println(result); // Output: [5, 5, 4, 9]
    }
}

