
class HelloWorld {
    public static void main(String args[]) {

        int[][] matrix = {
                {1, 3, 1, 9, 1},
                {1, 5, 1, 1, 6},
                {2, 5, 4, 1, 6},
                {1, 2, 1, 3, 6},
                {9, 3, 9, 9, 8}
        };

        System.out.println(minimumPathSum(matrix));
    }

    public static int minimumPathSum(int[][] matrix) {
        int width = matrix.length;
        int height = matrix[0].length;

        return findMinimum(0, 0, matrix, new int[width][height], Integer.MAX_VALUE, 0);
    }

    public static int findMinimum(int currentX, int currentY, int[][] matrix, int[][] tracker, int currentBest, int currentDistance) {

        // Problem 1: It doesn't work
        // Problem 2: Set tracker back to zero at some point
        // Problem 4: Check this works for rectangular matrices

        // Update state
        currentDistance += matrix[currentX][currentY];
        tracker[currentX][currentY] = 1;

        int width = matrix.length;
        int height = matrix[0].length;
        if (currentX == width - 1 && currentY == height - 1) {

            tracker[currentX][currentY] = 0;

            // Print everything (currentDistance, currentBest, tracker)
            System.out.println("currentDistance: " + currentDistance + " currentBest: " + currentBest);

            System.out.println(tracker[0][0] + " " + tracker[0][1] + " " + tracker[0][2] + " " + tracker[0][3] + " " + tracker[0][4]);
            System.out.println(tracker[1][0] + " " + tracker[1][1] + " " + tracker[1][2] + " " + tracker[1][3] + " " + tracker[1][4]);
            System.out.println(tracker[2][0] + " " + tracker[2][1] + " " + tracker[2][2] + " " + tracker[2][3] + " " + tracker[2][4]);
            System.out.println(tracker[3][0] + " " + tracker[3][1] + " " + tracker[3][2] + " " + tracker[3][3] + " " + tracker[3][4]);
            System.out.println(tracker[4][0] + " " + tracker[4][1] + " " + tracker[4][2] + " " + tracker[4][3] + " " + tracker[4][4]);

            return currentDistance < currentBest ? currentDistance : currentBest;
        }

        boolean canGoRight = (currentX != width - 1) && tracker[currentX + 1][currentY] == 0;
        boolean canGoLeft = (currentX != 0) && tracker[currentX - 1][currentY] == 0;
        boolean canGoUp = (currentY != 0) && tracker[currentX][currentY - 1] == 0;
        boolean canGoDown = (currentY != height - 1) && tracker[currentX][currentY + 1] == 0;

        if (canGoRight){
            currentBest = findMinimum(currentX + 1, currentY, matrix, tracker, currentBest, currentDistance);
        }

        if (canGoLeft){
            currentBest = findMinimum(currentX - 1, currentY, matrix, tracker, currentBest, currentDistance);
        }

        if (canGoUp){
            currentBest = findMinimum(currentX, currentY - 1, matrix, tracker, currentBest, currentDistance);
        }

        if (canGoDown) {
            currentBest = findMinimum(currentX, currentY + 1, matrix, tracker, currentBest, currentDistance);
        }

        tracker[currentX][currentY] = 0;

        return currentBest;
    }
}
