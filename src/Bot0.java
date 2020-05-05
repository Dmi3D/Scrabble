import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

import static java.lang.Integer.compare;

//LEAP-CARD BOT
public class Bot0 implements BotAPI
{
    private class WordEntry<Integer, String> implements Comparable<WordEntry<Integer, String>>
    {
        private int score;
        private String letters;

        public WordEntry( int key, String value )
        {
            this.score = key;
            this.letters = value;
        }

        public int getScore()
        {
            return score;
        }

        public String getLetters()
        {
            return letters;
        }

        @Override
        public int compareTo( WordEntry<Integer, String> other )
        {
            return compare( this.getScore(), other.getScore() );
        }

        @Override
        public java.lang.String toString()
        {
            int scoreToPrint = getScore() * ( -1 );

            return letters + " (" + scoreToPrint + ")";
        }
    }

    // The public API of Bot must not change
    // This is ONLY class that you can edit in the program
    // Rename Bot to the name of your team. Use camel case.
    // Bot may not alter the state of the game objects
    // It may only inspect the state of the board and the player objects

    private PlayerAPI me;
    private OpponentAPI opponent;
    private BoardAPI board;
    private UserInterfaceAPI info;
    private DictionaryAPI dictionary;
    private int turnCount;

    Bot0( PlayerAPI me, OpponentAPI opponent, BoardAPI board, UserInterfaceAPI ui, DictionaryAPI dictionary )
    {
        this.me = me;
        this.opponent = opponent;
        this.board = board;
        this.info = ui;
        this.dictionary = dictionary;
        turnCount = 0;
    }

    private String getFrameAsWord()
    {
        String lettersInFrame;
        lettersInFrame = me.getFrameAsString();
        StringBuilder permutation = new StringBuilder();

        for ( int i = 1; i < lettersInFrame.length() - 1; i++ )
        {
            if ( lettersInFrame.charAt( i ) != ',' && lettersInFrame.charAt( i ) != ' ' )
            {
                permutation.append( lettersInFrame.charAt( i ) );
            }
        }

        return permutation.toString();
    }

    private int getScoreOfWord( String word )
    {
        int score = 0;

        for ( int i = 0; i < word.length(); i++ )
        {
            Tile tile = new Tile( word.charAt( i ) );
            score += tile.getValue();
        }

        return score;
    }

    private void generatePermutations( String permutation, String lettersToPermute, PriorityQueue<WordEntry<Integer, String>> permutations )
    {
        if ( lettersToPermute.length() == 0 )
        {
            int score = -1 * getScoreOfWord( permutation );

            WordEntry<Integer, String> entry = new WordEntry<>( score, permutation );
            //System.out.println( "Perm: " + entry.getLetters() );
            permutations.add( entry );
        }

        for ( int i = 0; i < lettersToPermute.length(); i++ )
        {
            generatePermutations( permutation + lettersToPermute.charAt( i ), lettersToPermute.substring( 0, i ) + lettersToPermute.substring( i + 1 ), permutations );
        }
    }

    private void generatePermutations( ArrayList<String> combinations, PriorityQueue<WordEntry<Integer, String>> permutations )
    {
        for ( int i = 0; i < combinations.size(); i++ )
        {
            generatePermutations( "", combinations.get( i ), permutations );
        }
    }

    private void generateCombinations( String combination, String lettersToCombine, ArrayList<String> combinations )
    {
        if ( combination.length() >= 1 )
        {
            //System.out.println( "Combo: " + combination );
            combinations.add( combination );
        }

        for ( int i = 0; i < lettersToCombine.length(); i++ )
        {
            generateCombinations( combination + lettersToCombine.charAt( i ), lettersToCombine.substring( i + 1 ), combinations );
        }
    }

    private void generateCombinations( String lettersToCombine, ArrayList<String> combinations )
    {
        generateCombinations( "", lettersToCombine, combinations );
    }

