import java.util.*;
import java.util.regex.Pattern;

public class Graph {
    private Vertex head;

    // Graph COnstructor
    public Graph() {
        head = null;
    }


    // Adds a vertex to the graph by prepending it to the linked list
    public void addVertex(String label) {
        Vertex newNode = new Vertex(label);
        newNode.next = head;
        head = newNode;
    }

    //Method that adds an edge using the format in the input file
    public void addEdge(String sourceLabel, String destLabel, String edgeLabel) {
        //Start at the top of the graph
        Vertex current = head;
        // loop until you find the veretx that contains the right label
        while (current != null && !current.label.equals(sourceLabel)) {
            current = current.next;
        }
        // add the edge if you actually found the right source vertex
        if (current != null) {
            current.addEdge(destLabel, edgeLabel);
        }
    }

    // Check if a vertex exists in the graph
    public boolean hasVertex(String label) {
        Vertex current = head;
        while (current != null && !current.label.equals(label)) {
            current = current.next;
        }
        return current != null;
    }

    // Check if an edge exists between two vertices
    public boolean hasEdge(String sourceLabel, String destLabel) {
        Vertex current = head;
        while (current != null && !current.label.equals(sourceLabel)) {
            current = current.next;
        }
        if (current != null) {
            return current.hasEdge(destLabel);
        }
        return false;
    }

    // Gets all edges from a specified vertex
    public List<Edge> edgesFrom(String label) {
        Vertex current = head;
        // Find vertex
        while (current != null && !current.label.equals(label)) {
            current = current.next;
        }
        // Return the list of edges if it exists
        if (current != null) {
            return current.edges;
        }
        return new ArrayList<>();
    }

    // Prints the graph. Used for debugging
    public void printGraph() {
        Vertex current = head;
        // Print until you reach the end
        while (current != null) {
            System.out.print(current.label + " -> ");
            // Loop though the edges of the current vertex, get their labels, construct a string,
            // And print it.
            for (Edge edge : current.edges) {
                System.out.print(edge.destLabel + "(" + edge.label + ")" + " -> ");
            }
            System.out.println();
            current = current.next;
        }
    }

    // Get the number of vertices in the graph
    public int getNumberOfVertices() {
        Vertex currentVertex = head;
        if (head == null) {
            return 0;
        } // Special Case

        int count = 1;
        while (currentVertex.next != null) {
            count++;
            currentVertex = currentVertex.next;
        } // while
        return count;
    } // getNumberOfVertices



    // This is a recursive algorithm for DFS. It was used as the backbone of later algorithms
    // It is however never used in this project
    public void depthFirstSearch(String start) {
        Vertex currentVertex = findVertex(start); // Get the specified vertex
        ArrayList<Vertex> visited = new ArrayList<>(); // Make visited list
        depthFirstSearch(currentVertex, visited); // Search through the graph
        System.out.println();
    }

    // This is called by the previous DFS alg, and by itself recursively
    private void depthFirstSearch(Vertex current, ArrayList<Vertex> visited) {
        System.out.print(current.label + " ");

        visited.add(current);
        //Edge[] currentEdges = new Edge[current.edges.size()];
        // Absolute monstrosity of a line to create an array from the list
        Edge[] currentEdges = current.edges.toArray(new Edge[current.edges.size()]);
        for (int i = 0; i < currentEdges.length; i++) {

            // Find the next vertex and continue searching if it has not been visited and not null
            Vertex next = findVertex(currentEdges[i].destLabel);
            if (next != null && !visited.contains(next)) {
                depthFirstSearch(next, visited);
            }
        }
    }


    // Finds a vertex given its label
    public Vertex findVertex(String label) {
        // loop through all of the vertices and return the one with the matching label
        // Return null if none match
        Vertex currentVertex = head;
        for (int i = 0; i < getNumberOfVertices(); i++) {

            if (currentVertex.label.equals(label)) {
                return currentVertex;
            }
            currentVertex = currentVertex.next;

        }
        return null;
    } // Find Vertex




    // BELOW ARE THE MAIN METHODS USED BY GraphDriver
    public void printAllPaths(String startLabel, String endLabel) {
        List<List<Vertex>> listOfVertexPaths = findAllPaths(startLabel, endLabel); // find all paths
        List<List<String>> pathsAsStrings = convertPathsToStrings(listOfVertexPaths); // convert them to strings
        printPathsAsString(pathsAsStrings); // print them
    }

