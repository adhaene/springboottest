package com.bezkoder.spring.jpa.h2.exercices;



class TreeHeight {

    public static void main(String[] args) {
        Tree right2  = new Tree(105, null, null);
        Tree left  = new Tree(5, null, right2);
        Tree right  = new Tree(5, null, null);
        Tree top  = new Tree(5, left, right);
        TreeHeight unit = new TreeHeight();
        System.out.println( unit.solution(top));
    }
    public int solution(Tree T) {
        return height(T);
    }

    private int height(Tree node) {
        if (node == null) {
            return -1; // empty tree convention
        }
        int left = height(node.l);
        int right = height(node.r);
        return 1 + Math.max(left, right);
    }
}
