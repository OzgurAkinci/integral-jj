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

class IntegralJJ extends Exp {
    Polynomial e1;

    public IntegralJJ(Polynomial a) {
        e1 = a;
    }

    public String toString() {
        return "IntegralJJ(" + e1 + ")";
    }

    public Object accept(Visitor v) {
        return v.visit(this);
    }
}

class PolynomialFunction extends Exp {
    String type;
    String e1;

    public PolynomialFunction(String t, String a) {
        type = t;
        e1 = a;
    }

    public String toString() {
        return (type.equals("poly") ? "PolynomialFunction=" : "PolynomialIntFunction= ") +  "(" + e1 + ")";
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
