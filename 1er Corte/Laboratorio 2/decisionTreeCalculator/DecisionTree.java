public class DecisionTree {

    private String root;        // Valor del nodo
    private DecisionTree yes;   // Hijo para respuesta "sí"
    private DecisionTree no;    // Hijo para respuesta "no"

    /*
     * Constructor that creates a decision tree with a single decision node.
     * The decision tree will have only one node, which is both the root and a leaf.
     */
    public DecisionTree(String root){
        if (root == null || root.isEmpty()) {
            throw new IllegalArgumentException("El nodo raíz no puede ser nulo o vacío");
        }
        this.root = root.toLowerCase(); // Convertimos a minúsculas según las pruebas
        this.yes = null;
        this.no = null;
    }
    
    
    /*
     * Adds a question node with two children to an existing question node.
     * @returns true if the addition is successful, false otherwise.
     */
    public boolean add(String parent, String yesChild, String noChild){
        if (parent == null || yesChild == null || noChild == null) {
        return false;
        }
    
        // Buscar el nodo padre
        DecisionTree parentNode = findNode(parent.toLowerCase());
        
        // Verificar si el padre existe y no tiene hijos
        if (parentNode == null || parentNode.yes != null || parentNode.no != null) {
            return false;
        }
        
        // Verificar que no haya nodos duplicados
        if (contains(yesChild) || contains(noChild)) {
            return false;
        }
        
        // Agregar los hijos
        parentNode.yes = new DecisionTree(yesChild);
        parentNode.no = new DecisionTree(noChild);
        return true;
    }


    /*
     * Deletes a node from the tree.
     * @returns true if the deletion is successful, false otherwise.
     */
    public boolean delete(String node) {
        if (node == null) return false;
    node = node.toLowerCase();


    if (this.root.equals(node)) {
        return false;
    }


    if (yes != null && yes.root.equals(node)) {
        yes = null;
        return true;
    }

    if (no != null && no.root.equals(node)) {
        no = null;
        return true;
    }


    boolean deleted = false;
    if (yes != null) {
        deleted = yes.delete(node);
        if (deleted) return true;
    }
    if (no != null) {
        deleted = no.delete(node);
        if (deleted) return true;
    }
    return false;
    }
    

    /**
     * Evaluates the decision tree with a given set of input values.
     * @param values Matrix with (question, answer) pairs.
     * @return A subtree resulting from the evaluation.
     */
    public DecisionTree eval(String [][] values){
        DecisionTree current = this;
    while (current.yes != null && current.no != null) {
        String answer = null;

        for (String[] pair : values) {
            if (pair[0] != null && pair[0].equalsIgnoreCase(current.root)) {
                answer = pair[1];
                break;
            }
        }
        if (answer == null) {
            break;
        }
        if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("si")) {
            current = current.yes;
        } else if (answer.equalsIgnoreCase("no")) {
            current = current.no;
        } else {
            break;
        }
    }
    return current;
    }
    
    /**
     * Checks if the tree contains a specific node.
     * @param node Node to search.
     * @return true if the node exists, false otherwise.
     */
    public boolean contains(String node){
        if (node == null) return false;
        node = node.toLowerCase();
        
        if (root.equals(node)) {
            return true;
        }
        
        if (yes != null && yes.contains(node)) {
            return true;
        }
        
        if (no != null && no.contains(node)) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Determines if a node is a question (has children).
     * @param node Node to check.
     * @return true if the node is a question, false otherwise.
     */
    public boolean isQuestion(String node){
        DecisionTree foundNode = findNode(node.toLowerCase());
        return foundNode != null && (foundNode.yes != null && foundNode.no != null);
    }

    /**
     * Determines if a node is a decision (leaf node).
     * @param node Node to check.
     * @return true if the node is a decision, false otherwise.
     */
    public boolean isDecision(String node){
        DecisionTree foundNode = findNode(node.toLowerCase());
        return foundNode != null && (foundNode.yes == null && foundNode.no == null);
    }
    

    /**
     * Realiza una copia profunda del árbol de decisiones.
     * @return Una nueva instancia idéntica del árbol.
     */
    private DecisionTree copy() {
        DecisionTree nuevo = new DecisionTree(this.root);
        if (this.yes != null) {
            nuevo.yes = this.yes.copy();
        }
        if (this.no != null) {
            nuevo.no = this.no.copy();
        }
        return nuevo;
    }

    /**
     * Joins two decision trees into one.
     * @param dt Another decision tree.
     * @return Resulting tree from the union.
     */
    public DecisionTree union (DecisionTree dt){
        if (dt == null) return this;
        String newRoot = this.root + " o " + dt.root;
        DecisionTree result = new DecisionTree(newRoot);
    
        result.yes = this.copy();
        result.no = dt.copy();
        return result;
    }

    /**
     * Returns the number of nodes in the tree.
     * @return Total number of nodes.
     */
    public int nodes(){
        int count = 1; // Contamos el nodo actual
        if (yes != null) {
            count += yes.nodes();
        }
        if (no != null) {
            count += no.nodes();
        }
        return count;
    }
    
    /**
     * Calculates the height of the tree.
     * @return Height of the tree.
     */
    public int height(){
        if (yes == null && no == null) {
            return 1; // Altura de un nodo hoja
        }
        int heightYes = yes != null ? yes.height() : 0;
        int heightNo = no != null ? no.height() : 0;
        return Math.max(heightYes, heightNo) + 1;
    }    
    
    
    /**
     * Compares if two decision trees are equal.
     * @param dt Decision tree to compare.
     * @return true if both trees are equivalent, false otherwise.
     */
    public boolean equals(DecisionTree dt){
        if (dt == null) return false;
    
        // Comparar raíces (insensible a mayúsculas/minúsculas)
        if (!this.root.equalsIgnoreCase(dt.root)) return false;
        
        // Comparar estructura de hijos
        if (this.yes == null && dt.yes != null) return false;
        if (this.no == null && dt.no != null) return false;
        
        // Comparar hijos recursivamente si existen
        if (this.yes != null && !this.yes.equals(dt.yes)) return false;
        if (this.no != null && !this.no.equals(dt.no)) return false;
        
        return true;
    }
    
    /**
     * Overrides the equals method to compare with a generic object.
     * @param g Object to compare.
     * @return true if the object is an equivalent decision tree, false otherwise.
     */
    public boolean equals(Object g){
        if (this == g) return true;
        if (g == null || getClass() != g.getClass()) return false;
        return equals((DecisionTree)g);
    }
     

    /**
     * String representation of the decision tree.
     * Format: Trees are represented inside parentheses. 
     * Node names are in lowercase. 
     * Children must always appear in yes/no order.
     * Example: (a yes (b yes (c) no (d)) no (e)) 
     * @return String representation of the tree.
     */
    public String toString() {
        if (isDecision(root)) {
            return "(" + root + ")";
        }
        return "(" + root + " yes " + yes.toString() + " no " + no.toString() + ")";
    }

    /**
     * Helper method to find a node by its value.
     * @param node Value of the node to find.
     * @return The DecisionTree node if found, null otherwise.
     */
    private DecisionTree findNode(String node) {
        if (root.equals(node)) {
            return this;
        }
    
        DecisionTree found = null;
        if (yes != null) {
            found = yes.findNode(node);
        }
        if (found == null && no != null) {
            found = no.findNode(node);
        }
        return found;
    }

}
