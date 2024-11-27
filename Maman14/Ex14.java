/**
 * Maman 14
 * 
 * @author Dekel Moens
 * @version 16.05.2022
 * 
 * Question 1
 * Section A: 
 * 1 - false
 * 2 - false
 * 3 - true
 * 4 - false
 * 5 - true
 * 6 - true
 */
public class Ex14
{
    // Question 1, Section B part 1:
    /**
     * Finding if val is in a given two-dimensional array (The method what(array) must return true for the array)
     * The "what" method ensuring that every value is smaller than the next row and the next column values
     * which mean that every row and column are increasing sequence
     * The method will exam the array corners values and set them as the corners of the focus area as they are all represent 
     * min/ max value of an increasing sequence.
     * If val is smaller than the smallest row value (most left) so its smaller from all the rest of the values in the row,
     * there for we can remove that row.
     * If val is greater than the biggest row value (most right) so its greater from all the rest of the values in the row,
     * there for we can remove that row.
     * If val is smaller than the smallest column value(top) so its smaller from all the rest of the values in the column,
     * there for we can remove that column.
     * If val is greater than the biggest column value(bottom) so its greater from all the rest of the values in the column,
     * there for we can remove that column.
     * Before every row/column removing, the corners will check and if val is equal to one of the corners value the method will return true.
     *
     * In this way we will minimize the array focus area to 1or2X2or1 if val is not equal to any of the smallest focus area corners
     * the method will return false.
     * 
     * Time complexity = O(n) - In every loop iteration we remove 1row/1column/2rows/2columns, there are n columns and n rows = 2n
     * 2n/1 = 2n (worst case only removing 1 row or 1 column) =O(n).
     * Space complexity = O(1) - The array was pass by reference and there is final number of parameters.
     * 
     * @param m The array to be checked(the array must return true for the what method).
     * @param val The value to be found in the array.
     * 
     *  @return true if the val is in the array and false if it isn’t.
     */
    public static boolean findValWhat (int [][] m, int val)
    {
        if( m == null || m.length == 0) // if the array is not initialize or the size of the array is 0, so val is not existing  
        // and false will be returned
        {
            return false;
        }
        return findValWhat(m,val,0,m.length-1,0,m.length-1);// after confirming there is values in the array, a overloaded method 
        // will be called with the additional focus area corners parameters.
    }

    private static boolean findValWhat (int [][] m, int val,int top,int bottom,int left, int right)
    {
        boolean topEqualsBottom = false, leftEqualsRight = false;// flags to ensure that the left index won’t pass the right index
        //and the bottom index won’t pass the top index. if the left index will be equal to the right index the method will stop
        // change the right and left indexes of the focused area (the same principle for top and bottom).
        while(!(topEqualsBottom && leftEqualsRight && m[top][right] != val))// in ever loop iteration the 4 corners are being checked
        //so when we reach 1X1 box the 4 previous corners were checked. However is some cases when the focus area is 1X3 array and
        //the middle index value is the one we are searching the method will remove the corners values 
        // and it will be necessary to ensure the middle index value isn’t equal to val.
        {
            if(m[top][left] == val || m[top][right] == val || m[bottom][left] == val || m[bottom][right] == val)
            // check if the val equals to any of the focus area corners.
            {
                return true;
            }

            if(m[top][right]<val)//If val is greater than the biggest top row value (most right) so its greater from all 
            //the rest of the values in the row, there for we can remove the top row and minimize the focus area.
            {
                if(!topEqualsBottom)// Ensure that the row can be remove
                {
                    top ++;// Minimizing the focus area be remove the top row
                    if (top == bottom) topEqualsBottom =true; //Updating the flag
                }
            }
            else //m[top][right]>val (cannot be equals as we checked it in the beginning of the iteration)
            //If val is smaller than the smallest right column value(top) so its smaller from all the rest of the values in the column,
            //there for we can remove the right column.
            {
                if(!leftEqualsRight)// Ensure that the column can remove
                {
                    right --;// Minimizing the focus area be remove the right column
                    if (left == right) leftEqualsRight = true;//Updating the flag
                }
            }

            if(m[bottom][left]<val)//If val is greater than the biggest left column value(bottom) so its greater from all the 
            //rest of the values in the left column, there for we can remove that column.
            {
                if(!leftEqualsRight)// Ensure that the column can remove
                {
                    left ++;
                    if (left == right) leftEqualsRight = true;//Updating the flag
                }
            }
            else// m[bottom][left]>val (cannot be equals as we checked it in the beginning of the iteration)
            //If val is smaller than the smallest bottom row value (most left) so its smaller from all the rest of the 
            //values in the bottom row, there for we can remove that row.
            {
                if(!topEqualsBottom)
                {
                    bottom--;
                    if (top == bottom) topEqualsBottom =true;//Updating the flag
                }
            }
        }
        return false;// As it’s mentioned in the loop description if the loop is over its mean the size of the focus area is 1X1. 
        // The loop signature confirmed that the last index is not equal to val and will be returned
    }

