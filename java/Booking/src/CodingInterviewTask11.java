import java.util.*;

public class CodingInterviewTask11 {

    /*
     * Problem: Room Service Order Tracker
     *
     * A hotel processes room service orders floor by floor.
     * Orders arrive from different floors. The hotel processes
     * all orders from the current floor before moving to the next.
     *
     * Given a queue of orders (each has a floor number and item),
     * group and process them by floor number in the order floors
     * first appear.
     *
     * Example:
     *   orders = [{floor:3, "Pizza"}, {floor:1, "Coffee"},
     *             {floor:3, "Wine"}, {floor:1, "Towels"}]
     *
     *   Floor 3 appeared first: Pizza, Wine
     *   Floor 1 appeared next:  Coffee, Towels
     *
     *   Result: ["Floor 3: Pizza", "Floor 3: Wine",
     *            "Floor 1: Coffee", "Floor 1: Towels"]
     */
    public static void main(String[] args) {
        var orders = List.of(
                new int[]{3, 0}, // floor 3, item index 0
                new int[]{1, 1}, // floor 1, item index 1
                new int[]{3, 2}, // floor 3, item index 2
                new int[]{1, 3}  // floor 1, item index 3
        );
        var items = List.of("Pizza", "Coffee", "Wine", "Towels");

        System.out.println(solution1(orders, items));
        // Expected: [Floor 3: Pizza, Floor 3: Wine, Floor 1: Coffee, Floor 1: Towels]

        // Test 2: single order
        System.out.println(solution1(
                List.of(new int[]{5, 0}),
                List.of("Soda")
        ));
        // Expected: [Floor 5: Soda]
    }

    public static List<String> solution1(List<int[]> orders, List<String> items) {
        if (orders.isEmpty()) {
            return List.of();
        }

        var result = new ArrayList<String>();
        var orderMap = new HashMap<Integer, List<Integer>>();

        var firstOrderFloor = orders.getFirst()[0];

        var floorQueue = new ArrayDeque<Integer>();
        floorQueue.add(firstOrderFloor);

        var visitedFloorSet = new HashSet<Integer>();
        visitedFloorSet.add(firstOrderFloor);

        for (var order : orders) {
            var floor = order[0];
            var item = order[1];

            if (visitedFloorSet.add(floor)) {
                floorQueue.add(floor);
            }

            orderMap.computeIfAbsent(floor, i -> new ArrayList<>()).add(item);
        }

        while (!floorQueue.isEmpty()) {
            var floor = floorQueue.poll();
            var itemIndexList = orderMap.get(floor);

            for (var itemIndex : itemIndexList) {
                    var floorWithItem = String.format("Floor %s: %s", floor, items.get(itemIndex));
                    result.add(floorWithItem);
            }
        }

        return result;
    }
}
