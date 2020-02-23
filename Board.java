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

    //Returns correct row index for matrix
    private int getRow(int row)
    {
        return --row;
    }

    //Returns correct column index for matrix
    private int getColumn(char columnLetter)
    {
        int column = Character.toUpperCase(columnLetter) - 'A';
        return column;
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

    //Initialises the Square's scores and types
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

    //THIS WILL BE REMOVED. PURELY FOR TESTING.
    //Displays the board's scores and types only
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

    //Places a word on the board in certain direction, with a starting index
    public boolean placeWord(String word, char direction, int startRow, char columnLetter, Player player)
    {
        boolean canPlace = false;

        startRow = getRow(startRow);

        int startColumn = getColumn(columnLetter);

        direction = Character.toUpperCase(direction);

        if (playerHasTiles( word, player ) || !isNotWithinBounds( startRow, startColumn ) || canPlaceWordInDirection( word, direction, startRow, startColumn ))
        {
            canPlace = true;    // when word can be placed
        }

        if ( canPlace )
        {
            //Removing letters of the word from the player's frame after it has been successfully placed on the board
            for ( int i = 0; i < word.length(); i++ )
            {
                player.getPlayerFrame().removeTile( word.charAt( i ) );
            }

            numOfWords++;
        }

        return canPlace;
    }

    /* Places an individual tile on a square of the board */
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

    private boolean isNotWithinBounds(int row, int column)
    {
        return (row < 0 || row > BOUNDS-1 || column < 0 || column > BOUNDS-1);
    }

    // checks if the word being placed is the first one to be placed on the board
    private boolean isFirstWord()
    {
        return isEmpty();
    }

    //Checks if a word would go through the centre
    private boolean goesThroughCentre(String word, char direction, int row, int column)
    {
       int centreIndex = 7;
       int startIndex = 0;
       int endIndex = 0;

       if(direction == '>')
       {
           //If we are not on the same row as the centre
           if(row != centreIndex)
               return false;

           startIndex = column;
           endIndex = column + word.length()-1;
       }

        else if(direction == 'V')
        {
            //If we are not on the same column as the centre
            if(column != centreIndex)
                return false;

            startIndex = row;
            endIndex = row + word.length()-1;
        }

        //Return true if the centre index is in-between the to be placed word
        return centreIndex >= startIndex && centreIndex <= endIndex;
}

    private boolean canPlaceWordInDirection(String word, char direction, int row, int column)
    {
        int lastRowIndex = ( row + word.length() ) - 1; // getting the column in which the last letter of the word will fall (for horizontally placed words)

        int lastColumnIndex = (column + word.length() ) - 1; // getting the row in which the last letter of the word will fall (for vertically placed words)

        //If the word will not fit on board
        if ( (lastColumnIndex > BOUNDS-1 && direction == '>') || (lastRowIndex > BOUNDS-1 && direction == 'V'))
        {
            return false; // when word to be placed goes past the bounds of the board's columns
        }

        else if(isFirstWord())
        {
            if(!goesThroughCentre(word, direction, row, column))
                return false;
        }

        for (int i = 0; i < word.length(); i++)
        {
            char letterToPlace = word.charAt(i);

            if ( direction == '>' ) // when word is to be placed horizontally
            {
                placeTile( letterToPlace, row, column ); // placing each tile in corresponding position
                column++;  // incrementing column number
            }
            else if ( direction == 'V' )
            {
                placeTile( letterToPlace, row, column ); // placing each tile in corresponding position
                row++;  // incrementing row number
            }
        }

        return true;
    }

    private Square getSquareAt(int row, char letter)
    {
        row = getRow(row);
        int col = getColumn(letter);

        if(row < 0 || row > 14 || col < 0 || col > 14)
            return null;

        return board[row][col];
    }

    private boolean isEmpty()
    {
        return numOfWords == 0;
    }

    public static void main(String[] args)
    {
        Board Board = new Board();

      /*  Pool thePool = new Pool();
        Frame frameOne = new Frame();
        Player playerOne = new Player(frameOne);
        System.out.print("The frame: ");
        playerOne.getPlayerFrame().displayFrame();
        System.out.println();

        Scanner scanner = new Scanner( System.in );
        System.out.print( "Please enter word you want to place on the board: " );
        String word = scanner.next();
        System.out.print( "\nPlease enter the direction in which you want to place the word ('V' for Up->Down or '>' for Left->Right): " );
        char direction = scanner.next().charAt( 0 );
        System.out.print( "\nPlease enter row number of the first letter: " );
        int row = scanner.nextInt();
        System.out.print( "\nPlease enter column letter of the first letter: " );
        char column = scanner.next().charAt( 0 );
        System.out.println("Could we place: " + Board.placeWord( word, direction, row, column, playerOne ));
        Board.displayBoard();System.out.println("The frame: ");
        playerOne.getPlayerFrame().displayFrame();*/
        System.out.println(Board.getSquareAt(1, 'A').getWeight());
        System.out.println(Board.getSquareAt(1, 'A').getType());

        Board.displayWeightsMatrix();

    }
}
