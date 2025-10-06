import java.util.HashMap;

/** DecisionTreeCalculator.java
 * 
 * @author ESCUELA 2025-02
 */
    
public class DecisionTreeCalculator{
    
    /** Map of variables associating names with decision trees. */
    private static HashMap<String,DecisionTree> variables = new HashMap<>();
    private static boolean lastOperationOk = true;
    
    /**
     * Constructor. Initializes the storage structure.
     */
    public DecisionTreeCalculator(){
        lastOperationOk = true;
    }

    /**
     * Creates a new calculator.
     * @param name Name of the variable to be created.
     */
    public static void create(String name){
        if (name == null || name.isEmpty()) {
            lastOperationOk = false;
            return;
        }

        if (variables.containsKey(name)) {
            lastOperationOk = false;
            return;
        }

        variables.put(name, null);
        lastOperationOk = true;
    }
     
    /**
     * Creates a decision tree and assigns it to an existing variable.
     * @param a Variable name where the tree will be stored.
     * @param root Root node of the decision tree.
     */
    public void assign(String a, String root){
        if (a == null || root == null || !variables.containsKey(a)) {
            lastOperationOk = false;
            return;
        }

        try {
            DecisionTree newTree = new DecisionTree(root);
            variables.put(a, newTree);
            lastOperationOk = true;
        } catch (IllegalArgumentException e) {
            lastOperationOk = false;
        }
    }    
    
    
    /**
     * Assigns the result of a unary operation to a variable.
     * 
     * Available operators:
     * <ul>
     *   <li>'+'  add children. Parameters: [[parent, yesChild, noChild]]</li>
     *   <li>'-'  remove a node. Parameters: [[nodeName]]</li>
     *   <li>'?'  evaluate a decision tree. Parameters: [[node1, val1], [node2, val2], ...]</li>
     * </ul>
     * 
     * @param a Variable where the result will be stored.
     * @param b Input variable containing a tree.
     * @param op Unary operator ('+', '-', '?').
     * @param parameters Parameters required for the operation.
     */

    public void assignUnary(String a, String b, char op, String [][] parameters){
    }
      
    
    /**
     * Assigns the result of a binary operation to a variable.
     * 
     * Available operators:
     * <ul>
     *   <li>'u'  union of trees.</li>
     *   <li>'i'  intersection of trees.</li>
     *   <li>'d'  difference of trees.</li>
     * </ul>
     * 
     * @param a Variable where the result will be stored.
     * @param b First input variable.
     * @param op Binary operator ('u', 'i', 'd').
     * @param c Second input variable.
     */
    public void assignBinary(String a, String b, char op, String c){
    }
  
    
    /**
     * Returns the string representation of a decision tree 
     * in alphabetical order.
     * 
     * @param decisionTree Variable name that contains the tree.
     * @return Text representation of the tree in alphabetical order.
     */
    public String toString(String decisionTree){
        return null;
    }
    
    
    /**
     * Indicates whether the last operation was successfully completed.
     * 
     * @return true if the operation succeeded, false otherwise.
     */
    public boolean ok(){
        return false;
    }
}
    



