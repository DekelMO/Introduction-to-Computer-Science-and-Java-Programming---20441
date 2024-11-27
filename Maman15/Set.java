/**
 * Represents a set of odd natural number.
 * A Set object is represented by the set length, the set head (first value) and its tail (last value).
 * The set is being executed with sorted link list data stricture.   
 * 
 * @author Dekel Moens
 * @version 27.05.2022
 */
public class Set
{
    // variables
    private IntNode _head, _tail;
    private int _length = 0;

    // constructors
    /**
     * Constructor for a Set object. 
     * Create an empty set
     * Time and Space complexity are O(1) - 2 tasks were executed and there is no use in new space. 
     */
    public Set()
    {
        _head = null;
        _tail = null;
    }
    //Methods
    /**
     * Checks if the set is empty.
     * Time and Space complexity are O(1) - only 1 task is being executed and there is no use in space.
     * 
     * @return True if the set is empty if not return false.
     */
    public boolean isEmpty ()
    {
        if (_head == null)//If the first object in the linked list is empty, so there is no linked list and the set is empty.
        {
            return true;
        }
        return false;
    }

    /**
     * Check if num is in the set. 
     * Check all the elements in the set and check if num is equal to any their value.
     * Time complexity is O(n) in the worst case it is necessary to compare num to every value in the set
     * Space complexity is O(1) - final number of variables were created(runner).
     * 
     * @param num The number to be search in the set.
     * 
     * @return True if num was found in the set else return false.
     */
    public boolean isMember (int num)
    {
        if(isEmpty() || num > _tail.getValue())//If the set is empty num cannot be in the set. The Set is sorted and therefor
        // if num is greater then the tail value so its greater from all the rest of the values in the set.
        {
            return false;
        }
        for(IntNode runner = _head; runner != null; runner = runner.getNext())// The loop run through all the elements in the list.
        {
            if (runner.getValue() == num)// If num was found in the set return true.
            {
                return true;
            }
            if (runner.getValue() > num)//If the runner is greater from num,  as runner moving forward all the 
            //next elements values are greater from the current runner value and therefor they are greater from num so it 
            //unnecessary to check them.
            {
                return false;
            }
        }
        return false;// After all the elements in the list were check if num was not found false will be returned.
    }

    /**
     * Check if This set and Other set contain the same elements.
     * Time complexity is O(n) As the lists are sorted it will require two passes, one pass for each list so its O(2n) = O(n)
     * Space complexity is O(1) - final number of variable were created(p,q).
     * 
     * @param other The other set to be checked if its contain the same elements as This set. 
     * 
     * @return True if the sets are equal else return false.
     */
    public boolean equals (Set other)
    {
        if(isEmpty() && other.isEmpty())// If both sets are empty they are equal and true is to be returned.
        {
            return true;
        }
        if(isEmpty() || other.isEmpty() || _length != other._length)//If only 1 of the sets is empty they cannot be equals
        //(the option that both are empty was rejected) and also if their length is different they cannot be equals.
        {
            return false;
        }
        IntNode p = _head;// This set runner.
        IntNode q = other._head;// Other set runner.
        while(p != null)// Both of the sets length are equals so it doesn’t matter which runner will be use as loop stop
        //if the sets are equal both of the runner will reach to null at the same iteration.
        {
            if(p.getValue() != q.getValue())//Tthe lists are sorted so its  enough that two value in the same list location 
            //will be different to ensure that the sets are different.
            {
                return false;
            }
            p = p.getNext();//After the current value was checked both runner will mover forward to compare the next elements.
            q = q.getNext();
        }
        return true;// If all the elements in the lists were compered and find to be equal true will be returned.
    }

    /**
     * Check the length of the sets. 
     * Check how many elements there are in the set
     * Time complexity is O(1) There is instance variable with this info;
     * Space complexity is O(1) - final number of variables were created(none).
     * 
     * @return The amount of elements in the set.
     */
    public int numOfElements ()
    {
        return _length;
    }

