public class StepDto {
    private int[][] matrix;
    private String equation;
    private int pivotRow;

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public int getPivotRow() {
        return pivotRow;
    }

    public void setPivotRow(int pivotRow) {
        this.pivotRow = pivotRow;
    }
}
