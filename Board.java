// Board Class Implementation

import java.lang.reflect.Array;
import java.util.Arrays;

public class Board
{
    // BOARD 2D ARRAY DIMENSION
    private static final int BOUNDS = 15;

    private Square[][] board;

    public Board()
    {
        this.board = new Square[BOUNDS][BOUNDS];
        initScores();
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
        board[rowNumber][columnNumber].setTile(tile);
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
                char tile = board[i][j].getTile();

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

    private void initScores()
    {
        for(int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                if((j == i && i == 5) || ((j == (BOUNDS-1-i)) && i == 5))
                    board[i][j] = new Square(3, false);

                else if((j == i && i == 0) || ((j == i) && (i == (BOUNDS-1))))
                    board[i][j] = new Square(3, true);

                else if(((BOUNDS-1) - j == i && i == 0) || (((BOUNDS-1) - j == i) && (i == (BOUNDS-1))))
                    board[i][j] = new Square(3, true);

                else if(j == i || (BOUNDS-1) - j == i)
                    board[i][j] = new Square(2, true);
                else
                    board[i][j] = new Square();
            }
        }
    }

    //THIS WILL BE REMOVED. PURELY FOR TESTING
    public void displayWeightsMatrix()
    {
        System.out.print("     |");

        //Printing the letter cords
        for (int i = 0; i < board[0].length; i++)
        {
            System.out.print("     ");

            System.out.print((char) ('A' + i));

            System.out.print("      |");
        }

        System.out.print("\n");

        for (int i = 0; i < board.length; i++)
        {
            //Print dividers between number cords
            System.out.print("-----+");

            //Prints row divider
            for (int j = 0; j < board[0].length; j++)
            {
                System.out.print("------------+");
            }

            System.out.print("\n");

            //Prints number cords
            if(i < 9)
                System.out.print("   " + (i + 1) + " |");
            else
                System.out.print("  " + (i + 1) + " |");

            //Prints the insides of the scores
            for (int j = 0; j < board[0].length; j++)
            {
                Square location = board[i][j];

                System.out.print("   ");

                System.out.print(location.getWeight() + "," + location.getType());

                System.out.print("   ");

                System.out.print("|");
            }
            System.out.print("\n");
        }

        //The following prints the bottom line of the scores
        System.out.print("-----+");

        for (int j = 0; j < board[0].length; j++)
        {
            System.out.print("------------+");
        }

        System.out.println("\n\n");
    }

    public String getTypeAt(int row, char letter)
    {
        if(row < 1 || row > 15 || letter < 'A' || letter > 'O')
            return "none";

        int col = letter - 'A';

        return board[row][col].getType();
    }

    public int getWeightAt(int row, char letter)
    {
        if(row < 1 || row > 15 || letter < 'A' || letter > 'O')
            return 0;

        row--;

        int col = letter - 'A';

        return board[row][col].getWeight();
    }

    public static void main(String[] args)
    {
        Board Board = new Board();

        //Board.displayBoard();

        System.out.println(Board.getWeightAt(1, 'A'));
        System.out.println(Board.getTypeAt(1, 'A'));

        Board.displayWeightsMatrix();

      /*  for ( int i = 0; i < 5; i++ )   // TESTING 5 WORD PLACEMENTS
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
        }*/
    }
}
