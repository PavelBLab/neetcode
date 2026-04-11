package livecodinginterview;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class CodingInterviewTask6_2_HotelChainOrgChart {

    /*
     * A hotel chain has a hierarchical structure.
     * Given a tree where each node is a hotel name,
     * return the level order traversal — each level shows
     * which hotels are at the same depth in the org chart.
     */
    static class HotelNode {
        String name;
        List<HotelNode> children;
        HotelNode(String name) {
            this.name = name;
            this.children = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        var root = new HotelNode("HQ");
        var europe = new HotelNode("Europe");
        var asia = new HotelNode("Asia");
        var amsterdam = new HotelNode("Amsterdam");
        var london = new HotelNode("London");
        var tokyo = new HotelNode("Tokyo");

        root.children.add(europe);
        root.children.add(asia);
        europe.children.add(amsterdam);
        europe.children.add(london);
        asia.children.add(tokyo);

        System.out.println(orgChartLevels(root));
        // Expected: [[HQ], [Europe, Asia], [Amsterdam, London, Tokyo]]
    }

    public static List<List<String>> orgChartLevels(HotelNode root) {
        if (root == null) {
            return List.of();
        }

        var result = new ArrayList<List<String>>();
        var nodesQueue = new ArrayDeque<HotelNode>();
        nodesQueue.add(root);

        while (!nodesQueue.isEmpty()) {
            var layerSize = nodesQueue.size();
            var layer = new ArrayList<String>();

            for (var i = 0; i < layerSize; i++) {
                var currentNode = nodesQueue.poll();

                if (currentNode == null) {
                    continue;
                }

                layer.add(currentNode.name);

                var children = currentNode.children;

                for (var child : children) {
                    if (child != null) {

                        nodesQueue.add(child);
                    }
                }
            }
            result.add(layer);
        }
        return result;
    }
}
