import java.util.Stack;
import java.util.StringTokenizer;

public class ExpressionCalculator {

    public static double evaluateExpression(String expression) throws Exception {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        Stack<Character> braces = new Stack<>();

        StringTokenizer tokens = new StringTokenizer(expression, "()+-*/^{}[] ", true);

        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim();

            if (token.isEmpty()) continue;

            char c = token.charAt(0);

            if (Character.isDigit(c)) {
                values.push(Double.parseDouble(token));
            } else if (c == '(' || c == '{' || c == '[') {
                operators.push(c);
                braces.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                while (!operators.isEmpty() && operators.peek() != '(' && operators.peek() != '{' && operators.peek() != '[') {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                if (!operators.isEmpty() && (operators.peek() == '(' || operators.peek() == '{' || operators.peek() == '[')) {
                    operators.pop();
                }
                if (!braces.isEmpty()) {
                    char matchingBrace = braces.pop();
                    if (!isMatchingBrace(matchingBrace, c)) {
                        throw new Exception("Erro de sintaxe: " + matchingBrace + " no lugar de " + c);
                    }
                }
            } else if (isOperator(c)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    private static double applyOperation(char operator, double b, double a) throws Exception {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) throw new Exception("Divisão por zero");
                return a / b;
            case '^':
                return Math.pow(a, b);
        }
        return 0;
    }

    private static boolean isMatchingBrace(char open, char close) {
        return (open == '(' && close == ')') || (open == '{' && close == '}') || (open == '[' && close == ']');
    }
}
