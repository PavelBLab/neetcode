package livecodinginterview;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class CodingInterviewTask6_1_HotelChainOrgChart {

    /*
     * Problem: Hotel Chain Org Chart
     *
     * Booking.com's partner hotel chain has a management hierarchy.
     * Each manager oversees at most two direct reports (left and right).
     *
     * Given the root manager, return a report showing staff at each
     * management level for a company review meeting.
     */

    static class OrgNode {
        String title;
        OrgNode left;
        OrgNode right;

        OrgNode(String title) {
            this.title = title;
        }
    }

    public static void main(String[] args) {
        //          CEO
        //         /    \
        //   VP Ops    VP Tech
        //    /          \
        // Regional    Head of Support

        var root = new OrgNode("CEO");
        root.left = new OrgNode("VP Operations");
        root.right = new OrgNode("VP Technology");
        root.left.left = new OrgNode("Regional Manager");
        root.right.right = new OrgNode("Head of Support");

        // Test 1: full org chart
        System.out.println(solution4(root));
        // Expected: [[CEO], [VP Operations, VP Technology], [Regional Manager, Head of Support]]

        // Test 2: single person company
        System.out.println(solution4(new OrgNode("Founder")));
        // Expected: [[Founder]]

        // Test 3: no company
        System.out.println(solution4(null));
        // Expected: []
    }

    public static List<List<String>> solution4(OrgNode root) {
        if (root == null) {
            return List.of();
        }

        var result = new ArrayList<List<String>>();

        var orgNodeDeque = new ArrayDeque<OrgNode>();
        orgNodeDeque.add(root);

        while (!orgNodeDeque.isEmpty()) {
            var layerSize = orgNodeDeque.size();
            var layer = new ArrayList<String>();

            for (var i = 0; i < layerSize; i++) {
                var node = orgNodeDeque.poll();
                var currentTitle = node.title;

                layer.add(currentTitle);

                if (node.left != null) orgNodeDeque.add(node.left);
                if (node.right != null) orgNodeDeque.add(node.right);
            }
            result.add(layer);
        }
        return result;
    }

    public static List<List<String>> solution3(OrgNode root) {
        if (root == null) {
            return List.of();
        }

        var result = new ArrayList<List<String>>();

        var nodesQueue = new ArrayDeque<OrgNode>();
        nodesQueue.add(root);

        while (!nodesQueue.isEmpty()) {
            var layerSize = nodesQueue.size();
            var layer = new ArrayList<String>();

            for (var i = 0; i < layerSize; i++) {
                var currentNode = nodesQueue.poll();

                if (currentNode != null) {
                    layer.add(currentNode.title);
                }

                if (currentNode != null && currentNode.left != null) {
                    nodesQueue.add(currentNode.left);
                }

                if (currentNode != null && currentNode.right != null) {
                    nodesQueue.add(currentNode.right);
                }
            }
            result.add(layer);
        }

        return result;
    }


    public static List<List<String>> solution2(OrgNode root) {
        if (root == null) {
            return List.of();
        }

        var result = new ArrayList<List<String>>();

        var managerJobTitleQueue = new ArrayDeque<OrgNode>();
        managerJobTitleQueue.add(root);

        while (!managerJobTitleQueue.isEmpty()) {
            var layerSize = managerJobTitleQueue.size();
            var layer = new ArrayList<String>();

            for (var i = 0; i < layerSize; i++) {
                var orgNode = managerJobTitleQueue.poll();

                if (orgNode != null) {
                    layer.add(orgNode.title);
                }

                if (orgNode != null && orgNode.left != null) {
                    managerJobTitleQueue.add(orgNode.left);
                }

                if (orgNode != null && orgNode.right != null) {
                    managerJobTitleQueue.add(orgNode.right);
                }
            }

            if (!layer.isEmpty()) {
                result.add(layer);
            }
        }

        return result;
    }


    public static List<List<String>> solution1(OrgNode root) {
        if (root == null) {
            return List.of();
        }

        var result = new ArrayList<List<String>>();
        var queue = new ArrayDeque<OrgNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            var layerSize = queue.size();
            var layer = new ArrayList<String>();

            for (var i = 0; i < layerSize; i++) {
                var node = queue.poll();

                if (node != null) {
                    layer.add(node.title);
                }

                if (node != null && node.left != null) {
                    queue.add(node.left);
                }

                if (node != null && node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(layer);
        }

        return result;
    }
}
