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
        System.out.println("y = " + Arrays.stream(pointers).toList().stream().map(PointerDTO::getyCoefficient).collect(Collectors.joining(",")));

        // poly
        System.out.println("========================================================");
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
        AppConstant.printMatrix(initMatrix);

        System.out.println("========================= Echelon Matrix ================================");
        MatrixDto echelonMatrix = AppConstant.findEchelonMatrix(initMatrix, B);
        AppConstant.printMatrix(echelonMatrix.getEchelonMatrix());
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