    //Question 1, Section B part 2:
    /**
     * Finding if val is in a given two-dimensional array (The method Test(array) must return true for the array)
     * The Test method ensuring that every value in a row is equal or smaller than all the values in the next row. 
     * Which cause to every column to be increasing (not strong increasing) sequence (top to bottom).
     * Because every column to be increasing sequence we will use binary search on the first column to find 2 values.
     * "Start value" should be smaller or equal to val.
     * "End value" should be greater than val.
     * Both values should be close as possible to val, in purpose to insure it we will demand that the index difference will be 1
     *There might be equal values in the column if val is higher than the index value its mean val can be
     *only in the lowest row index or below (as the index value is equal or greater than all the index values of the previous rows
     *same principle if the value is smaller than the index value
     * Because the "Start value" is smaller or equal to val and all the values in the previous rows are smaller than the "Start value"
     * so they are smaller from val and which mean we can remove them 
     * Because the "End value" is greater than val and all the values in the next rows are greater than the "End value"
     * so they are greater from val and which mean we can remove them.
     * After we locate the 2 values that val might be in their rows, we will check all the values in each row.
     * 
     * Time complexity = O(n) - The binary search to find the two rows were val might be is log(n), the in the worst case there
     * are 2 rows to check (Linear search = O(n) so its O(log(n) + 2*O(n) = O(n)
     * Space complexity = O(1) - The array was pass by reference and there is final number of parameters.
     * 
     * @param m The array to be checked (the array must return true for the test method).
     * @param val The value to be found in the array.
     * 
     * @return True if val is in the array if its isn’t false.
     */

    public static boolean findValTest (int [][] m, int val)
    {
        if( m == null || m.length == 0)// If the array is not initialize or the size of the array is 0, so val is not existing  
        // and false will be returned
        {
            return false;
        }
        return findValTest(m,val,0,m.length-1);// After confirming there is values in the array, a overloaded method 
        // will be called with additional start and end parameters for the binary search in the first column.
    }

    private static boolean findValTest (int [][] m, int val, int start, int end)
    {
        if (val <= m[start][0])// If val is smaller or equals to the first value in the first column its mean it cannot be in any
        //of the next rows as all their values are greater than first row first value.
        {
            return findValTest(m[start], val);// call of overloaded method that check if val is in 1 dimensional array(first row)

        }

        if (val >= m[end][0])// If val is bigger or equals to the first value in the last column its mean it cannot be in any
        //of the previous rows as all their values are smaller than last row first value.
        {
            return findValTest(m[end], val);// call of overloaded method that check if val is in 1 dimensional array(last row)
        }
        //binary search in the first column
        while(end-start != 1)//loop will until two following indexes will be found, that the value of first index in the first column 
        //will be smaller or equal to val and the second index value in the first column will be greater than val
        // and both will be closest as possible to val
        {
            int index = (start + end)/2;//Middle index between start and end 
            if(m[index][0] <= val)// If the value of the index (in the first column) is smaller than val, so beacuse all the previous
            //rows values are smaller than the value of the index they are also smaller than val and rows can be removed.
            {
                start = index;//removing all the rows before the index row
            }
            else // If the value of the index (in the first column) is greater than val, so because all the next
            //rows values are greater than the value of the index they are also greater than val and rows can be removed.
            {
                end = index;//removing all the rows after the index row
            }
        }
        if(findValTest(m[start],val))//call of overloaded method that check if val is in 1 dimensional array start index row array
        // the row which its first value is smaller or equal to value and close as possible to val
        {
            return true;// if val is in the start index row array return true
        }
        return findValTest(m[end],val);//call of overloaded method that check if val is in 1 dimensional array end index row array
        // the row which its first value is greater than value and close as possible to val
    }

    private static boolean findValTest (int [] m, int val)// search in 1 dimensional array
    {
        for(int i = 0; i < m.length ; i++)// loop will run on the values in the array, if the val is equal to any of the values 
        //return true if not continue to run on the next values.
        {
            if(m[i] == val)
            {
                return true;
            }
        }
        return false;//the loop ran on all the values on the array and val was not equal to any of the values return false.
    }

