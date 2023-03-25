import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class AppConstant {

    private AppConstant() {
        // No need to instantiate the class, we can hide its constructor
    }

    // Variables
    public static final String PolynomialFunction = "Polynomial F.";
    public static final String PolynomialIntFunction = "PolynomialInt F.";

    //Functions
    static String[] calcPointers (int n) {
        int mod = n % 2; // If n is even or odd

        int leftCounter = ((mod == 0) ? (((n/2)-1) * -1) : ((n/2) * -1));
        int rightCounter = (n / 2); // always be n/2

        String[] arr = new String[n];

        int i=0;
        while (leftCounter < 0) {
            arr[i] = leftCounter == -1 ? "-h" : (leftCounter + "h");
            leftCounter++; i++;
        }

        i = n-1;
        while (rightCounter >= 0) {
            arr[i] = rightCounter == 1 ? "h" : (rightCounter == 0 ? "0" : (rightCounter + "h"));
            rightCounter--; i--;
        }
        System.out.println("Pointers (y values..): " + Arrays.toString(arr));
        return arr;
    }

    static PolynomialDTO getPolynomialDto(int n) {
        StringBuilder poly = new StringBuilder();
        List<Fraction> coefficients = new ArrayList<>();

        StringBuilder polyInt = new StringBuilder();
        for (int i = n ; i >= 0; i--) {
            // p(x) = cnx^n + ... + c3x^3 + c2x^2 + c1x + c0
            poly.append("c_").append("{").append(i).append("}").append("x^").append(i);
            if(i != 0) {
                poly.append(" + ");
            }
            // P(x) = (cnx^(n+1) / (n+1)) + .... + ((c3x^4) / 4) + ((c2x^3) / 3) + ((c1x^2) / 2) + (c0x)
            polyInt.append("(").append("(").append("c_").append("{").append(i).append("}").append(")").append("x^").append(i+1).append(")").append("/").append(i+1);
            if(i != 0) {
                polyInt.append(" + ");
            }

            Fraction f = Fraction.getInstance(i,i+1);
            coefficients.add(f);
        }

        return new PolynomialDTO(poly.toString(), polyInt.toString(), coefficients);
    }

    public static List<PreGaussDto> createHFunction(String poly, String[] arr) {
        List<PreGaussDto> pgdList = new ArrayList<>();

        for(int i=0; i<arr.length; i++) {
            PreGaussDto pgd = new PreGaussDto();
            pgd.setfName1("y" + i);
            pgd.setfName2("P" + i + "(" + arr[i] + ")");
            pgd.setFunc(poly);
            //pgd.setBuildFunc(Objects.equals(arr[i], "0") ? arr[i] : poly.replace("x", arr[i]));
            pgd.setBuildFunc(poly.replace("x", arr[i]));
            pgdList.add(pgd);
        }

        return pgdList;
    }
}
