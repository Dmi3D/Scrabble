package sample;// Team Name: Leap Card
// Team Members: Andra Antal-Berbecaru and Dmitriy Dranko

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Pool
{
    private HashMap<Character, Integer> tileValues;

    private HashMap<Character, Integer> tileFrequencies;

    private static int tilesInPool;

    public Pool()
    {
        // Initialising a mutable HashMap with the tile values of the different tiles
        tileValues = new HashMap<Character, Integer>()
        {
            {
                put( 'A', 1 ); put( 'B', 3 ); put( 'C', 3 );
                put( 'D', 2 ); put( 'E', 1 ); put( 'F', 4 );
                put( 'G', 3 ); put( 'H', 4 ); put( 'I', 1 );
                put( 'J', 8 ); put( 'K', 5 ); put( 'L', 1 );
                put( 'M', 3 ); put( 'N', 1 ); put( 'O', 1 );
                put( 'P', 3 ); put( 'Q', 10 ); put( 'R', 1 );
                put( 'S', 1 ); put( 'T', 1 ); put( 'U', 1 );
                put( 'V', 4 ); put( 'W', 4 ); put( 'X', 8 );
                put( 'Y', 4 ); put( 'Z', 10 ); put( '*', 0 );
            }
        };



        setTileFrequencies();
        tilesInPool = 100;
    }

    public HashMap<Character, Integer> getTileFrequencies()
    {
        return tileFrequencies;
    }

    public Map<Character, Integer> getTileValues()
    {
        return tileValues;
    }

    public int getTilesInPool()
    {
        return tilesInPool;
    }

    public int getValue( char tile )
    {
        char newTile;
        // '*' represents a blank in Scrabble
        if ( Character.isLetter( tile ) || tile == '*' )
        {
            if ( Character.isLowerCase( tile ) )
            {
                newTile = Character.toUpperCase( tile );
                return getTileValues().get( newTile );
            }

            return getTileValues().get( tile );
        }
        // Return value is -1 for any characters provided at method call
        // that do not satisfy the conditions above
        return -1;
    }

    private void setTileFrequencies()
    {
        // Initialising a mutable HashMap as tiles get removed from and added to pool during the game
        tileFrequencies = new HashMap<Character, Integer>()
        {
            {
                put( 'E', 12 ); put( 'A', 9 ); put( 'I', 9 );
                put( 'O', 8 );  put( 'N', 6 ); put( 'R', 6 );
                put( 'T', 6 );  put( 'L', 4 ); put( 'S', 4 );
                put( 'U', 4 );  put( 'D', 4 ); put( 'G', 3 );
                put( 'B', 2 );  put( 'C', 2 ); put( 'M', 2 );
                put( 'P', 2 );  put( 'F', 2 ); put( 'H', 2 );
                put( 'V', 2 );  put( 'W', 2 ); put( 'Y', 2 );
                put( 'K', 1 );  put( 'J', 1 ); put( 'X', 1 );
                put( 'Q', 1 );  put( 'Z', 1 ); put( '*', 2 );
            }
        };
    }


    public void reset()
    {
        setTileFrequencies();
        tilesInPool = 100;
    }

    public boolean isEmpty()
    {
        return tilesInPool == 0;
    }

    public char drawTile()
    {
        if ( tilesInPool == 0 )
        {
            return ' ';
        }

        Object[] tiles = tileFrequencies.keySet().toArray();
        Random random = new Random();

        // Generating a random tile from the array of keys from the hashmap of frequencies
        Object drawnTile = tiles[random.nextInt( tiles.length )];

        // Avoiding errors of suspicious calls due to object drawnTile being a Character
        assert drawnTile instanceof Character;

        int tileFrequency = tileFrequencies.get( ( drawnTile ) );
        while (tileFrequency < 1)
        {
            // DRAWING A NEW TILE AGAIN
            drawnTile = tiles[random.nextInt( tiles.length )];
            assert drawnTile instanceof Character;
            tileFrequency = tileFrequencies.get( drawnTile );
        }

        // Decrementing frequency of drawn tile in its corresponding position in the hashmap
        tileFrequencies.put( (Character) drawnTile, tileFrequency - 1 );

        tilesInPool -= 1;

        return (Character) drawnTile;
    }
}
