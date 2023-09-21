public class PostfixConversion {

    // Converts an infix expression to postfix
    public static String convert(String infix) {
        String postfix = "";
        LinkedListStack<Character> operatorStack = new LinkedListStack<>();

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            if (PostfixEvaluation.isOperand(c)) {
                postfix = postfix + c;
            } else if (PostfixEvaluation.isOperator(c)) {
                while (!operatorStack.isEmpty() && precedence(operatorStack.top()) >= precedence(c)) {

                    postfix = postfix + operatorStack.pop();
                }
                operatorStack.push(c);
            } else if (c == '(') {
                operatorStack.push(c);
            } else if (c == ')') {
                while (!operatorStack.isEmpty() && operatorStack.top() != '(') {
                    postfix = postfix + operatorStack.pop();
                }
                if (operatorStack.isEmpty()) {
                    throw new IllegalArgumentException("Invalid infix expression");
                }
                operatorStack.pop();
            } else {
                System.out.println(c);
                throw new IllegalArgumentException("Invalid character in expression");
            }
        }

        // pop the rest of the operatorStack and add it to the final String
        while (!operatorStack.isEmpty()) {
            char c = operatorStack.pop();
            if (c == '(') {
                throw new IllegalArgumentException("Invalid infix expression");
            }
            postfix = postfix + c;
        }

        return postfix.toString();
    }

    // Determines whether the input char is an operand
    // Only integers 0 through 9 will return true
    public static boolean isOperand(char c) {
        return (c >= '0' && c <= '9');
    }

    // Determined whether the input char is an Operator
    public static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '^');
    }

    // Returns the precedence an operator has in Order Of Operations
    public static int precedence(char c) {
        if (c == '+' || c == '-') {
            return 1;
        } else if (c == '*' || c == '/') {
            return 2;
        } else if (c == '^') {
            return 3;
        } else {
            return 0;
        }
    }
}
