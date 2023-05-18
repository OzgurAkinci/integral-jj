import java.util.Arrays;
import java.util.stream.Collectors;

class EvalVisitor implements Visitor {
    int n;

    public Object visit(Exp e) {
        return e.accept(this);
    }

    public Object visit(INT e) {
        // input
        System.out.println("========================= input(n) ======================================");
        n = ((Num) e.exp).number;
        System.out.println("n = " + n);

        // pointers
        System.out.println("========================= h & y =========================================");
        PointerDTO[] pointers = AppConstant.calcPointers(n+1);
        System.out.println("h = " + Arrays.stream(pointers).toList().stream().map(PointerDTO::gethCoefficient).collect(Collectors.joining(",")));
        System.out.println("y = " + Arrays.stream(pointers).toList().stream().map(PointerDTO::getyCoefficient).collect(Collectors.joining(",")));

        // poly
        System.out.println("========================= Polynomial Function ===========================");
        PolynomialDTO polynomialDTO = AppConstant.getPolynomialDto(n);
        System.out.println(AppConstant.PolynomialFunction + " = "+ polynomialDTO.getPoly());
        System.out.println(AppConstant.PolynomialIntFunction + " = "+ polynomialDTO.getPolyInt());


        // h functions
        System.out.println("========================= Symbolic Init Matrix ==========================");
        String[][] initSymbolicMatrix = AppConstant.initSymbolicMatrix(pointers, n);
        AppConstant.printStrMatrix(initSymbolicMatrix);

        System.out.println("========================= Init Matrix ===================================");
        int[][] initMatrix = AppConstant.initMatrix(pointers, n);
        String[] B = new String[pointers.length];
        for(int i=0; i<pointers.length; i++) {B[i] = pointers[i].getyCoefficient();}
        MatrixDto echelonMatrix = AppConstant.findEchelonMatrix(initMatrix, B);
        AppConstant.printMatrix(echelonMatrix.getInitMatrix());

        System.out.println("========================= Echelon Matrix ================================");
        AppConstant.printMatrix(echelonMatrix.getEchelonMatrix());


        System.out.println("========================= Steps ================================");
        if(echelonMatrix.getSteps() != null){
            for(int i=0; i < echelonMatrix.getSteps().size(); i++) {
                System.out.println("PivotRow: " + echelonMatrix.getSteps().get(i).getPivotRow());
                System.out.println("Equation: " + echelonMatrix.getSteps().get(i).getEquation());
                AppConstant.printMatrix(echelonMatrix.getSteps().get(i).getMatrix());
                System.out.println("####################################");
            }
        }else {
            System.out.println("Steps is null");
        }

        System.out.println("========================= Solution Matrix ===============================");
        System.out.println(Arrays.toString(echelonMatrix.getSolutionMatrix()));

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
