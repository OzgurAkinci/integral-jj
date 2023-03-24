class EvalVisitor implements Visitor {
    int size;

    public Object visit(Exp e) {
        return e.accept(this);
    }

    public Object visit(INT e) {
        size = ((Num) e.e1).sayi;

        System.out.println(new PolynomialFunction(size, "PolynomialFunction"));
        System.out.println(new PolynomialFunction(size, "PolynomialIntFunction"));
        return  e;
    }


    public Object visit(List e) {
        size = e.e1.size();
        return e;
    }

    public Object visit(Num e) {
        return e;
    }

    public Object visit(RNum e) {
        return e;
    }

    public Object visit(PolynomialFunction e) {
        return e;
    }


}
