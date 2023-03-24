import java.util.ArrayList;

abstract class Exp {
    public abstract Object accept(Visitor v);
}


class List extends Exp {
    ArrayList<Object> e1 = new ArrayList<Object>();

    public List(Exp[] a) {
        //e1=a;
        for (int i = 0; i < a.length; i++) {
            e1.add(a[i]);
        }
    }

    public String toString() {
        return "List(" + e1 + ")";
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

        if(type.equals("PolynomialFunction")) {
            // p(x) = cnx^n + ... + c3x^3 + c2x^2 + c1x + c0
            StringBuilder poly = new StringBuilder();
            for (int n = size-1 ; n > 0; n--) {
                poly.append("x^").append(n);
                if(n != 1) {
                    poly.append("+");
                }
            }
            //System.out.println(new PolynomialFunction("poly", poly.toString()));
            return type + ": " + poly;
        }else if (type.equals("PolynomialIntFunction")) {
            // P(x) = (cnx^(n+1) / (n+1)) + .... + ((c3x^4) / 4) + ((c2x^3) / 3) + ((c1x^2) / 2) + (c0x)
            StringBuilder polyInt = new StringBuilder();
            for (int n = size-1 ; n > 0; n--) {
                polyInt.append("(").append("(").append(n).append("x^").append(n+1).append(")").append("/").append(n+1).append(")");
                if(n != 1) {
                    polyInt.append("+");
                }
            }
            return type + ": " + polyInt;
        }else {
            return null;
        }


        //return (type.equals("poly") ? "PolynomialFunction=" : "PolynomialIntFunction= ") +  "(" + e1 + ")";
    }

    public Object accept(Visitor v) {
        return v.visit(this);
    }
}

class Num extends Exp {
    int sayi;

    public Num(int a) {
        sayi = a;
    }

    public String toString() {
        return "Num(" + sayi + ")";
    }

    public Object accept(Visitor v) {
        return v.visit(this);
    }
}

class RNum extends Exp {
    int sayi1, sayi2;

    public RNum(int a, int b) {
        sayi1 = a;
        sayi2 = b;
    }

    public String toString() {
        return "RNum(" + sayi1 + "/" + sayi2 + ")";
    }

    public Object accept(Visitor v) {
        return v.visit(this);
    }
}
