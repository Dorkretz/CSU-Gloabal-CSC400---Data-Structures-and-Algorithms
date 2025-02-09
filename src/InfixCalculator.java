import java.io.*;
import java.util.*;

public class InfixCalculator {

    // Returns operator precedence
    private static int precedence(char op) {
        return (op == '+' || op == '-') ? 1 : (op == '*' || op == '/' || op == '%') ? 2 : -1;
    }

    // Applies arithmetic operation
    private static double applyOp(double a, double b, char op) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) throw new ArithmeticException("Division by zero");
                yield a / b; // Ensure floating-point division
            }
            case '%' -> {
                if (b == 0) throw new ArithmeticException("Modulo by zero");
                yield a % b;
            }
            default -> throw new IllegalArgumentException("Invalid operator");
        };
    }

    // Evaluates an infix expression
    public static double evaluate(String expr) {
        Stack<Double> values = new Stack<>();  // Change Integer to Double
        Stack<Character> operators = new Stack<>();
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
                values.push(num);  // Now pushing a Double
                continue;
            }

            if ("+-*/%".indexOf(c) != -1) {  // Operator handling
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                    double b = values.pop(), a = values.pop();
                    values.push(applyOp(a, b, operators.pop()));  // No more type mismatch
                }
                operators.push(c);
            } else {
                throw new IllegalArgumentException("Invalid character: " + c);
            }
            i++;
        }

        while (!operators.isEmpty()) {  // Apply remaining operations
            double b = values.pop(), a = values.pop();
            values.push(applyOp(a, b, operators.pop()));
        }

        return values.pop();
    }

    // Reads and evaluates expressions from a file
    public static void evaluateFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    System.out.println("Expression: " + line + " = " + evaluate(line));
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    // Main method for user input or file evaluation
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an infix expression (or type 'exit' to quit, 'file' to read from a file):");

        while (true) {
            System.out.print("> ");  // Command-line prompt
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            } else if (input.equalsIgnoreCase("file")) {
                System.out.print("Enter file path: ");
                evaluateFromFile(scanner.nextLine());
            } else {
                try {
                    System.out.println("Result: " + evaluate(input));
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        scanner.close();
    }}
