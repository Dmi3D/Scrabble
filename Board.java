// Board Class Implementation

import java.util.Arrays;
import java.util.Scanner;


public class Board
{
    // BOARD 2D ARRAY DIMENSION
    private static final int BOUNDS = 15;

    private char[][] board;

    private int[][] values;

    public Board()
    {
        this.board = new char[BOUNDS][BOUNDS];
        this.values = new int[BOUNDS][BOUNDS];
        initScores();
    }

    public char getTileAtPosition(int rowNum, char columnLetter)
    {
        rowNum--;
        int columnNum = columnLetter - 'A';
        return board[rowNum][columnNum];
    }

    /* Places word on board in direction indicated, starting at position indicated */
    /* Precondition: word is verified to contain letters available in the player's frame */
    public boolean placeWord(String word, char direction, int startRow, char columnLetter)
    {
        // CONSIDER WRITING EXCEPTIONS AND THROWING THEM WHENEVER CERTAIN CONDITION IS MET E.G. WordOutOfBoundsException

        boolean isPlaced;
        startRow--;
        int startColumn = columnLetter - 'A';
        if ( startRow < 0 || startRow > BOUNDS-1 || startColumn < 0 || startColumn > BOUNDS-1 )
        {
               isPlaced = false;    // when word is to be placed out of bounds
        }
        else
        {
            char[] letters = word.toCharArray();
            int endRow = ( startRow + letters.length ) - 1; // getting the column in which the last letter of the word will fall (for horizontally placed words)
            int endColumn = (startColumn + letters.length) - 1; // getting the row in which the last letter of the word will fall (for vertically placed words)
            if ( (endColumn > BOUNDS-1 && direction == 'H') || (endRow > BOUNDS-1 && direction == 'V'))
            {
                isPlaced = false; // when word to be placed goes past the bounds of the board's columns
            }
            else
            {
                for ( char letter : letters )
                {
                    if ( direction == 'H' ) // when word is to be placed horizontally
                    {
                        placeTile( letter, startRow, startColumn ); // placing each tile in corresponding position
                        startColumn++;  // incrementing column number
                    }
                    else if ( direction == 'V' )
                    {
                        placeTile( letter, startRow, startColumn ); // placing each tile in corresponding position
                        startRow++;  // incrementing row number
                    }
                }
                isPlaced = true;
            }
        }

        return isPlaced;
    }


    /* Places an individual tile on a square of the board */
    private void placeTile(char tile, int rowNumber, int columnNumber)
    {
        board[rowNumber][columnNumber] = tile;
    }


    public void displayBoard()
    {
        System.out.print("     |");

        //Printing the letter cords
        for (int i = 0; i < board[0].length; i++)
        {
            System.out.print("   ");

            System.out.print((char) ('A' + i));

            System.out.print("   |");
        }

        System.out.print("\n");

        for (int i = 0; i < board.length; i++)
        {
            //Print dividers between number cords
            System.out.print("-----+");

            //Prints row divider
            for (int j = 0; j < board[0].length; j++)
            {
                System.out.print("-------+");
            }

            System.out.print("\n");

            //Prints number cords
            if(i < 9)
                System.out.print("   " + (i + 1) + " |");
            else
                System.out.print("  " + (i + 1) + " |");

            //Prints the insides of the board
            for (int j = 0; j < board[0].length; j++)
            {
                char tile = board[i][j];

                boolean isNull = tile == '\u0000';

                System.out.print("   ");

                if(!isNull)
                    System.out.print(tile);
                else
                    System.out.print(" ");

                System.out.print("   ");

                System.out.print("|");
            }
            System.out.print("\n");
        }

        //The following prints the bottom line of the board
        System.out.print("-----+");

        for (int j = 0; j < board[0].length; j++)
        {
            System.out.print("-------+");
        }

        System.out.println("\n\n");
    }

    //TO BE COMPLETE
    private void initScores()
    {
        Arrays.fill(values, 1);
    }

    public static void main(String[] args)
    {
        Board Board = new Board();

        Board.displayBoard();

        for ( int i = 0; i < 5; i++ )   // TESTING 5 WORD PLACEMENTS
        {
            Scanner scanner = new Scanner( System.in );
            System.out.print( "Please enter word you want to place on the board: " );
            String word = scanner.next();
            System.out.print( "\nPlease enter the direction in which you want to place the word ('V' for Up->Down or 'H' for Left->Right): " );
            char direction = scanner.next().charAt( 0 );
            System.out.print( "\nPlease enter row number of the first letter: " );
            int row = scanner.nextInt();
            System.out.print( "\nPlease enter column letter of the first letter: " );
            char column = scanner.next().charAt( 0 );
            Board.placeWord( word, direction, row, column );
            Board.displayBoard();
        }

    }
}
