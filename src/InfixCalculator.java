import java.io.*;
import java.util.*;

/**
 * A simple infix expression calculator that supports basic arithmetic operations.
 * It evaluates expressions with +, -, *, /, and % operators and allows reading expressions from a file.
 * Uses ArrayDeque instead of Stack for better performance.
 */
public class InfixCalculator {

    /**
     * Determines the precedence of an operator.
     * @param op The operator character.
     * @return The precedence value (higher value means higher precedence).
     */
    private static int precedence(char op) {
        return (op == '+' || op == '-') ? 1 : (op == '*' || op == '/' || op == '%') ? 2 : -1;
    }

    /**
     * Performs the given arithmetic operation.
     * @param a The first operand.
     * @param b The second operand.
     * @param op The operator.
     * @return The result of applying the operator to the operands.
     * @throws ArithmeticException if division or modulo by zero occurs.
     */
    private static double applyOp(double a, double b, char op) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) throw new ArithmeticException("Division by zero");
                yield a / b;
            }
            case '%' -> {
                if (b == 0) throw new ArithmeticException("Modulo by zero");
                yield a % b;
            }
            default -> throw new IllegalArgumentException("Invalid operator: " + op);
        };
    }

    /**
     * Evaluates an infix arithmetic expression.
     * @param expr The infix expression to evaluate.
     * @return The evaluated result as a double.
     * @throws IllegalArgumentException If the input is null or empty.
     */
    public static double evaluate(String expr) {
        if (expr == null || expr.trim().isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty");
        }

        ArrayDeque<Double> values = new ArrayDeque<>();
        ArrayDeque<Character> operators = new ArrayDeque<>();
        int i = 0, n = expr.length();

        while (i < n) {
            char c = expr.charAt(i);

            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            if (Character.isDigit(c)) {  // Read full number
                double num = 0;
                while (i < n && Character.isDigit(expr.charAt(i))) {
                    num = num * 10 + (expr.charAt(i++) - '0');
                }
                values.push(num);
                continue;
            }

            if ("+-*/%".indexOf(c) != -1) {  // Operator handling
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                    double b = values.pop(), a = values.pop();
                    values.push(applyOp(a, b, operators.pop()));
                }
                operators.push(c);
            } else {
                throw new IllegalArgumentException("Invalid character in expression: " + c);
            }
            i++;
        }

        while (!operators.isEmpty()) {  // Apply remaining operations
            double b = values.pop(), a = values.pop();
            values.push(applyOp(a, b, operators.pop()));
        }

        return values.pop();
    }

    /**
     * Reads and evaluates expressions from a file.
     * @param filePath The path to the input file.
     */
    public static void evaluateFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    System.out.println("Expression: " + line + " = " + evaluate(line));
                } catch (Exception e) {
                    System.out.println("Error evaluating \"" + line + "\": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}