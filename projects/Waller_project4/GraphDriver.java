import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GraphDriver {


    // Main method constructs a graph and runs command loop
    public static void main(String args[]) {
        String fileContents = readFileFromInput(args[0]);
        Graph g = new Graph();
        insertAllVerticesAndEdgesIntoTheGraph(g, fileContents);
        runCommandLoop(g);
    } // end of Main

    // Prints commands
    private static void printCommands() {
        System.out.println("Commands: ");
        System.out.println("1: Find all directed paths between A and B");
        System.out.println("2: Find all directed paths of a given length (edge count) between A and B");
        System.out.println("3: Find shortest path(s) with minimum number of edges between A and B");
        System.out.println("4: Find paths that match a pattern between A and B");
        System.out.println("5: help (displays these commands)");
        System.out.println("6: exit the Program");

    } // printCommands

    //Input args[0] and it will return the contents of the file as a string
    public static String readFileFromInput(String f) {
        File file = new File(f);
        String fileContents = "";
        try {
            Scanner fileReader = new Scanner(file);
            while(fileReader.hasNext()) {
                fileContents = fileContents + fileReader.nextLine();
                if (fileReader.hasNext()) {
                    fileContents = fileContents + "\n";
                }
            }
            fileContents.trim();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } // try catch
        return fileContents;
    } // readFileFromInput


    // Splits up all of the strings to make vertices and edges adding them to the graph
    public static void insertAllVerticesAndEdgesIntoTheGraph(Graph g, String fileContents) {
        // Split input into its lines in the format "sourceLabel edgeLabel destLabel"
        String[] edges = fileContents.split("\n");

        // Seperate the edges into there components to make vertices
        ArrayList<String> vertexLabels = new ArrayList<String>();
        for (int i = 0; i < edges.length; i++) {
            String[] currentLine = edges[i].split(" ");

            if (!vertexLabels.contains(currentLine[0])) {
                vertexLabels.add(currentLine[0]);
            }
            if (!vertexLabels.contains(currentLine[2])) {
                vertexLabels.add(currentLine[2]);
            }
        }

        // Add all vertices to the graph
        for (int i = 0; i < vertexLabels.size(); i++) {
            g.addVertex(vertexLabels.get(i));
        }

        // Add all edges to the graph
        for (int i = 0; i < edges.length; i++) {
            String[] currentLine = edges[i].split(" ");
            g.addEdge(currentLine[0], currentLine[2], currentLine[1]);
        }


    } // insertAllVerticesAndEdgesIntoTheGraph

    // Gets input from the user for choosing a command
    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(">> ");
        return scanner.nextLine().trim();
    }

    // The main command loop
    public static void runCommandLoop(Graph g) {
        // First you need to get the start and end nodes from the user
        boolean invalidNodes = true;
        String start = "";
        String end = "";
        while(invalidNodes) {
            System.out.println("Input start and end node labels below: ");
            Scanner input = new Scanner(System.in);
            System.out.print("Start node: ");
            start = input.nextLine().trim();
            if (g.hasVertex(start)) { // Check each input for validity
                System.out.print("End node: ");
                end = input.nextLine().trim();
                if (g.hasVertex(end)) {
                    invalidNodes = false;
                }
            }
            if (invalidNodes) {
                System.out.println("Invalid Node Labels. Try Again.");
            }
        }

        printCommands();
        // loop through user commands
        while(true) {
            String userInput = getUserInput();
            if (userInput.equals("1")) { // find All Paths between two points
                g.printAllPaths(start, end);
            } else if (userInput.equals("2")) { // Find paths of given length between two points
                Scanner input = new Scanner(System.in);
                System.out.print("Length: ");
                String lengthAsString = input.nextLine().trim();
                int length = Integer.parseInt(lengthAsString);
                g.printPathsOfGivenLength(start, end, length);
            } else if (userInput.equals("3")) { // Find shortest paths between two points
                g.printShortestPaths(start, end);
            } else if (userInput.equals("4")) { // find all paths given a regular expression
                Scanner input = new Scanner(System.in);
                System.out.print("Input the regular expression: ");
                String regex = input.nextLine().trim();
                g.printRegexPaths(regex, start, end);
            } else if (userInput.equals("5")) { // Display Commands
                printCommands();
            } else if (userInput.equals("6")) { // Exit the program
                System.out.println("Exiting...");
                System.exit(0);
            } else {
                System.out.println("Command invalid: try again");
            }
        } // while true
    } // runCommandLoop

} // GraphDriver
