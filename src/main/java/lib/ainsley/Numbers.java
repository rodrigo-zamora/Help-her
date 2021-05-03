package lib.ainsley;

import java.util.Random;

public class Numbers {

    /**
     * Method to get a random number in range
     * @param min
     * @param max
     * @return
     */
    public static int randomNumberBetween(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    /**
     * Method to get a random number
     * @param bound
     * @return
     */
    public static int randomNumber(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }

}
