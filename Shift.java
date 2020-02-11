import java.util.Random;
import java.util.Scanner;

/**
 * Write a description of class Shift here.
 *
 * @author Jordan Breen
 * @version 02/09/2020
 */

public class Shift
{
    private static final int NON_CHIP_FLAG = 0;    // signifies an index occupied by a "non-chip" value 
    private static int[] gameBoard;                // a one dimensional array (range: [9-15]) representing a game board
    private static int[] chips;                    // chips are signified by integer values, 1, 2, 3, [4], [5]
    
    private static Random rand;
    private static final int GB_UP_B = 6;
    private static final int GB_LOW_B = 9;
    private static final int CHIP_UP_B = 2;
    private static final int CHIP_LOW_B = 3;
    
    private static String[] players;
    private static int numberOfPlayers;
    private static int currentPlayer;
    
    private boolean isFirstTurn;
    
    public Shift()
    {
        rand = new Random();
        gameBoard = new int[rand.nextInt(GB_UP_B) + GB_LOW_B];
        chips = new int[rand.nextInt(CHIP_UP_B) + CHIP_LOW_B];
        Number_Chips();
        Init_Chips_Game_Board();
        numberOfPlayers = 0;
        currentPlayer = 1;
        isFirstTurn = true;
    }

    public Shift(int inGameBoardSize)
    {
        rand = new Random();
        gameBoard = new int[inGameBoardSize];
        chips  = new int[rand.nextInt(2) + 3];
        Number_Chips();
        Init_Chips_Game_Board();
        numberOfPlayers = 0;
        currentPlayer = 1;
        isFirstTurn = true;
    }
    
    private static void Number_Of_Players(int inNumberOfPlayers, String... inPlayerNames)
    {
        currentPlayer = rand.nextInt(inNumberOfPlayers) + 1;
        numberOfPlayers = inNumberOfPlayers;
        players = new String[inNumberOfPlayers];
        for( int i = 0; i < inNumberOfPlayers; i++ )
        {
            players[i] = inPlayerNames[i];
        }
    }
    
    private static void Next_Turn()
    {
        if(currentPlayer == numberOfPlayers)
        currentPlayer = 1;
        else
        currentPlayer++;
    }
    private static void Current_Turn()
    {
        System.out.println("Player " + currentPlayer + " (" + players[currentPlayer - 1] + "): Your Turn");
    }
    
    private static void Victory_Print()
    {
        System.out.println("Player " + currentPlayer + " (" + players[currentPlayer - 1] + "): You Win!");
    }
    
    private static void Number_Chips()
    {
        for(int i = 0; i < chips.length; i++) // For each chip
        {
            chips[i] = i + 1; // index[0] = 1, index[1] = 2, etc
        }
    }
    
    private static void Init_Chips_Game_Board()
    {
        boolean success = false;
        int numChips = chips.length;
        int[] chipPlacement = new int[numChips];
        int posOnGameBoard;
        while(success == false)
        {
            // Resets the test to see if all chips are AT MOST 3 steps away from each other: //
            boolean[] threeOrLessAway = new boolean[numChips];
            
            // Resets the gameBoard: //
            for(int i = 0; i < gameBoard.length; i++) // For every space on the gameBoard, set to zero
            { 
                gameBoard[i] = NON_CHIP_FLAG;
            }
            
            // Resets the recorded positons of chip placement (for threeOrLessAway testing): //
            for(int i = 0; i < numChips; i++) // For every space on the game board, set to zero
            { 
                chipPlacement[i] = NON_CHIP_FLAG;
            }
            
            // Finds an index not yet occupied by another chip: //
            for(int i = 0; i < numChips; i++) // For each chip
            {
                do // Until an available spot (element != 0) is found | Condition 1: Must not be occupied by another chip
                {
                    posOnGameBoard = rand.nextInt(gameBoard.length);
                } 
                while(gameBoard[posOnGameBoard] != NON_CHIP_FLAG);
                
                gameBoard[posOnGameBoard] = chips[i];
                chipPlacement[i] = posOnGameBoard;
            }
            
            // Test to see if all chips are AT MOST 3 indexes away: //
            for(int i = 0; i < numChips; i++) // For each chip
            {
                for(int j = 0; j < numChips; j++)
                {
                    if(i != j)
                    {
                        if(Math.abs(chipPlacement[i] - chipPlacement[j]) <= 3)
                        {
                            threeOrLessAway[i] = true;
                        }
                        else
                        {
                            threeOrLessAway[i] = false;
                        }
                    }
                }
                if(threeOrLessAway[i] == false)
                break;
            }
            
            // Test to see if all conditions are met: //
                                                // if a unmet condition is not found, then success is true
            for(int i = 0; i < numChips; i++)   // For each chip
            {
                if(threeOrLessAway[i] == false) // If any of the placed chips are more than 3 spaces away from any other chip placed
                {
                    success = false;            // Restart the entire while loop
                    break;                      // Exit for loop prematurely
                }
                else
                {
                    success = true; 
                }
            }
        }
    }   
    private static void PrintScreen()
    {
        System.out.print("Current configuration: |");
        for(int i = 0; i < gameBoard.length; i++)
        {
            if(gameBoard[i] != NON_CHIP_FLAG)
            {
                System.out.print(gameBoard[i]);
            }
            else
            {
                System.out.print("-");
            }
        }
        System.out.println("|");
    }
    
