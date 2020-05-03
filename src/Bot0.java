import java.util.ArrayList;
import java.util.PriorityQueue;

import static java.lang.Integer.compare;

//LEAP-CARD BOT
public class Bot0 implements BotAPI
{
    private class PermutationEntry<Integer, String> implements Comparable<PermutationEntry<Integer, String>>
    {
        private int score;
        private String letters;

        public PermutationEntry( int key, String value )
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
        public int compareTo( PermutationEntry<Integer, String> other )
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

    private String swap( String wordToPermute, int positionOne, int positionTwo )
    {
        char temp;
        char[] charArray = wordToPermute.toCharArray();

        temp = charArray[positionOne];
        charArray[positionOne] = charArray[positionTwo];
        charArray[positionTwo] = temp;

        return String.valueOf( charArray );
    }

    private void generatePermutations( String word, PriorityQueue<PermutationEntry<Integer, String>> permutations, int startIndex, int endIndex )
    {
        if ( startIndex == endIndex )
        {
            int score = -1 * getScoreOfWord( word );
            PermutationEntry<Integer, String> entry = new PermutationEntry<>( score, word );

            permutations.add( entry );
        }

        else
        {
            for ( int i = startIndex; i <= endIndex; i++ )
            {
                word = swap( word, startIndex, i );
                generatePermutations( word, permutations, startIndex + 1, endIndex );
                word = swap( word, startIndex, i );
            }
        }
    }

    private void generatePermutations( String lettersToPermute, PriorityQueue<PermutationEntry<Integer, String>> permutations )
    {
        generatePermutations( lettersToPermute, permutations, 0, lettersToPermute.length() - 1 );
    }

    private String getWordWithHighestScore( String word )
    {
        PriorityQueue<PermutationEntry<Integer, String>> permutations = new PriorityQueue<>();

        generatePermutations( word, permutations );
        System.out.println( "TreeMap size: " + permutations.size() );

        String permutation = permutations.poll().getLetters();

        Word permutationAsWord = new Word( 8, 8, true, permutation );

        ArrayList<Word> permutationList = new ArrayList<>();

        permutationList.add( permutationAsWord );

        // Check if permutation is word, if not, poll last entry again.
        while ( !permutations.isEmpty() && !dictionary.areWords( permutationList ) )
        {
            permutationList.remove(0);

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

        return "H8 A " + word;
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
