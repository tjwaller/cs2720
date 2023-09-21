import java.io.*;
import java.util.Scanner;
/**
 * This class is the main class and runs the commmand line application for linked lists.
 */
public class LinkedListDriver {
    private char INSERT = 'i';
    private char DELETE = 'd';
    private char SEARCH = 's';
    private char GET_NEXT = 'n';
    private char RESET_LIST = 'r';
    private char DEL_ALT = 'a';
    private char MERGE = 'm';
    private char INTERSECTION = 'i';
    private char PRINT_ALL = 'p';
    private char LENGTH = 'l';
    private char QUIT = 'q';
    private static char currentCommand = ' ';
    private static SortedLinkedList currentList;

    /**
     * This method is the main method that runs the program
     * @param args
     */
    public static void main(String args[]) {
        // read file contents and store them in a string
        String str = "";
        try {
            String fileContents = new Scanner(new File(args[0])).useDelimiter("\\Z").next();
            str = fileContents;
            //System.out.println(fileContents);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } // catch

        // create a new SortedLinkedList
        currentList = new SortedLinkedList(str);

        printCommands();

        // Main program loop
        int whileLoopCondition = 1;
        while (whileLoopCondition == 1) {
            getUserCommand();
            runCommand();
        } // while (Main Loop)

    } // main

    /**
     * This method prints the starting commands for the user to run the application.
     */
    private static void printCommands() {
        System.out.println("Commands:");
        System.out.println("(i) - Insert value");
        System.out.println("(d) - Delete value");
        System.out.println("(s) - Search value");
        System.out.println("(n) - Print next iterator value");
        System.out.println("(r) - Reset iterator");
        System.out.println("(a) - Delete alternate nodes");
        System.out.println("(m) - Merge lists");
        System.out.println("(t) - Find intersection");
        System.out.println("(p) - Print list");
        System.out.println("(l) - print length");
        System.out.println("(q) - Quit program\n");

    } // printCommands

    /**
     * This method gets and returns a command from the user.
     * @return the command
     */
    private static void getUserCommand() {
        Scanner scanner = new Scanner(System.in); // initialize scanner
        char returnValue = ' ';
        boolean isInvalid = true;
        while (isInvalid) {

            // get user input
            System.out.print("Enter a command: ");
            String input = scanner.nextLine();
            //System.out.println();

            // just read the first character
            input.trim();
            char charInput = input.charAt(0);

            //check if the command is valid
            if (commandIsValid(charInput)) {
                isInvalid = false;
                returnValue = charInput;
            } else {
                System.out.println("Invalid command, try again!");
            }
        } // while

        //scanner.close();
        currentCommand = returnValue;

    } // getUserInput

    /**
     * Tests for invalid inputs.
     * @param c the first char of the input
     * @return true or false based on validity
     */
    private static boolean commandIsValid(char c) {
        boolean test1 = false;
        boolean test2 = false;
        // run two tests to see if c is a valid char
        if (c == 'i' || c == 'd' || c == 's' || c == 'n' || c == 'r' || c == 'a') {
            test1 = true;
        } // if
        if (c == 'm' || c == 't' || c == 'p' || c == 'l' || c == 'q') {
            test2 = true;
        } // if
        if (test1 || test2) {
            return true;
        } else {
            return false;
        }
    } //commandIsValid

    // add commands here after you write them
    private static void runCommand() {
        if (currentCommand == 'i') {
            insertValue();
        } else if (currentCommand == 'd') {
            deleteValue();
        } else if (currentCommand == 's') {
            searchValue();
        } else if (currentCommand == 'n') {
            printNext();
        } else if (currentCommand == 'r') {
            reset();
        } else if (currentCommand == 'a') {
            deleteAlternateNodes();
        } else if (currentCommand == 'm') {
            mergeLists();
        } else if (currentCommand == 't') {
            findIntersection();
        } else if (currentCommand == 'p') {
            printList();
        } else if (currentCommand == 'l') {
            printLength();
        } else if (currentCommand == 'q') {
            quit();
        } else {
            System.out.println("Something went wrong, the command doesn't seem to be valid");
        } // if
    } // runCommand


    private static void insertValue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number to insert: ");
        int insertNum = scanner.nextInt();
        String original = currentList.toString();
        ItemType item = new ItemType(insertNum);


        currentList.insertItem(item);
        System.out.println("Original list : " + original);
        if (currentList.insertItem(item) == -1) {
            System.out.println("Item already exists");
        } // if
        System.out.println("New list : " + currentList.toString());

    } // insertValue

    private static void deleteValue() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number to delete: ");
        int intToDelete = scanner.nextInt();
        ItemType itemToDelete = new ItemType(intToDelete);
        currentList.deleteItem(itemToDelete);
    } // deleteValue

    private static void searchValue() {
        Scanner scanner = new Scanner(System.in);
        String original = currentList.toString();
        System.out.print("Enter a number to search: ");
        int numberToSearch = scanner.nextInt();
        ItemType item = new ItemType(numberToSearch);
        System.out.println("Original list : " + original);
        int index = currentList.searchItem(item);
        if (index == -1) { // the item is not present
            System.out.println("The item is not present in the list");
        } else if (currentList.getHead() == null) { // the list is empty

        } else { // the item is present

        } // if else
            System.out.println("The item is present at index " + index + 1);


    } // searchValue

    private static void printNext() {
        if (currentList.getNextItem().getValue() != -1) {
            System.out.println(currentList.getNextItem().getValue());
        } // if
    } // printNext

    private static void reset() {
        currentList.resetList();
        System.out.println("Iterator has been reset");
    } // reset

    private static void deleteAlternateNodes() {
        currentList.deleteAlternateNodes();
    } // deleteAlternateNodes

    private static void mergeLists() {
        currentList.mergeList();
    } // mergeLists

    private static void findIntersection() {
        currentList.intersection();
    } // findIntersection

    private static void printList() {
        System.out.print("The list is: ");
        System.out.println(currentList.toString());
    } // printlist

    private static void printLength() {
        System.out.print("The length of the list is ");
        System.out.println("" + currentList.getLength());
    } // printLength

    private static void quit() {
        System.out.println("Exiting the Program...");
        System.exit(0);
    } // quit



}
