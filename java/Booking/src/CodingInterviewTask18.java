import java.util.*;

public class CodingInterviewTask18 {

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
        System.out.println(solution1(root));
        // Expected: [[3], [9, 20], [15, 7]]

        // Test 2: single node
        System.out.println(solution1(new TreeNode(1)));
        // Expected: [[1]]

        // Test 3: null tree
        System.out.println(solution1(null));
        // Expected: []
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
