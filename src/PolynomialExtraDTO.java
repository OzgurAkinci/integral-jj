public class PolynomialExtraDTO {
    private String fName1;
    private String fName2;
    private String func;
    private String buildFunc;

    public PolynomialExtraDTO() {}

    public PolynomialExtraDTO(String fName1, String fName2, String func, String buildFunc) {
        this.fName1 = fName1;
        this.fName2 = fName2;
        this.func = func;
        this.buildFunc = buildFunc;
    }

    public String getfName1() {
        return fName1;
    }

    public void setfName1(String fName1) {
        this.fName1 = fName1;
    }

    public String getfName2() {
        return fName2;
    }

    public void setfName2(String fName2) {
        this.fName2 = fName2;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getBuildFunc() {
        return buildFunc;
    }

    public void setBuildFunc(String buildFunc) {
        this.buildFunc = buildFunc;
    }

    @Override
    public String toString() {
        return buildFunc;
    }
}
