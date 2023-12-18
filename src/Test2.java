public class Test2 {
    public static void main(String[] args) {
        double[][] matrix = {
                {1, -1, 1},
                {0, 0, 1},
                {1, 1, 1}
        };

        double[][] inverse = jordanYontemi(matrix);

        // İnverse matrisini yazdırma
        for (int i = 0; i < inverse.length; i++) {
            for (int j = 0; j < inverse[i].length; j++) {
                System.out.print(inverse[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double[][] jordanYontemi(double[][] matrix) {
        int n = matrix.length;

        // Matrix ve birim matrisi birleştirme
        double[][] augmentedMatrix = new double[n][2 * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmentedMatrix[i][j] = matrix[i][j];
            }

            augmentedMatrix[i][i + n] = 1;
        }

        // Jordan Yöntemi uygulama
        for (int i = 0; i < n; i++) {
            // Pivot sütunu seçme
            int pivotColumn = i;
            double pivotValue = augmentedMatrix[i][i];

            for (int j = i + 1; j < n; j++) {
                if (Math.abs(augmentedMatrix[j][i]) > Math.abs(pivotValue)) {
                    pivotColumn = j;
                    pivotValue = augmentedMatrix[j][i];
                }
            }

            // Pivot satırını yer değiştirme
            double[] tempRow = augmentedMatrix[i];
            augmentedMatrix[i] = augmentedMatrix[pivotColumn];
            augmentedMatrix[pivotColumn] = tempRow;

            // Pivot satırını ölçeklendirme
            for (int j = 0; j < 2 * n; j++) {
                augmentedMatrix[i][j] /= pivotValue;
            }

            // Diğer satırları eleme
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double factor = augmentedMatrix[j][i];

                    for (int k = 0; k < 2 * n; k++) {
                        augmentedMatrix[j][k] -= factor * augmentedMatrix[i][k];
                    }
                }
            }
        }

        // Ters matrisi oluşturma
        double[][] inverse = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverse[i][j] = augmentedMatrix[i][j + n];
            }
        }

        return inverse;
    }

}