    /**
     * Check if the other set is sub set of this set. 
     * Check that this set contain every element from the other set
     * 
     * Time complexity is O(n) As the lists are sorted it will be required two passes, one pass for each list so it O(2n) = O(n)
     * Space complexity is O(1) - final number of variables were created(p,q).
     * 
     * @param other The set to be checked if its sub test of this set.
     * 
     * @return True if the other set is subset else return false.
     */
    public boolean subSet (Set other)
    {
        if(other.isEmpty())// Every set contain the empty set.
        {
            return true;
        }
        if(isEmpty())//If this is empty, it cannot contain any set except the empty set(which we already rejected).
        {
            return false;
        }
        IntNode p = _head;//this runner
        IntNode q = other._head;//other runner
        while( q != null)// the loop will stop after all the element from other sets were checked       
        {
            if (p == null)//If the loop reach to a point when the Other set isn’t empty and this set it empty
            //so as it explain before empty set cannot contain a none empty set and false will be returned.
            {
                return false;
            }
            if(p.getValue() > q.getValue())// As both sets are sorted if the this set element is greater from the Other
            //set element, there is no reason to continue to check as all the next elements in this set will be greater
            //from the other set element and false need to be returned.
            {
                return false;
            }
            if(p.getValue() == q.getValue())// If both elements are equal we can procced to check the next element in Other
            //set.
            {
                q = q.getNext();
            }
            p = p.getNext();// In any case as long as this set element is still smaller from other set element there is a
            //chance that one of the next elements value will be equal to Other element so the program will procced to check This
            // next element.
        }
        return true;
    }

    /**
     * Adding a new element to the set. 
     * Check if the element is an odd natural element and if so it will be added to the correct location in the sorted set.
     * 
     * Time complexity is O(n) The list is sorted so it requires to search between all element for the correct element location.
     * note that adding an element that is greater then the biggest set element or smaller then the smallest element is o(1)
     * beacuse these checks are being conducted before the loop is running through all the set elements.
     * Space complexity is O(1) - final number of variables were created(prev,runner).
     * 
     * @param x The element to be added.
     */
    public void addToSet (int x)
    {
        if (x%2 != 0 && 0 < x)// Confirming that the element is odd and natural number.
        {
            if (isEmpty())// If its empty set, the head and tail will be define as x (node that contain x)
            // and the set length will be updated.
            {
                _head = new IntNode(x,null);// Creating new node with the x value and pointer to null( its the only
                // element in the set) and head is define as this element
                _tail = _head;// There is only one element so its the head and the tail of the set.
                _length++;// set length will be extended by 1.
            }
            else if(x < _head.getValue())// Covering the case when x is smaller then the smallest set element.
            {
                _head = new IntNode(x,_head);//New node will be created with the x value and the pointer to the previous
                // smallest set element (_head) and then the new element will be defined as the smallest element(_head)
                _length++;// Set length will be extended by 1.
            }
            else if(x > _tail.getValue())// Covering the case when x is greater then the biggest set element.
            //note that this case will ensure that if we adding element that is greater from the biggest set element 
            // it won’t be require to check all the elements in the set.
            {
                _tail.setNext(new IntNode(x,null));//New node will be created with the x value and the pointer to null
                // as it the biggest element in the set. Then the previous biggest element pointer will be updated to point 
                //to the new biggest element.
                _tail = _tail.getNext();//The biggest element in the set(tail variable) will be updated.
                _length++;// set length will be extended by 1.
            }
            else// Covering the case when the new element should be added in the in the "middle of the set"
            {
                if( _head.getValue() == x)// Confirming the smallest element(_head) isn’t equal to x as each element 
                // can be in the set up to 1 time.
                {
                    return;
                }
                IntNode prev = _head;
                for(IntNode runner = _head.getNext(); runner != null ; runner = runner.getNext())// The loop will check
                // all the elements in the set.
                {
                    if (runner.getValue() > x)// IN every iteration the loop will check if the runner element is greater 
                    //then x. If it is the element need to be placed before the runner element.
                    {
                        IntNode temp = new IntNode(x,runner);//New node will be crate with the x value and pointer to
                        //runner as it the first element that greater from x.
                        prev.setNext(temp);// The previous element that was before runner( x is greater from tis value)
                        // will now point to the new node( with x value)
                        _length++;// set length will be extended by 1.
                        return;// The place was found so the method can stop as each element can be in the set up to 1 time.
                    }
                    if (runner.getValue() == x)//If x equal the element value so its  already exting in the set, 
                    // as each element can be in the set up to 1 time the method will stop.
                    {
                        return;
                    }
                    prev = runner;// Updated the prev value that contained always the element before the runner.
                }
            }
        }
    }

