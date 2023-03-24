class EvalVisitor implements Visitor {
    int size;

    public Object visit(Exp e) {
        return e.accept(this);
    }

    public Object visit(INT e) {
        size = ((Num) e.e1).number;

        System.out.println(new PolynomialFunction(size, AppConstant.PolynomialFunction));
        System.out.println(new PolynomialFunction(size, AppConstant.PolynomialIntFunction));
        return  e;
    }

    public Object visit(Num e) {
        return e;
    }

    public Object visit(RNum e) {
        return e;
    }
}
