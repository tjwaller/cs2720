public class BinarySearchTree<T extends Comparable<T>> {

    private NodeType<T> root;


    // Constructor
    public BinarySearchTree() {
        NodeType<T> root = new NodeType<T>();
    }


    // inserts a node with the given key into the tree
    public void insert(T key) {
        root = insert(root, key);
    }

    // inserts a node into the tree
    private NodeType<T> insert(NodeType<T> node, T key) {
        // If the node is already in the tree, then display error message
        if (search(node, key)) {
            System.out.println("The item already exists in the tree.");
        } else {
            if (node == null) {
                node = new NodeType<T>(key);
            } else if (key.compareTo(node.info) < 0) {
                node.left = insert(node.left, key);
            } else if (key.compareTo(node.info) > 0) {
                node.right = insert(node.right, key);
            }
            return node;
        }
        return node;
    }

    // Simple method that takes one input
    public NodeType<T> delete(T key) {
        return delete(root, key);
    }

    // recursive method for deleting nodes
    private NodeType<T> delete(NodeType<T> node, T key) {
        if (search(node, key)) { // If the node is in the tree
            if (node == null) {
                return null;
            } else if (key.compareTo(node.info) < 0) {
                node.left = delete(node.left, key);
            } else if (key.compareTo(node.info) > 0) {
                node.right = delete(node.right, key);
            } else {
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                } else {
                    NodeType<T> temp = node.right;
                    while (temp.left != null) {
                        temp = temp.left;
                    }
                    node.info = temp.info;
                    node.right = delete(node.right, temp.info);
                }
            }
            return node;
        } else {
            return null;
        }


    }

    // Simple search (one input)
    public boolean search(T item) {
        return search(root, item);
    }

    // Search with two input enabling it to call itself
    private boolean search(NodeType<T> node, T item) {
        if (node == null) { // parent (current)
            return false;
        } else if (item.compareTo(node.info) < 0) { // left tree
            return search(node.left, item);
        } else if (item.compareTo(node.info) > 0) { // right tree
            return search(node.right, item);
        } else {
            return true;
        }
    }

    // Returns a String representation of the tree
    public String inOrder() {
        return inOrder(root).trim();
    }

    // Returns a String representation of the tree
    private String inOrder(NodeType<T> node) {
        String str = "";
        // If the node exists,
        if (node != null) {
            str = str + inOrder(node.left);
            str = str + node.info + " ";
            str = str + inOrder(node.right);
        }
        return str;
    }

    // Get single Parent
    public void getSingleParent() {
        getSingleParent(root);
    }

    //Recursive Method
    private void getSingleParent(NodeType<T> node) {

        if (node != null) { //If node is not a leaf node
            // Check if it is a single parent
            if ((node.left == null && node.right != null) || (node.left != null && node.right == null)) {
                //If the current node is a single parent, add one to the count
                System.out.print(node.info + " ");
            }
            //Then check the current node's left subtree and right subtree
            getSingleParent(node.left);
            getSingleParent(node.right);

        }
    } // end of getSingleParents


    //gets number of leaf nodes and prints the output
    public int getNumLeafNodes() {
        return getNumLeafNodes(root);
    }

    // Recursive method
    private int getNumLeafNodes(NodeType<T> node) {
        if (node == null) {
            return 0;
        } else if (node.left == null && node.right == null) {
            return 1;
        } else {
            //At each step, there is either 0 or one leaf nodes + the ones from the left and right subtree
            return getNumLeafNodes(node.left) + getNumLeafNodes(node.right);
        }
    } // end getNumLeafNodes

    /* It returns level of the node if it is present
in tree, otherwise returns 0.*/
public int getLevel(NodeType<T> root, NodeType<T> node, int level)
{
    // return 0 if root is a leaf node
    if (root == null)
        return 0;
    if (root == node)
        return level;

    // If node is present in left subtree
    int downlevel = getLevel(root.left, node, level+1);
    if (downlevel != 0)
        return downlevel;

    // If node is not present in left subtree
    return getLevel(root.right, node, level+1);
}

/* Print nodes at a given level such that sibling of
node is not printed if it exists */
public void printGivenLevel(NodeType<T> root, NodeType<T> node, int level)
{
    // Base cases
    if (root == null || level < 2)
        return;

    // If current node is parent of a node with
    // given level
    if (level == 2)
    {
        if (root.left == node || root.right == node)
            return;
        if (root.left != null)
        System.out.print(root.left.info + " ");
        if (root.right != null)
        System.out.print(root.right.info + " ");
    }

    // Recur for left and right subtrees
    else if (level > 2)
    {
        printGivenLevel(root.left, node, level-1);
        printGivenLevel(root.right, node, level-1);
    }
}

//Returns the cousins of the given key
public void getCousins(T key) {
    NodeType<T> node = searchNode(root, key);
    getCousins(root, node);

}

// This function prints cousins of a given node
public void getCousins(NodeType<T> root, NodeType<T> node)
{
    // Get level of given node
    int level = getLevel(root, node, 1);

    // Print nodes of given level.
    printGivenLevel(root, node, level);
}

public NodeType<T> searchNode(NodeType<T> node, T key) {
    if (node == null || node.info.equals(key)) {
        return node;
    }
    int comparison = key.compareTo(node.info);
    if (comparison < 0) {
        return searchNode(node.left, key);
    } else {
        return searchNode(node.right, key);
    }
}


}