    /**
     * Removing a element from the set. 
     * Check if the element is an odd natural and if so it will remove the element from the set(if its in it).
     * 
     * Time complexity is O(n) In the worst case its require to check each element from the set and compare it to x
     * Space complexity is O(1) - final number of variables were created(prev,runner).
     * 
     * @param x The element to be added.
     */
    public void removeFromSet (int x)
    {
        if (x%2 != 0)
        {
            if (_head == null || x < _head.getValue() || x > _tail.getValue())//If the set is empty no element can be removed.
            //and if X is smaller from the smallest element(head) or greater then the biggest element(tail) so x is not 
            // in the set and there for it cant be removed.
            {
                return;
            }
            else if(x == _head.getValue())//If X is the value of the smallest element(head).
            {
                _head = _head.getNext();//Update the smallest element to be the next element after head, by this the 
                //previous head element will be removed
                _length--;// length will be updated (shorten by 1)
            }
            else//Checks if x is inside the set
            {
                IntNode prev = _head;
                for(IntNode runner = _head; runner != null ; runner = runner.getNext())// The loop will run though all
                //the elements after head(head was already check)
                {
                    if (runner.getValue() == x)// Element that its value is equal to x was found 
                    {
                        if (runner.getValue() == _tail.getValue())// if its the biggest element(tail) in the set
                        {
                            _tail = prev;// The biggest element(tail) will be updated to the second biggest element
                            //( the previous element to tail)
                            _tail.setNext(null);//The biggest element (tail) pointer will be updated to null so the 
                            //previous biggest element will be deleted.
                            _length--;// length will be updated(shorten by 1)
                            return;// The element was remove and the method is done
                        }
                        //if its not the biggest element in the set:
                        prev.setNext(runner.getNext());//The previous element(the element before the element to be remove)
                        //pointer will point to the element after the one to be remove and by this the element
                        //to be remove will be removed
                        _length--;// length will be updated (shorten by 1)
                        return;// The element was remove and the method is done
                    }
                    prev = runner;// If x was not equal to the runner value the prev element will be updated to the runner
                    // and in the next iteration the loop signature will update runner one element forward.
                }
            }
        }
    }

    /**
     * Return a string representation of this set (for example: "{1,2,3,6,8,44}").
     * 
     * Time complexity is O(n) In purpose to add all element to a string it require to check the value of each element
     * Space complexity is O(1) - final number of variables were created (tempString).
     * @return String representation of this set (for example: "{1,2,3,6,8,44}").
     */
    public String toString()
    {
        if(isEmpty())// If the set is empty "{}" will return .
        {
            return "{}";
        }
        String tempString = "{";//create a tempString that all the elements values from the set will add to it
        for(IntNode runner = _head ; runner != _tail ; runner = runner.getNext())// Loop that run through all the elements
        //element up to the last element(tail)
        {
            tempString = tempString + runner.getValue() + ",";//Add each element to the string with "," (for separation)
        }
        tempString = tempString + _tail.getValue() + "}";// After all the elements were added except the last element(tail)
        // tail will be added (without "'" as its the last element) with the closer to the set "}"
        return tempString;
    }

    /**
     * Find out what is the intersection set of two sets. 
     * Return a new set of all the elements that are in both sets.
     * 
     * Time complexity is O(n) As the lists are sorted it will be required two passes, one pass for each list so it O(2n) = O(n)
     * Space complexity is O(1) - final number of variables were created (p,q).
     * 
     * @param other The set to be check for its intersection together with this set.
     * 
     * @return The intersection set (new set).
     */
    public Set intersection (Set other)
    {
        Set intersection = new Set();//Creating new set.
        IntNode p = _head;//This set runner.
        IntNode q = other._head;// Other set runner.
        while(p != null && q != null)
        {
            if (p.getValue() == q.getValue())//If the elements are equal.
            {
                intersection.addToSet(p.getValue());//The element will be added to the new list
                //Because This and the Other lists are both sorted every element that added to intersection set
                // is greater from the biggest(tail) element of the intersection set.
                q = q.getNext();//Move to next element in Other.
                p = p.getNext();//Move to next element in This.
            }
            else if (p.getValue() > q.getValue())// If This element is greater from Other element, they cannot be equal.
            {
                q = q.getNext();// Because the lists are sorted there might be element in Other set that equal to the element
                // of This set runner, therefor Other set runner will move to the next element.
            }
            else//If Other element is greater from This element they, cannot be equal
            {
                p = p.getNext();// Because the lists are sorted there might be element in This set that equal to the runner element
                // of Other. ttherefore This set runner will move to the next element.
            }
        }
        return intersection;//Return the set.
    }

