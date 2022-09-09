
class HelloWorld {
    public static void main(String args[]) {

        int[][] matrix = {
                {1, 1, 1, 1, 3},
                {9, 8, 9, 1, 9},
                {7, 1, 1, 1, 9},
                {2, 9, 2, 9, 1},
                {2, 8, 3, 9, 7}
        };
        
        System.out.println(minimumPathSum(matrix));
    }

    public static int minimumPathSum(int[][] matrix) {

        int distance = 0;
        int currentX = 0;
        int currentY = 0;
        int width = matrix.length;
        int height = matrix[0].length;
        int currentBest = Integer.MAX_VALUE;
        int currentDistance = 0;
        int[][] tracker = new int[width][height];
        
        findMinimum(currentX, currentY, matrix, tracker, currentBest, currentDistance);

        return distance;
    }

    public static int findMinimum(int currentX, int currentY, int[][] matrix, int[][] tracker, int currentBest, int currentDistance) {

        // Problem 1: It doesn't work
        // Problem 2: Set tracker back to zero at some point
        // Problem 3: Put this in git
        // Problem 4: Check this works for rectangular matrices

        // Update state
        currentDistance += matrix[currentX][currentY];
        tracker[currentX][currentY] = 1;

        int width = matrix.length;
        int height = matrix[0].length;
        if (currentX == width - 1 && currentY == height - 1){
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

        tracker = new int[width][height];

        return currentBest;
    }
}
