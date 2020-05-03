import java.util.ArrayList;
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

    private void generatePermutations( String lettersToPermute, PriorityQueue<WordEntry<Integer, String>> permutations )
    {
        generatePermutations( "", lettersToPermute, permutations );
    }

    private void generateCombinations( String combination, String lettersToCombine, ArrayList<String> combinations )
    {
        if ( combination.length() >= 2 )
        {
            int score = -1 * getScoreOfWord( combination );

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

    private String getWordWithHighestScore( String word )
    {
        ArrayList<String> combinations = new ArrayList<>();
        generateCombinations( word, combinations );

        PriorityQueue<WordEntry<Integer, String>> permutations = new PriorityQueue<>();

        for ( int i = 0; i < combinations.size(); i++ )
        {
            generatePermutations( combinations.get( i ), permutations );
        }

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

    private String placeFirstWord()
    {
        String word = getFrameAsWord();

        word = getWordWithHighestScore( word );

        if ( word == null )
        {
            return null;
        }

        //TODO
        // If word length more than 4, check first and last letters
        // Put towards left or right

        char column = (char) (73 - word.length());

        return column + "8 A " + word;
    }

    public String getCommand()
    {
        // Add your code here to input your commands

        // Your code must give the command NAME <botname> at the start of the game
        String command;

        switch ( turnCount )
        {
            case 0:
                command = "NAME DmitriyAngel";
                break;
            case 1:
                command = placeFirstWord();

                if ( command == null )
                {
                    return "PASS";
                }

                break;
            default:
                command = "PASS";
                break;
        }
        turnCount++;
        return command;
    }
}