    /**
     * Find out what is the union set of two sets. 
     * Return a new set of all the elements that are at least in one of the sets.
     * 
     * Time complexity is O(n) As the lists are sorted it will be required two passes, one pass for each list so it O(2n) = O(n)
     * Space complexity is O(1) - final number of variable were created (p,q).
     * 
     * @param other The set to be check for its union together with This set.
     * 
     * @return The union set (new set).
     */
    public Set union (Set other)
    {
        Set union = new Set();// Create a new Set
        IntNode p = _head;//This set runner.
        IntNode q = other._head;// Other set runner.
        while(p != null && q != null)// The loop will run until one of the set runner reach to null(so all it element
        //were checked).
        {
            if (p.getValue() == q.getValue())//If two equal elements were found.
            {
                union.addToSet(p.getValue());// Adding the element to the union set, the element is greater then all
                // the other elements in union set because all 3 sets are sorted.
                q = q.getNext();//Move to next element in Other.
                p = p.getNext();//Move to next element in This.
            }
            else if (p.getValue() > q.getValue())// If This element value is greater then Other element value.
            {
                union.addToSet(q.getValue());//Other value element will be added, because its isn’t equal to any of 
                //upcoming elements in both list(so it wont be added twice) because the lists are sorted.
                q = q.getNext();//The runner moves to next element in Other, This element runner is not being updated, because we
                //need to ensure the element is not existing also in Other set (which will cause the same element to be added twice)
            }
            else// If other element value is greater then this element value.
            {
                union.addToSet(p.getValue()); //This value element will be added, because its isn’t equal to any of 
                //upcoming elements in both list(so it wont be added twice) because the lists are sorted.
                p = p.getNext();//The runner moves to next element in This, Other element runner is not being updated, because we
                //need to ensure that the element is not existing also in This set (which will cause the same element to be added twice)
            }
        }
        //Check which one of the runners reach to null and update p to be the other runner (cause its 
        //mean there is still elements to be added to the union set
        if( p == null)
        {
            p = q;
        }
        //Note that if both runners were null so p is also null and it will skip the loop
        while(p != null)//The loop will run thorugh all the element that was not added yet to the set ( because the runner 
        //didn’t reach to null(it will continue from the same place that its stopped to check)
        {
            union.addToSet(p.getValue());// All the element will be added one after the other(no checks are required
            // as we adding elements only from 1 sorted set that its element are greater from all the union set elements.
            p = p.getNext();
        }
        return union;//After all elements were added return the new set
    }

    /**
     * Find out which element existing only in This set. 
     * Return a new set of all the elements that are existing in This set and not existing in Other set.
     * 
     * Time complexity is O(n) As the lists are sorted it will be required two passes, one pass for each list so it O(2n) = O(n)
     * Space complexity is O(1) - final number of variables were created(p,q).
     * 
     * @param other The set that all his elements need to be reduce from This set.
     * 
     * @return The difference set (new set).
     */
    public Set difference (Set other)
    {
        Set difference = new Set();// Create new set.
        IntNode p = _head;//This runner.
        IntNode q = other._head;//Other runner.
        while (p != null && q != null)//Loop will run until one of the runner finished to pass through all the set elements.
        {
            if (p.getValue() == q.getValue())//If equals elements were found nothing is to be added to the set.
            {
                q = q.getNext();//Move to next element in Other.
                p = p.getNext();//Move to next element in This.
            }
            else if  (p.getValue() < q.getValue())//If Other element is greater then This element, so al the next elements
            //in Other are also greater from This runer element and therefor they cannot be equal to it
            {
                difference.addToSet(p.getValue());//This element is not in Other set so its added to the difference set.
                p = p.getNext();//Move to next element in This, Other runner is not being updated because its current element
                //might be equal to future This set element.
            }
            else//This element is greater then Other element, so its mean that other element runner is smaller from all the 
            //next elements in This therefor it cannot be equal to them.
            {
                //There is a chance that one of the next elements in Other might be equal to the current This runner
                //element and therefor it cannot be added it to the difference set
                q = q.getNext();//Other runner is not equal to the This runner or any other element in This set
                //There for Other runner will be updated to the next element in Other.
            }
        }

        while(p != null)//If This Runner didn’t reach to the null element, its mean that all  Other element were checked
        // and all the element that was not Check in This set(if its not null) can be added to the difference set.
        {
            difference.addToSet(p.getValue());//Adding the elements.
            p = p.getNext();//Move to next element in This.
        }
        return difference;//After all elements that belong to This set and not belong to Other set
        //were added to difference set it will be returned.

    }
}
