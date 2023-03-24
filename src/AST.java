
abstract class Exp {
    public abstract Object accept(Visitor v);
}

class INT extends Exp {
    Exp exp;

    public INT(Exp e) {
        exp = e;
    }

    public String toString() {
        return "INT(" + exp + ")";
    }

    public Object accept(Visitor v) {
        return v.visit(this);
    }

}

class PolynomialFunction extends Exp {
    int size;
    String type;

    public PolynomialFunction(int sizeParam, String typeParam) {
        size = sizeParam;
        type = typeParam;
    }

    public String toString() {
        StringBuilder poly = new StringBuilder();
        if(type.equals(AppConstant.PolynomialFunction)) {
            // p(x) = cnx^n + ... + c3x^3 + c2x^2 + c1x + c0
            for (int n = size-1 ; n >= 0; n--) {
                poly.append("x^").append(n);
                if(n != 0) {
                    poly.append("+");
                }
            }
        }else if (type.equals(AppConstant.PolynomialIntFunction)) {
            // P(x) = (cnx^(n+1) / (n+1)) + .... + ((c3x^4) / 4) + ((c2x^3) / 3) + ((c1x^2) / 2) + (c0x)
            for (int n = size-1 ; n >= 0; n--) {
                poly.append("(").append("(").append(n).append("x^").append(n+1).append(")").append("/").append(n+1).append(")");
                if(n != 0) {
                    poly.append("+");
                }
            }
        }
        return type + ": " + poly;
    }

    public Object accept(Visitor v) {
        return v.visit(this);
    }
}

class Num extends Exp {
    int number;

    public Num(int a) {
        number = a;
    }

    public String toString() {
        return "Num(" + number + ")";
    }

    public Object accept(Visitor v) {
        return v.visit(this);
    }
}

class RNum extends Exp {
    int number1, number2;

    public RNum(int a, int b) {
        number1 = a;
        number2 = b;
    }

    public String toString() {
        return "RNum(" + number1 + "/" + number2 + ")";
    }

    public Object accept(Visitor v) {
        return v.visit(this);
    }
}
