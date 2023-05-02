import java.util.ArrayList;
import java.util.List;

public final class AppConstant {

    private AppConstant() {
        // No need to instantiate the class, we can hide its constructor
    }

    // Variables
    public static final String PolynomialFunction = "p(x)";
    public static final String PolynomialIntFunction = "P(x)";

    //Functions
    static PointerDTO[] calcPointers (int n) {
        int mod = n % 2; // If n is even or odd

        int leftCounter = ((mod == 0) ? (((n/2)-1) * -1) : ((n/2) * -1));
        int rightCounter = (n / 2); // always be n/2

        PointerDTO[] arr = new PointerDTO[n];

        int i=0;
        while (leftCounter < 0) {
            arr[i] = new PointerDTO(leftCounter, leftCounter == -1 ? "-h" : (leftCounter + "h"));;
            leftCounter++; i++;
        }

        i = n-1;
        while (rightCounter >= 0) {
            arr[i] = new PointerDTO(rightCounter, rightCounter == 1 ? "h" : (rightCounter == 0 ? "0" : (rightCounter + "h")));;
            rightCounter--; i--;
        }
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

            Fraction f = i == 0 ? Fraction.ZERO : Fraction.getInstance(1, i);
            coefficients.add(f);
        }

        return new PolynomialDTO(poly.toString(), polyInt.toString());
    }


    public static String[][] initMatrix (PointerDTO[] pointers, int n) {
        String[][] matrix = new String[n+1][n+2];

        for(int i=0; i<pointers.length; i++) {
            for(int j=0; j<=n; j++) {
                PointerDTO pointer = pointers[i];
                int coefficient = pointer.getCoefficient();
                String element = (coefficient == 0 && j == 0) ? ("c{" + pointers[i].getCoefficient() + "}") : h(coefficient, j);
                matrix[i][j] = element;
            }
            matrix[i][n+1] = "y" + "{" + pointers[i].getCoefficient() + "}";
        }

        for (String[] strings : matrix) {
            for (int c = 0; c <= n + 1; c++) {
                System.out.print(strings[c] + " ");
            }
            System.out.println();
        }

        return matrix;
    }

    public static String h(int coefficientInt, int pow) {
        boolean powIsEvenNumber = (pow % 2) == 0;
        coefficientInt = (powIsEvenNumber && coefficientInt < 0) ? (coefficientInt * -1): coefficientInt;

        if(coefficientInt == 0) {
            return "0";
        }else if(pow == 0) {
            return "1";
        }else if(pow == 1 || pow == -1) {
            return symMultiply(coefficientInt, pow);
        } else {
            return symMultiply((int)Math.pow(coefficientInt, pow), pow) + "^" + pow;
        }
    }

    public static String symMultiply(int num, int pow) {
        if(num == 0) {
            return "0";
        }else if(num == -1) {
            return "-h";
        }else if(num == 1) {
            return "h";
        }else {
            return num + "h";
        }
    }
}
