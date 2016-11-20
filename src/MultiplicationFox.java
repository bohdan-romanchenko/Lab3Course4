import java.lang.*;
class MultiplicationFox extends Thread  {
    static int[][] run(int size, int matrix1[][], int matrix2[][], int matrix3[][], int matrix4[][]){

        int result [][] = new int[size][size];
        int result1[][] = new int[size][size];
        int result2[][] = new int[size][size];

        int aux = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                aux = 0;
                for (int k = 0; k < size; k++) {
                    aux += matrix1[i][k] * matrix2[k][j];
                    result1[i][j] = aux;
                }
                aux = 0;
                for (int k = 0; k < size; k++) {
                    aux += matrix3[i][k] * matrix4[k][j];
                    result2[i][j] = aux;
                }
                result[i][j] = result1[i][j] + result2[i][j];
            }

        }

        return result;
    }

}