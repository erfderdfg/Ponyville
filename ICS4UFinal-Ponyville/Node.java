/**
 * Node, the building block of map
 * 
 * @author Xuanxi Jiang & George (refactor)
 * @version 1.0
 */
public class Node{
    //enemy may be not used (if based on rng)
    private int type;
    
    /**
     * Simple constructor for a node that initializes node with a type.
     * 
     * @param type  The type of this node.
     */
    public Node(int type){
        this.type = type;
    }
    
    /**
     * Returns the type which current node represents
     * <ul>0   - clear</ul>
     * <ul>1   - main character spawn point</ul>
     * <ul>2   - blocked</ul>
     * <ul>3+n - item, numbered starting from n=0 (if 3 -> item 0, etc.)</ul>
     * 
     * @return int  The type that current node represents.
     */
    public int getType(){
        return type;
    }
    
    /**
     * Sets the type of current node to another type
     * 
     * @param type  The updated type of current node
     */
    public void setType(int type){
        this.type = type;
    }
}
