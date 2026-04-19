package livecodinginterview;

import java.util.*;

public class CodingInterviewTask15_1_MinimumKnightMoves {

    /*
     * A chess knight is placed on a standard 8×8 board at position
     * (startRow, startCol). Find the minimum number of moves
     * to reach (targetRow, targetCol).
     *
     * A knight moves in an L-shape:
     *   - 2 squares in one direction + 1 square perpendicular
     *   - 8 possible moves from any position
     *
     * Return -1 if target is unreachable.
     */
    public static void main(String[] args) {
        System.out.println(solution2(0, 0, 1, 2));
        // Expected: 1 (direct L-move)

        System.out.println(solution2(0, 0, 4, 4));
        // Expected: 4 (0,0 → 2,1 → 4,2... or other 4-move paths)

        System.out.println(solution2(0, 0, 7, 7));
        // Expected: 6
    }

    public static int solution2(int startRow, int startCol, int targetRow, int targetCol) {
        var board = new int[8][8];

        var positionsQueue = new ArrayDeque<int[]>();
        positionsQueue.add(new int[]{startRow, startCol});

        var visitedPositions = new boolean[8][8];
        visitedPositions[startRow][startCol] = true;

        var directions = new int[][] {
                {+2, +1},
                {+2, -1},
                {-2, +1},
                {-2, -1},
                {+1, +2},
                {+1, -2},
                {-1, +2},
                {-1, -2}
        };

        var minimumNumberOfMoves = 0;

        while (!positionsQueue.isEmpty()){
            var layerSize = positionsQueue.size();

            for (var i = 0; i < layerSize; i++) {
                var currentPosition = positionsQueue.poll();

                if (currentPosition == null) {
                    continue;
                }

                if (currentPosition[0] == targetRow && currentPosition[1] == targetCol) {
                    return minimumNumberOfMoves;
                }

                for (var direction : directions) {
                    var nextRow = direction[0] + currentPosition[0];
                    var nextCol = direction[1] + currentPosition[1];

                    if (nextRow >= 0 && nextRow < board.length &&
                        nextCol >= 0 && nextCol < board[0].length &&
                        !visitedPositions[nextRow][nextCol]) {
                        positionsQueue.add(new int[]{nextRow, nextCol});
                        visitedPositions[nextRow][nextCol] = true;
                    }

                }
            }
            minimumNumberOfMoves++;
        }

        return -1;
    }

    public static int solution1(int startRow, int startCol, int targetRow, int targetCol) {
        var grid = new int[8][8];

        if (startRow < 0 || startRow >= grid.length || startCol < 0 || startCol >= grid[0].length) {
            return -1;
        }

        if (targetRow < 0 || targetRow >= grid.length || targetCol < 0 || targetCol >= grid[0].length) {
            return -1;
        }

        var startingPosition = new int[]{startRow, startCol};
        var positionsQueue = new ArrayDeque<int[]>();
        positionsQueue.add(startingPosition);

        var visitedPositions = new boolean[grid.length][grid[0].length];
        visitedPositions[startRow][startCol] = true;

        var directions = new int[][]{ // 5,5
                {+2, +1},  // 7, 6
                {+2, -1},  // 7, 4
                {-2, +1},  // 3, 6
                {-2, -1},  // 3, 4
                {+1, +2},  // 6, 7
                {+1, -2},  // 6, 3
                {-1, +2},  // 4, 7
                {-1, -2}   // 4, 3
        };

        var knightMovesCounter = 0;

        while (!positionsQueue.isEmpty()) {
            var layerSize = positionsQueue.size();

            for (var i = 0; i < layerSize; i++) {
                var currentPosition = positionsQueue.poll();
                var currentRow = Objects.requireNonNull(currentPosition)[0];
                var currentCol = Objects.requireNonNull(currentPosition)[1];

                if (currentRow == targetRow && currentCol == targetCol) {
                    return knightMovesCounter;
                }

                for (var direction : directions) {
                    var nextRow = currentRow + direction[0];
                    var nextCol = currentCol + direction[1];

                    if (nextRow >= 0 && nextRow < grid.length &&
                            nextCol >= 0 && nextCol < grid[0].length
                            && !visitedPositions[nextRow][nextCol]) {

                        positionsQueue.add(new int[]{nextRow, nextCol});
                        visitedPositions[nextRow][nextCol] = true;
                    }
                }
            }
            knightMovesCounter++;
        }

        return -1;
    }

}