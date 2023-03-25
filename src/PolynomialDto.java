import java.util.List;

public class PolynomialDto {
    private String poly;
    private String polyInt;
    private List<Fraction> coefficients;

    public PolynomialDto () {}

    public PolynomialDto (String poly, String polyInt, List<Fraction> coefficients) {
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

    public List<Fraction> getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(List<Fraction> coefficients) {
        this.coefficients = coefficients;
    }
}
