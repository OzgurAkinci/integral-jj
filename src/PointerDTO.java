public class PointerDTO {
    private int coefficient;
    private String hCoefficient;

    PointerDTO() {}
    PointerDTO(int coefficient, String hCoefficient) {
        this.coefficient = coefficient;
        this.hCoefficient = hCoefficient;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public String gethCoefficient() {
        return hCoefficient;
    }

    public void sethCoefficient(String hCoefficient) {
        this.hCoefficient = hCoefficient;
    }

    @Override
    public String toString() {
        return "PointerDTO{" +
                "coefficient=" + coefficient +
                ", hCoefficient='" + hCoefficient + '\'' +
                '}';
    }
}
