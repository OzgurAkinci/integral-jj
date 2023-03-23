import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fraction extends Number implements Comparable<Fraction>, Serializable {
    public final static Fraction ZERO = new Fraction(BigInteger.ZERO, BigInteger.ONE);
    private final static long serialVersionUID = 1099377265582986378L;

    private final BigInteger numerator, denominator;

    private Fraction(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    private static Fraction canonical(BigInteger numerator, BigInteger denominator, boolean checkGcd) {
        if (denominator.signum() == 0) {
            throw new IllegalArgumentException("denominator is zero");
        }
        if (numerator.signum() == 0) {
            return ZERO;
        }
        if (denominator.signum() < 0) {
            numerator = numerator.negate();
            denominator = denominator.negate();
        }
        if (checkGcd) {
            BigInteger gcd = numerator.gcd(denominator);
            if (!gcd.equals(BigInteger.ONE)) {
                numerator = numerator.divide(gcd);
                denominator = denominator.divide(gcd);
            }
        }
        return new Fraction(numerator, denominator);
    }

    public static Fraction getInstance(BigInteger numerator, BigInteger denominator) {
        return canonical(numerator, denominator, true);
    }

    public static Fraction getInstance(long numerator, long denominator) {
        return canonical(new BigInteger("" + numerator), new BigInteger("" + denominator), true);
    }

    public static Fraction getInstance(String numerator, String denominator) {
        return canonical(new BigInteger(numerator), new BigInteger(denominator), true);
    }

    public static Fraction valueOf(Double param) {
        String s = param.toString();
        Pattern p = Pattern.compile("(-?\\d+)(?:.(\\d+)?)?0*(?:e(-?\\d+))?");
        Matcher m = p.matcher(s);
        if (!m.matches()) {
            throw new IllegalArgumentException("Unknown format '" + s + "'");
        }

        // this translates 23.123e5 to 25,123 / 1000 * 10^5 = 2,512,300 / 1 (GCD)
        String whole = m.group(1);
        String decimal = m.group(2);
        String exponent = m.group(3);
        String n = whole;

        // 23.123 => 23123
        if (decimal != null) {
            n += decimal;
        }
        BigInteger numerator = new BigInteger(n);

        // exponent is an int because BigInteger.pow() takes an int argument
        // it gets more difficult if exponent needs to be outside {-2 billion,2 billion}
        int exp = exponent == null ? 0 : Integer.valueOf(exponent);
        int decimalPlaces = decimal == null ? 0 : decimal.length();
        exp -= decimalPlaces;
        BigInteger denominator;
        if (exp < 0) {
            denominator = BigInteger.TEN.pow(-exp);
        } else {
            numerator = numerator.multiply(BigInteger.TEN.pow(exp));
            denominator = BigInteger.ONE;
        }

        // done
        return canonical(numerator, denominator, true);
    }

    // Comparable
    public int compareTo(Fraction o) {
        // note: this is a bit of cheat, relying on BigInteger.compareTo() returning
        // -1, 0 or 1.  For the more general contract of compareTo(), you'd need to do
        // more checking
        if (numerator.signum() != o.numerator.signum()) {
            return numerator.signum() - o.numerator.signum();
        } else {
            // oddly BigInteger has gcd() but no lcm()
            BigInteger i1 = numerator.multiply(o.denominator);
            BigInteger i2 = o.numerator.multiply(denominator);
            return i1.compareTo(i2); // expensive!
        }
    }

    public Fraction add(Fraction o) {
        if (o.numerator.signum() == 0) {
            return this;
        } else if (numerator.signum() == 0) {
            return o;
        } else if (denominator.equals(o.denominator)) {
            return new Fraction(numerator.add(o.numerator), denominator);
        } else {
            return canonical(numerator.multiply(o.denominator).add(o.numerator.multiply(denominator)), denominator.multiply(o.denominator), true);
        }
    }


    public Fraction multiply(Fraction o) {
        if (numerator.signum() == 0 || o.numerator.signum( )== 0) {
            return ZERO;
        } else if (numerator.equals(o.denominator)) {
            return canonical(o.numerator, denominator, true);
        } else if (o.numerator.equals(denominator)) {
            return canonical(numerator, o.denominator, true);
        } else if (numerator.negate().equals(o.denominator)) {
            return canonical(o.numerator.negate(), denominator, true);
        } else if (o.numerator.negate().equals(denominator)) {
            return canonical(numerator.negate(), o.denominator, true);
        } else {
            return canonical(numerator.multiply(o.numerator), denominator.multiply(o.denominator), true);
        }
    }

    public BigInteger getNumerator() { return numerator; }
    public BigInteger getDenominator() { return denominator; }
    public boolean isInteger() { return numerator.signum() == 0 || denominator.equals(BigInteger.ONE); }
    public Fraction negate() { return new Fraction(numerator.negate(), denominator); }
    public Fraction invert() { return canonical(denominator, numerator, false); }
    public Fraction abs() { return numerator.signum() < 0 ? negate() : this; }
    public Fraction pow(int exp) { return canonical(numerator.pow(exp), denominator.pow(exp), true); }
    public Fraction subtract(Fraction o) { return add(o.negate()); }
    public Fraction divide(Fraction o) { return multiply(o.invert()); }
    public Fraction min(Fraction o) { return compareTo(o) <= 0 ? this : o; }
    public Fraction max(Fraction o) { return compareTo(o) >= 0 ? this : o; }

    public BigDecimal toBigDecimal(int scale, RoundingMode roundingMode) {
        return isInteger() ? new BigDecimal(numerator) : new BigDecimal(numerator).divide(new BigDecimal(denominator), scale, roundingMode);
    }

    // Number
    public int intValue() { return isInteger() ? numerator.intValue() : numerator.divide(denominator).intValue(); }
    public long longValue() { return isInteger() ? numerator.longValue() : numerator.divide(denominator).longValue(); }
    public float floatValue() { return (float)doubleValue(); }
    public double doubleValue() { return isInteger() ? numerator.doubleValue() : numerator.doubleValue() / denominator.doubleValue(); }

    @Override
    public String toString() { return isInteger() ? String.format("%,d", numerator) : String.format("%,d / %,d", numerator, denominator); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction that = (Fraction) o;

        if (denominator != null ? !denominator.equals(that.denominator) : that.denominator != null) return false;
        if (numerator != null ? !numerator.equals(that.numerator) : that.numerator != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numerator != null ? numerator.hashCode() : 0;
        result = 31 * result + (denominator != null ? denominator.hashCode() : 0);
        return result;
    }
}
