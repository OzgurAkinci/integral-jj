import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        // [y{-1}/-1, y{0}-0*y{-1}/-1/2, y{1}-1*y{-1}/-1-0*y{0}-0*y{-1}/-1/2/-6, y{2}-8*y{-1}/-1-12*y{0}-0*y{-1}/-1/2-0*y{1}-1*y{-1}/-1-0*y{0}-0*y{-1}/-1/2/-6/1]
        String expression = "x+6/4+y";
        String expression2 = " x-0*y/-1/2";
        String expression3 = "y{1}-1*y{-1}/-1-0*y{0}-0*y{-1}/-1/2/-6";
        String simplified = simplifyExpression(expression3);
        System.out.println(simplified);  // x+2+y
    }

    public static String simplifyExpression(String expression) {
        // Sayısal olmayan ifadeleri boşluklarla ayırma
        String separated = expression.replaceAll("([a-zA-Z])", " $1 ");

        // Sayısal ifadelerin çözülmesi ve sadeleştirilmesi
        Pattern pattern = Pattern.compile("\\d+\\s*[/*]\\s*\\d+");
        Matcher matcher = pattern.matcher(separated);
        while (matcher.find()) {
            String match = matcher.group();
            String[] parts = match.split("[/*]");
            int num1 = Integer.parseInt(parts[0].trim());
            int num2 = Integer.parseInt(parts[1].trim());
            double result;
            if (match.contains("*")) {
                result = num1 * 1.0 * num2;
            } else {
                result = num1 * 1.0 / num2;
            }
            separated = separated.replace(match, String.valueOf(result));
        }

        // İfadeyi sadeleştirme
        String[] tokens = separated.split("\\s+");
        StringBuilder simplified = new StringBuilder();
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.matches("[a-zA-Z]")) {
                simplified.append(token);
            } else {
                if (i > 0 && tokens[i - 1].matches("[a-zA-Z]")) {
                    simplified.append(token);
                } else {
                    simplified.append(" ").append(token);
                }
            }
        }

        return simplified.toString().trim();
    }
}
