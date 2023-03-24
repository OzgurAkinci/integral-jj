interface Visitor {
  public Object visit(Exp e);
  public Object visit(INT e);
  public Object visit(List e);
  public Object visit(Num e);
  public Object visit(RNum e);
  public Object visit(PolynomialFunction e);
}
