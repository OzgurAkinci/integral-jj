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
        PolynomialDTO polynomialDTO = AppConstant.getPolynomialDto(n);
        System.out.println(AppConstant.PolynomialFunction + "= "+ polynomialDTO.getPoly());
        System.out.println(AppConstant.PolynomialIntFunction + "= "+ polynomialDTO.getPolyInt());
        System.out.println("Coefficients" + "= "+ polynomialDTO.getCoefficients());



        /*
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
