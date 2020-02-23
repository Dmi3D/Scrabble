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
                board[i][j] = new Square();

                // INITIALISING SQUARES ON THE DIAGONALS
                if(j == i || (BOUNDS-1) - j == i)
                {
                    if (i == 0 || i == BOUNDS - 1)
                        board[i][j] = new Square(3, true);

                    else if(i == 5 || i == BOUNDS-1 - 5)
                        board[i][j] = new Square(3, false);

                    else if(i == 6 || i == BOUNDS-1 - 6)
                        board[i][j] = new Square(2, false);

                    else
                        board[i][j] = new Square(2, true);
                }

                // INITIALISING SQUARES ON TOP AND BOTTOM WALL
                if(i == 0 || i == (BOUNDS - 1))
                {
                    if(j == 3 || j == (BOUNDS - 1) - 3)
                        board[i][j] = new Square(2, false);

                    else if(j == 7)
                        board[i][j] = new Square(3, true);
                }

                // INITIALISING SQUARES ON LEFT AND RIGHT WALL
                if(j == 0 || j == (BOUNDS - 1))
                {
                    if(i == 3 || i == (BOUNDS - 1) - 3)
                        board[i][j] = new Square(2, false);

                    else if(i == 7)
                        board[i][j] = new Square(3, true);
                }

                // INITIALISING TRIANGLE SQUARES TOP AND BOTTOM
                if((i >= 1 && i <= 3) || (i <= (BOUNDS -1) - 1 && i >= (BOUNDS-1) - 3))
                {
                    if(i == 1 || i == (BOUNDS -1) - 1)
                    {
                        if(j == 5 || j == (BOUNDS -1) - 5)
                            board[i][j] = new Square(3, false);
                    }

                    else if(i == 2 || i == (BOUNDS -1) - 2)
                    {
                        if(j == 6 || j == (BOUNDS -1) - 6)
                            board[i][j] = new Square(2, false);
                    }

                    else if(j == 7)
                        board[i][j] = new Square(2, false);
                }

                // INITIALISING TRIANGLE SQUARES LEFT AND RIGHT
                if((j >= 1 && j <= 3) || (j <= (BOUNDS -1) - 1 && j >= (BOUNDS-1) - 3))
                {
                    if(j == 1 || j == (BOUNDS -1) - 1)
                    {
                        if(i == 5 || i == (BOUNDS -1) - 5)
                            board[i][j] = new Square(3, false);
                    }

                    else if(j == 2 || j == (BOUNDS -1) - 2)
                    {
                        if(i == 6 || i == (BOUNDS -1) - 6)
                            board[i][j] = new Square(2, false);
                    }

                    else if(i == 7)
                        board[i][j] = new Square(2, false);
                }
            }
        }
    }

    // THIS WILL BE REMOVED. PURELY FOR TESTING.
    /* DISPLAYS THE BOARD'S WEIGHTS AND TYPE ONLY */
    public void displayBoardWithWeights()
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
    public boolean placeWord(String string, char direction, int row, char columnLetter, Player player)
    {
        char word[] = new char[string.length()];

        for(int i = 0; i < string.length(); i++)
        {
            word[i] = string.charAt(i);
        }

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
            string = string.toUpperCase();
            // Removing letters of the word from the player's frame after it has been successfully placed on the board
            for ( int i = 0; i < string.length(); i++ )
            {
                char letterToPlace = string.charAt( i );

                if ( direction == 'A' )
                    placeTile(letterToPlace, row, column+i);

                else if ( direction == 'D' )
                    placeTile(letterToPlace, row+i, column);

               player.getPlayerFrame().removeTile(string.charAt( i ));
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
    private boolean playerHasTiles(char[] word, Player player)
    {
        boolean playerHasTiles = true;

        if ( word.length < 1 )    // ensuring player has at least 1 tile provided for placement
        {
            playerHasTiles = false;
        }

        else
        {
            Frame frameCopy = new Frame( player.getPlayerFrame() );

            for ( int i = 0; i < word.length; i++ )
            {
                char matchingTile = word[i];

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
    private int getEndIndex(char[] word, int startIndex)
    {
        return startIndex + word.length-1;
    }

    /* CHECKS TO SEE IF THE WORD IS GOING THROUGH THE CENTRE */
    private boolean goesThroughCentre(char[] word, char direction, int row, int column)
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
    private boolean isWithinBounds(char[] word, char direction, int row, int column)
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
    public Square getSquareAt(int row, char letter)
    {
        row = getRowIndex(row);
        int col = getColumnIndex(letter);

        if(row < 0 || row > 14 || col < 0 || col > 14)
            return null;

        return board[row][col];
    }

    /* RETURNS SQUARE AT A CERTAIN POSITION ON THE BOARD */
    private Square getSquareAt(int row, int column)
    {
        if(row < 0 || row > 14 || column < 0 || column > 14)
            return null;

        return board[row][column];
    }

    private boolean doesOverlap(char[] word, char direction, int row, int column)
    {
        int startIndex = -1;
        int endIndex = -1;

        if(direction == 'A')
        {
            startIndex = row;
            endIndex = getEndIndex(word, row);
        }

        else if(direction == 'D')
        {
            startIndex = column;
            endIndex = getEndIndex(word, column);
        }

        //If there is any letter between the start index and the end index of a word
        for(int i = startIndex; i <= endIndex; i++)
        {
            if(direction == 'A')
            {
                if (getSquareAt(row, i).getTile() != '\u0000')
                    return true;
            }

            else if(direction == 'D')
            {
                if (getSquareAt(i, column).getTile() != '\u0000')
                    return true;
            }
        }

        return false;
    }

    /* CHECKS IF THE BOARD IS EMPTY */
    private boolean isEmpty()
    {
        return numOfWords == 0;
    }

    public void reset()
    {
        this.board = new Square[BOUNDS][BOUNDS];
        numOfWords = 0;
    }
}
