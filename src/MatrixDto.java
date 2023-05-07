public class MatrixDto {
    private int[][] echelonMatrix;
    private String[] solutionMatrix;
    private StepDto steps;

    public MatrixDto() {}

    public MatrixDto(int[][] echelonMatrix, String [] solutionMatrix) {
        this.echelonMatrix = echelonMatrix;
        this.solutionMatrix = solutionMatrix;
    }

    public int[][] getEchelonMatrix() {
        return echelonMatrix;
    }

    public void setEchelonMatrix(int[][] echelonMatrix) {
        this.echelonMatrix = echelonMatrix;
    }

    public String[] getSolutionMatrix() {
        return solutionMatrix;
    }

    public void setSolutionMatrix(String[] solutionMatrix) {
        this.solutionMatrix = solutionMatrix;
    }

    public StepDto getSteps() {
        return steps;
    }

    public void setSteps(StepDto steps) {
        this.steps = steps;
    }
}
