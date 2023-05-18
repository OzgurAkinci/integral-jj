import java.util.List;

public class MatrixDto {
    private int[][] initMatrix;
    private int[][] echelonMatrix;
    private String[] solutionMatrix;
    private List<StepDto> steps;

    public MatrixDto() {}

    public MatrixDto(int[][] initMatrix, int[][] echelonMatrix, String [] solutionMatrix) {
        this.initMatrix = initMatrix;
        this.echelonMatrix = echelonMatrix;
        this.solutionMatrix = solutionMatrix;
    }

    public int[][] getInitMatrix() {
        return initMatrix;
    }

    public void setInitMatrix(int[][] initMatrix) {
        this.initMatrix = initMatrix.clone();
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

    public List<StepDto> getSteps() {
        return steps;
    }

    public void setSteps(List<StepDto> steps) {
        this.steps = steps;
    }
}