    private static Shift Init_Shift(Scanner inScnr)
    {
        int gameBoardSize = 0;
        Shift outShift = null;
        do
        {
            System.out.print("Choose a Board Size [ 9 - 15 ]: ");
            gameBoardSize = inScnr.nextInt(); inScnr.nextLine(); // grab the next integer value in input buffer
            if(gameBoardSize < 9 || gameBoardSize > 15)
            {
                System.out.print("\nERROR: Value not within specified range");
            }
            else
            {
                outShift  = new Shift(gameBoardSize);
            }
        }
        while(gameBoardSize < 9 || gameBoardSize > 15);
        return outShift;
    }
    
    private static void Move_A_Chip(Scanner inScnr)
    {
        int chipSelect = 0;
        String availableChips = "[ ";
        for( int i = 0; i < chips.length; i++ )
        {
            availableChips += ("" + chips[i]);
            if(i != chips.length - 1)
            availableChips += ", ";
        }
        availableChips += " ]";
        do
        {
            System.out.print( "Choose a chip number " + availableChips );
            chipSelect = inScnr.nextInt();
            if(chipSelect > 0 && chipSelect <= chips.length) // valid chip select
            {
                int maxMoves = 0;
                int startIndex = -1;
                for( int i = 0; i < gameBoard.length; i++)
                {
                    if ( gameBoard[i] == chipSelect )
                    {startIndex = i;
                    System.out.println( "Start Index: " + startIndex );}
                }
                for( int i = startIndex; i < gameBoard.length - 1; i++)
                {
                    if(gameBoard[i + 1] == NON_CHIP_FLAG)
                    {
                        maxMoves++;
                        System.out.println( "maxMoves: " + maxMoves );
                    }
                    else
                    {
                        break;
                    }
                }
                if(maxMoves == 0)
                {
                    System.out.println( 
                    "\nChip #" + ("" + chipSelect) + 
                    " has no available moves, please choose another chip" );
                }
                else
                {
                    int movementChoice = 0;
                    boolean validMove = false;
                    while(validMove == false)
                    {
                        
                        System.out.println( "\nChoose number of spaces to move chip #" + ("" + chipSelect) );
                        System.out.print( "( Available moves for chip #" + ("" + chipSelect) + " ) [" );
                        
                        if( maxMoves > 1 )
                        System.out.print( " 1 - " + maxMoves + " ]: " );
                        else
                        System.out.print( " " + maxMoves + " ]: " );
                        
                        movementChoice = inScnr.nextInt(); inScnr.nextLine();
                        if( maxMoves >= movementChoice && movementChoice > 0)
                        { validMove = true; }
                        if( validMove == false )
                        { System.out.println( "\nInvalid movement for chip #" + ("" + chipSelect) ); }
                        
                    }
                    if( validMove == true )
                    {
                        gameBoard[ startIndex + movementChoice ] = chipSelect;
                        gameBoard[ startIndex ] = NON_CHIP_FLAG;
                    }
                }
            }
            else
            {
                System.out.println
                ("\n Chip #" + chipSelect + 
                 " not an available option, select from " + 
                  availableChips );
            }
        }
        while(chipSelect <= chips[0] || chipSelect > chips.length);
    }
    
    private static boolean Is_Game_Win()
    {
        boolean isGameWin = true;
        for( int i = 0; i < chips.length; i++ )
        {
            if( gameBoard [ gameBoard.length - ( i + 1 ) ] == NON_CHIP_FLAG )
            isGameWin = false;
        }
        return isGameWin;
    }
    
    static void main()
    {
        Scanner scnr = new Scanner(System.in);
        Shift shift = Init_Shift(scnr);
        Number_Of_Players(4, "Bob", "Stan", "John", "Mike");
        boolean win = false;
        while( win == false )
        {
            Current_Turn();
            shift.PrintScreen();
            Move_A_Chip(scnr);
            win = Is_Game_Win();
            if( win == false )
            Next_Turn();
        }
    }
}
