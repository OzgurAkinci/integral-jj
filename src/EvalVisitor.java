import java.util.Arrays;
import java.util.List;

class EvalVisitor implements Visitor {
    int n;

    public Object visit(Exp e) {
        return e.accept(this);
    }

    public Object visit(INT e) {
        // input
        System.out.println("========================================================");
        n = ((Num) e.exp).number;
        System.out.println("n = " + n);

        // pointers
        System.out.println("========================================================");
        String[] arr = AppConstant.calcPointers(n+1);
        System.out.println("h[] =  " + Arrays.toString(arr));


        // poly
        System.out.println("========================================================");
        PolynomialDTO polynomialDTO = AppConstant.getPolynomialDto(n);
        System.out.println(AppConstant.PolynomialFunction + " = "+ polynomialDTO.getPoly());
        System.out.println(AppConstant.PolynomialIntFunction + " = "+ polynomialDTO.getPolyInt());


        // poly
        System.out.println("========================================================");
        List<PolynomialExtraDTO> pdList = AppConstant.createHFunction(polynomialDTO.getPoly(), arr);
        pdList.forEach(pd -> System.out.println(pd.getfName1() + " = " + pd.getfName2() + " = " + pd.getBuildFunc()));


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
