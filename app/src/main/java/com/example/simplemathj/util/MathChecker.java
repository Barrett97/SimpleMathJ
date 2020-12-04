package com.example.simplemathj.util;

public class MathChecker {

    // Does x + y = z?
    public static boolean add(int x, int y, int z) {
        return (x + y) == z;
    }

    // Does x - y = z?
    public static boolean sub(int x, int y, int z) {
        return (x - y) == z;
    }

    // Does x * y = z?
    public static boolean mult(int x, int y, int z) {
        return (x * y) == z;
    }

    // Does x / y = z?
    public static boolean div(int x, int y, int z) {
        return (x / y) == z;
    }

}
