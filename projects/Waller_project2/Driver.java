import java.util.InputMismatchException;
import java.util.Scanner;
// This class handles the command line interface for the application
public class Driver {

    // Takes user input about which part of the program to run
    public static void main(String args[]) {
        // variables
        int userCommand;
        boolean running = true;
        boolean displayUserPrompt = true;

        // main application loop
        while (running) {

            // take user input
            Scanner scanner = new Scanner(System.in);
            if (displayUserPrompt) {
                System.out.println("Enter Command:\n(1) postfix conversion");
                System.out.println("(2) postfix evaluation\n(3) quit");
            } // if
            System.out.print(">> ");
            try {
                userCommand = scanner.nextInt();

                // decide which program to run
                if (userCommand == 3) {
                    running = false;
                    System.out.println("Exiting the program...");
                    System.exit(0);
                } else if (userCommand == 1) {
                    Scanner infixExpression = new Scanner(System.in);
                    PostfixConversion converter = new PostfixConversion();
                    System.out.println("Enter infix expression to convert: ");
                    System.out.println("\tSpaces not needed. Use only single digit numbers or variables");
                    System.out.println("\tExamples: (A*(B+C)), (3*4(8+9))");
                    System.out.print(">> ");
                    String expression = infixExpression.nextLine();
                    System.out.println(converter.convert(expression));

                    displayUserPrompt = true;
                } else if (userCommand == 2) {
                    Scanner postfixExpression = new Scanner(System.in);
                    PostfixEvaluation evaluator = new PostfixEvaluation();
                    System.out.println("Enter postfix expression below: ");
                    System.out.println("\tMust use spaces. Example: 6 2 / 5 +");
                    System.out.print(">> ");
                    String expression = postfixExpression.nextLine();
                    try {
                        int answer = evaluator.evaluate(expression);
                        System.out.println(answer);
                        displayUserPrompt = true;
                    } catch (IllegalArgumentException e)  {
                        System.out.println("Invalid postfix expression. re-enter command.");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Must enter an expresion. re-enter command.");
                    } catch (ArithmeticException e) {
                        System.out.println("Cannot divide by zero. re-enter expression.");
                    } // try catch

                } else {
                    System.out.println("invalid Command. Try again.");
                    displayUserPrompt = false;
                } // if else

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again");
            } // try catch


        } // while
    } // main

} // Driver
