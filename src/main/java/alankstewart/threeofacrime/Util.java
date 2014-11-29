package alankstewart.threeofacrime;

import java.util.stream.IntStream;

public final class Util {

    private Util() {
    }

    public static int combination(final int n, final int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    public static int factorial(final int n) {
        return IntStream.rangeClosed(1, n).reduce((x, y) -> x * y).getAsInt();
    }
}