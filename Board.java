// Board Class Implementation

import java.util.Arrays;

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
    }

    //TO BE COMPLETE
    private void initScores()
    {
        Arrays.fill(values, 1);
    }

    public static void main(String[] args)
    {
        Board Board = new Board();

        Board.board[14][14] = 'A';
        Board.displayBoard();
    }
}
