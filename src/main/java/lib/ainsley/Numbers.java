package lib.ainsley;

import java.util.Random;

public class Numbers {

    /**
     * Method to get a random number in range
     *
     * @param min receives the minimum number
     * @param max receives the maximum number
     * @return an integer between min and max
     */
    public static int randomNumberBetween(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    /**
     * Method to get a random number
     *
     * @param bound receives the maximum number
     * @return an integer between 0 and bound
     */
    public static int randomNumber(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }
}
