// Board Class Implementation

public class Board
{
    // BOARD 2D ARRAY DIMENSION
    private static final int BOUNDS = 15;

    private char[][] board;

    public Board()
    {
        board = new char[BOUNDS][BOUNDS];
    }

    //TODO
    //storing weighted square values
    //placing word on board (no score calculation needed)
    //word placement checking (long)
    //displays board in ASCII characters on console
    //board reset

    /* Places an individual tile on a square of the board */
    private char placeTile(int rowNumber, char columnLetter, char tile)
    {
        rowNumber--;
        int columnNumber = tile - 'A';
        if ( rowNumber < 0 || rowNumber > 14 || columnNumber < 0 || columnNumber > 14 )
        {
            return '\u0000';    // cannot place a tile on an index out of bounds
        }
        return tile;
    }


    public void displayBoard()
    {
        System.out.print("     |");

        //Printing the top horizontal cords with spaces and dividers
        for (int i = 0; i < board[0].length; i++)
        {
            System.out.print("   ");

            System.out.print( (char) ('A' + i));

            System.out.print("   |");
        }

        System.out.print("\n");

        for (int i = 0; i < board.length; i++)
        {
            //Print dividers between vertical cords
            System.out.print("-----+");

            //
            for (int j = 0; j < board[0].length; j++)
            {
                System.out.print("-------+");
            }

            System.out.print("\n");

            if(i < 9)
                System.out.print("   " + (i + 1) + " |");
            else
                System.out.print("  " + (i + 1) + " |");

            for ( int j = 0; j < board[0].length; j++)
            {
                int spaces;

                spaces = 9;

                char tile = board[i][j];

                boolean isNull = tile == '\u0000';

                for (int  k = 0; k < 2; k++)
                {
                    System.out.print("   ");

                    if(k == 1)
                        break;

                    if(!isNull)
                        System.out.print(tile);
                    else
                        System.out.print(" ");
                }
                System.out.print("|");
            }
            System.out.print("\n");
        }

        System.out.print("-----+");

        for (int j = 0; j < board[0].length; j++)
        {
            System.out.print("-------+");
        }
    }

    public static void main(String[] args)
    {
        Board Board = new Board();

        Board.board[14][14] = 'A';
        Board.displayBoard();
    }
}
