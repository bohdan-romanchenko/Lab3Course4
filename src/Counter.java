public class Counter {

    private int counter;

    public int increment(){
        return ++this.counter;
    }

    public int decrement(){
        return --this.counter;
    }

    public Counter() {
        this.counter = 0;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
