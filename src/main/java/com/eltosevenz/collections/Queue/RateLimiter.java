package com.eltosevenz.collections.Queue;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
/*
Design a RateLimiter class that allows N requests per T seconds per user.

Each user has a unique userId.
The RateLimiter should allow or deny requests based on the number of allowed requests in the
sliding window of T seconds.
 */
public class RateLimiter {

    int maxRequests;
    int timeWindowInSeconds;

    Map<String, Queue<Long>> rateLimiterMap = new HashMap<>();

    public RateLimiter(int maxRequests, int timeWindowInSeconds){
        this.maxRequests = maxRequests;
        this.timeWindowInSeconds = timeWindowInSeconds;
    }

    public boolean allowRequest(String userId, long timestampInSeconds){
        Queue<Long> hitsAllowed = rateLimiterMap.getOrDefault(userId, new ArrayDeque<>());
        while (!hitsAllowed.isEmpty() && (timestampInSeconds - hitsAllowed.peek() >= timeWindowInSeconds)) {
            hitsAllowed.poll();
        }
        if (hitsAllowed.size() < maxRequests) {
            hitsAllowed.offer(timestampInSeconds);
            rateLimiterMap.put(userId, hitsAllowed);
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] arg){
        RateLimiter rateLimiter = new RateLimiter(3,10);
        System.out.println(rateLimiter.allowRequest("u1", 1));
        System.out.println(rateLimiter.allowRequest("u1", 2));
        System.out.println(rateLimiter.allowRequest("u1", 8));
        System.out.println(rateLimiter.allowRequest("u1", 4));
        System.out.println(rateLimiter.allowRequest("u1", 14));
        System.out.println(rateLimiter.allowRequest("u2", 4));
        System.out.println(rateLimiter.allowRequest("u1", 15));
        System.out.println(rateLimiter.allowRequest("u1", 16));
        System.out.println(rateLimiter.rateLimiterMap);
    }
}