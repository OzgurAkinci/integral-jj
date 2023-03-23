import java.util.ArrayList;


class EvalVisitor implements Visitor {
    int size;

    public Object visit(Exp e) {
        return e.accept(this);
    }


    public Object visit(List e) {
        size = e.e1.size();

        // p(x) = cnx^n + ... + c3x^3 + c2x^2 + c1x + c0
        StringBuilder poly = new StringBuilder();
        for (int n = size-1 ; n > 0; n--) {
            poly.append("x^").append(n);
            if(n != 1) {
                poly.append("+");
            }
        }
        System.out.println(new PolynomialFunction("poly", poly.toString()));

        // P(x) = (cnx^(n+1) / (n+1)) + .... + ((c3x^4) / 4) + ((c2x^3) / 3) + ((c1x^2) / 2) + (c0x)
        StringBuilder polyInt = new StringBuilder();
        for (int n = size-1 ; n > 0; n--) {
            polyInt.append("(").append("(").append(n).append("x^").append(n+1).append(")").append("/").append(n+1).append(")");
            if(n != 1) {
                polyInt.append("+");
            }
        }
        System.out.println(new PolynomialFunction("polyInt", polyInt.toString()));


        // FractionInt
        Fraction f = Fraction.getInstance(1, 2);
        System.out.println("Fraction: " + f);

        return e;
    }

    public Object visit(IntegralJJ e) {
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