    private String getFirstWord( String word )
    {
        ArrayList<String> combinations = new ArrayList<>();
        generateCombinations( word, combinations );

        PriorityQueue<WordEntry<Integer, String>> permutations = new PriorityQueue<>();

        generatePermutations( combinations, permutations );

        //System.out.println( "PQ size: " + permutations.size() );

        String permutation = permutations.poll().getLetters();

        Word permutationAsWord = new Word( 8, 8, true, permutation );

        ArrayList<Word> permutationList = new ArrayList<>();

        permutationList.add( permutationAsWord );

        // Check if permutation is word, if not, poll last entry again.
        while ( !permutations.isEmpty() && !dictionary.areWords( permutationList ) )
        {
            permutationList.remove( 0 );

            permutation = permutations.poll().getLetters();

            permutationAsWord = new Word( 8, 8, true, permutation );

            permutationList.add( permutationAsWord );
        }

        // Word is found
        if ( dictionary.areWords( permutationList ) )
        {
            return permutationAsWord.getLetters();
        }

        else
        {
            return null;
        }
    }

    private String getCommandPlaceFirstWord()
    {
        if ( board.isFirstPlay() )
        {
            String word = getFirstWord( getFrameAsWord() );

            if ( word == null )
            {
                return null;
            }

            //TODO
            // If frameLetters length more than 4, check first and last letters
            // Put towards left or right

            char column = (char) ( 73 - word.length() );

            return column + "8 A " + word;
        }

        return null;
    }

    private PriorityQueue<WordEntry<Integer, String>> getLettersOnBoard()
    {
        PriorityQueue<WordEntry<Integer, String>> lettersOnBoard = new PriorityQueue<>();

        for ( int i = 0; i < Board.BOARD_SIZE; i++ )
        {
            for ( int j = 0; j < Board.BOARD_SIZE; j++ )
            {
                Square square = board.getSquareCopy( i, j );

                if ( square.isOccupied() )
                {
                    Tile tile = square.getTile();

                    WordEntry<Integer, String> entry = new WordEntry<>( ( -1 * tile.getValue() ), String.valueOf( tile.getLetter() ) );

                    // Avoiding repeating letters
                    if ( !lettersOnBoard.contains( entry ) )
                    {
                        lettersOnBoard.add( entry );
                    }
                }
            }
        }

        return lettersOnBoard;
    }

    private ArrayList<String> appendLetter( String letterToAppend, ArrayList<String> combinations )
    {
        ArrayList<String> combinationsWithLetterOnBoard = new ArrayList<>();

        for ( int i = 0; i < combinations.size(); i++ )
        {
            combinationsWithLetterOnBoard.add( combinations.get( i ) );
        }

        for ( int i = 0; i < combinationsWithLetterOnBoard.size(); i++ )
        {
            String combination = combinationsWithLetterOnBoard.get( i );
            combination += letterToAppend;

            combinationsWithLetterOnBoard.set( i, combination );
        }

        return combinationsWithLetterOnBoard;
    }

    private PriorityQueue<WordEntry<Integer, String>> getValidWords( PriorityQueue<WordEntry<Integer, String>> permutations )
    {
        PriorityQueue<WordEntry<Integer, String>> validWords = new PriorityQueue<>();

        // While there are still permutations to check if valid words
        while ( !permutations.isEmpty() )
        {
            String permutation = permutations.poll().getLetters();

            Word permutationToCheckInDictionary = new Word( 8, 8, true, permutation );

            ArrayList<Word> permutationAsList = new ArrayList<>();

            permutationAsList.add( permutationToCheckInDictionary );

            // If word is valid word in dictionary, add to validWords PQ
            if ( dictionary.areWords( permutationAsList ) )
            {
                Word validWord = permutationAsList.remove( 0 );

                String validString = validWord.getLetters();
                int validStringScore = getScoreOfWord( validString ) * ( -1 );

                WordEntry<Integer, String> validWordEntry = new WordEntry<>( validStringScore, validString );

                validWords.add( validWordEntry );
            }
        }

        return validWords;
    }

