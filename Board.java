// Board Class Implementation

public class Board
{
    private static final int BOUNDS = 15;

    private char[][] board = new char[BOUNDS][BOUNDS];

    //TODO
    // storing current tile positions
    //storing weighted square values
    //placing word on board (no score calculation needed)
    //word placement checking (long)
    //displays board in ASCII characters on console
    //board reset

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
