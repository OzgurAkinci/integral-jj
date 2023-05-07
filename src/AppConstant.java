import java.util.*;

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
            arr[i] = new PointerDTO(leftCounter, leftCounter == -1 ? "-h" : (leftCounter + "h"), "y{" + leftCounter + "}");
            leftCounter++; i++;
        }

        i = n-1;
        while (rightCounter >= 0) {
            arr[i] = new PointerDTO(rightCounter, rightCounter == 1 ? "h" : (rightCounter == 0 ? "0" : (rightCounter + "h")), "y{" + rightCounter + "}");
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

    /*
    Bu fonksiyon, bir 2 boyutlu double türündeki matrisi parametre olarak alır ve
    matrisin üzerinde değişiklik yapar, bu nedenle eşelon matrisi elde etmek için,
    matrisi önce fonksiyona göndermeniz gerekir. Fonksiyon, eşelon matrisi oluşturmak için
    Gauss eleme yöntemini kullanır.
    */
    public static int[][] findEchelonMatrix(int[][] A, String[] B) {
        int rowCount = A.length;
        int columnCount = A[0].length;

        int row = 0;
        for (int col = 0; col < columnCount && row < rowCount; col++) {
            // Birinci adım: anahtar elemanın 0 olmadığı bir satır bulun
            int pivotRow = row;
            while (pivotRow < rowCount && A[pivotRow][col] == 0) {
                pivotRow++;
            }

            if (pivotRow == rowCount) {
                // Bu sütundaki tüm elemanlar zaten sıfır
                continue;
            }

            // İkinci adım: anahtar elemanı olan satırı bulun ve eşleştirin
            if (pivotRow != row) {
                int[] tempRow = A[pivotRow];
                A[pivotRow] = A[row];
                A[row] = tempRow;
            }

            // Üçüncü adım: anahtar elemanı sıfırdan farklı bir sayı yapın
            int pivot = A[row][col];
            for (int j = col; j < columnCount; j++) {
                A[row][j] /= pivot;

            }

            // Dördüncü adım: anahtar elemanın altındaki tüm elemanlarda sıfır yapın
            for (int j = row + 1; j < rowCount; j++) {
                int factor = A[j][col];
                for (int k = col; k < columnCount; k++) {
                    A[j][k] -= factor * A[row][k];
                }
            }

            // Beşinci adım: bir sonraki anahtar eleman için ilerleyin
            row++;
        }
        return A;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }

    public static void printDoubleMatrix(double[][] matrix) {
        for (double[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }


    public static String[] solveSystemOfEquations(int[][] A) {
        int m = A.length;
        int n = A[0].length;

        // sembolik matris B oluşturulur
        String[] B = new String[n - 1];
        for (int i = 0; i < n - 1; i++) {
            B[i] = "x" + (i + 1);
        }

        // matrisin eşelon hali oluşturulur
        for (int k = 0; k < Math.min(m, n - 1); k++) {
            int i_max = k;
            for (int i = k + 1; i < m; i++) {
                if (Math.abs(A[i][k]) > Math.abs(A[i_max][k])) {
                    i_max = i;
                }
            }

            if (A[i_max][k] == 0) {
                continue;
            }

            // satırlar değiştirilir
            int[] temp = A[k];
            A[k] = A[i_max];
            A[i_max] = temp;

            // satırların çarpılması ve çıkarılması
            for (int i = k + 1; i < m; i++) {
                int f = A[i][k] / A[k][k];
                for (int j = k + 1; j < n; j++) {
                    A[i][j] -= f * A[k][j];
                }
                A[i][k] = 0;
            }
        }

        // matrisin üst üçgen şekli elde edilir
        for (int i = m - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                int f = A[j][i] / A[i][i];
                for (int k = i; k < n; k++) {
                    A[j][k] -= f * A[i][k];
                }
            }
        }

        // sonuçlar sembolik matris B'nin elemanları olarak döndürülür
        for (int i = 0; i < n - 1; i++) {
            B[i] += " = ";
            if (A[i][n - 1] % A[i][i] != 0) {
                B[i] += A[i][n - 1] + "/" + A[i][i];
            } else {
                B[i] += A[i][n - 1] / A[i][i];
            }
        }

        return B;
    }


    public static int[][] matrixConcatenate(int[][] matrix1, int[][] matrix2) {
        // İki matrisin de boyları eşit olmalıdır.
        int satirSayisi = matrix1.length;
        int sutunSayisi = matrix1[0].length;
        if (satirSayisi != matrix2.length || sutunSayisi != matrix2[0].length) {
            throw new IllegalArgumentException("Matrislerin boyutları eşleşmiyor");
        }

        // Yeni bir matris oluşturun ve iki matrisi birleştirin.
        int[][] birlesikMatris = new int[satirSayisi][sutunSayisi * 2];
        for (int i = 0; i < satirSayisi; i++) {
            for (int j = 0; j < sutunSayisi; j++) {
                birlesikMatris[i][j] = matrix1[i][j];
                birlesikMatris[i][j + sutunSayisi] = matrix2[i][j];
            }
        }
        return birlesikMatris;
    }


}
