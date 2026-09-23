package com.amc.bfsi.withoutspring;

import java.util.*;

public class OutputQuiz {
    static int tryFinallyValue() {
        int x = 1;
        try { return x; }
        finally { x = 99; }            // changes x AFTER the return value was taken
    }
    static int tryFinallyReturn() {
        try { return 1; }
        finally { return 2; }          // a return in finally wins
    }
    static String overload(Object o) { return "Object"; }
    static String overload(String s) { return "String"; }

    static class Customer {
        final int id;
        Customer(int id) { this.id = id; }
    }

    public static void main(String[] args) {
        System.out.println("Q1  " + (7 / 2) + " , " + (7 % 2) + " , " + ((double) 7 / 2));
        System.out.println("Q2  " + (0.1 + 0.2) + " , equals 0.3 = " + (0.1 + 0.2 == 0.3));

        String a = "AMC", b = "AMC", c = new String("AMC");
        System.out.println("Q3  a==b " + (a == b) + " , a==c " + (a == c) + " , a.equals(c) " + a.equals(c));

        int i = 0;
        System.out.println("Q4  " + (i++ + ++i));

        Integer x1 = 127, y1 = 127, x2 = 128, y2 = 128;
        System.out.println("Q5  127==127 " + (x1 == y1) + " , 128==128 " + (x2 == y2));

        Set<Customer> set = new HashSet<>();
        set.add(new Customer(1)); set.add(new Customer(1));
        System.out.println("Q6  " + set.size());

        List<Integer> nums = new ArrayList<>(List.of(10, 20, 30));
        nums.remove(1);                                  // index, not value
        System.out.println("Q7  " + nums);
        List<Integer> nums2 = new ArrayList<>(List.of(10, 20, 30));
        nums2.remove(Integer.valueOf(10));
        System.out.println("Q8  " + nums2);

        System.out.println("Q9  " + tryFinallyValue() + " , " + tryFinallyReturn());

        System.out.println("Q10 " + overload(null));      // most specific wins

        try {
            List<String> fixed = Arrays.asList("a", "b");
            fixed.add("c");
        } catch (UnsupportedOperationException e) {
            System.out.println("Q11 UnsupportedOperationException");
        }

        List<Integer> three = new ArrayList<>(List.of(10, 20, 30));
        try {
            for (Integer n : three) { if (n == 10) three.remove(n); }
            System.out.println("Q12 no exception, list = " + three);
        } catch (ConcurrentModificationException e) {
            System.out.println("Q12 ConcurrentModificationException");
        }
        List<Integer> two = new ArrayList<>(List.of(10, 30));
        try {
            for (Integer n : two) { if (n == 10) two.remove(n); }
            System.out.println("Q12b NO exception - loop just ended early, list = " + two);
        } catch (ConcurrentModificationException e) {
            System.out.println("Q12b ConcurrentModificationException");
        }

        Map<String, Integer> m = new HashMap<>();
        m.put("A", 1);
        m.merge("A", 5, Integer::sum);
        m.merge("B", 5, Integer::sum);
        System.out.println("Q13 " + m + " , get(\"Z\") = " + m.get("Z") + " , getOrDefault " + m.getOrDefault("Z", 0));

        char ch = 'A';
        System.out.println("Q14 " + (ch + 1) + " , " + (char) (ch + 1) + " , " + ("" + ch + 1));

        System.out.println("Q15 " + new StringBuilder("abc").reverse());

        long count = List.of("a", "bb", "ccc").stream().filter(s -> s.length() > 1).count();
        System.out.println("Q16 " + count);

        List<String> src = List.of("b", "a", "c");
        List<String> sorted = new ArrayList<>(src);
        Collections.sort(sorted);
        System.out.println("Q17 " + src + " , " + sorted);
    }
}
