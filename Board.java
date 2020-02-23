import java.util.Scanner;

public class Board
{
    // BOARD 2D ARRAY DIMENSION
    private static final int BOUNDS = 15;

    private Square[][] board;
    private int numOfWords;

    public Board()
    {
        this.board = new Square[BOUNDS][BOUNDS];
        initScores();
        numOfWords = 0;
    }

    /* DISPLAYS THE TILES ON THE BOARD */
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

    /* RETURNS CORRECT ROW INDEX FOR MATRIX */
    private int getRowIndex(int row)
    {
        return --row;
    }

    /* RETURNS CORRECT COLUMN INDEX FOR MATRIX */
    private int getColumnIndex(char columnLetter)
    {
        int column = Character.toUpperCase(columnLetter) - 'A';
        return column;
    }

    /* INITIALISES THE SQUARES WEIGHT AND TYPE IN BOARD */
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

    // THIS WILL BE REMOVED. PURELY FOR TESTING.
    /* DISPLAYS THE BOARD'S WEIGHTS AND TYPE ONLY */
    private void displayBoardWithWeights()
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
                System.out.print("   ");

                char col = (char) (j + 'A');

                System.out.print(getSquareAt(i+1, col).getWeight() + "," + getSquareAt(i+1, col).getType());

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

    /* PLACES A WORD WITH A CERTAIN DIRECTION. PASS IN STARTING POINT */
    public boolean placeWord(String word, char direction, int row, char columnLetter, Player player)
    {
        boolean canPlace = false;

        row = getRowIndex(row);

        int column = getColumnIndex(columnLetter);

        direction = Character.toUpperCase(direction);

        if (playerHasTiles( word, player ) && isWithinBounds( word, direction, row, column ))
        {
            if(isFirstWord())
                canPlace = goesThroughCentre(word, direction, row, column);

            else
                canPlace = true;    // when word can be placed
        }

        if ( canPlace )
        {
            word = word.toUpperCase();
            // Removing letters of the word from the player's frame after it has been successfully placed on the board
            for ( int i = 0; i < word.length(); i++ )
            {
                char letterToPlace = word.charAt( i );

                if ( direction == 'A' )
                    placeTile(letterToPlace, row, column+i);

                else if ( direction == 'D' )
                    placeTile(letterToPlace, row+i, column);

               player.getPlayerFrame().removeTile(word.charAt( i ));
            }

            numOfWords++;
        }

        return canPlace;
    }

    /* PLACES A TILE ON THE BOARD */
    private void placeTile(char tile, int row, int column)
    {
        board[row][column].setTile(tile);
    }

    /* CHECKING IF A PLAYER'S RACK OF TILES CONTAINS ALL THE LETTERS NEEDED FOR THEIR WORD */
    private boolean playerHasTiles(String word, Player player)
    {
        boolean playerHasTiles = true;

        word = word.toUpperCase();

        if ( word.length() < 1 )    // ensuring player has at least 1 tile provided for placement
        {
            playerHasTiles = false;
        }

        else
        {
            Frame frameCopy = new Frame( player.getPlayerFrame() );

            for ( int i = 0; i < word.length(); i++ )
            {
                char matchingTile = word.charAt( i );

                if ( frameCopy.getIndexOfTile( matchingTile ) != -1 )
                {
                    frameCopy.removeTile( matchingTile );   // removing each tile of the word from the frame copy
                    // when it is found to exist on both the frame and in the word
                }
                else
                {
                    playerHasTiles = false;
                    break;
                }
            }
        }

        return playerHasTiles;
    }

    /* CHECKS IF THE WORD BEING PLACES IS THE FIRST WORD */
    private boolean isFirstWord()
    {
        return isEmpty();
    }

    /* RETURNS THE END INDEX CORRESPONDING TO THE STARTING INDEX AND THE WORD'S LENGTH */
    private int getEndIndex(String word, int startIndex)
    {
        return startIndex + word.length()-1;
    }

    /* CHECKS TO SEE IF THE WORD IS GOING THROUGH THE CENTRE */
    private boolean goesThroughCentre(String word, char direction, int row, int column)
    {
       int centreIndex = 7;
       int startIndex = -1;
       int endIndex = -1;

       if(direction == 'A')
       {
           //If we are not on the same row as the centre
           if(row != centreIndex)
               return false;

           startIndex = column;
           endIndex = getEndIndex(word, startIndex);
       }

        else if(direction == 'D')
        {
            //If we are not on the same column as the centre
            if(column != centreIndex)
                return false;

            startIndex = row;
            endIndex = getEndIndex(word, startIndex);
        }

        //Return true if the centre index is in-between the to be placed word
        return centreIndex >= startIndex && centreIndex <= endIndex;
}

    /* CHECKS TO SEE IF THE WORD IS PLACED WITHIN THE BOUNDS OF THE BOARD */
    private boolean isWithinBounds(String word, char direction, int row, int column)
    {
        int startIndex = -1;
        int endIndex = -1;

        if(direction == 'A')
        {
            startIndex = row;
            endIndex = getEndIndex(word, startIndex);
        }

        else if(direction == 'D')
        {
            startIndex = column;
            endIndex = getEndIndex(word, startIndex);
        }

        // If the word will not fit on board
        if ( startIndex < 0 || startIndex > BOUNDS-1 || endIndex > BOUNDS-1 )
        {
            return false; // When word to be placed goes past the bounds of the board's columns
        }

        return true;
    }

    /* RETURNS SQUARE AT A CERTAIN POSITION ON THE BOARD */
    private Square getSquareAt(int row, char letter)
    {
        row = getRowIndex(row);
        int col = getColumnIndex(letter);

        if(row < 0 || row > 14 || col < 0 || col > 14)
            return null;

        return board[row][col];
    }

    /* CHECKS IF THE BOARD IS EMPTY */
    private boolean isEmpty()
    {
        return numOfWords == 0;
    }
}
