package sample;

import java.util.ArrayList;
import java.util.Arrays;

public class Board
{
    // error codes
    public static final int WORD_INCORRECT_FIRST_PLAY = 0;
    public static final int WORD_OUT_OF_BOUNDS = 1;
    public static final int WORD_LETTER_NOT_IN_FRAME = 2;
    public static final int WORD_LETTER_CLASH = 3;
    public static final int WORD_NO_LETTER_PLACED = 4;
    public static final int WORD_NO_CONNECTION = 5;

    private int errorCode;

    public int getErrorCode()
    {
        return errorCode;
    }

    // BOARD 2D ARRAY DIMENSION
    private static final int BOUNDS = 15;

    private Square[][] board;
    private int numOfWords;
    private char[] lastTilesPlaced;
    private int[][] lastTilesPlacedLocations; //Stores locations of the letters of the last placed word
    private char[] lastPlacedWord;
    public ArrayList<String> wordsCreatedLastMove;
    private int scoreFromLastMove;

    public int getScoreFromLastMove()
    {
        return scoreFromLastMove;
    }

    public void changeBlankOnBoard( char blankReplacement )
    {
        int indexOfBlank = -1;

        for ( int i = 0; i < lastTilesPlaced.length; i++ )
        {
            if ( lastPlacedWord[i] == '*' )
            {
                indexOfBlank = i;
                break;
            }
        }

        if ( indexOfBlank != -1 )
        {
            int row = lastTilesPlacedLocations[0][indexOfBlank];
            int column = lastTilesPlacedLocations[1][indexOfBlank];

            getSquareAt( row, column ).setTile( blankReplacement );
        }
    }

    public void changeBlankInWordsCreated( char blankReplacement )
    {
        for ( int i = 0; i < wordsCreatedLastMove.size(); i++ )
        {
            char[] wordCreated = wordsCreatedLastMove.get( i ).toCharArray();

            for ( int j = 0; j < wordCreated.length; j++ )
            {
                if ( wordCreated[j] == '*' )
                {
                    wordCreated[j] = blankReplacement;

                    String wordWithBlankRemoved = String.valueOf( wordCreated );
                    wordsCreatedLastMove.remove( i );
                    wordsCreatedLastMove.add( i, wordWithBlankRemoved );
                    break;
                }
            }
        }
    }

    private char[] getLastPlacedWord()
    {
        return lastPlacedWord;
    }

    private void setLastPlacedWord( String string )
    {
        lastPlacedWord = new char[string.length()];

        for ( int i = 0; i < string.length(); i++ )
        {
            lastPlacedWord[i] = string.charAt( i );
        }
    }

    public Board()
    {
        this.board = new Square[BOUNDS][BOUNDS];
        initScores();
        numOfWords = 0;
        lastTilesPlaced = null;
        scoreFromLastMove = 0;
    }

    /* DISPLAYS THE TILES ON THE BOARD */
    public void displayBoard()
    {
        System.out.print( "     |" );

        //Printing the letter cords
        for ( int i = 0; i < board[0].length; i++ )
        {
            System.out.print( "   " );

            System.out.print( (char) ('A' + i) );

            System.out.print( "   |" );
        }

        System.out.print( "\n" );

        for ( int i = 0; i < board.length; i++ )
        {
            //Print dividers between number cords
            System.out.print( "-----+" );

            //Prints row divider
            for ( int j = 0; j < board[0].length; j++ )
            {
                System.out.print( "-------+" );
            }

            System.out.print( "\n" );

            //Prints number cords
            if ( i < 9 )
                System.out.print( "   " + (i + 1) + " |" );
            else
                System.out.print( "  " + (i + 1) + " |" );

            //Prints the insides of the board
            for ( int j = 0; j < board[0].length; j++ )
            {
                char tile = board[i][j].getTile();

                boolean isNull = tile == '\u0000';

                System.out.print( "   " );

                if ( !isNull )
                    System.out.print( tile );
                else
                    System.out.print( " " );

                System.out.print( "   " );

                System.out.print( "|" );
            }
            System.out.print( "\n" );
        }

        //The following prints the bottom line of the board
        System.out.print( "-----+" );

        for ( int j = 0; j < board[0].length; j++ )
        {
            System.out.print( "-------+" );
        }

        System.out.println( "\n\n" );
    }

