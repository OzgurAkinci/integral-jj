public class MatrixDto {
    private int[][] matrix;
    private int[][] echelonMatrix;
    private String[] solutionMatrixIn;
    private String[] solutionMatrixOut;
    private StepDto steps;

    public MatrixDto(int[][] matrix, String [] solutionMatrixIn) {
        this.matrix = matrix;
        this.solutionMatrixIn = solutionMatrixIn;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int[][] getEchelonMatrix() {
        return echelonMatrix;
    }

    public void setEchelonMatrix(int[][] echelonMatrix) {
        this.echelonMatrix = echelonMatrix;
    }

    public String[] getSolutionMatrixIn() {
        return solutionMatrixIn;
    }

    public void setSolutionMatrixIn(String[] solutionMatrixIn) {
        this.solutionMatrixIn = solutionMatrixIn;
    }

    public String[] getSolutionMatrixOut() {
        return solutionMatrixOut;
    }

    public void setSolutionMatrixOut(String[] solutionMatrixOut) {
        this.solutionMatrixOut = solutionMatrixOut;
    }

    public StepDto getSteps() {
        return steps;
    }

    public void setSteps(StepDto steps) {
        this.steps = steps;
    }
}
