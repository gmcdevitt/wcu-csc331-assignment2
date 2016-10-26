package prog1;

class DefaultArrayInitializer {

    private int defaultValue;
    private final int[] array;

    DefaultArrayInitializer(int[] array, int defaultValue) {
        this.array = array;
        this.defaultValue = defaultValue;
    }

    void initialize() {
        for (int i = 0; i < array.length; i++) {
            new InitializerThread(i).run();
        }
    }

    private class InitializerThread implements Runnable {
        int index;

        InitializerThread(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            array[index] = defaultValue;
        }
    }
}
