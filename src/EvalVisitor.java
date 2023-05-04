import java.util.Arrays;
import java.util.stream.Collectors;

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
        PointerDTO[] pointers = AppConstant.calcPointers(n+1);
        System.out.println("h = " + Arrays.stream(pointers).toList().stream().map(PointerDTO::gethCoefficient).collect(Collectors.joining(",")));

        // poly
        System.out.println("========================================================");
        PolynomialDTO polynomialDTO = AppConstant.getPolynomialDto(n);
        System.out.println(AppConstant.PolynomialFunction + " = "+ polynomialDTO.getPoly());
        System.out.println(AppConstant.PolynomialIntFunction + " = "+ polynomialDTO.getPolyInt());


        // h functions
        System.out.println("========================================================");
        String[][] initMatrix = AppConstant.initMatrix(pointers, n);

        /*
        // coefficients
        System.out.println("========================================================");
        GaussEliminationInputDTO coefficients = AppConstant.createCoefficientAsGaussEliminationInputDTO(arr, n);

        //Fraction[] b = {Fraction.valueOf(1.0), Fraction.valueOf(1.0), Fraction.valueOf(1.0) , Fraction.valueOf(1.0)};
        //Fraction[] c = GaussElimination.solve(coefficients.getA(), b);
        //System.out.println("c_{0} = " + c[0] + ", c_{1} = " + c[1] + ", c_{2} = " + c[2]);
        */

        System.out.println("========================================================");
        int[][] matrix = new int[][] {
                { -1,1,-1,0 },
                { 0,0,0,0 },
                { 1,1,1,0 },
                { 2,2,2,0 }
        };

        int[][] new_matrix = AppConstant.findEchelonMatrix(matrix);
        AppConstant.printMatrix(new_matrix);


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