    // Question 2
    /**
     * Check the mount of increasing sequence in the array
     * increasing sequence need to have more than 1 value, for increasing sequence in size n there are 
     * n-1 - size 2 increasing sequence, n-2  - size 3 increasing sequence, n-3  - size 4 increasing sequence ... 
     * n-(n-1)=1 size n increasing sequence.
     * The total amount increasing sequence (size 2 and above) for increasing sequence size n is equal to sum of Arithmetic progression
     * of 1,2,3,4...n-2,n-1 -> 1+2+3+4...+(n-2)+(n-1) = n(1+n)/2.
     * The method will check how many main increasing sequences there are and the calculate all the sub-increasing sequence
     * and sum it all together
     *
     * @param a The Array to be checked.
     * 
     * @return The number of increasing sequence in the array
     */
    public static int strictlyIncreasing (int[] a)
    {
        int counter = 0, sum = 0;
        for(int i =1; i <a.length; i++)//loop that run on all the values in the array. start from index 1 because it requires
        //at least 2 values for increasing sequence in the array, from the same reason the counter also starts from 0
        {
            if (a[i] > a[i-1])// start of new increasing sequence in the array or continuation of existing one 
            {
                counter ++;
            }
            else// end of increasing sequence or new increasing sequence was not started
            {
                sum += counter*(1+counter)/2;// If its the end of increasing sequence the calculation will provide the amount of 
                //sub-increasing sequence and sum its with sum. If new increasing sequence was not started so 
                //n =0 and the calculation result will be 0
                counter = 0;//resetting the counter
            }
        }
        if (counter != 0)//if the loop pass on all the array indexes but the array end up with increasing sequence so 
        //its required to calculate the sub-increasing sequence and add it to sum
        {
            sum += counter*(1+counter)/2;
        }
        return sum;// return the total sum
    }
    // Question 3
    /**
     * Check the length of the longest flat sequence in a given array.
     * Flat sequence is a sequence that composed from two consecutive numbers.
     * The method will check the 2 possible options:
     *
     *The first option will check if 2 following values are (odd and even) or ( even and even ) or (odd and odd)
     * if its (odd and even) the even value should be greater by one from the odd 
     * and if its (even and even) or (odd and odd) they should be equals for a flat sequence.
     * As there only one even value that is greater by one from specific odd value so every sequence that comply with 
     * this option will be flat sequence.
     * EX: 6,5,5,4,3 - 6 and 5 are even and odd value that the even is greater by one from the odd. 5 is equal to the odd number 5
     * so the length of the flats sequence so for is 3, however when we reach 4 we have even and odd number but the odd
     * is greater than the even this case isn’t relevant for this option and a new flat sequence will start with 4 and 3.
     * 
     * The second option is the same as the first but this time the odd number should be greater than the even number by one
     * EX 6,5,5,4,3 - 6 and 5 are odd and even, but the even value is greater so the length of the flat sequence start from 6 is one.
     * The next two value is 5 and 5 two odd equals values so the length of the sequence will be 2 and then we reach the value 4.
     * 4 and 5 are odd and even values and the odd value is greater by 1 than the even so the length of the flat sequence will be 3.
     * However when we reach 3, we have odd and even values (4,3) but the even is greater than the odd and this case 
     * is not cover in this option so a new flat sequence will start from 3.
     * 
     *The method will the compare the longest flat sequence of both option and return the longest one.
     * 
     * @param a The Array to be checked.
     * 
     * @return The number of increasing sequence in the array
     */

    public static int longestFlatSequence (int[] arr)
    {
        if( arr == null || arr.length == 0)//If there are no values in the array the longest flat sequence is 0
        {
            return 0;
        }
        if (arr.length == 1)// if there is only 1 value so there is only 1 flat sequence that its length is 1
        {
            return 1;
        }
        return Math.max((longestFlatSequence(arr,0,1,0,true)),(longestFlatSequence(arr,0,1,0,false)));// comparison between
        //the option when the flat sequence in composed from even number the greater by 1 from odd to the option
        //when the flat sequence composed from odd number that greater from the even by 1.
    }

