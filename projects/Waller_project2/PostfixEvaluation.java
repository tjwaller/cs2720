import java.lang.Math;

public class PostfixEvaluation {
    private LinkedListStack<Integer> stack;

    public PostfixEvaluation() {
        stack = new LinkedListStack<>();
    }

    /**
     * Evaluates a postfix expression and returns the result.
     * @param expression the postfix expression to evaluate
     * @return the result of the evaluation
     * @throws IllegalArgumentException if the expression is invalid
     */
    public int evaluate(String expression) {
        if (expression.equals("")) {
            throw new IllegalArgumentException("Invalid postfix expression");
        } // if

        String[] tokens = expression.split(" ");
        for (String token : tokens) {

            if (isOperand(token.charAt(0))) {
                // Push operands onto the stack
                stack.push(Integer.parseInt(token));

            } else if (isOperator(token.charAt(0))) {
                // Pop two operands and apply the operator
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid postfix expression");
                }

                int operand2 = stack.pop();
                int operand1 = stack.pop();
                int result;
                if (token.charAt(0) == '+') {
                    result = operand1 + operand2;
                } else if (token.charAt(0) == '-') {
                    result = operand1 - operand2;
                } else if (token.charAt(0) == '*') {
                    result = operand1 * operand2;
                } else if (token.charAt(0) == '/') {
                    result = operand1 / operand2;
                } else if (token.charAt(0) == '^') {
                    result = (int) Math.pow(operand1, operand2);
                } else {
                    throw new IllegalArgumentException("Invalid postfix expression");
                } // if else

                stack.push(result);
            } else {
                throw new IllegalArgumentException("Invalid character in expression");
            } // if else

        }
        // Check that there is only one element left on the stack
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid postfix expression");
        }
        return stack.pop();
    }

    // Returns true if the input char is an operand
    // This means it is not an operator or parenthesis
    public static boolean isOperand(char c) {
        return !isOperator(c) && c != '(' && c != ')';
    }

    // Determines if the char is an Operator
    public static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '^');
    }
}