    public void printShortestPaths(String start, String end) {
        List<List<Vertex>> shortestPaths = findShortestPaths(start, end); //Find shortest paths
        List<List<String>> pathsAsStrings = convertPathsToStrings(shortestPaths); // convert them all to strings
        printPathsAsString(pathsAsStrings); // print them

    }

    // I made this on accident, but I'm going to leave it in now in case I want to expand this project later
    public void printLongestPaths(String start, String end) {
        List<List<Vertex>> longestPaths = findLongestPaths(start, end);
        List<List<String>> pathsAsStrings = convertPathsToStrings(longestPaths);
        printPathsAsString(pathsAsStrings);
    }

    public void printPathsOfGivenLength(String start, String end, int length) {
        List<List<Vertex>> paths = findPathsOfGivenLength(start, end, length);
        List<List<String>> pathsAsStrings = convertPathsToStrings(paths);
        printPathsAsString(pathsAsStrings);
    }

    public void printRegexPaths(String regex, String start, String end) {
        List<List<Vertex>> regexPaths = findAllPathsGivenARegularExpression(regex, start, end);
        List<List<String>> stringPaths = convertPathsToStrings(regexPaths);
        printPathsAsString(stringPaths);
    }

    // Finds all paths between two points and returns lists of paths. "The paths are lists of vertices"
    // So that means it must return a list of lists of vertices.
    public List<List<Vertex>> findAllPaths(String startLabel, String endLabel) {
        // Initialize variables
        List<List<Vertex>> allPaths = new ArrayList<>();
        Vertex start = findVertex(startLabel);
        Vertex end = findVertex(endLabel);
        // Call the recursive findAllPaths() method if start and end exist
        if (start != null && end != null) {
            List<Vertex> currentPath = new ArrayList<>();
            currentPath.add(start);
            findAllPaths(start, end, currentPath, allPaths);
        }
        return allPaths;
    }

    //
    private void findAllPaths(Vertex current, Vertex end, List<Vertex> currentPath, List<List<Vertex>> allPaths) {
        if (current.equals(end)) { // Base Case
            allPaths.add(new ArrayList<>(currentPath));
        } else { // If you havent reached the end yet
            // Loop through all of the edges and search for the destination Vertex among them
            for (Edge edge : current.edges) {
                Vertex next = findVertex(edge.destLabel);
                if (next != null && !currentPath.contains(next)) {
                    currentPath.add(next); // add the next vertex to the current path (They may be removed if they don't lead to the target)
                    // Repeat the process
                    findAllPaths(next, end, currentPath, allPaths);
                    currentPath.remove(currentPath.size() - 1); // Remove the edges that didnt lead to the end vertex
                }
            }
        }
    }


    // Input a path as a list of Vertices and this method returns a list of string paths instead
    // Input Vertex1 Vertex2, Vertex2 Vertex3
    // Output Vertex1 -edge-> Vertex2, vertex2 -edge-> vertex3
    public List<List<String>> convertPathsToStrings(List<List<Vertex>> paths) {
        List<List<String>> pathsAsStrings = new ArrayList<>();
        for (List<Vertex> path : paths) {
            List<String> pathAsStrings = new ArrayList<>();
            for (int i = 0; i < path.size() - 1; i++) {
                Vertex current = path.get(i);
                Vertex next = path.get(i+1);
                for (Edge edge : current.edges) {
                    if (edge.destLabel.equals(next.label)) {
                        pathAsStrings.add(current.label + " -" + edge.label + "-> " + next.label);
                    }
                }
            }
            pathsAsStrings.add(pathAsStrings);
        }
        return pathsAsStrings;
    }

    // This method is very similar to "convertPathsToStrings"
    // Returns a string of edge paths to later match with a regex
    public String findEdgesFromVertexPath(List<Vertex> path) {
        String edgePath = "";
        // loops through each pair of vertices
        for (int i = 0; i < path.size() - 1; i++) {
            Vertex current = path.get(i);
            Vertex next = path.get(i+1);
            // find the edge label between the two vertices
            for (Edge edge : current.edges) {
                if (edge.destLabel.equals(next.label)) {
                    edgePath = edgePath + edge.label;
                }
            }
        }

        return edgePath;

    }


