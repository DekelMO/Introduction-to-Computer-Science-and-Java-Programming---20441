/*Chess Class - The program will check if there is a threat bewtween two Chessmen and print description of the treat.
. The type of the chessmen and location will be inserted by the user. */ 
// Author: Dekel Moens
// Date: 04/03/2022

import java.util.Scanner; 
public class Chess 
{ 
    public static void main (String [] args) 
    { 
        /*constants - The chess board size is 8x8, therefore slots need to be between 1 to 8.
        Knight moves combained from two and one slots moves.*/
        final int MAX_SLOT  = 8, MIN_SLOT = 1, MOVE_TWO = 2, MOVE_ONE =1;
        //chessmen types
        final char BISHOP = 'b', ROOK = 'r', KNIGHT = 'k';
        Scanner scan = new Scanner (System.in); 
        System.out.println("Please enter the type"+ 
            " of the first chessman"); 
        char first = scan.next().charAt(0); 
        System.out.println ("Please enter the number of row"); 
        int row1 = scan.nextInt(); 
        System.out.println ("Please enter the number of column"); 
        int col1 = scan.nextInt(); 
        System.out.println("Please enter the type"+ 
            " of the second chessman"); 
        char second = scan.next().charAt(0); 
        System.out.println ("Please enter the number of row"); 
        int row2 = scan.nextInt(); 
        System.out.println ("Please enter the number of column"); 
        int col2 = scan.nextInt(); 

        //confirming diffrent Chessmen were iserted
        if(first == second)
        {
            System.out.println("Chessmen should be different from each other");
        }

        //confirming inserted positions are legal, by checking if any of the values is outside the board 
        else if((MIN_SLOT > row1) || (row1 > MAX_SLOT) || (MIN_SLOT > row2) || (row2 > MAX_SLOT) || (MIN_SLOT > col1) 
        || (col1 > MAX_SLOT) || (MIN_SLOT > col2) || (col2 > MAX_SLOT))
        {
            System.out.println("Position is not legal");
        }

        // confirming diffrent Chessmen positions
        else if((row1 == row2) && (col1 == col2))
        {
            System.out.println("Chessmen positions should not be identical");
        }

        //checking for treat between the Chessmen
        else
        {
            //Threats between Bishop and Rook
            if((first == BISHOP && second == ROOK) || (first == ROOK && second == BISHOP))
            {
                if((row1 == row2) || (col1 == col2))// The rook threatening any other chessman with the same column or row number
                {
                    System.out.println("rook threats bishop");
                }
                /* The bishop threatening any other chessman with the same diagonal.
                The row distance and the column distance(absolute distance) bewteen two slots of the same diagonal
                are always equal. we will use this method to check if there is a threat by the bishop*/  
                else if((Math.abs(row1 - row2)) == (Math.abs(col1 - col2)))//True value mean that the two chessmen share diagonal
                {
                    System.out.println("bishop threats rook");
                }

                else
                {
                    System.out.println("no threat");
                }
            }

            //Threats between Bishop and Knight
            else if((first == BISHOP && second == KNIGHT) || (first == KNIGHT && second == BISHOP))
            {
                if((Math.abs(row1 - row2)) == (Math.abs(col1 - col2))) //please see "bishop threat rook" comment   
                {
                    System.out.println("bishop threats knight");
                } 

                /* The Knight threatening any other chessman in distance of 2 row slots and 1 column slot
                or 2 column slot and 1 row slot.*/  
                else if(((Math.abs(row1 - row2) == MOVE_TWO) && (Math.abs(col1 - col2) == MOVE_ONE)) || 
                ((Math.abs(row1 - row2) == MOVE_ONE) && (Math.abs(col1 - col2) == MOVE_TWO)))
                {
                    System.out.println("knight threats bishop");
                }

                else
                {
                    System.out.println("no threat");
                }
            }

            //Threats of Kinight and Rook
            else if((first == KNIGHT && second == ROOK) || (first == ROOK && second == KNIGHT))
            {
                if((row1 == row2) || (col1 == col2))// //please see "rook threat bishop" comment   
                {
                    System.out.println("rook threats knight");
                }
                else if((Math.abs(row1 - row2) == MOVE_TWO && Math.abs(col1 - col2) == MOVE_ONE) || 
                (Math.abs(row1 - row2) == MOVE_ONE&& Math.abs(col1 - col2) == MOVE_TWO))//please see "knight threat bishop" comment   
                {
                    System.out.println("knight threats rook");
                }

                else
                {
                    System.out.println("no threat");
                }
            }
        }        
    } // end of method main 
} //end of class Chess 