    private static int longestFlatSequence (int[] arr, int index,int count,int max, boolean evenGreaterThanoOdd)
    // overloaded method that check the longest flat sequence for each option
    {
        if(index == arr.length - 1)//all the indexes of the array were checked.
        //If he flat Sequence that start from the last value of the array cannot be longer than 1. 
        //So it cannot be longer than the longest flat sequence as we already confirmed that the array contain values.
        //If its part from the flat sequence that start from previous index it was already calculated. 
        {
            return Math.max(count,max);// return the  longest flat sequence by comparing the max to the current flat sequence counter
        }

        if(evenGreaterThanoOdd)// check the option that the even is greater by one from the odd
        {
            if (arr[index] + arr[index]%2 == arr[index+1] + arr[index+1]%2)//for the case of 2 equals values if will return true as its
            //the same calculation, and if its odd and even true will be returned only if the even is grater by 1 than the odd.
            // EX 4 + 4%2 = 4 = 3+1 = 3 + 3%2
            {
                count++;// current flat sequence length increases by 1
            }
            else // If the values are not equals and the even value is not greater than the odd it the end of flat sequence
            {
                if (count > max)
                {
                    max = count;// update the max length of all the flat sequence that were checked so far
                }
                count =1;//reset the counter, start with 1 because we always compare 2 values as, individual value is 
                //flat sequence with length 1.
            }
        }
        else// check the option that the odd is greater by one from the even
        {
            if (arr[index] - arr[index]%2 == arr[index+1] - arr[index+1]%2)//For the case of 2 equals values if will return true as its
            //the same calculation, and if its odd and even true will be returned only if the odd is greater by 1 than the even.
            // EX 4 - 4%2 = 4 = 5-1 = 5 - 5%2
            {
                count++;// current flat sequence length increases by 1
            }
            else // If the values are not equals and the odd value is not greater than the even it the end of flat sequence
            {
                if (count > max)
                {
                    max = count;// update the max length of all the flat sequence that were checked so far
                }
                count =1;//reset the counter, start with 1 because we always compare 2 values as, individual value is 
                //flat sequence in length 1
            }
        }
        return longestFlatSequence(arr,index + 1,count,max,evenGreaterThanoOdd);// after the current index was checked, the method will
        //call itself but with the next index
    }
    // Question 4
    /**
     * Find the highest path value in a array
     * The value of a path is the sum of all the values of the indexes which the path pass through.
     * The array indexes value can only be 0,1,-1 and the path cannot pass through indexes if their value is -1 
     * and cannot pass through the same index twice.
     * For even row index that path can procced only down or right and for odd row index it can procced left or down.
     * The method will backtracking all the possible path and calculate the value of the path for each proceeding option.
     * During the calculation every index that the path  pass through will be marked to ensure the path is not passing in the 
     * index twice. A path calculation will end once the path have no option to procced in the array.
     * After the option were calculated the program will compare the values and the highest value will be returned.
     * 
     * @param a The Array to be checked.
     * 
     * @return The highest path value.
     */
    public static int findMaximum(int[][] mat)
    {
        if(mat == null || mat.length == 0 || mat[0][0] == -1)// Confirming that the array isn’t empty, and after it confirming 
        //that the first index value isn’t -1 as the path cannot pass through the value -1.
        {
            return -1;// if there is no path at all the value -1 will be returned
        }
        return findMaximum(mat,0,0,0);// Calling an overload method to check all the possible paths and thier values. 
    }

    public static int findMaximum(int[][] mat, int row, int col, int sum)
    {
        if(row == mat.length || col == mat[0].length || row < 0 || col < 0 || mat[row][col] == -1)// If the path exceed the array
        // boundaries or if it passing through index with the value -1 its mean that the path is over.
        {
            return sum;// the path is over and therefor the value of the path will be retuned for comparation with other path values.
        }
        int tempValue = mat[row][col];//saving the current index value in a temp parameter.
        mat[row][col] = -1;// To ensure the path will not pass the same index twice the current index value is set to -1.
        if(row%2 == 0)// checking if the row index is even
        {
            int sumIfMoveDown = findMaximum(mat,row + 1, col, sum + tempValue);// Adding the current index value to the path value
            //and checking that value of the path if it will procced down.
            int sumIfMoveRight = findMaximum(mat,row, col + 1, sum + tempValue);// Adding the current index value to the path value
            //and checking that value of the path if it will procced right.
            mat[row][col] = tempValue;// After the path that pass in the current index was checked the value of the index will be
            // set to its original value for other paths that might pass in the index.
            if(sumIfMoveDown > sumIfMoveRight)// After both proceeding option of the path were checked their value will be compared 
            // and the highest value will be returned.
            {
                return sumIfMoveDown;
            }
            else
            {
                return sumIfMoveRight;
            }
        }
        else// if a row index is not even so its must be odd.
        {
            int sumIfMoveDown = findMaximum(mat,row + 1, col, sum + tempValue);// Adding the current index value to the path value
            //and checking that value of the path if it will procced down.
            int sumIfMoveLeft = findMaximum(mat,row, col - 1, sum + tempValue);// Adding the current index value to the path value
            //and checking that value of the path if it will procced left.
            mat[row][col] = tempValue;// after the path that pass in the current index was checked the value of the index will be
            // set to its original value for other paths that might pass in the index.
            if(sumIfMoveDown > sumIfMoveLeft)// after both proceeding option of the path were checked their value will be compared 
            // and the highest value will be returned.
            {
                return sumIfMoveDown;
            }
            else
            {
                return sumIfMoveLeft;
            }
        }
    }
}

