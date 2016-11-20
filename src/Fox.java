import java.lang.*;

class Fox extends MultiplicationFox {

    private static int [][] merge(int matrix[][], int line, int caso){


        int block = line / 2;
        int Block1A [][] = new int[block][block];
        int Block2A [][] = new int[block][block];
        int Block3A [][] = new int[block][block];
        int Block4A [][] = new int[block][block];

        int i1, j1, i2, j2, i3, j3, i4, j4;
        i1 = j1 = i2 = j2 = i3 = j3 = i4 = j4 = 0;
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < line; j++) {
                switch (caso){
                    case 1:
                        if (i < block && j < block) {
                            Block1A[i][j] = matrix[i][j];
                        }
                    case 2:
                        if (i < block && j >= block) {
                            j2 = j - block;
                            if (j2 < 0) {
                                j2 = 0;
                            }
                            Block2A[i][j2] = matrix[i][j];
                            j2++;
                        }
                    case 3:
                        if (i >= block && j < block) {
                            i3 = i - block;
                            if (i3 < 0) {
                                i3 = 0;
                            }
                            Block3A[i3][j] = matrix[i][j];
                            i3++;
                        }
                    case 4:
                        if (i >= block && j >= block) {
                            i4 = i - block;
                            j4 = j - block;
                            if (i4 < 0) {
                                i4 = 0;
                            }
                            if (j4 < 0) {
                                j4 = 0;
                            }
                            Block4A[i4][j4] = matrix[i][j];
                            j4++;
                            i4++;
                        }

                }

            }
        }

        if (caso == 1){
            return Block1A;
        }
        if (caso == 2){
            return Block2A;
        }

        if (caso == 3){
            return Block3A;
        }

        return Block4A;

    }


    private static int[][] reborn(int size, int result1[][], int result2[][], int result3[][], int result4[][]){

        int block = size / 2;
        int result[][] = new int [size][size];
        // joint the blocks
        int i1, j1, i2, j2, i3, j3, i4, j4;
        i1 = j1 = i2 = j2 = i3 = j3 = i4 = j4 = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (i < block && j < block) {
                    result[i][j] = result1[i][j];
                }

                if (i < block && j >= block) {
                    j2 = j - block;
                    if (j2 < 0) {
                        j2 = 0;
                    }
                    result[i][j] = result2[i][j2];
                    j2++;
                }

                if (i >= block && j < block) {
                    i3 = i - block;
                    if (i3 < 0) {
                        i3 = 0;
                    }
                    result[i][j] = result3[i3][j];
                    i3++;
                }

                if (i >= block && j >= block) {
                    i4 = i - block;
                    j4 = j - block;
                    if (i4 < 0) {
                        i4 = 0;
                    }
                    if (j4 < 0) {
                        j4 = 0;
                    }
                    result[i][j] = result4[i4][j4];
                    j4++;
                    i4++;
                }

            }
        }
        return result;
    }

    int[][] FoxMultiplicationThreadWorker(int matrix1[][], int matrix2[][]){

        int column = matrix1[0].length;
        int block = matrix1[0].length / 2;

        int Block1A [][] = merge(matrix1,column,1);
        int Block2A [][] = merge(matrix1,column,2);
        int Block3A [][] = merge(matrix1,column,3);
        int Block4A [][] = merge(matrix1,column,4);

        int Block1B [][] = merge(matrix2,column,1);
        int Block2B [][] = merge(matrix2,column,2);
        int Block3B [][] = merge(matrix2,column,3);
        int Block4B [][] = merge(matrix2,column,4);

        MultiplicationFox thread1 = new MultiplicationFox();
        MultiplicationFox thread2 = new MultiplicationFox();
        MultiplicationFox thread3 = new MultiplicationFox();
        MultiplicationFox thread4 = new MultiplicationFox();

        Thread mult1 = new Thread(thread1);
        Thread mult2 = new Thread(thread2);
        Thread mult3 = new Thread(thread3);
        Thread mult4 = new Thread(thread4);

        mult1.start();
        mult2.start();
        mult3.start();
        mult4.start();

        int result1[][] = run(block, Block1A, Block1B, Block2A, Block3B);
        int result2[][] = run(block, Block1A, Block2B, Block2A, Block4B);
        int result3[][] = run(block, Block3A, Block1B, Block4A, Block3B);
        int result4[][] = run(block, Block3A, Block2B, Block4A, Block4B);

        return reborn(column, result1, result2, result3, result4);
    }

}