public class LinearMultiplicationThreadWorker extends Thread {
    private int row;
    private int col;
    private int[][] matrix1;
    private int[][] matrix2;
    private int[][] resultMatrix;

    public LinearMultiplicationThreadWorker(int row, int col, int[][] matrix1, int[][] matrix2, int[][] resultMatrix) {
        this.row = row;
        this.col = col;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.resultMatrix = resultMatrix;
    }

    public void run() {
        for (int i = 0; i < matrix1[0].length; i++)
            resultMatrix[row][col]+= (matrix1[row][i] * matrix2[i][col]);
    }
}
