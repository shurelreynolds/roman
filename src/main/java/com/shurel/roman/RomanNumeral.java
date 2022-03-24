/*
 * @author Shurel Reynolds
 * >0 && <4000. The largest number is MMMCMXCIX, or 3,999
 * //Symbol    I    V    X     L     C      D      M
 * //Decimal   1    5    10    50    100    500    1,000
 *
 */
package com.shurel.roman;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * the largest number is MMMCMXCIX, or 3999
 *       //Symbol    I    V    X    L    C      D      M
 *       //Decimal   1    5    10   50   100    500    1000
 */
enum Symbol {
    I(1), V(5), X(10), L(50), C(100), D(500), M(1000),
    IV(4), IX(9), XL(40), XC(90), CD(400), CM(900);

    private final int decimal;

    Symbol(int decimal) {
        this.decimal = decimal;
    }

    public int getDecimal() {
        return decimal;
    }

    public static int match(String token) {

        int dec = 0;
        try {
            dec = Symbol.valueOf(token.toUpperCase()).getDecimal();

        } catch (IllegalArgumentException e) {
            new RomanNumeralException("Invalid token: " + token);
        }
        return dec;
    }

    public static Symbol getBase(int num) {
        return Arrays.stream(Symbol.values())
                .filter(val -> val.getDecimal() == num)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Out of Bounds: " + num));

    }
}


public class RomanNumeral {

    static Pattern romanNumeralsPattern = Pattern.compile("^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    static Matcher matcher;
    static Pattern pattern;
    //sorted largest first
    private final static List<Integer> bases = Stream.of(Symbol.values())
            .map(Symbol::getDecimal)
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

    static List<Symbol> remPrimList = new ArrayList<>(Arrays.asList(Symbol.IV, Symbol.IX, Symbol.XL, Symbol.XC,
            Symbol.CD, Symbol.CM));


    static List<Symbol> remSecList = new ArrayList<>(Arrays.asList(Symbol.M, Symbol.D, Symbol.C, Symbol.L, Symbol.X,
            Symbol.V, Symbol.I));


    public static boolean isValidRomanNumeral(String numerals) throws RomanNumeralException {


        if (numerals == null || numerals.trim().length() == 0)
            throw new RomanNumeralException("No Roman Numeral given");

        else return romanNumeralsPattern.matcher(numerals.toUpperCase()).matches();
    }


    public static int toDecimal(String numeral) throws RomanNumeralException {
        if (!isValidRomanNumeral(numeral)) throw new RomanNumeralException("Invalid Roman Numeral: " + numeral);
        List<Integer> list = new ArrayList<>();


        for (Symbol s : remPrimList) {
            pattern = Pattern.compile(s.name());

            matcher = pattern.matcher(numeral);
            while (matcher.find()) {
                numeral = numeral.replace(s.name(), "");
                list.add(s.getDecimal());
            }
        }
        char[] num = numeral.toCharArray();

        for (int i = 0; i < num.length; i++) {
            list.add(Symbol.match(num[i] + ""));
        }

        return list.stream().collect(Collectors.summingInt(Integer::intValue));
    }

    public static void main(String[] a) {
        String input = null;
        String title = "<^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^>\n" +
                "       Welcome to Shurel's Roman Numeral Converter       \n" +
                "<^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^>\n" +
                "- Converts roman numerals to decimal\n" +
                "- Enter range I-MMMCMXCIX \n" +
                "- Type [quit] to end the program.\n" +
                "_______________________________________________________\n";

        System.out.println(title);
        String msg = "Please enter a Roman Numeral";
        System.out.println(msg);
        Scanner scanner = new Scanner(System.in);
        while ((input = scanner.nextLine()) != null && !input.toLowerCase().equals("quit")) {
            try {
                System.out.printf("You converted the Roman Numeral %s to the number %d%n", input, toDecimal(input));
            } catch (RomanNumeralException e) {
                System.out.println(e);
            }
        }
        if (input.toLowerCase().equals("quit")) {
            System.out.println("Bye");
            System.exit(0);
        }

    }


    public static String toRomanNumeral(int num) throws RomanNumeralException {
        if (num < 1 || num > 3999)
            throw new RomanNumeralException("Out of Bounds: " + num);
        StringBuilder result = new StringBuilder();
        for (int b : bases) {

            while (num >= b) {
                result.append(Symbol.getBase(b));
                num -= b;
            }

        }

        return result.toString();
    }


}
