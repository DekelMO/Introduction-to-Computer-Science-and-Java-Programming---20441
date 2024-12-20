/**
 * Represents a node in list - we will use it to represent a singel number in a set. 
 * 
 * @author Dekel Moens(Originally created by the Open University)  
 * @version 27.05.2022
 */
public class IntNode
{
    // variables
    private int _value;
    private IntNode _next;

    /**
     * Constructor for a node object. 
     * Create an new node (new number represention).
     * Time and Space complexity are O(1) - 2 tasks were executed and there is no use in new space. 
     */
    public IntNode(int v, IntNode n)
    {
        _value = v;// The number value.
        _next = n;//pointer to the next ndoe in the list.
    }
    //Methods
    /**
     * return the node(number) value. 
     * 
     * Time and Space complexity are O(1) - 1 task is executed and there is no use in new space. 
     * 
     * @return The node value(number value).
     */
    public int getValue() {
        return _value;
    }

    /**
     * return the node pointer to the next node(number). 
     * 
     * Time and Space complexity are O(1) - 1 task is executed and there is no use in new space. 
     * 
     * @return The node nextNode.
     */
    public IntNode getNext() {
        return _next;
    }

    /**
     * Set the node(number) value. 
     *
     * Time and Space complexity are O(1) - 1 task is executed and there is no use in new space. 
     * 
     * @param v The new value to be set in node.
     */
    public void setValue(int v) {
        _value = v;
    }

    /**
     * Set the node(number) pointer to the next node(number). 
     * 
     * Time and Space complexity are O(1) - 1 task is executed and there is no use in new space. 
     * 
     * @param n The node to set as next.
     */
    public void setNext(IntNode n) {
        _next = n;
    }
}