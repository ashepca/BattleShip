/** Filename Battleship.java
* Written by @author Andrew Shepherd
* Written on 15-Apr-2017
* This program plays a game of Battleship between the computer and player 
**/

import java.util.Scanner;
import javax.swing.JOptionPane;


/** This is the main method
* @return void
* @param args
* This method starts / finishes the battleship game and allows the
* computer and player to place their ships and calls the torpedo methods
**/ 

public class BattleShip
{
    public static void main(String[] args)
    {
        String player;
        Scanner keyboard = new Scanner(System.in);
        int row = 0;
        int col = 0;
        int computerHits = 0;
        int playerHits = 0;
        int compWins = 0;
        int playWins = 0;
        char[][] playerBoard = { {' ', '1', '2', '3', '4', '5'},
                               {'A', ' ', ' ', ' ', ' ', ' '},
                               {'B', ' ', ' ', ' ', ' ', ' '},
                               {'C', ' ', ' ', ' ', ' ', ' '},
                               {'D', ' ', ' ', ' ', ' ', ' '},
                               {'E', ' ', ' ', ' ', ' ', ' '} };
        char[][] computerBoardVisible = { {' ', '1', '2', '3', '4', '5'},
                                        {'A', ' ', ' ', ' ', ' ', ' '},
                                        {'B', ' ', ' ', ' ', ' ', ' '},
                                        {'C', ' ', ' ', ' ', ' ', ' '},
                                        {'D', ' ', ' ', ' ', ' ', ' '},
                                        {'E', ' ', ' ', ' ', ' ', ' '} };
        char[][] computerBoardInvisible = { {' ', '1', '2', '3', '4', '5'},
                                          {'A', ' ', ' ', ' ', ' ', ' '},
                                          {'B', ' ', ' ', ' ', ' ', ' '},
                                          {'C', ' ', ' ', ' ', ' ', ' '},
                                          {'D', ' ', ' ', ' ', ' ', ' '},
                                          {'E', ' ', ' ', ' ', ' ', ' '} };
        
        JOptionPane.showMessageDialog(null, "Welcome to the game of BattleShip !!!");
	System.out.print("\nEnter your name please >");
        player = keyboard.nextLine();
        System.out.println("\nThe BattleShip Game Board looks like this:\n");

	for(row = 0; row < 6; row++)
        {
            for(col = 0; col < 6; col++)            
	    {
                System.out.print(playerBoard[row][col]);             
            }
            System.out.println();
        }

	System.out.println("\nLegend:\nH - hit\nM - miss\nS - ship\n");  

	computerPlacesShips(computerBoardInvisible);   /** Computer places ships **/
	playerPlacesShips(playerBoard);  /** Player places ships **/

        System.out.print("\nGet ready " + player + " the battle begins!\n");

	while(compWins < 2 && playWins < 2)  /** keep count of hits to determine winner and end game **/
        {
	    computerHits = computerTorpedo(playerBoard);
	    compWins = compWins + computerHits;
            if(compWins == 2)	
            {	   
                JOptionPane.showMessageDialog(null, "The Computer Wins!!!");
	    }	        
            else
            {
                playerHits = playerTorpedo(computerBoardInvisible, computerBoardVisible);
		playWins = playWins + playerHits;
                if(playWins == 2)
                {
                    JOptionPane.showMessageDialog(null, "Congratulations " + player + ", you win!!!");
                }      
            }
        }
    }


    /** The computerTorpedo method
    * @return int
    * @param playerBoard
    * This method randomly selects the coordinate for the computer to torpedo
    **/ 

    public static int computerTorpedo(char[][] playerBoard)
    {	
        int row = ((int)(Math.random() * 100) % 5 + 1);
        int col = ((int)(Math.random() * 100) % 5 + 1);
        int hits = 0;

        System.out.println("\nThe computer fires a missile at you!");
        
        while(playerBoard[row][col] == 'H' || playerBoard[row][col] == 'M')  /** prevents duplicate shots **/
        {
            row = ((int)(Math.random() * 100) % 5 + 1);
            col = ((int)(Math.random() * 100) % 5 + 1);
        }

        if(playerBoard[row][col] == 'S')     /** indicates a hit or miss is made **/
        {
            System.out.println("\nThe computer hit a ship!");
            playerBoard[row][col] = 'H';
            hits = 1;	
        }
        else
        {
            System.out.println("\nThe computer missed.");
            playerBoard[row][col] = 'M';	    
        }

        System.out.println("\nYour BattleShip Game Board\n");  /** shows current status of player board **/

        for(row = 0; row < 6; row++)
        {
            for(col = 0; col < 6; col++)            
            {
                System.out.print(playerBoard[row][col]);             
            }
            System.out.println();
        }

        return hits; 	
    }