    private ArrayList<int[]> getLocationsOfLetterOnBoard( String letterOnBoard )
    {
        ArrayList<int[]> locationsOfLetterOnBoard = new ArrayList<>();

        for ( int i = 0; i < Board.BOARD_SIZE; i++ )
        {
            for ( int j = 0; j < Board.BOARD_SIZE; j++ )
            {
                Square square = board.getSquareCopy( i, j );

                if ( square.isOccupied() )
                {
                    String matchingLetter = String.valueOf( square.getTile().getLetter() );

                    // If letter we looking for matches letter on board, save its location
                    if ( letterOnBoard.equals( matchingLetter ) )
                    {
                        int[] location = {i, j};
                        locationsOfLetterOnBoard.add( location );
                    }
                }
            }
        }

        return locationsOfLetterOnBoard;
    }

    private ArrayList<Integer> getDirectionsOfLettersOnBoard( ArrayList<int[]> locationsOfLetterOnBoard )
    {
        ArrayList<Integer> directionsOfLettersOnBoard = new ArrayList<>( locationsOfLetterOnBoard.size() );

        // 0 is neither
        // 1 is horizontal
        // 2 is vertical

        // For each co-ordinate of letter on board
        for ( int i = 0; i < locationsOfLetterOnBoard.size(); i++ )
        {
            int[] location = locationsOfLetterOnBoard.get( i );
            int row = location[0];
            int column = location[1];

            // If not on the side edge
            if ( row > 0 && row < Board.BOARD_SIZE - 1 )
            {
                if ( !board.getSquareCopy( row - 1, column ).isOccupied() && !board.getSquareCopy( row + 1, column ).isOccupied() )
                {
                    directionsOfLettersOnBoard.add( 2 );
                    continue;
                }
            }

            // If not on the top and bottom edge
            if ( column > 0 && column < Board.BOARD_SIZE - 1 )
            {
                if ( !board.getSquareCopy( row, column - 1 ).isOccupied() && !board.getSquareCopy( row, column + 1 ).isOccupied() )
                {
                    directionsOfLettersOnBoard.add( 1 );
                    continue;
                }
            }

            // If letter is occupied on all sides, it cant be placed at that letter
            directionsOfLettersOnBoard.add( 0 );
        }

        return directionsOfLettersOnBoard;
    }

    private int[] getStartingLocation( String wordToTryPlace, String letterOnBoard, int[] locationOfLetter, int directionOfLetter )
    {
        // 1 = horizontal
        // 2 = vertical

        int offset = 0;

        for ( int i = 0; i < wordToTryPlace.length(); i++ )
        {
            String letterInWord = String.valueOf( wordToTryPlace.charAt( i ) );

            if ( letterInWord.equals( letterOnBoard ) )
            {
                break;
            }

            offset++;
        }

        int[] startingLocation = new int[2];

        int row = locationOfLetter[0];
        int column = locationOfLetter[1];

        // If direction to place word is horizontal
        if ( directionOfLetter == 1 )
        {
            startingLocation[0] = row;
            startingLocation[1] = column - offset;
            return startingLocation;
        }

        else if ( directionOfLetter == 2 )
        {
            startingLocation[0] = row - offset;
            startingLocation[1] = column;
            return startingLocation;
        }

        startingLocation[0] = -1;
        startingLocation[1] = -1;

        return startingLocation;
    }

    private char convertToLetterCord( int column )
    {
        int a = 'A';

        int locationAsASCII = a + column;

        char cord = (char) locationAsASCII;

        return cord;
    }

    private char convertToDirection( int direction )
    {
        if ( direction == 1 )
        {
            return 'A';
        }

        else if ( direction == 2 )
        {
            return 'D';
        }

        else return '\u0000';
    }

