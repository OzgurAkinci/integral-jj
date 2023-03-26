public class GaussEliminationInputDTO {
    private String[][] A;
    private String[] b;

    public GaussEliminationInputDTO() {}

    public GaussEliminationInputDTO(String[][] a, String[] b) {
        A = a;
        this.b = b;
    }

    public String[][] getA() {
        return A;
    }

    public void setA(String[][] a) {
        A = a;
    }

    public String[] getB() {
        return b;
    }

    public void setB(String[] b) {
        this.b = b;
    }
}