    /** The playerTorpedo method
    * @return int
    * @param computerBoardInvisible, computerBoardVisible
    * This method allows the player to select a coordinate to torpedo
    **/ 

    public static int playerTorpedo(char[][] computerBoardInvisible, char[][] computerBoardVisible)
    {
        int row = 0;
        int col = 0;
        Scanner keyboard = new Scanner(System.in);
        String torpedo1;
        int hits = 0;
        char torpedo1Row;
        char torpedo1Col;
        int valid1 = 0;
        int valid2 = 0; 	

        System.out.print("\nYour turn, enter coordinates to fire a torpedo, e.g. E2 >");
        torpedo1 = keyboard.nextLine();
        torpedo1Row = torpedo1.charAt(0);
        torpedo1Col = torpedo1.charAt(1);

        while(valid1 == 0)  /** checks to confirm valid coordinates selected **/
        {
            if((torpedo1Row == 'A' || torpedo1Row == 'B' || torpedo1Row ==  'C' || torpedo1Row == 'D' || torpedo1Row == 'E') &&
                (torpedo1Col == '1' || torpedo1Col =='2' || torpedo1Col == '3' || torpedo1Col == '4' || torpedo1Col == '5'))
            {
                valid1 = 1;
            }
            else
            {
                System.out.print("Invalid coordinate, please try again, e.g. A1 >");
                torpedo1 = keyboard.nextLine();
                torpedo1Row = torpedo1.charAt(0);
                torpedo1Col = torpedo1.charAt(1);
            }
        } 

        switch(torpedo1Row)  /** assigns int values for char letter row input **/
        {
            case 'A':
                row = 1;
                break;
            case 'B':
                row = 2;
                break;
            case 'C':
                row = 3;
                break;
            case 'D':
                row = 4;
                break;
            case 'E':
                row = 5;
                break;
        }
 
        switch(torpedo1Col)  /** assigns int values for char number column input **/
        {
            case '1':
                col = 1;
                break;
            case '2':
                col = 2;
                break;
            case '3':
                col = 3;
                break;
            case '4':
                col = 4;
                break;
            case '5':
                col = 5;
                break;
        }

        while(computerBoardVisible[row][col] == 'H' || computerBoardVisible[row][col] == 'M')  /** prevents duplicate shots **/
        {
            System.out.print("You already targeted that coordinate, please try again >");
            torpedo1 = keyboard.nextLine();	
            torpedo1Row = torpedo1.charAt(0);
            torpedo1Col = torpedo1.charAt(1);

            while(valid2 == 0)  /** checks to confirm valid coordinates selected **/
            {
                if((torpedo1Row == 'A' || torpedo1Row == 'B' || torpedo1Row ==  'C' || torpedo1Row == 'D' || torpedo1Row == 'E') &&
                    (torpedo1Col == '1' || torpedo1Col =='2' || torpedo1Col == '3' || torpedo1Col == '4' || torpedo1Col == '5'))
                {
                    valid2 = 1;
                }
                else
                {
                    System.out.print("Invalid coordinate, please try again, e.g. A1 >");
                    torpedo1 = keyboard.nextLine();
                    torpedo1Row = torpedo1.charAt(0);
                    torpedo1Col = torpedo1.charAt(1);
                }
            } 

            switch(torpedo1Row)  /** assigns int values for char letter row input **/
            {
                case 'A':
                    row = 1;
                    break;
                case 'B':
                    row = 2;
                    break;
                case 'C':
                    row = 3;
                    break;
                case 'D':
                    row = 4;
                    break;
                case 'E':
                    row = 5;
                    break;
            }
 
            switch(torpedo1Col)   /** assigns int values for char number column input **/
            {
                case '1':
                    col = 1;
                    break;
                case '2':
                    col = 2;
                    break;
                case '3':
                    col = 3;
                    break;
                case '4':
                    col = 4;
                    break;
                case '5':
                    col = 5;
                    break;
            } 
        }

        if(computerBoardInvisible[row][col] == 'S')  /** indicates a hit or miss is made **/
        {
            System.out.println("\nYou hit a ship!");
            computerBoardVisible[row][col] = 'H';
            computerBoardInvisible[row][col] = 'H';
            hits = 1;
        }
        else
        {
            System.out.println("\nYou missed.");
            computerBoardVisible[row][col] = 'M';
            computerBoardInvisible[row][col] = 'M';	    
        }
	
        System.out.println("\nComputer's BattleShip Game Board\n");   /** shows current status of computer board **/

        for(row = 0; row < 6; row++)
        {
            for(col = 0; col < 6; col++)            
            {
                System.out.print(computerBoardVisible[row][col]);             
            }
            System.out.println();
        } 

        return hits;
    }	


    /** The computerPlacesShips method
    * @return void
    * @param computerBoardInvisible
    * This method allows the computer to place two random ships
    **/ 