    private String getCommandPlaceWord()
    {
        System.out.println( "LETTERS IN FRAME: " + me.getFrameAsString() );
        // Get all combinations of letters in frame
        ArrayList<String> combinations = new ArrayList<>();
        generateCombinations( getFrameAsWord(), combinations );

        // Append first letter from board to copy of the array list, and to each combination
        PriorityQueue<WordEntry<Integer, String>> lettersOnBoard = getLettersOnBoard();

        System.out.println( "Letters on board: " + lettersOnBoard );

        while ( !lettersOnBoard.isEmpty() )
        {
            // The letter on the board we are currently considering
            String letterOnBoard = lettersOnBoard.poll().getLetters();

            System.out.println( "Combination at 10: " + combinations.get( 10 ) );
            // Get the list of combinations with the letter on the board appended
            ArrayList<String> appendedCombinations = appendLetter( letterOnBoard, combinations );
            System.out.println( "Combination with Append at 10: " + appendedCombinations.get( 10 ) );

            // Get permutation of each combination
            PriorityQueue<WordEntry<Integer, String>> permutations = new PriorityQueue<>();
            generatePermutations( appendedCombinations, permutations );

            System.out.println( "Number of permutations: " + permutations.size() );

            // Check each permutation to see if it is a word and store in max pq
            PriorityQueue<WordEntry<Integer, String>> validWords = getValidWords( permutations );
            System.out.println( "Number of valid words from permutations: " + validWords.size() );
            System.out.println( "Valid words: " + validWords );

            // Get co-ordinates of letter on board
            ArrayList<int[]> locationsOfLetterOnBoard = getLocationsOfLetterOnBoard( letterOnBoard );
            System.out.println( "Location of " + letterOnBoard + ": " + Arrays.toString( locationsOfLetterOnBoard.get( 0 ) ) );

            // Get direction for each letter on board
            ArrayList<Integer> directionsOfLettersOnBoard = getDirectionsOfLettersOnBoard( locationsOfLetterOnBoard );

            String dir = "no direction";

            if ( directionsOfLettersOnBoard.get( 0 ) == 2 )
            {
                dir = "vertical";
            }
            else if ( directionsOfLettersOnBoard.get( 0 ) == 1 )
            {
                dir = "horizontal";
            }

            System.out.println( "Should place in direction " + dir );

            // While there are still valid words to check at the locations
            while ( !validWords.isEmpty() )
            {
                String validWord = validWords.poll().getLetters();

                // For each location on the board
                for ( int i = 0; i < locationsOfLetterOnBoard.size(); i++ )
                {
                    int[] locationOfLetter = locationsOfLetterOnBoard.get( i );
                    int directionOfLetter = directionsOfLettersOnBoard.get( i );

                    // If location is not occupied on all sides
                    if ( directionOfLetter > 0 )
                    {
                        int[] startingLocation;

                        startingLocation = getStartingLocation( validWord, letterOnBoard, locationOfLetter, directionOfLetter );

                        int row = startingLocation[0];
                        int column = startingLocation[1];
                        boolean isHorizontal = directionOfLetter == 1;

                        if ( ( row >= 0 && column >= 0 ) && ( ( row < Board.BOARD_SIZE ) && ( column < Board.BOARD_SIZE ) ) )
                        {
                            Word wordToPlace = new Word( row, column, isHorizontal, validWord );

                            System.out.println( "Should be true: " + board.isLegalPlay( me.getFrame(), wordToPlace ) );

                            if ( board.isLegalPlay( me.getFrame(), wordToPlace ) )
                            {
                                char columnAsLetter = convertToLetterCord( column );
                                System.out.println( "Column as letter: " + columnAsLetter );
                                char direction = convertToDirection( directionOfLetter );
                                row++;

                                String command = "";

                                command += columnAsLetter;
                                command += row;
                                command += " ";
                                command += direction;
                                command += " ";
                                command += wordToPlace.getLetters();

                                System.out.println( command );
                                return command;
                            }
                        }
                    }
                }
            }
        }

        System.out.println( "Returned null" );
        return null;
    }

    private String getPlaceCommand()
    {
        if ( board.isFirstPlay() )
        {
            return getCommandPlaceFirstWord();
        }

        return getCommandPlaceWord();
    }

    public String getCommand()
    {
        // Add your code here to input your commands

        // Your code must give the command NAME <botname> at the start of the game
        String command;

        switch ( turnCount )
        {
            case 0:
                command = "NAME Opposition";
                break;
            default:
                command = getPlaceCommand();

                if ( command == null )
                {
                    return "PASS";
                }

                break;
        }
        turnCount++;
        return command;
    }
}