    /* INITIALISES THE SQUARES WEIGHT AND TYPE IN BOARD */
    private void initScores()
    {
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board.length; j++ )
            {
                board[i][j] = new Square();

                // INITIALISING SQUARES ON THE DIAGONALS
                if ( j == i || (BOUNDS - 1) - j == i )
                {
                    if ( i == 0 || i == BOUNDS - 1 )
                        board[i][j] = new Square( 3, true );

                    else if ( i == 5 || i == BOUNDS - 1 - 5 )
                        board[i][j] = new Square( 3, false );

                    else if ( i == 6 || i == BOUNDS - 1 - 6 )
                        board[i][j] = new Square( 2, false );

                    else
                        board[i][j] = new Square( 2, true );
                }

                // INITIALISING SQUARES ON TOP AND BOTTOM WALL
                if ( i == 0 || i == (BOUNDS - 1) )
                {
                    if ( j == 3 || j == (BOUNDS - 1) - 3 )
                        board[i][j] = new Square( 2, false );

                    else if ( j == 7 )
                        board[i][j] = new Square( 3, true );
                }

                // INITIALISING SQUARES ON LEFT AND RIGHT WALL
                if ( j == 0 || j == (BOUNDS - 1) )
                {
                    if ( i == 3 || i == (BOUNDS - 1) - 3 )
                        board[i][j] = new Square( 2, false );

                    else if ( i == 7 )
                        board[i][j] = new Square( 3, true );
                }

                // INITIALISING TRIANGLE SQUARES TOP AND BOTTOM
                if ( (i >= 1 && i <= 3) || (i <= (BOUNDS - 1) - 1 && i >= (BOUNDS - 1) - 3) )
                {
                    if ( i == 1 || i == (BOUNDS - 1) - 1 )
                    {
                        if ( j == 5 || j == (BOUNDS - 1) - 5 )
                            board[i][j] = new Square( 3, false );
                    }

                    else if ( i == 2 || i == (BOUNDS - 1) - 2 )
                    {
                        if ( j == 6 || j == (BOUNDS - 1) - 6 )
                            board[i][j] = new Square( 2, false );
                    }

                    else if ( j == 7 )
                        board[i][j] = new Square( 2, false );
                }

                // INITIALISING TRIANGLE SQUARES LEFT AND RIGHT
                if ( (j >= 1 && j <= 3) || (j <= (BOUNDS - 1) - 1 && j >= (BOUNDS - 1) - 3) )
                {
                    if ( j == 1 || j == (BOUNDS - 1) - 1 )
                    {
                        if ( i == 5 || i == (BOUNDS - 1) - 5 )
                            board[i][j] = new Square( 3, false );
                    }

                    else if ( j == 2 || j == (BOUNDS - 1) - 2 )
                    {
                        if ( i == 6 || i == (BOUNDS - 1) - 6 )
                            board[i][j] = new Square( 2, false );
                    }

                    else if ( i == 7 )
                        board[i][j] = new Square( 2, false );
                }
            }
        }
    }

    // THIS WILL BE REMOVED. PURELY FOR TESTING.
    /* DISPLAYS THE BOARD'S WEIGHTS AND TYPE ONLY */
    public void displayBoardWithWeights()
    {
        System.out.print( "     |" );

        //Printing the letter cords
        for ( int i = 0; i < board[0].length; i++ )
        {
            System.out.print( "     " );

            System.out.print( (char) ('A' + i) );

            System.out.print( "      |" );
        }

        System.out.print( "\n" );

        for ( int i = 0; i < board.length; i++ )
        {
            //Print dividers between number cords
            System.out.print( "-----+" );

            //Prints row divider
            for ( int j = 0; j < board[0].length; j++ )
            {
                System.out.print( "------------+" );
            }

            System.out.print( "\n" );

            //Prints number cords
            if ( i < 9 )
                System.out.print( "   " + (i + 1) + " |" );
            else
                System.out.print( "  " + (i + 1) + " |" );

            //Prints the insides of the scores
            for ( int j = 0; j < board[0].length; j++ )
            {
                System.out.print( "   " );

                char col = (char) (j + 'A');

                System.out.print( getSquareAt( i + 1, col ).getWeight() + "," + getSquareAt( i + 1, col ).getType() );

                System.out.print( "   " );

                System.out.print( "|" );
            }
            System.out.print( "\n" );
        }

        //The following prints the bottom line of the scores
        System.out.print( "-----+" );

        for ( int j = 0; j < board[0].length; j++ )
        {
            System.out.print( "------------+" );
        }

        System.out.println( "\n\n" );
    }

    /* CHECKS IF A WORD PLACEMENT IS VALID OR NOT */
    private boolean canPlaceWord( char[] word, char direction, int row, int column, Player player )
    {
        // If word is not within bounds, it cannot be placed
        if ( !isWithinBounds( word, direction, row, column ) )
        {
            errorCode = WORD_OUT_OF_BOUNDS;
            return false;
        }

        // If the word will not touch any other word on the board
        if ( !isTouching( word, direction, row, column ) && !isFirstWord() )
        {
            errorCode = WORD_NO_CONNECTION;
            return false;
        }

        // If the word does not properly overlap with tiles on the board
        if ( !manageOverlappingTiles( word, direction, row, column ) )
        {
            errorCode = WORD_LETTER_CLASH;
            return false;
        }

        // If the player has the remaining tiles needed
        if ( !playerHasTiles( word, player ) )
        {
            return false;
        }

        // If it is the first word, we can only place if it goes through the centre
        if ( isFirstWord() && !goesThroughCentre( word, direction, row, column ) )
        {
            errorCode = WORD_INCORRECT_FIRST_PLAY;
            return false;
        }

        return true;
    }

    private char[] convertStringToArray( String string )
    {
        string = string.toUpperCase();

        char[] arr = new char[string.length()];

        for ( int i = 0; i < string.length(); i++ )
        {
            arr[i] = string.charAt( i );
        }

        return arr;
    }

    /* METHOD WHICH RETURNS AN ARRAY LIST OF ALL THE WORDS CREATED FROM THE LAST PLACED TILE */
    public ArrayList<String> getWordsCreated( char[] tilesPlaced, char direction, int row, int column )
    {
        ArrayList<String> wordsCreated = new ArrayList<>();

        int startIndex = -1;
        int endIndex = -1;

        //Get the originally created word stored
        if ( direction == 'A' )
        {
            startIndex = column;
            endIndex = column;

            //If there is a letter at the left of the placed tile, keep going left
            while ( startIndex - 1 >= 0 && getSquareAt( row, startIndex - 1 ).getTile() != '\u0000' )
            {
                startIndex--;
            }

            //If there is a letter at the right of the placed tile, keep going right
            while ( endIndex + 1 < BOUNDS && getSquareAt( row, endIndex + 1 ).getTile() != '\u0000' )
            {
                endIndex++;
            }

            int lengthOfWord = endIndex + 1 - startIndex;

            String word = "";

            for ( int i = 0; i < lengthOfWord; i++ )
            {
                word += getSquareAt( row, startIndex ).getTile();

                startIndex++;
            }

            wordsCreated.add( word );
        }

        //Get the originally created word stored
        else if ( direction == 'D' )
        {
            startIndex = row;
            endIndex = row;

            //If there is a letter at the left of the placed tile, keep going left
            while ( startIndex - 1 >= 0 && getSquareAt( startIndex - 1, column ).getTile() != '\u0000' )
            {
                startIndex--;
            }

            //If there is a letter at the right of the placed tile, keep going right
            while ( endIndex + 1 < BOUNDS && getSquareAt( endIndex + 1, column ).getTile() != '\u0000' )
            {
                endIndex++;
            }

            int lengthOfWord = endIndex + 1 - startIndex;

            String word = "";

            for ( int i = 0; i < lengthOfWord; i++ )
            {
                word += getSquareAt( startIndex, column ).getTile();

                startIndex++;
            }

            wordsCreated.add( word );
        }

        int indexOfTilePlaced = 0;

        if ( direction == 'A' )
            indexOfTilePlaced = column;

        if ( direction == 'D' )
            indexOfTilePlaced = row;

        for ( int i = 0; i < tilesPlaced.length; i++ )
        {
            if ( direction == 'A' )
            {
                startIndex = row;
                endIndex = row;

                //Look at a placed tile, and check above and below
                if ( tilesPlaced[i] != ' ' )
                {
                    //If there is something above or below the placed tile
                    if ( row != 0 && getSquareAt( row - 1, indexOfTilePlaced ).getTile() != '\u0000' || row != BOUNDS - 1 && getSquareAt( row + 1, indexOfTilePlaced ).getTile() != '\u0000' )
                    {
                        //Keep going up to find the start of the word
                        while ( startIndex - 1 >= 0 && getSquareAt( startIndex - 1, indexOfTilePlaced ).getTile() != '\u0000' )
                        {
                            startIndex--;
                        }

                        //Keep going down to find the end of the word
                        while ( endIndex + 1 < BOUNDS && getSquareAt( endIndex + 1, indexOfTilePlaced ).getTile() != '\u0000' )
                        {
                            endIndex++;
                        }

                        int lengthOfWord = endIndex + 1 - startIndex;

                        String word = "";

                        for ( int j = 0; j < lengthOfWord; j++ )
                        {
                            word += getSquareAt( startIndex, indexOfTilePlaced ).getTile();

                            startIndex++;
                        }

                        wordsCreated.add( word );
                    }
                }

                indexOfTilePlaced++;
            }

            else if ( direction == 'D' )
            {
                startIndex = column;
                endIndex = column;

                //Look at a placed tile, and check left and right
                if ( tilesPlaced[i] != ' ' )
                {
                    //If there is something left or right the placed tile
                    if ( column != 0 && getSquareAt( indexOfTilePlaced, column - 1 ).getTile() != '\u0000' || column != BOUNDS - 1 && getSquareAt( indexOfTilePlaced, column + 1 ).getTile() != '\u0000' )
                    {
                        //Keep going left to find the start of the word
                        while ( startIndex - 1 >= 0 && getSquareAt( indexOfTilePlaced, startIndex - 1 ).getTile() != '\u0000' )
                        {
                            startIndex--;
                        }

                        //Keep going right to find the end of the word
                        while ( endIndex + 1 < BOUNDS && getSquareAt( indexOfTilePlaced, endIndex + 1 ).getTile() != '\u0000' )
                        {
                            endIndex++;
                        }

                        int lengthOfWord = endIndex + 1 - startIndex;

                        String word = "";

                        for ( int j = 0; j < lengthOfWord; j++ )
                        {
                            word += getSquareAt( indexOfTilePlaced, startIndex ).getTile();

                            startIndex++;
                        }

                        wordsCreated.add( word );
                    }
                }

                indexOfTilePlaced++;
            }
        }

        return wordsCreated;
    }

    /* RETURNS VALID SCORE OF ONE WORD*/
    private int getScoreOfWord( char[] word, Pool pool )
    {
        char[] lastTilesPlacedOne = Arrays.copyOf( getLastTilesPlaced(), getLastTilesPlaced().length );
        char[] wordWithTilesOnBoard = Arrays.copyOf( word, word.length );
        char[] wordWithTilesPlaced = Arrays.copyOf( word, word.length );

        //Removing actual placed tiles from word to make word with tiles on board
        for ( int i = 0; i < word.length; i++ )
        {
            for ( int j = 0; j < lastTilesPlacedOne.length; j++ )
            {
                if ( word[i] == lastTilesPlacedOne[j] )
                {
                    wordWithTilesOnBoard[i] = ' ';
                    lastTilesPlacedOne[j] = ' ';
                    break;
                }
            }
        }

        int scoreOfTilesOnBoard = 0;

        //CALCULATING SCORE OF TILES ALREADY ON BOARD
        for ( int i = 0; i < wordWithTilesOnBoard.length; i++ )
        {
            if ( wordWithTilesOnBoard[i] != ' ' )
            {
                scoreOfTilesOnBoard += pool.getValue( wordWithTilesOnBoard[i] );
            }
        }

        //Removing tiles on board from word to make word with tiles placed
        for ( int i = 0; i < wordWithTilesOnBoard.length; i++ )
        {
            if ( wordWithTilesOnBoard[i] != ' ' )
            {
                wordWithTilesPlaced[i] = ' ';
            }
        }

        //CALCULATING SCORE OF TILES WHICH WERE PLACED
        int[] scoresOfPlacedTiles = new int[word.length];
        ArrayList<Integer> wordScores = new ArrayList<>();

        char[] lastTilesPlacedTwo = Arrays.copyOf( getLastTilesPlaced(), getLastTilesPlaced().length );

        // For each letter in last word placed
        for ( int i = 0; i < wordWithTilesPlaced.length; i++ )
        {
            if ( wordWithTilesPlaced[i] == ' ' )
            {
                continue;
            }

            int row = 0;
            int column = 0;

            int indexOfPlacedTile;

            for ( indexOfPlacedTile = 0; indexOfPlacedTile < lastTilesPlacedTwo.length; indexOfPlacedTile++ )
            {
                if ( wordWithTilesPlaced[i] == lastTilesPlacedTwo[indexOfPlacedTile] )
                {
                    row = lastTilesPlacedLocations[0][indexOfPlacedTile];
                    column = lastTilesPlacedLocations[1][indexOfPlacedTile];
                    lastTilesPlacedTwo[indexOfPlacedTile] = ' ';
                    break;
                }
            }

            char tile = wordWithTilesPlaced[i];

            Square square = getSquareAt( row, column );

            scoresOfPlacedTiles[i] = pool.getValue( tile );

            // If the tile is on a letter score
            if ( square.getType().equals( "letr" ) )
            {
                // Multiply that specific letter score by the weight
                scoresOfPlacedTiles[i] *= square.getWeight();
            }

            // If the tile is on a word score
            else if ( square.getType().equals( "word" ) )
            {
                // Add the weight of the word score to an array list
                wordScores.add( square.getWeight() );
            }
        }

        int scoresOfTilesPlaced = Arrays.stream( scoresOfPlacedTiles ).sum();

        //ADDING SCORES OF TILES WHICH WERE ALREADY ON BOARD AND THE TILES WHICH WERE PLACED
        int totalScoreOfWord = scoreOfTilesOnBoard + scoresOfTilesPlaced;

        //If placed tile was on a word score, multiply result
        if ( !wordScores.isEmpty() )
        {
            // Multiply the total score by the word scoresOfPlacedTiles
            for ( int i = 0; i < wordScores.size(); i++ )
            {
                totalScoreOfWord *= wordScores.get( i );
            }
        }

        return totalScoreOfWord;
    }

    /* RETURNS SCORE OF ALL THE CREATED WORDS */
    public int calculateScoreFromLastMove( Pool pool )
    {
        int score = 0;

        for ( int i = 0; i < wordsCreatedLastMove.size(); i++ )
        {
            char[] wordCreated = convertStringToArray( wordsCreatedLastMove.get( i ) );

            score += getScoreOfWord( wordCreated, pool );
        }

        // Bingo condition
        if ( getLastTilesPlaced().length == 7 )
        {
            score += 50;
        }

        scoreFromLastMove = score;

        return score;
    }

    /* PLACES A WORD WITH A CERTAIN DIRECTION. PASS IN STARTING POINT */
    public boolean placeWord( String string, char direction, int row, char columnLetter, Player player )
    {
        row = getRowIndex( row );
        int column = getColumnIndex( columnLetter );
        direction = Character.toUpperCase( direction );

        char[] tilesToPlace = convertStringToArray( string );

        // If we can place, place tiles (and auto remove them from frame)
        if ( canPlaceWord( tilesToPlace, direction, row, column, player ) )
        {
            lastTilesPlacedLocations = new int[2][tilesToPlace.length];
            int placedTiles = 0;

            //Placing tile on board and removing it from frame
            for ( int i = 0; i < tilesToPlace.length; i++ )
            {
                char letterToPlace = tilesToPlace[i];

                if ( letterToPlace == ' ' )
                    continue;

                if ( direction == 'A' )
                {
                    placeTile( letterToPlace, row, column + i );
                    setLastLettersPlacedLocations( row, column + i, placedTiles );
                    placedTiles++;
                }

                else if ( direction == 'D' )
                {
                    placeTile( letterToPlace, row + i, column );
                    setLastLettersPlacedLocations( row + i, column, placedTiles );
                    placedTiles++;
                }

                player.getPlayerFrame().removeTile( letterToPlace );
            }

            if ( placedTiles == 0 )
            {
                errorCode = WORD_NO_LETTER_PLACED;
                return false;
            }

            numOfWords++;

            setLastTilesPlaced( tilesToPlace );
            setLastPlacedWord( string );

            wordsCreatedLastMove = getWordsCreated( tilesToPlace, direction, row, column );

            return true;
        }

        return false;
    }

    /* CHECKS TO SEE IF THE WORD IS PLACED WITHIN THE BOUNDS OF THE BOARD */
    private boolean isWithinBounds( char[] word, char direction, int row, int column )
    {
        //If the starting position is out of bounds
        if ( row < 0 || row > BOUNDS - 1 || column < 0 || column > BOUNDS - 1 )
            return false;

        int endIndex = -1;

        if ( direction == 'A' )
        {
            endIndex = getEndIndex( word, column );
        }

        else if ( direction == 'D' )
        {
            endIndex = getEndIndex( word, row );
        }

        // If the word will not fit on board
        if ( endIndex > BOUNDS - 1 )
        {
            return false; // When word to be placed goes past the bounds of the board's columns
        }

        return true;
    }

    /* CHECKS IF A WORD WILL TOUCH ANOTHER WORD ON THE BOARD */
    private boolean isTouching( char[] word, char direction, int row, int column )
    {
        int startIndex = -1;
        int endIndex = -1;

        if ( direction == 'A' )
        {
            startIndex = column;
            endIndex = getEndIndex( word, column );

            //If there is a letter at the short start of the word
            if ( startIndex - 1 >= 0 && getSquareAt( row, startIndex - 1 ).getTile() != '\u0000' )
                return true;

            //If there is a letter at the short end of the word
            if ( endIndex + 1 <= BOUNDS - 1 && getSquareAt( row, endIndex + 1 ).getTile() != '\u0000' )
                return true;
        }

        else if ( direction == 'D' )
        {
            startIndex = row;
            endIndex = getEndIndex( word, row );

            //If there is a letter at the short start of the word
            if ( startIndex - 1 >= 0 && getSquareAt( startIndex - 1, column ).getTile() != '\u0000' )
                return true;

            //If there is a letter at the short end of the word
            if ( endIndex + 1 <= BOUNDS - 1 && getSquareAt( endIndex + 1, column ).getTile() != '\u0000' )
                return true;

        }

        //If there is any letter on the long sides of the word
        for ( int i = startIndex; i <= endIndex; i++ )
        {
            //If no letters above and below
            if ( direction == 'A' )
            {
                if ( row != 0 && getSquareAt( row - 1, i ).getTile() != '\u0000' )
                    return true;

                if ( row != BOUNDS - 1 && getSquareAt( row + 1, i ).getTile() != '\u0000' )
                    return true;
            }

            else if ( direction == 'D' )
            {
                if ( column != 0 && getSquareAt( i, column - 1 ).getTile() != '\u0000' )
                    return true;

                if ( column != BOUNDS - 1 && getSquareAt( i, column + 1 ).getTile() != '\u0000' )
                    return true;
            }
        }

        return false;
    }

    /* CHECKS IF THE WORD BEING PLACES IS THE FIRST WORD */
    private boolean isFirstWord()
    {
        return isEmpty();
    }

    /* CHECKS TO SEE IF THE WORD IS GOING THROUGH THE CENTRE */
    private boolean goesThroughCentre( char[] word, char direction, int row, int column )
    {
        int centreIndex = 7;
        int startIndex = -1;
        int endIndex = -1;

        if ( direction == 'A' )
        {
            //If we are not on the same row as the centre
            if ( row != centreIndex )
                return false;

            startIndex = column;
            endIndex = getEndIndex( word, startIndex );
        }

        else if ( direction == 'D' )
        {
            //If we are not on the same column as the centre
            if ( column != centreIndex )
                return false;

            startIndex = row;
            endIndex = getEndIndex( word, startIndex );
        }

        //Return true if the centre index is in-between the to be placed word
        return centreIndex >= startIndex && centreIndex <= endIndex;
    }

    /* MANAGES OVERLAPPING TILES. RETURNS FALSE IF OVERLAP IS INVALID */
    private boolean manageOverlappingTiles( char[] word, char direction, int row, int column )
    {
        int startIndex = -1;
        int endIndex = -1;

        if ( direction == 'A' )
        {
            startIndex = column;
            endIndex = getEndIndex( word, column );
        }

        else if ( direction == 'D' )
        {
            startIndex = row;
            endIndex = getEndIndex( word, row );
        }

        int indexOfWord = 0;

        //If there is any letter between the start index and the end index of a word
        for ( int i = startIndex; i <= endIndex; i++ )
        {
            if ( direction == 'A' )
            {
                if ( getSquareAt( row, i ).getTile() != '\u0000' )
                {
                    if ( word[indexOfWord] == getSquareAt( row, i ).getTile() )
                    {
                        word[indexOfWord] = ' ';
                    }

                    else
                    {
                        return false;
                    }
                }
            }

            else if ( direction == 'D' )
            {
                if ( getSquareAt( i, column ).getTile() != '\u0000' )
                {
                    if ( word[indexOfWord] == getSquareAt( i, column ).getTile() )
                    {
                        word[indexOfWord] = ' ';
                    }

                    else
                    {
                        return false;
                    }
                }
            }

            indexOfWord++;
        }

        return true;
    }

    /* CHECKING IF A PLAYER'S RACK OF TILES CONTAINS ALL THE LETTERS NEEDED FOR THEIR WORD */
    private boolean playerHasTiles( char[] word, Player player )
    {
        boolean playerHasTiles = true;

        if ( word.length < 1 )    // ensuring player has at least 1 tile provided for placement
        {
            playerHasTiles = false;
        }

        else
        {
            Frame frameCopy = new Frame( player.getPlayerFrame() );

            for ( char matchingTile : word )
            {
                if ( matchingTile == ' ' )
                {
                    continue;
                }

                if ( frameCopy.getIndexOfTile( matchingTile ) != -1 )
                {
                    frameCopy.removeTile( matchingTile );   // removing each tile of the word from the frame copy
                    // when it is found to exist on both the frame and in the word
                }

                else
                {
                    playerHasTiles = false;
                    errorCode = WORD_LETTER_NOT_IN_FRAME;
                    break;
                }
            }
        }

        return playerHasTiles;
    }

    /* PLACES A TILE ON THE BOARD */
    private void placeTile( char tile, int row, int column )
    {
        if ( tile != ' ' )
            board[row][column].setTile( tile );
    }

    /* RETURNS THE END INDEX CORRESPONDING TO THE STARTING INDEX AND THE WORD'S LENGTH */
    private int getEndIndex( char[] word, int startIndex )
    {
        return startIndex + word.length - 1;
    }

    /* RETURNS SQUARE AT A CERTAIN POSITION ON THE BOARD */
    public Square getSquareAt( int row, char letter )
    {
        row = getRowIndex( row );
        int col = getColumnIndex( letter );

        if ( row < 0 || row > 14 || col < 0 || col > 14 )
            return null;

        return board[row][col];
    }

    /* RETURNS SQUARE AT A CERTAIN POSITION ON THE BOARD */
    public Square getSquareAt( int row, int column )
    {
        if ( row < 0 || row > 14 || column < 0 || column > 14 )
            return null;

        return board[row][column];
    }

    /* RETURNS CORRECT ROW INDEX FOR MATRIX */
    private int getRowIndex( int row )
    {
        return row - 1;
    }

    /* RETURNS CORRECT COLUMN INDEX FOR MATRIX */
    private int getColumnIndex( char columnLetter )
    {
        return Character.toUpperCase( columnLetter ) - 'A';
    }

    /* CHECKS IF THE BOARD IS EMPTY */
    private boolean isEmpty()
    {
        return numOfWords == 0;
    }

    /* RETURNS THE NUMBER OF WORDS CURRENTLY ON THE BOARD */
    public int getNumOfWords()
    {
        return numOfWords;
    }

    /* SETS THE LAST WORD PLACED ON THE BOARD */
    private void setLastTilesPlaced( char[] lastTilesPlaced )
    {
        this.lastTilesPlaced = lastTilesPlaced;
    }

    /* RETURNS THE LAST WORD PLACED ON THE BOARD */
    public char[] getLastTilesPlaced()
    {
        return lastTilesPlaced;
    }

    /* SETS THE LOCATIONS OF THE LETTERS PLACED IN THE LSAT MOVE */
    private void setLastLettersPlacedLocations( int row, int column, int letterIndex )
    {
        lastTilesPlacedLocations[0][letterIndex] = row;
        lastTilesPlacedLocations[1][letterIndex] = column;
    }

    /* REMOVES THE LAST PLACED TILES FROM THE BOARD */
    public void removeLastTilesPlaced()
    {
        for ( int i = 0; i < lastTilesPlacedLocations[0].length; i++ )
        {
            int row = lastTilesPlacedLocations[0][i];
            int column = lastTilesPlacedLocations[1][i];
            board[row][column].setTile( '\u0000' );
        }

        numOfWords--;
    }

    /* RESETS THE BOARD TO ITS ORIGINAL STATE */
    public void reset()
    {
        this.board = new Square[BOUNDS][BOUNDS];
        initScores();
        numOfWords = 0;
        setLastTilesPlaced( null );
    }
}
