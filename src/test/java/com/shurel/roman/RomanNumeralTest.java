/*
 * @author Shurel Reynolds
*/
package com.shurel.roman;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RomanNumeralTest {
    RomanNumeral util;

    @Before
    public void setup() {
        util = new RomanNumeral();
    }


    @Test
    public void checkIsValidRomanNumeralFormat() {
        boolean expected = true;
        try {
            assertEquals(expected, util.isValidRomanNumeral("XII"));
        } catch (RomanNumeralException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void checkValidRomanNumeralIncorrectOrder() {
        boolean expected = false;
        try {
            assertEquals(expected, util.isValidRomanNumeral("IXI"));
        } catch (RomanNumeralException e) {
            e.printStackTrace();
        }

    }

    @Test(expected = RomanNumeralException.class)
    public void checkIsEmptyOrNullRomanNumeral() throws RomanNumeralException {

        util.isValidRomanNumeral(null);
        util.isValidRomanNumeral("");

    }

    @Test
    public void checkCapitalRomanNumeral() {
        boolean expected = true;
        try {
            assertEquals(expected, util.isValidRomanNumeral("MMXXII"));
        } catch (RomanNumeralException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void checkLowerCaseRomanNumeral() {
        boolean expected = true;
        try {
            assertEquals(expected, util.isValidRomanNumeral("xliii"));
        } catch (RomanNumeralException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void checkMixedCaseRomanNumeral() {
        boolean expected = true;
        try {
            assertEquals(expected, util.isValidRomanNumeral("XliIi"));
        } catch (RomanNumeralException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkNumbersInRomanNumeral() {
        boolean expected = false;
        try {
            assertEquals(expected, util.isValidRomanNumeral("2021"));
        } catch (RomanNumeralException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkRomanNumeralToDecimal() {
        int expected = 1534;
        try {
            assertEquals(expected, util.toDecimal("MDXXXIV"));
        } catch (RomanNumeralException e) {
            e.printStackTrace();
        }
    }

    @Test

    public void checkArrayOfRomanNumerals() {
        String[] numTest = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII",
                "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX",
                "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII",
                "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII",
                "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI",
                "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV",
                "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV",
                "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII",
                "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII",
                "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI",
                "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C", "CLI",
                "CDLXIV", "DCCC", "CM", "M", "MCD", "MMMCMXCIX"};

        boolean expected = true;
        try {
            for (int i = 0; i < numTest.length; i++)
                assertEquals(expected, util.isValidRomanNumeral(numTest[i]));
        } catch (RomanNumeralException e) {
            e.printStackTrace();
        }

    }

    @Test(expected = RomanNumeralException.class)
    public void checkDecimal0() throws RomanNumeralException {

        util.toRomanNumeral(0);

    }

    @Test(expected = RomanNumeralException.class)
    public void checkIncorrectDecimalRange() throws RomanNumeralException {

        util.toRomanNumeral(4000);

    }


}
