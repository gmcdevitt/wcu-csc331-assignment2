package main.java.problem2;

class PrintingProcessManager {

    /**
     * Token used to guarantee the correct sequence of character printing.
     */
    private final Token token = new Token();

    /**
     * Logic that solves problem 2 of assignment 2: use three threads to print the sequence 'abc' an arbitrary number of times.
     * @param numberOfRuns see {@link Main#main(String[])}, the number of times to print 'abc'
     */
    void doPrints(int numberOfRuns) {
        PrinterThread a = new PrinterThread(0, 'a');
        PrinterThread b = new PrinterThread(1, 'b');
        PrinterThread c = new PrinterThread(2, 'c');

        for (int i = 0; i < numberOfRuns; i++) {
            a.run();
            b.run();
            c.run();
        }
    }

    /**
     * Generic Printer thread capable of printing an arbitrary character synchronized against the ProcessManager's {@link PrintingProcessManager#token};
     */
    private class PrinterThread implements Runnable {

        private char printableChar = ' ';
        private int order;

        PrinterThread(int order, char character) {
            this.order = order;
            this.printableChar = character;
        }

        public void run() {
            synchronized (token) {
                try {
                    while (true) {
                        if (token.getValue() == order) {
                            System.out.print(printableChar);
                            token.update();
                            return;
                        } else {
                            token.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
