package livecodinginterview;

import java.util.*;

public class CodingInterviewTask5_TreeLevelOrderTraversal {

    /*
     * Problem: Tree Level Order Traversal
     *
     * Given a binary tree, return its values level by level.
     * Same BFS + layerSize pattern you already know.
     *
     * Reported in Booking.com requirements: "trees/graphs"
     *
     *        3
     *       / \
     *      9   20
     *         /  \
     *        15   7
     *
     * Output: [[3], [9, 20], [15, 7]]
     */

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        var root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        // Test 1: normal tree
        System.out.println(solution3(root));
        // Expected: [[3], [9, 20], [15, 7]]

        // Test 2: single node
        System.out.println(solution3(new TreeNode(1)));
        // Expected: [[1]]

        // Test 3: null tree
        System.out.println(solution3(null));
        // Expected: []
    }

    public static List<List<Integer>> solution4(TreeNode root) {
        var result = new ArrayList<List<Integer>>();

        var treeNodeDeque = new ArrayDeque<TreeNode>();
        treeNodeDeque.add(root);

        while (!treeNodeDeque.isEmpty()) {
            var layerSize = treeNodeDeque.size();
            var layer = new ArrayList<Integer>();

            for (var i = 0; i < layerSize; i++) {
                var node = treeNodeDeque.poll();
                var currentLevel = node.val;

                layer.add(currentLevel);

                if (node.left != null) {
                    treeNodeDeque.add(node.left);
                }

                if (node.right != null) {
                    treeNodeDeque.add(node.right);
                }
            }
            result.add(layer);
        }

        return result;
    }


    public static List<List<Integer>> solution3(TreeNode root) {
        if (root == null) {
            return List.of();
        }

        var result = new ArrayList<List<Integer>>();

        var orderQueue = new ArrayDeque<TreeNode>();
        orderQueue.add(root);

        while (!orderQueue.isEmpty()) {
            var layerSize = orderQueue.size();
            var layer = new ArrayList<Integer>();

            for (var i = 0; i < layerSize; i++) {
                var node = orderQueue.poll();

                if (node != null) {
                    layer.add(node.val);
                }

                if (node != null && node.left != null) {
                    orderQueue.add(node.left);
                }

                if (node != null && node.right != null) {
                    orderQueue.add(node.right);
                }
            }

            if (!layer.isEmpty()) {
                result.add(layer);
            }
        }

        return result;
    }


    public static List<List<Integer>> solution2(TreeNode root) {
        if (root == null) {
            return List.of();
        }

        var result = new ArrayList<List<Integer>>();

        var valuesQueue = new ArrayDeque<TreeNode>();
        valuesQueue.add(root);

        while (!valuesQueue.isEmpty()) {
            var layerSize = valuesQueue.size();
            var layer = new ArrayList<Integer>();

            for (var i = 0; i < layerSize; i++) {
                var node = valuesQueue.poll();

                if (node != null) {
                    layer.add(node.val);
                }

                if (node != null && node.left != null) {
                    valuesQueue.add(node.left);
                }

                if (node != null && node.right != null) {
                    valuesQueue.add(node.right);
                }
            }
            result.add(layer);
        }


        return result;
    }

    public static List<List<Integer>> solution1(TreeNode root) {
        if (root == null) {
            return List.of();
        }

        var result = new ArrayList<List<Integer>>();

        var trueQueue = new ArrayDeque<TreeNode>();
        trueQueue.add(root);

        while (!trueQueue.isEmpty()) {
            var layerSize = trueQueue.size();
            var layer = new ArrayList<Integer>();

            for (var i = 0; i < layerSize; i++) {
                var node = trueQueue.poll();

                if (node != null) {
                    layer.add(node.val);
                }

                if (node != null && node.left != null) {
                    trueQueue.add(node.left);
                }

                if (node != null && node.right != null) {
                    trueQueue.add(node.right);
                }
            }
            result.add(layer);
        }

        return result;
    }
}
