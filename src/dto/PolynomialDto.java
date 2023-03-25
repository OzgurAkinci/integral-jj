package dto;

public class PolynomialDto {
    private String poly;
    private String polyInt;
    private int[] coefficients;

    public PolynomialDto () {}

    public PolynomialDto (String poly, String polyInt, int[] coefficients) {
        this.poly = poly;
        this.polyInt = polyInt;
        this.coefficients = coefficients;
    }

    public String getPoly() {
        return poly;
    }

    public void setPoly(String poly) {
        this.poly = poly;
    }

    public String getPolyInt() {
        return polyInt;
    }

    public void setPolyInt(String polyInt) {
        this.polyInt = polyInt;
    }

    public int[] getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(int[] coefficients) {
        this.coefficients = coefficients;
    }
}
