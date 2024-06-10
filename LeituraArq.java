import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class LeituraArq {
    public static void main(String[] args) {
        String fileName = "expressoes3.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String expression;
            int count = 1;

            while ((expression = br.readLine()) != null) {
                try {
                    System.out.println(count);
                    System.out.println("Expressão: " + expression);
                    double result = ExpressionCalculator.evaluateExpression(expression);
                    System.out.println("Resultado: " + result);
                    System.out.println("Tamanho máximo da pilha: " + getMaxStackSize(expression));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getMaxStackSize(String expression) {
        Stack stack = new Stack();
        StringTokenizer tokens = new StringTokenizer(expression, "()+-*/^{}[] ", true);
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim();
            if (token.isEmpty()) continue;
            char c = token.charAt(0);
            if (c == '(' || c == '{' || c == '[' || Character.isDigit(c)) {
                stack.push(1);
            } else if (c == ')' || c == '}' || c == ']') {
                stack.pop();
            }
        }
        return stack.getMaxSize();
    }
}
