public class GaussElimination {
    public static Fraction[] solve(Fraction[][] A, Fraction[] b) {
        int n = b.length;

        // A ve b matrislerini birleştirin
        Fraction[][] Ab = new Fraction[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Ab[i][j] = A[i][j];
            }
            Ab[i][n] = b[i];
        }

        // Üçgen matris oluşturma
        for (int i = 0; i < n; i++) {
            // Pivot seçimi
            int maxRow = i;
            for (int j = i + 1; j < n; j++) {
                if (Ab[j][i].abs().compareTo(Ab[maxRow][i].abs()) > 0) {
                    maxRow = j;
                }
            }
            Fraction[] temp = Ab[i];
            Ab[i] = Ab[maxRow];
            Ab[maxRow] = temp;

            // Eliminasyon işlemi
            for (int j = i + 1; j < n; j++) {
                Fraction factor = Ab[j][i].divide(Ab[i][i]);
                for (int k = i; k < n + 1; k++) {
                    Ab[j][k] = Ab[j][k].subtract(factor.multiply(Ab[i][k]));
                }
            }
        }

        // Geriye doğru yerine koyma
        Fraction[] x = new Fraction[n];
        for (int i = n - 1; i >= 0; i--) {
            Fraction sum = Fraction.ZERO;
            for (int j = i + 1; j < n; j++) {
                sum = sum.add(Ab[i][j].multiply(x[j]));
            }
            x[i] = Ab[i][n].subtract(sum).divide(Ab[i][i]);
        }

        return x;
    }

    public static String[] solveSymbolicPolynomial(String[] coefficients, String[] equation) {
        int n = coefficients.length;
        String[][] matrix = new String[n][n + 1];

        // Matrisi doldurma
        for (int i = 0; i < n; i++) {
            String[] terms = equation[i].split(" ");
            for (int j = 0; j < n + 1; j++) {
                matrix[i][j] = terms[j];
            }
        }

        // Gauss eleminasyonu yöntemi
        for (int i = 0; i < n; i++) {
            // Pivot bulma
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (matrix[j][i].length() > matrix[maxIndex][i].length()) {
                    maxIndex = j;
                }
            }
            // Pivot satırı değiştirme
            String[] temp = matrix[i];
            matrix[i] = matrix[maxIndex];
            matrix[maxIndex] = temp;
            // Normalleştirme
            for (int j = i + 1; j < n; j++) {
                String multiplier = divide(matrix[j][i], matrix[i][i]);
                for (int k = i; k < n + 1; k++) {
                    matrix[j][k] = subtract(matrix[j][k], multiply(matrix[i][k], multiplier));
                }
            }
        }

        // Geriye doğru çözme
        String[] result = new String[n];
        for (int i = n - 1; i >= 0; i--) {
            String sum = "0";
            for (int j = i + 1; j < n; j++) {
                sum = add(sum, multiply(matrix[i][j], result[j]));
            }
            result[i] = divide(subtract(matrix[i][n], sum), matrix[i][i]);
        }
        return result;
    }


    public static String add(String a, String b) {
        return "(" + a + " + " + b + ")";
    }

    public static String subtract(String a, String b) {
        return "(" + a + " - " + b + ")";
    }

    public static String multiply(String a, String b) {
        return "(" + a + " * " + b + ")";
    }

    public static String divide(String a, String b) {
        return "(" + a + " / " + b + ")";
    }
}
