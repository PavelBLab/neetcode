package livecodinginterview;

import java.util.*;

public class CodingInterviewTask3_NearestHotelFinder {

    /*
     * Problem: Nearest Hotel Finder
     *
     * Booking.com is building a feature to show travelers the nearest hotel
     * from their current location in a city grid.
     *
     * You are given a 2D grid where:
     *   0 = road (walkable)
     *   1 = building (blocked, cannot pass through)
     *   2 = hotel
     *
     * Given a starting position (startRow, startCol), find the minimum
     * number of steps to reach the nearest hotel.
     * You can move up, down, left, right (not diagonally).
     * If no hotel is reachable, return -1.
     */
    public static void main(String[] args) {
        // Test 1: hotel one step away
        int[][] grid1 = {
                {0, 0, 0, 2},
                {0, 1, 1, 0},
                {0, 0, 0, 0},
                {2, 1, 0, 0}
        };
        System.out.println(solution5(grid1, 2, 0)); // Expected: 1

        // Test 2: must navigate around walls
        int[][] grid2 = {
                {0, 1, 2},
                {0, 1, 0},
                {0, 0, 0}
        };
        System.out.println(solution5(grid2, 0, 0)); // Expected: 6

        // Test 3: hotel unreachable
        int[][] grid3 = {
                {0, 1},
                {1, 2}
        };
        System.out.println(solution5(grid3, 0, 0)); // Expected: -1

        // Test 4: start on a hotel
        int[][] grid4 = {
                {2, 0},
                {0, 0}
        };
        System.out.println(solution5(grid4, 0, 0)); // Expected: 0
    }

    public static int solution5(int[][] grid, int startRow, int startCol) {
        var positionsQueue = new ArrayDeque<int[]>();
        positionsQueue.add(new int[] {startRow, startCol});

        var visitedPositions = new boolean[grid.length][grid[0].length];
        visitedPositions[startRow][startCol] = true;

        var directions = new int[][] {
                {-1, 0},  // up
                {+1, 0},  // down
                {0, -1},  // left
                {0, +1}   // right
        };

        var stepsCounter = 0;

        while (!positionsQueue.isEmpty()) {
            var layerSize = positionsQueue.size();

            for (var i = 0; i < layerSize; i++) {
                var currentPosition = positionsQueue.poll();
                var row = currentPosition[0];
                var col = currentPosition[1];

                if (grid[row][col] == 2) {
                    return stepsCounter;
                }

                for (var direction : directions) {
                    var nextRow = direction[0] + row;
                    var nextCol = direction[1] + col;

                    if (nextRow >= 0 && nextRow < grid.length &&
                        nextCol >= 0 && nextCol < grid[0].length &&
                            !visitedPositions[nextRow][nextCol] && grid[nextRow][nextCol] != 1) {
                        visitedPositions[nextRow][nextCol] = true;
                        positionsQueue.add(new int[] {nextRow, nextCol});
                    }
                }
            }
            stepsCounter++;
        }

        return -1;
    }


    public static int solution4(int[][] grid, int startRow, int startCol) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        if (startRow < 0 || startRow >= grid.length || startCol < 0 || startCol >= grid[startRow].length) {
            return -1;
        }

        var startingPosition = new int[]{startRow, startCol};
        var positionsQueue = new ArrayDeque<int[]>();
        positionsQueue.add(startingPosition);

        var visitedPositons = new boolean[grid.length][grid[0].length];
        visitedPositons[startRow][startCol] = true;

        var directions = new int[][]{
                {-1, 0}, // up
                {+1, 0}, // down
                {0, -1}, // left
                {0, +1}  // right
        };

        var stepCounter = 0;

        while (!positionsQueue.isEmpty()) {
            var layerSize = positionsQueue.size();

            for (var i = 0; i < layerSize; i++) {
                var currentPosition = positionsQueue.poll();

                var currentRow = Objects.requireNonNull(currentPosition)[0];
                var currentCol = Objects.requireNonNull(currentPosition)[1];

                if (grid[currentRow][currentCol] == 2) {
                    return stepCounter;
                }

                for (var direction : directions) {
                    var nextRow = currentRow + direction[0];
                    var nextCol = currentCol + direction[1];

                    if (0 <= nextRow && nextRow < grid.length &&
                            0 <= nextCol && nextCol < grid[0].length &&
                            grid[nextRow][nextCol] != 1 && !visitedPositons[nextRow][nextCol]) {

                        positionsQueue.add(new int[]{nextRow, nextCol});
                        visitedPositons[nextRow][nextCol] = true;
                    }
                }
            }
            stepCounter++;
        }

