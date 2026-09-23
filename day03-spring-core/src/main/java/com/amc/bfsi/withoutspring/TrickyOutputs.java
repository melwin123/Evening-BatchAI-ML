package com.amc.bfsi.withoutspring;

import java.util.*;

public class TrickyOutputs {
    static int finallyWins() {
        try { return 1; } finally { return 2; }
    }
    static int finallyDoesNotOverwrite() {
        int x = 1;
        try { return x; } finally { x = 99; }
    }
    static class NoHash {
        final int id; NoHash(int id){this.id=id;}
        @Override public boolean equals(Object o){ return o instanceof NoHash && ((NoHash)o).id==id; }
    }
    public static void main(String[] a) {
        int p=7,q=2;
        System.out.println("1  " + (p/q) + " " + ((double)p/q) + " " + (p%q));
        System.out.println("2  " + ("10"+20) + " " + (10+20+"x") + " " + ('A'+1) + " " + (char)('A'+1));
        int i=0; System.out.println("3  " + (i++ + ++i));
        System.out.println("4  " + (0.1+0.2) + " " + (0.1+0.2==0.3));
        String s1="AMC", s2="AMC", s3=new String("AMC");
        System.out.println("5  " + (s1==s2) + " " + (s1==s3) + " " + s1.equals(s3));
        Integer i1=127,i2=127,i3=128,i4=128;
        System.out.println("6  " + (i1==i2) + " " + (i3==i4));
        List<Integer> nums = new ArrayList<>(List.of(10,20,30));
        nums.remove(1);
        System.out.println("7  " + nums);
        List<Integer> nums2 = new ArrayList<>(List.of(10,20,30));
        nums2.remove(Integer.valueOf(10));
        System.out.println("8  " + nums2);
        Set<NoHash> set = new HashSet<>(); set.add(new NoHash(1)); set.add(new NoHash(1));
        System.out.println("9  " + set.size());
        System.out.println("10 " + finallyWins() + " " + finallyDoesNotOverwrite());
        Map<String,String> hm = new HashMap<>(); hm.put(null,"x");
        System.out.println("11 " + hm.get(null) + " " + hm.size());
        List<String> fixed = Arrays.asList("a","b");
        try { fixed.add("c"); } catch (UnsupportedOperationException e) { System.out.println("12 UnsupportedOperationException"); }
        List.of("a","b","c").stream().filter(s -> { System.out.println("   filter ran"); return true; });
        System.out.println("13 nothing printed above - no terminal operation");
        StringBuilder sb = new StringBuilder("AMC"); sb.append(" Bank");
        String str = "AMC"; str.concat(" Bank");
        System.out.println("14 " + sb + " | " + str);
        System.out.println("15 " + Math.round(-4.5) + " " + Math.round(4.5) + " " + (-7/2) + " " + (-7%2));
    }
}
