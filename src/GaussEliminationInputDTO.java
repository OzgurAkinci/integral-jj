public class GaussEliminationInputDTO {
    private Fraction[][] A;
    private String[] b;

    public GaussEliminationInputDTO() {}

    public GaussEliminationInputDTO(Fraction[][] a, String[] b) {
        A = a;
        this.b = b;
    }

    public Fraction[][] getA() {
        return A;
    }

    public void setA(Fraction[][] a) {
        A = a;
    }

    public String[] getB() {
        return b;
    }

    public void setB(String[] b) {
        this.b = b;
    }
}
