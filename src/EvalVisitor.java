import dto.PolynomialDto;

class EvalVisitor implements Visitor {
    int n;

    public Object visit(Exp e) {
        return e.accept(this);
    }

    public Object visit(INT e) {
        n = ((Num) e.exp).number;
        System.out.println("Input n = " + n);

        // pointers
        System.out.println("========================================================");
        int[] arr = AppConstant.calcPointers(n);

        // poly
        System.out.println("========================================================");
        PolynomialDto polynomialDto = AppConstant.getPolynomialDto(n);
        System.out.println(AppConstant.PolynomialFunction + "= "+ polynomialDto.getPoly());
        System.out.println(AppConstant.PolynomialIntFunction + "= "+ polynomialDto.getPolyInt());

        /*
        // polynomial functions
        System.out.println("========================================================");
        String poly = new PolynomialFunction(n, AppConstant.PolynomialFunction).toString();
        System.out.println(AppConstant.PolynomialFunction + "= "+ poly);
        String intPoly = new PolynomialFunction(n, AppConstant.PolynomialIntFunction).toString();
        System.out.println(AppConstant.PolynomialIntFunction + "= "+ intPoly);

        // polynomial
        System.out.println(new Polynomial(5,3));

        // pointers
        System.out.println("========================================================");
        int[] arr = AppConstant.calcPointers(n);

        System.out.println("========================================================");
        String negH = intPoly.replace("x", "-h");
        String posH = intPoly.replace("x", "h");
        String I = "[" + posH + "]" + "-" + "[" +  negH + "]";
        System.out.println("I= "+ I);


        */


        System.out.println("========================================================");
        return  e;
    }

    public Object visit(Num e) {
        return e;
    }

    public Object visit(RNum e) {
        return e;
    }
}
