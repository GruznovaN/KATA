package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ARCalc {
    static Scanner scanner = new Scanner(System.in);
    static Integer number1, number2;
    static char operation;
    static int result;

    public static void main(String[] args) throws Exception {

        System.out.println("Введите выражение из двух арабских или римских чисел: a+b или a-b или a*b или a/b");

        String userInput = scanner.nextLine();

        char[] under_char = new char[9];
        char[] operation_char = new char[5];

        for (int i = 0; i < userInput.length(); i++) {
            try {
                under_char[i] = userInput.charAt(i);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                throw new ArrayIndexOutOfBoundsException("// т.к нарушено условие ввода, формат ввода a+b*");
            }
            if (under_char[i] == '+') {
                operation = '+';
                operation_char[i] = '+';
            }
            if (under_char[i] == '-') {
                operation= '-';
                operation_char[i] = '-';
            }
            if (under_char[i] == '*') {
                operation = '*';
                operation_char[i] = '*';
            }
            if (under_char[i] == '/') {
                operation = '/';
                operation_char[i] = '/';
            }
        }

        String q =String.valueOf(operation_char);
        String w=q.trim();

        if( w.length()>1)
        {throw new Exception ("//т.к. формат математической операции не удавлетворяет заданию-два операнда и один оператор(*,-,/,+)");
        }

        String under_charString = String.valueOf(under_char);
        String[] numbers = under_charString.split("[+-/*]");
        if (numbers.length<2)
        {throw new Exception ("//т.к строка не является математической операцией");}

        String number01 = numbers[0];
        String number002 = numbers[1];
        String number02 = number002.trim();

        number1 = romanToNumber(number01);
        number2 = romanToNumber(number02);

        if (under_char[0]=='I'|| under_char[0]=='V'||under_char[0]=='X'){

            if (number1 < 0 || number2 < 0) {
                throw new Exception("//введенное число больше 10 или используются разные системы счисления");
            } else {
                result = calculated(number1, number2, operation);
                System.out.println("Результат для римских цифр:");

                try {
                    String resultRoman = convertNumToRoman(result);
                    System.out.println(number01 + " " + operation + " " + number02 + " = " + resultRoman);
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    throw new ArrayIndexOutOfBoundsException("//т.к. в римской системе нет отрицательных чисел");
                }
            } }

        else {

            try {
                number1 = Integer.parseInt(number01);
                number2 = Integer.parseInt(number02);
            }
            catch (NumberFormatException e){
                throw new NumberFormatException("//т.к. используются разные системы счисления");
            }

            if (number1 < 1 || number1 > 10 || number2 < 1 || number2 > 10) {
                result = 0;
                throw new Exception("Введены недопустимые числа, условие: числа должны быть от 1 до 10 включительно");
            } else {
                result = calculated(number1, number2, operation);
                System.out.println("Результат для арабских цифр:");
                System.out.println(number1 + " " + operation + " " + number2 + " = " + result);
            }

        }}


    private static String convertNumToRoman (int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String s = roman[numArabian];
        return s;
    }
    private static int romanToNumber (String roman) {
        if (roman.equals("I")) {
            return 1;
        } else if (roman.equals("II")) {
            return 2;
        } else if (roman.equals("III")) {
            return 3;
        } else if (roman.equals("IV")) {
            return 4;
        } else if (roman.equals("V")) {
            return 5;
        } else if (roman.equals("VI")) {
            return 6;
        } else if (roman.equals("VII")) {
            return 7;
        } else if (roman.equals("VIII")) {
            return 8;
        } else if (roman.equals("IX")) {
            return 9;
        } else if (roman.equals("X")) {
            return 10;
        }
        return -1;
    }

    public static int calculated (int num1, int num2, char op) {
        int result = 0;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                try {
                    result = num1 / num2;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("Допускаются только целочисленные ненулевые параметры");
                    break;
                }
                break;
            default:throw new IllegalArgumentException("Не верный знак операции");
        }
        return result;
    }
}