    public static void computerPlacesShips(char[][] computerBoardInvisible)
    {
        int row = ((int)(Math.random() * 100) % 5 + 1);
        int col = ((int)(Math.random() * 100) % 5 + 1);

        computerBoardInvisible[row][col] = 'S';
	
        while(computerBoardInvisible[row][col] == 'S')  /** loop to prevent picking the same spot for 2nd ship **/
        {
            row = ((int)(Math.random() * 100) % 5 + 1);
            col = ((int)(Math.random() * 100) % 5 + 1);
        }	

        computerBoardInvisible[row][col] = 'S';

        System.out.println("The computer has placed two 1x1 ships on it's board.");
    }


    /** The playerPlacesShips method
    * @return void
    * @param playerBoard
    * This method allows the player to place two ships
    **/ 

    public static void playerPlacesShips(char[][] playerBoard)
    {
        int valid3 = 0; 
        int valid4 = 0;
        String ship1;
        String ship2;
        char ship1Row;
        char ship1Col;
        char ship2Row;
        char ship2Col;
        int row = 0;
        int col = 0;
        Scanner keyboard = new Scanner(System.in);     

        System.out.print("\nYou have two 1x1 ships to place on your board.\n\n");
        System.out.print("Enter coordinates to place ship 1, e.g. C4 >");
        ship1 = keyboard.nextLine();
        ship1Row = ship1.charAt(0);
        ship1Col = ship1.charAt(1);
        
	while(valid3 == 0)   /** checks to confirm valid coordinates selected **/
        {
            if((ship1Row == 'A' || ship1Row == 'B' || ship1Row ==  'C' || ship1Row == 'D' || ship1Row == 'E') &&
	        (ship1Col == '1' || ship1Col =='2' || ship1Col == '3' || ship1Col == '4' || ship1Col =='5'))
            {
                valid3 = 1;
            }
            else
            {
                System.out.print("Invalid coordinate, please try again, e.g. A1 >");
                ship1 = keyboard.nextLine();
                ship1Row = ship1.charAt(0);
                ship1Col = ship1.charAt(1);
            }
        } 

        System.out.print("Enter coordinates to place ship 2, e.g. B3 >");
        ship2 = keyboard.nextLine();
        ship2Row = ship2.charAt(0);
        ship2Col = ship2.charAt(1); 
	
        while(valid4 == 0)  /** checks to confirm valid or duplicate coordinates selected **/
        {
            if((ship2Row == 'A' || ship2Row == 'B' || ship2Row ==  'C' || ship2Row == 'D' || ship2Row == 'E') &&
                (ship2Col == '1' || ship2Col =='2' || ship2Col == '3' || ship2Col == '4' || ship2Col =='5'))
            {
                if(ship2Row == ship1Row && ship2Col == ship1Col)
                {
                    System.out.print("Duplicate coordinate, please try again >");
                    ship2 = keyboard.nextLine();
                    ship2Row = ship2.charAt(0);
                    ship2Col = ship2.charAt(1);
                }
                else
                {
                    valid4 = 1;
                }  
            }
            else	    
            {
                System.out.print("Invalid coordinate, please try again, e.g. C5 >");
                ship2 = keyboard.nextLine();
                ship2Row = ship2.charAt(0);
                ship2Col = ship2.charAt(1);
            } 
        } 

        switch(ship1Row)   /** assigns int values for char letter row input **/
        {
            case 'A':
                row = 1;
                break;
            case 'B':
                row = 2;
                break;
            case 'C':
                row = 3;
                break;
            case 'D':
                row = 4;
                break;
            case 'E':
                row = 5;
                break;
        }
 
        switch(ship1Col)  /** assigns int values for char number column input **/
        {
            case '1':
                col = 1;
                break;
            case '2':
                col = 2;
                break;
            case '3':
                col = 3;
                break;
            case '4':
                col = 4;
                break;
            case '5':
                col = 5;
                break;
        }
       
        playerBoard[row][col] = 'S';

        switch(ship2Row)
        {
            case 'A':
                row = 1;
                break;
            case 'B':
                row = 2;
                break;
            case 'C':
                row = 3;
                break;
            case 'D':
                row = 4;
                break;
            case 'E':
                row = 5;
                break;
        }
 
        switch(ship2Col)
        {
            case '1':
                col = 1;
                break;
            case '2':
                col = 2;
                break;
            case '3':
                col = 3;
                break;
            case '4':
                col = 4;
                break;
            case '5':
                col = 5;
                break;
        }

        playerBoard[row][col] = 'S';

        System.out.println("\nYour BattleShip Game Board\n");    /** shows current status of player board **/

        for(row = 0; row < 6; row++)
        {
            for(col = 0; col < 6; col++)            
            {
                System.out.print(playerBoard[row][col]);             
            }
            System.out.println();
        } 
    }				
}