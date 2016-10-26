package prog1;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] input;

        try {
            input = new int[Integer.parseInt(args[0])]; //Initialize to length n
            System.out.printf("Number of integers \t\t\t = '%s'.\n", args[0]);
            String[] tmp = Arrays.copyOfRange(args, 1, args.length);

            for (int i = 0; i < tmp.length; i++) {
                input[i] = Integer.parseInt(tmp[i]);
            }

            System.out.printf("Input Values \t\t\t\t = '%s'.\n", Arrays.toString(tmp));

        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        new ArraySearcher().findMaxIn(input);
    }
}