    // Prints the paths "You must input the string versions of the paths"
    public void printPathsAsString(List<List<String>> pathsAsStrings) {
        if (pathsAsStrings.isEmpty()) {
            System.out.println("\tNo paths found.");
        } else {
            System.out.println("\tPaths found:");

            for (List<String> path : pathsAsStrings) {
                int edgeNumber = 1; // Set a counter for later
                for (String edge : path) {
                    if (edgeNumber == 1) { // this if statement insures that it doesn't print unnessesary Vertex labels
                        System.out.print(edge);
                    } else {
                        //System.out.print(edge.substring(0, edge.indexOf(" ")));
                        System.out.print(edge.trim().substring(edge.indexOf(" ")));
                    }
                    edgeNumber++;
                }
                System.out.println();
            }

        }
    }
    // END OF PRINT ALL PATHS METHODS


    // Finds the shortest paths between two points
    public List<List<Vertex>> findShortestPaths(String start, String end) {
        List<List<Vertex>> allPaths = findAllPaths(start, end);
        // Initialize shortest path length to a really big number
        int shortestPathLength = Integer.MAX_VALUE;
        // Create a list to store the shortest paths
        List<List<Vertex>> shortestPaths = new ArrayList<>();

        // Iterate over all paths and find the shortest one(s)
        for (List<Vertex> path : allPaths) {
            // Compute the length of the current path
            int pathLength = path.size() - 1;
            // Check if the current path is shorter than the shortest path(s) found so far
            if (pathLength < shortestPathLength) {
                shortestPathLength = pathLength;
                shortestPaths.clear();
                shortestPaths.add(path);
            } else if (pathLength == shortestPathLength) {
                shortestPaths.add(path);
            }
        }

        return shortestPaths;
    }

    // The exact same code as above but with minor changes to find longest paths
    public List<List<Vertex>> findLongestPaths(String start, String end) {
        List<List<Vertex>> allPaths = findAllPaths(start, end);
        // Initialize shortest path length to infinity
        int longestPathLength = Integer.MIN_VALUE;
        // Create a list to store the shortest paths
        List<List<Vertex>> longestPaths = new ArrayList<>();

        // Iterate over all paths and find the shortest one(s)
        for (List<Vertex> path : allPaths) {
            // Compute the length of the current path
            int pathLength = path.size() - 1;
            // Check if the current path is shorter than the shortest path(s) found so far
            if (pathLength > longestPathLength) {
                longestPathLength = pathLength;
                longestPaths.clear();
                longestPaths.add(path);
            } else if (pathLength == longestPathLength) {
                longestPaths.add(path);
            }
        }

        return longestPaths;
    }

    // Find all of the paths of a given length
    public List<List<Vertex>> findPathsOfGivenLength(String start, String end, int length) {
        List<List<Vertex>> allPaths = findAllPaths(start, end);
        List<List<Vertex>> paths = new ArrayList<>();

        // Iterate over all paths and find the shortest one(s)
        for (List<Vertex> path : allPaths) {
            // Compute the length of the current path
            int pathLength = path.size() - 1;
            // Add path if it matches the target length
            if (pathLength == length) {
                paths.add(path);
            }
        }

        return paths;
    }

    public List<List<Vertex>> findAllPathsGivenARegularExpression(String regex, String start, String end) {
        // Somehow we will need to decode the regex
        Pattern pattern = Pattern.compile(regex);
        // First find all of the paths between the two points
        List<List<Vertex>> allPaths = findAllPaths(start, end);
        List<Vertex> regexPath = new ArrayList<Vertex>();
        List<List<Vertex>> regexPaths = new ArrayList<>();
        // Take the list of all paths and check each one to make sure it matches the regex given
        for (int i = 0; i < allPaths.size(); i++) {
            String edgeString = findEdgesFromVertexPath(allPaths.get(i));
            if (edgeString.matches(regex)) {
                regexPaths.add(allPaths.get(i));
            }
        }
        return regexPaths;
    }

    // Get a list of all of the vertices in the graph
    public List<String> getAllVertices() {
        List<String> allVertices = new ArrayList<>();
        Vertex currentNode = head;
        for (int i = 0; i < getNumberOfVertices(); i++) {
            allVertices.add(currentNode.label);
            currentNode = currentNode.next;
        }

        return allVertices;
    }



}
