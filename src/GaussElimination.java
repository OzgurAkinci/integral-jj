import java.util.ArrayList;
import java.util.Arrays;

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


}
