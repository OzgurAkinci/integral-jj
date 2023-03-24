class EvalVisitor implements Visitor {
    int n;

    public Object visit(Exp e) {
        return e.accept(this);
    }

    public Object visit(INT e) {
        n = ((Num) e.exp).number;
        System.out.println("n = " + n);

        // polynomial functions
        System.out.println(new PolynomialFunction(n, AppConstant.PolynomialFunction));
        System.out.println(new PolynomialFunction(n, AppConstant.PolynomialIntFunction));

        // pointers
        AppConstant.calcPointers(n);


        return  e;
    }

    public Object visit(Num e) {
        return e;
    }

    public Object visit(RNum e) {
        return e;
    }
}
