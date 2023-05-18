public class StepDto {
    private int[][] matrix;
    private int pivotRow;

    public StepDto() {}

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix.clone();
    }

    public int getPivotRow() {
        return pivotRow;
    }

    public void setPivotRow(int pivotRow) {
        this.pivotRow = pivotRow;
    }

    @Override
    public String toString() {
        return "StepDto{" +
                "pivotRow=" + pivotRow +
                '}';
    }
}
