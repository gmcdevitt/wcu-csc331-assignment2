package prog1;

import java.util.Arrays;

class ArraySearcher {

    void findMaxIn(int[] array) {
        int[] companion = initializeCompanionArray(array.length);

        System.out.printf("After Initialization \t\t\t = '%s'.\n", Arrays.toString(companion));

        System.out.println("Begin Step 2...");
        for (int i = 0; i < array.length; i++) {
            for (int k = i + 1; k < array.length; k++) {
                new Comparator(i, k, array, companion).run();
            }
        }

        System.out.printf("After Step 2 \t\t\t\t = '%s'.\n", Arrays.toString(companion));


        findIndexOfMaxIn(array, companion);
    }


    private int[] initializeCompanionArray(int length) {
        final int[] tmp = new int[length];
        new DefaultArrayInitializer(tmp, 1).initialize();

        return tmp;
    }

    private void findIndexOfMaxIn(int[] array, int[] companion) {
        for (int i = 0; i < companion.length; i++) {
            new Searcher(i, companion, array).run();
        }
    }


    private class Comparator implements Runnable {

        int index1;
        int index2;
        int[] array;
        int[] companion;

        Comparator(int index1, int index2, int[] array, int[] companion) {
            this.index1 = index1;
            this.index2 = index2;
            this.array = array;
            this.companion = companion;
        }

        @Override
        public void run() {
            int c1 = array[index1];
            int c2 = array[index2];
            boolean less = c1 < c2;
            System.out.printf("Thread T(%1$s, %2$s) compares x[%1$s] = %3$s and x[%2$s] = %4$s,\t", index1, index2, c1, c2);
            if (less) {
                companion[index1] = 0;
            } else {
                companion[index2] = 0;
            }

            System.out.printf("and writes %s into w[%s].\n", less ? 0 : 1, less ? index1 : index2);
        }
    }

    private class Searcher implements Runnable {

        int index;
        int[] companion;
        int[] array;

        Searcher(int index, int[] companion, int[] array) {
            this.index = index;
            this.companion = companion;
            this.array = array;
        }

        @Override
        public void run() {
            if (companion[index] == 1) {
                System.out.printf("Maximum \t\t\t\t = '%s'.\n", array[index]);
                System.out.printf("Location \t\t\t\t = '%s'.\n", index);
            }
        }
    }
}
