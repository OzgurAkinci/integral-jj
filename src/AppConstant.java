import java.util.Arrays;

public final class AppConstant {

    private AppConstant() {
        // No need to instantiate the class, we can hide its constructor
    }

    // Variables
    public static final String PolynomialFunction = "Polynomial F.";
    public static final String PolynomialIntFunction = "PolynomialInt F.";

    //Functions
    static int[] calcPointers (int n) {
        int mod = n % 2; // If n is even or odd

        int leftCounter = ((mod == 0) ? (((n/2)-1) * -1) : ((n/2) * -1));
        int rightCounter = (n / 2); // always be n/2

        int[] arr = new int[n];

        int i=0;
        while (leftCounter < 0) {
            arr[i] = (leftCounter);
            leftCounter++; i++;
        }

        i = n-1;
        while (rightCounter >= 0) {
            arr[i] = rightCounter;
            rightCounter--; i--;
        }
        System.out.println("Pointers: " + Arrays.toString(arr));
        return arr;
    }
}
