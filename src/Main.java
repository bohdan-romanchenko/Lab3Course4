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

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void ex1(){
        String sentence[] = {
                "We ", "have ", "the ", "java ", "learning ", "course!"
        };

        try {
            for (String s : sentence) {
                System.out.print(s);
                Thread.sleep(1);    //TODO: change on 1000
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void ex21(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int counter = 0;

                while (counter < LIMITEX2){
                    System.out.print("-");
                    counter++;
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int counter = 0;

                while (counter < LIMITEX2){
                    synchronized (lock){
                        System.out.print("|");
                        counter++;
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }

    private static void ex22(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int counter = 0;

                while (counter < LIMITEX2){
                    synchronized (lock){
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

                while (counter < LIMITEX2){
                    synchronized (lock){
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

    private static void ex31(){
        Counter counter = new Counter();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < LIMITEX3){
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
                while (i < LIMITEX3){
                    counter.decrement();
                    System.out.print(counter.getCounter() + " ");
                    i++;
                }
            }
        });

        thread1.start();
        thread2.start();
    }

    private static void ex32(){
        Counter counter = new Counter();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < LIMITEX3){
                    synchronized (lock){
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
                while (i < LIMITEX3){
                    synchronized (lock){
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

    private static void ex4(){

    }

    private static void ex5(){

    }

}
