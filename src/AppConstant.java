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

    static List<PolynomialExtraDTO> createHFunction(String poly, PointerDTO[] arr) {
        List<PolynomialExtraDTO> pdList = new ArrayList<>();

        for(int i=0; i<arr.length; i++) {
            PolynomialExtraDTO pgd = new PolynomialExtraDTO();
            pgd.setfName1("y" + i);
            pgd.setfName2("p_" + "{" + i + "}" + "(" + arr[i] + ")");
            pgd.setFunc(poly);
            pgd.setBuildFunc(poly.replace("x", arr[i].gethCoefficient()));
            pdList.add(pgd);
        }

        return pdList;
    }

    static GaussEliminationInputDTO createCoefficientAsGaussEliminationInputDTO(PointerDTO[] pointers, int n) {
        GaussEliminationInputDTO dto = new GaussEliminationInputDTO();
        Fraction[][] a = new Fraction[n + 1][pointers.length];
        Fraction[] b = new Fraction[n];
        for(int i=0; i<pointers.length; i++) {
            int k = n;
            for(int j=0; j<=n; j++) {
                a[i][j] = pointers[i].getCoefficient() == 0 ? Fraction.ZERO : Fraction.valueOf((double) (pointers[i].getCoefficient())).pow(k);
                k--;
            }
        }
        dto.setA(a);
        dto.setB(b);

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }

        return dto;
    }
}
