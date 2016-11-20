import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.exit;

public class Main {

    private static final int LIMITEX2 = 100;
    private static final int LIMITEX3 = 1000;
    private static final Object lock = new Object();

    public static void main(String[] args) {

        try {
            ex1();
            System.out.println();
            System.out.println();

            ex21();
            Thread.sleep(1000);
            System.out.println("\n");

            ex22();
            Thread.sleep(1000);
            System.out.println("\n");

            ex31();
            Thread.sleep(1000);
            System.out.println("\n");

            ex32();
            Thread.sleep(1000);
            System.out.println("\n");

            int size = 1200;

            int[][] matrix1 = new int[size][size];
            int[][] matrix2 = new int[size][size];

            Random random = new Random();

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    matrix1[i][j] = random.nextInt() % 100;
                    matrix2[i][j] = random.nextInt() % 100;
                }
            }

            long start = System.currentTimeMillis();
            ex4(matrix1, matrix2);
            long spent = System.currentTimeMillis() - start;
            Thread.sleep(1000);
            System.out.println("Spent alg1 : " + spent + " ms\n");

            start = System.currentTimeMillis();
            ex5(matrix1, matrix2);
            spent = System.currentTimeMillis() - start;
            Thread.sleep(1000);
            System.out.println("Spent alg2 : " + spent + " ms\n");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        exit(0);

    }

    private static void ex1() {
        String sentence[] = {
                "We ", "have ", "the ", "java ", "learning ", "course!"
        };

        try {
            for (String s : sentence) {
                System.out.print(s);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void ex21() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int counter = 0;

                while (counter < LIMITEX2) {
                    System.out.print("-");
                    counter++;
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int counter = 0;

                while (counter < LIMITEX2) {
                    synchronized (lock) {
                        System.out.print("|");
                        counter++;
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }

    private static void ex22() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int counter = 0;

                while (counter < LIMITEX2) {
                    synchronized (lock) {
                        System.out.print("-");
                        counter++;
                        try {
                            lock.notify();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int counter = 0;

                while (counter < LIMITEX2) {
                    synchronized (lock) {
                        System.out.print("|");
                        counter++;
                        try {
                            lock.notify();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }

    private static void ex31() {
        Counter counter = new Counter();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < LIMITEX3) {
                    counter.increment();
                    System.out.print(counter.getCounter() + " ");
                    i++;
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < LIMITEX3) {
                    counter.decrement();
                    System.out.print(counter.getCounter() + " ");
                    i++;
                }
            }
        });

        thread1.start();
        thread2.start();
    }

    private static void ex32() {
        Counter counter = new Counter();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < LIMITEX3) {
                    synchronized (lock) {
                        counter.increment();
                        System.out.print(counter.getCounter() + " ");
                        i++;
                        try {
                            lock.notify();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < LIMITEX3) {
                    synchronized (lock) {
                        counter.decrement();
                        System.out.print(counter.getCounter() + " ");
                        i++;
                        i++;
                        try {
                            lock.notify();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }

    private static void ex4(int[][] matrix1, int[][] matrix2) {

        if (matrix1[0].length == matrix2.length) {

            int[][] resultMatrix = new int[matrix1.length][matrix2[0].length];
            LinearMultiplicationThreadWorker[][] threads =
                    new LinearMultiplicationThreadWorker[resultMatrix.length][resultMatrix[0].length];
            for (int i = 0; i < resultMatrix.length; i++) {
                ExecutorService executorService = Executors.newCachedThreadPool();
                for (int j = 0; j < resultMatrix[0].length; j++) {
                    threads[i][j] = new LinearMultiplicationThreadWorker(i, j, matrix1, matrix2, resultMatrix);
                    executorService.execute(threads[i][j]);
                }
                executorService.shutdown();
            }

            Result resultEx4 = new Result(resultMatrix);
        } else {
            System.out.println("Wrong input");
        }

    }

    private static void ex5(int[][] matrix1, int[][] matrix2) {

        Fox fox = new Fox();

        int multiplicationThreadWorker[][] = fox.FoxMultiplicationThreadWorker(matrix1, matrix2);

        Result resultEx5 = new Result(multiplicationThreadWorker);

    }


}
