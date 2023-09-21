import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BinarySearchTreeDriver {

    public static void main(String[] args) {

        //Read the input file and store its contents as a String
        String fileInput = "File Not Found";
        try {
            File file = new File(args[0]);
            Scanner fileScanner = new Scanner(file);
            fileInput = fileScanner.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        String[] fileInputArray = fileInput.split(" ");
        Scanner listTypeScanner = new Scanner(System.in);


        //Construct each BinarySearchTree
        BinarySearchTree<Integer> intTree = new BinarySearchTree<Integer>();
        BinarySearchTree<Double> doubleTree = new BinarySearchTree<Double>();
        BinarySearchTree<String> stringTree = new BinarySearchTree<String>();



        boolean valid = false;
        System.out.print("Enter list type (i - int, d - double, s - string): ");

        while(!valid) { // makes sure the user gives a valid input
            String listType = listTypeScanner.nextLine();
            listType.trim();

            // Pick which list you are using then input all items into the list
            // After input, call the main application loop
            if(listType.equalsIgnoreCase("i")) { // This is the start of the code that handles INTEGERS
                // insert all inputs from file
                for(int i = 0; i < fileInputArray.length; i++) {
                    intTree.insert(Integer.parseInt(fileInputArray[i]));
                } // for


                // Handle the main command loop for integer Trees
                printCommands();
                //Get input
                Scanner scanner = new Scanner(System.in);
                boolean commandIsValid = false;
                while (!commandIsValid) {
                System.out.print("Enter a Command: ");
                String input = scanner.nextLine().trim().toLowerCase();


                if (input.equals("i")) { // Handle inserts

                    System.out.println("In-order: " + intTree.inOrder());
                    Scanner insertScanner = new Scanner(System.in);
                    System.out.print("Enter a number to insert: ");
                    int numberToInsert = insertScanner.nextInt();
                    intTree.insert(numberToInsert);
                    System.out.println("In-order: " + intTree.inOrder());

                } else if (input.equals("d")) { // Handle deletions

                    System.out.println("In-order: " + intTree.inOrder());
                    Scanner deletionScanner = new Scanner(System.in);
                    System.out.print("Enter a number to delete: ");
                    int numberToDelete = deletionScanner.nextInt();
                    // Handle deletions of nonexistent nodes
                    if (intTree.delete(numberToDelete) == null) {
                        System.out.println("The number is not present in the tree");
                    } else {
                        intTree.delete(numberToDelete);
                        System.out.println("In-order: " + intTree.inOrder());
                    }

                } else if (input.equals("p")) { // Print the tree in order

                    System.out.println("In-order: " + intTree.inOrder());

                } else if (input.equals("s")) { // Handle searches

                    System.out.println("In-order: " + intTree.inOrder());
                    Scanner searchScanner = new Scanner(System.in);
                    System.out.print("Enter a number to search: ");
                    int numberToSearch = searchScanner.nextInt();
                    if (intTree.search(numberToSearch)) {
                        System.out.println("Item is present in the tree");
                    } else {
                        System.out.println("Item is not present in the tree");
                    } // if else

                } else if (input.equals("l")) { // Count leaf nodes

                    System.out.println("The number of leaf nodes are " + intTree.getNumLeafNodes());

                } else if (input.equals("sp")) { // find single parents

                    System.out.print("Single Parents: ");
                    intTree.getSingleParent();
                    System.out.println();

                } else if (input.equals("c")) { // Find cousins
                    System.out.println("In-order: " + intTree.inOrder());
                    Scanner cousinsScanner = new Scanner(System.in);
                    System.out.print("Enter a number: ");
                    int number = cousinsScanner.nextInt();
                    System.out.print(number + " cousins: ");
                    intTree.getCousins(number);
                    System.out.println();
                    //System.out.println(number + " cousins: " + intTree.getCousins(number));

                } else if (input.equals("q")) { // quit the program
                    System.exit(0);
                } else {
                    System.out.println("Invalid Command. Try again");
                } // end of else if loop for inputting commands
            } // end of command loop for integers




                valid = true;
            } else if (listType.equalsIgnoreCase("d")) { // This is the start of the code that handles DOUBLES

                // insert all inputs from file
                for(int i = 0; i < fileInputArray.length; i++) {
                    doubleTree.insert(Double.parseDouble(fileInputArray[i]));
                } // for


                // Handle the main command loop for integer Trees
                printCommands();
                //Get input
                Scanner scanner = new Scanner(System.in);
                boolean commandIsValid = false;
                while (!commandIsValid) {
                System.out.print("Enter a Command: ");
                String input = scanner.nextLine().trim().toLowerCase();


                if (input.equals("i")) { // Handle inserts

                    System.out.println("In-order: " + doubleTree.inOrder());
                    Scanner insertScanner = new Scanner(System.in);
                    System.out.print("Enter a number to insert: ");
                    double numberToInsert = insertScanner.nextDouble();
                    doubleTree.insert(numberToInsert);
                    System.out.println("In-order: " + doubleTree.inOrder());

                } else if (input.equals("d")) { // Handle deletions

                    System.out.println("In-order: " + doubleTree.inOrder());
                    Scanner deletionScanner = new Scanner(System.in);
                    System.out.print("Enter a number to delete: ");
                    double numberToDelete = deletionScanner.nextDouble();
                    // Handle deletions of nonexistent nodes
                    if (doubleTree.delete(numberToDelete) == null) {
                        System.out.println("The number is not present in the tree");
                    } else {
                        doubleTree.delete(numberToDelete);
                        System.out.println("In-order: " + doubleTree.inOrder());
                    }

                } else if (input.equals("p")) { // Print the tree in order

                    System.out.println("In-order: " + doubleTree.inOrder());

                } else if (input.equals("s")) { // Handle searches

                    System.out.println("In-order: " + doubleTree.inOrder());
                    Scanner searchScanner = new Scanner(System.in);
                    System.out.print("Enter a number to search: ");
                    double numberToSearch = searchScanner.nextDouble();
                    if (doubleTree.search(numberToSearch)) {
                        System.out.println("Item is present in the tree");
                    } else {
                        System.out.println("Item is not present in the tree");
                    } // if else

                } else if (input.equals("l")) { // Count leaf nodes

                    System.out.println("The number of leaf nodes are " + doubleTree.getNumLeafNodes());

                } else if (input.equals("sp")) { // find single parents

                    System.out.print("Single Parents: ");
                    doubleTree.getSingleParent();
                    System.out.println();

                } else if (input.equals("c")) { // Find cousins
                    System.out.println("In-order: " + doubleTree.inOrder());
                    Scanner cousinsScanner = new Scanner(System.in);
                    System.out.print("Enter a number: ");
                    double number = cousinsScanner.nextDouble();
                    System.out.print(number + " cousins: ");
                    doubleTree.getCousins(number);
                    System.out.println();

                } else if (input.equals("q")) { // quit the program
                    System.exit(0);
                } else {
                    System.out.println("Invalid Command. Try again");
                } // end of else if loop for inputting commands
            } // end of command loop for integers

                valid = true;
            } else if (listType.equalsIgnoreCase("s")) { // This is the start of the code that handles STRINGS


                // insert all inputs from file
                for(int i = 0; i < fileInputArray.length; i++) {
                    stringTree.insert(fileInputArray[i]);
                } // for


                // Handle the main command loop for integer Trees
                printCommands();
                //Get input
                Scanner scanner = new Scanner(System.in);
                boolean commandIsValid = false;
                while (!commandIsValid) {
                System.out.print("Enter a Command: ");
                String input = scanner.nextLine().trim().toLowerCase();


                if (input.equals("i")) { // Handle inserts

                    System.out.println("In-order: " + stringTree.inOrder());
                    Scanner insertScanner = new Scanner(System.in);
                    System.out.print("Enter a string to insert: ");
                    String stringToInsert = insertScanner.nextLine().trim();
                    stringTree.insert(stringToInsert);
                    System.out.println("In-order: " + stringTree.inOrder());

                } else if (input.equals("d")) { // Handle deletions

                    System.out.println("In-order: " + stringTree.inOrder());
                    Scanner deletionScanner = new Scanner(System.in);
                    System.out.print("Enter a string to delete: ");
                    String stringToDelete = deletionScanner.nextLine().trim();
                    // Handle deletions of nonexistent nodes
                    if (stringTree.delete(stringToDelete) == null) {
                        System.out.println("The string is not present in the tree");
                    } else {
                        stringTree.delete(stringToDelete);
                        System.out.println("In-order: " + stringTree.inOrder());
                    }

                } else if (input.equals("p")) { // Print the tree in order

                    System.out.println("In-order: " + stringTree.inOrder());

                } else if (input.equals("s")) { // Handle searches

                    System.out.println("In-order: " + stringTree.inOrder());
                    Scanner searchScanner = new Scanner(System.in);
                    System.out.print("Enter a string to search: ");
                    String stringToSearch = searchScanner.nextLine().trim();
                    if (stringTree.search(stringToSearch)) {
                        System.out.println("Item is present in the tree");
                    } else {
                        System.out.println("Item is not present in the tree");
                    } // if else

                } else if (input.equals("l")) { // Count leaf nodes

                    System.out.println("The number of leaf nodes are " + stringTree.getNumLeafNodes());

                } else if (input.equals("sp")) { // find single parents

                    System.out.print("Single Parents: ");
                    stringTree.getSingleParent();
                    System.out.println();

                } else if (input.equals("c")) { // Find cousins
                    System.out.println("In-order: " + stringTree.inOrder());
                    Scanner cousinsScanner = new Scanner(System.in);
                    System.out.print("Enter a string: ");
                    String str = cousinsScanner.nextLine().trim();
                    System.out.print(str + " cousins: ");
                    stringTree.getCousins(str);
                    System.out.println();

                } else if (input.equals("q")) { // quit the program
                    System.exit(0);
                } else {
                    System.out.println("Invalid Command. Try again");
                } // end of else if loop for inputting commands
            } // end of command loop for integers

                valid = true;
            } else { // Handles invalid commands
                valid = false;
                System.out.println("Invalid Input: Try again \n");
            }

        } // End of while loop. tree is initialized


    } // end of main

    // prints the starting user instructions
    public static void printCommands() {
        System.out.println("Commands:");
        System.out.println("(i) - Insert Item");
        System.out.println("(d) - Delete Item");
        System.out.println("(p) - Print Tree");
        System.out.println("(s) - Search Item");
        System.out.println("(l) - Count Leaf Nodes");
        System.out.println("(sp) - Find Single Parents");
        System.out.println("(c) - Find Cousins");
        System.out.println("(q) - Quit Program");
    } //printCommands


} // end of BinarySearchTreeDriver
