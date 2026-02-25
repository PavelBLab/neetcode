import java.util.HashMap;
import java.util.HashSet;

public class ValidSudoku {

    public static void main(String[] args) {
        System.out.println(Solution1.isValidSudoku(new char[][]{
                {'1', '2', '.', '.', '3', '.', '.', '.', '.'},
                {'4', '.', '.', '5', '.', '.', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '.', '3'},
                {'5', '.', '.', '.', '6', '.', '.', '.', '4'},
                {'.', '.', '.', '8', '.', '3', '.', '.', '5'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '.', '.', '.', '.', '.', '2', '.', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '8'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        }));
        System.out.println("====================================== ");

        System.out.println(Solution1.isValidSudoku(new char[][]{
                {'1','2','.','.','3','.','.','.','.'},
                {'4','.','.','5','.','.','.','.','.'},
                {'.','9','1','.','.','.','.','.','3'},
                {'5','.','.','.','6','.','.','.','4'},
                {'.','.','.','8','.','3','.','.','5'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','.','.','.','.','.','2','.','.'},
                {'.','.','.','4','1','9','.','.','8'},
                {'1','.','.','.','8','.','.','7','9'}
        }));
        System.out.println("====================================== ");

        System.out.println(Solution1.isValidSudoku(new char[][]{
                 {'1','2','.','.','3','.','.','.','.'},
                 {'4','.','.','5','.','.','.','.','.'},
                 {'.','9','1','.','.','.','.','.','3'},
                 {'5','.','.','.','6','.','.','.','4'},
                 {'.','.','.','8','.','3','.','.','5'},
                 {'7','.','.','.','2','.','.','.','6'},
                 {'.','.','.','.','.','.','2','.','.'},
                 {'.','.','.','4','1','9','.','.','8'},
                 {'.','.','.','.','8','.','.','7','9'}
        }));
        System.out.println("====================================== ");

        System.out.println(Solution1.isValidSudoku(new char[][]{
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','3','.','.'},
                {'.','.','.','1','8','.','.','.','.'},
                {'.','.','.','7','.','.','.','.','.'},
                {'.','.','.','.','1','.','9','7','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','3','6','.','1','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','2','.'}
        }));
        System.out.println("====================================== ");

        System.out.println(Solution1.isValidSudoku(new char[][]{
                {'.','.','.','.','5','.','.','1','.'}, // [0, 7]
                {'.','4','.','3','.','.','.','.','.'},
                {'.','.','.','.','.','3','.','.','1'}, // [2, 8]
                {'8','.','.','.','.','.','.','2','.'},
                {'.','.','2','.','7','.','.','.','.'},
                {'.','1','5','.','.','.','.','.','.'},
                {'.','.','.','.','.','2','.','.','.'},
                {'.','2','.','9','.','.','.','.','.'},
                {'.','.','4','.','.','.','.','.','.'}
        }));
        System.out.println("====================================== ");

    }

    static class Solution1 {
        public static boolean isValidSudoku(char[][] board) {
            var boxMap = new HashMap<String, HashMap<Character, Integer>>();

            for (var i = 0; i < board.length; i++) {
                var horizontalMap = new HashMap<Character, Integer>();
                var verticalMap = new HashMap<Character, Integer>();

                for (var j = 0; j < board[i].length; j++) {
                    var horizontalValue = board[i][j]; // [0, 1], [0, 2], [0, 3], [0, 4], [0, 5], [0, 6], [0, 7], [0, 8]
                    var verticalValue = board[j][i];   // [0, 0], [1, 0], [2, 0], [3, 0], [4, 0], [5, 0], [6, 0], [7, 0], [8, 0]

                    System.out.println("Horizontal value: " + horizontalValue + ", Vertical value: " + verticalValue);

                    horizontalMap.put(horizontalValue, horizontalMap.getOrDefault(horizontalValue, 0) + 1);
                    verticalMap.put(verticalValue, verticalMap.getOrDefault(verticalValue, 0) + 1);

                    if (horizontalValue != '.') {
                        var key = (i / 3) + "," + (j / 3);
                        var box = boxMap.computeIfAbsent(key, k -> new HashMap<>());
                        box.put(horizontalValue, box.getOrDefault(horizontalValue, 0) + 1);

                        if (box.get(horizontalValue) > 1) {
                            System.out.println("Box duplication: " + horizontalValue + " in box " + key);
                            return false;
                        }
                    }

                }

                var horizontalDuplications = horizontalMap.entrySet().stream()
                        .filter(e -> e.getKey() != '.' && e.getValue() > 1)
                        .toList();

                var verticalDuplications = verticalMap.entrySet().stream()
                        .filter(e -> e.getKey() != '.' && e.getValue() > 1)
                        .toList();

                System.out.println("Horizontal duplications: " + horizontalDuplications);
                System.out.println("Vertical duplications: " + verticalDuplications);

                if (!horizontalDuplications.isEmpty()) {
                    System.out.println(horizontalDuplications);
                    return false;
                }

                if (!verticalDuplications.isEmpty()) {
                    System.out.println(verticalDuplications);
                    return false;
                }

                System.out.println("Horizontal map: " + horizontalMap);
                System.out.println("Vertical map: " + verticalMap);
                System.out.println("Box map: " + boxMap);
                System.out.println("-----------------------------");
            }

            return true;
        }
    }

    static class Solution2 {
        public static boolean isValidSudoku(char[][] board) {
            var boxMap = new HashMap<String, HashSet<Character>>();

            for (var i = 0; i < board.length; i++) {
                var horizontalSet = new HashSet<Character>();
                var verticalSet = new HashSet<Character>();

                for (var j = 0; j < 9; j++) {
                    var horizontalValue = board[i][j];
                    var verticalValue = board[j][i];

                    if (horizontalValue != '.' && !horizontalSet.add(horizontalValue)) return false; // set.add returns false if the element already exists in the set
                    if (verticalValue != '.' && !verticalSet.add(verticalValue)) return false;

                    var key = (i / 3) + "," + (j / 3);
                    var box = boxMap.computeIfAbsent(key, k -> new HashSet<>());
                    if (horizontalValue != '.' && !box.add(horizontalValue)) return false;
                }
            }

            return true;
        }
    }

}
