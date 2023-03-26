public class GaussEliminationInputDTO {
    private Fraction[][] A;
    private Fraction[] b;

    public GaussEliminationInputDTO() {}

    public GaussEliminationInputDTO(Fraction[][] a, Fraction[] b) {
        A = a;
        this.b = b;
    }

    public Fraction[][] getA() {
        return A;
    }

    public void setA(Fraction[][] a) {
        A = a;
    }

    public Fraction[] getB() {
        return b;
    }

    public void setB(Fraction[] b) {
        this.b = b;
    }
}
