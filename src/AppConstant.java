import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        System.out.println("Pointers (y values..): " + Arrays.toString(arr));
        return arr;
    }

    static PolynomialDto getPolynomialDto(int n) {
        PolynomialDto dto = new PolynomialDto();
        StringBuilder poly = new StringBuilder();
        List<Fraction> coefficients = new ArrayList<>();

        StringBuilder polyInt = new StringBuilder();
        for (int i = n-1 ; i >= 0; i--) {
            // p(x) = cnx^n + ... + c3x^3 + c2x^2 + c1x + c0
            poly.append("x^").append(i);
            if(i != 0) {
                poly.append(" + ");
            }
            // P(x) = (cnx^(n+1) / (n+1)) + .... + ((c3x^4) / 4) + ((c2x^3) / 3) + ((c1x^2) / 2) + (c0x)
            polyInt.append("(").append("(").append(i).append("x^").append(i+1).append(")").append("/").append(i+1).append(")");
            if(i != 0) {
                polyInt.append(" + ");
            }

            Fraction f = Fraction.getInstance(i,i+1);
            coefficients.add(f);
        }
        dto.setPoly(poly.toString());
        dto.setPolyInt(polyInt.toString());
        dto.setCoefficients(coefficients);
        return dto;
    }
}
