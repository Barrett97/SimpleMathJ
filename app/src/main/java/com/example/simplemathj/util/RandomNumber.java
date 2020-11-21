package com.example.simplemathj.util;

import java.util.Random;

public class RandomNumber {

    /*
        Return a random integer between 0 and max (inclusive)
     */
    public static int generateTo(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    /*
        Return a random integer greater than min and less than max (inclusive)
     */
    public static int generateBetween(int max, int min) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

}
