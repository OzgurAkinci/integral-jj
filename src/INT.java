class INT extends Exp {
    Exp e1;

    public INT(Exp a) {
        e1 = a;
    }

    public String toString() {
        return "INT(" + e1 + ")";
    }

    public Object accept(Visitor v) {
        return v.visit(this);
    }

}

