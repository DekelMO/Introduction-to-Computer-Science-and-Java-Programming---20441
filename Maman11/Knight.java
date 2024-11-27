/*Kinigt Class - The program will print all the possible moves of the Knight on the chess board,
according to the Knight current location which provided by the user. */ 
// Author: Dekel Moens
// Date: 04/03/2022

import java.util.Scanner; 
public class Knight 
{ 
    public static void main (String [] args) 
    {
        /*constants - Knight possible moves are 2 column slots and 1 row slot or 2 row slots and 1 column slot. 
        The chess board size is 8x8, therefore slots need to be between 1 to 8..*/
        final int MOVE_TWO = 2, MOVE_ONE = 1, MAX_SLOT = 8, MIN_SLOT = 1;
        int endingCol, endingRow;
        Scanner scan = new Scanner (System.in); 
        System.out.println ("This program reads two integers which " + 
            "represent the knight's location on the chess board: "); 
        //Kinight starting location will be inserted by the user.
        System.out.println ("Please enter the number of row"); 
        int row = scan.nextInt(); 
        System.out.println ("Please enter the number of column"); 
        int col = scan.nextInt(); 

        if(((MIN_SLOT <= col) && (col <= MAX_SLOT)) && ((MIN_SLOT <= row) && (row <= MAX_SLOT))) // confirming legal inputs
        //program checking all the possible moves
        {
            System.out.println("Moves:");

            // checking the move: two steps down one step right
            endingCol = col + MOVE_TWO;
            endingRow = row + MOVE_ONE;
            if(((MIN_SLOT <= endingCol) && (endingCol <= MAX_SLOT)) && ((MIN_SLOT <= endingRow) && (endingRow <= MAX_SLOT)))
            {
                System.out.println(endingRow + " " + endingCol);
            }
            // checking the move: two steps down one step left
            endingCol = col + MOVE_TWO;
            endingRow = row - MOVE_ONE;
            if(((MIN_SLOT <= endingCol) && (endingCol <= MAX_SLOT)) && ((MIN_SLOT <= endingRow) && (endingRow <= MAX_SLOT)))
            {
                System.out.println(endingRow + " " + endingCol);
            }
            // checking the move: two steps up one step right
            endingCol = col - MOVE_TWO;
            endingRow = row + MOVE_ONE;
            if(((MIN_SLOT <= endingCol) && (endingCol <= MAX_SLOT)) && ((MIN_SLOT <= endingRow) && (endingRow <= MAX_SLOT)))
            {
                System.out.println(endingRow + " " + endingCol);
            }
            // checking the move: two steps up one step left
            endingCol = col - MOVE_TWO;
            endingRow = row - MOVE_ONE;
            if(((MIN_SLOT <= endingCol) && (endingCol <= MAX_SLOT)) && ((MIN_SLOT <= endingRow) && (endingRow <= MAX_SLOT)))
            {
                System.out.println(endingRow + " " + endingCol);
            }
            // checking the move: two steps left one step down
            endingRow = row + MOVE_TWO;
            endingCol = col + MOVE_ONE;
            if(((MIN_SLOT <= endingCol) && (endingCol <= MAX_SLOT)) && ((MIN_SLOT <= endingRow) && (endingRow <= MAX_SLOT)))
            {
                System.out.println(endingRow + " " + endingCol);
            }
            // checking the move: two steps left one step up
            endingRow = row + MOVE_TWO;
            endingCol = col - MOVE_ONE;
            if(((MIN_SLOT <= endingCol) && (endingCol <= MAX_SLOT)) && ((MIN_SLOT <= endingRow) && (endingRow <= MAX_SLOT)))
            {
                System.out.println(endingRow + " " + endingCol);
            }
            // checking the move: two steps right one step down
            endingRow = row - MOVE_TWO;
            endingCol = col + MOVE_ONE;
            if(((MIN_SLOT <= endingCol) && (endingCol <= MAX_SLOT)) && ((MIN_SLOT <= endingRow) && (endingRow <= MAX_SLOT)))
            {
                System.out.println(endingRow + " " + endingCol);
            }
            // checking the move: two steps right one step up
            endingRow = row - MOVE_TWO;
            endingCol = col - MOVE_ONE;
            if(((MIN_SLOT <= endingCol) && (endingCol <= MAX_SLOT)) && ((MIN_SLOT <= endingRow) && (endingRow <= MAX_SLOT)))
            {
                System.out.println(endingRow + " " + endingCol);
            }
        }
        else
        {
            System.out.println("input is illegal");
        }

    } // end of method main 
} //end of class Knight 