        return -1;
    }


    public static int solution3(int[][] grid, int startRow, int startCol) {

        var startPosition = new int[]{startRow, startCol};
        var positionsQueue = new ArrayDeque<int[]>();
        positionsQueue.add(startPosition);

        var visitedPositions = new boolean[grid.length][grid[0].length];
        visitedPositions[startRow][startCol] = true;

        var directions = new int[][]{
                {-1, 0}, // up
                {+1, 0}, // down
                {0, -1}, // left
                {0, +1}  // right
        };

        var numberOfStepsCounter = 0;

        while (!positionsQueue.isEmpty()) {
            var layerSize = positionsQueue.size();

            for (var i = 0; i < layerSize; i++) {
                var cell = positionsQueue.poll();
                var currentRow = cell[0];
                var currentCol = cell[1];

                if (grid[currentRow][currentCol] == 2) {
                    return numberOfStepsCounter;
                }

                for (var direction : directions) {
                    var nextRow = currentRow + direction[0];
                    var nextCol = currentCol + direction[1];

                    if (nextRow >= 0 && nextRow < grid.length &&
                            nextCol >= 0 && nextCol < grid[0].length &&
                            grid[nextRow][nextCol] != 1 && !visitedPositions[nextRow][nextCol]) {
                        visitedPositions[nextRow][nextCol] = true;
                        positionsQueue.add(new int[]{nextRow, nextCol});
                    }
                }
            }
            numberOfStepsCounter++;
        }

        return -1;
    }

    public static int solution2(int[][] grid, int startRow, int startCol) {
        var minimumNumberOfSteps = 0;

        var positionsQueue = new ArrayDeque<int[]>();
        positionsQueue.add(new int[]{startRow, startCol});

        var visitedPositions = new boolean[grid.length][grid[0].length];
        visitedPositions[startRow][startCol] = true;

        int[][] directions = new int[][]{
                {-1, 0},  // up
                {+1, 0},  // down
                {0, -1},  // left
                {0, +1}   // right
        };

        while (!positionsQueue.isEmpty()) {
            var layerSize = positionsQueue.size();

            for (var i = 0; i < layerSize; i++) {
                var layer = positionsQueue.poll();
                var row = layer[0];
                var column = layer[1];

                if (grid[row][column] == 2) {
                    return minimumNumberOfSteps;
                }

                for (var direction : directions) {
                    var nextRow = row + direction[0];
                    var nextColumn = column + direction[1];

                    if (nextRow >= 0 && nextRow < grid.length &&
                            nextColumn >= 0 && nextColumn < grid[0].length &&
                            grid[nextRow][nextColumn] != 1 && !visitedPositions[nextRow][nextColumn]) {
                        visitedPositions[nextRow][nextColumn] = true;
                        positionsQueue.add(new int[]{nextRow, nextColumn});
                    }
                }
            }
            minimumNumberOfSteps++;
        }

        return -1;
    }

    public static int solution1(int[][] grid, int startRow, int startCol) {
        var coordinatesQueue = new ArrayDeque<int[]>();
        coordinatesQueue.add(new int[]{startRow, startCol});

        var visitedCoordinates = new boolean[grid.length][grid[0].length];
        visitedCoordinates[startRow][startCol] = true;

        int[][] directions = {
                {-1, 0},    // up
                {1, 0},     // down
                {0, -1},    // left
                {0, 1}      // right
        };

        var minimumNumberOfSteps = 0;

        while (!coordinatesQueue.isEmpty()) {
            var layerSize = coordinatesQueue.size();

            for (var i = 0; i < layerSize; i++) {
                var layer = coordinatesQueue.poll();
                var row = layer[0];
                var column = layer[1];

                var currentPosition = grid[row][column];

                if (currentPosition == 2) {
                    return minimumNumberOfSteps;
                }

                for (var dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = column + dir[1];

                    if ((newRow >= 0 && newRow < grid.length) && (newCol >= 0 && newCol < grid[row].length) &&
                            !visitedCoordinates[newRow][newCol] && grid[newRow][newCol] != 1) {

                        visitedCoordinates[newRow][newCol] = true;
                        var nextPosition = new int[]{newRow, newCol};

                        coordinatesQueue.add(nextPosition);
                    }

                }
            }
            minimumNumberOfSteps++;
        }

        return -1;
    }
}
