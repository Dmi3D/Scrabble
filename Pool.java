// Team Name: Leap Card
// Team Members: Andra Antal-Berbecaru and Dmitriy Dranko

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Pool
{
    private Map<Character, Integer> tileValues;

    private HashMap<Character, Integer> tileFrequencies;

    private static int tilesInPool;

    public Pool()
    {
        // Initialising an immutable HashMap as the tile values never change
        tileValues = Map.ofEntries(
                Map.entry( 'A', 1 ), Map.entry( 'B', 3 ), Map.entry( 'C', 3 ),
                Map.entry( 'D', 2 ), Map.entry( 'E', 1 ), Map.entry( 'F', 4 ),
                Map.entry( 'G', 3 ), Map.entry( 'H', 4 ), Map.entry( 'I', 1 ),
                Map.entry( 'J', 8 ), Map.entry( 'K', 5 ), Map.entry( 'L', 1 ),
                Map.entry( 'M', 3 ), Map.entry( 'N', 1 ), Map.entry( 'O', 1 ),
                Map.entry( 'P', 3 ), Map.entry( 'Q', 10 ), Map.entry( 'R', 1 ),
                Map.entry( 'S', 1 ), Map.entry( 'T', 1 ), Map.entry( 'U', 1 ),
                Map.entry( 'V', 4 ), Map.entry( 'W', 4 ), Map.entry( 'X', 8 ),
                Map.entry( 'Y', 4 ), Map.entry( 'Z', 10 ), Map.entry( '*', 0 ) );

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

    public int getValue(char tile)
    {
        char newTile;
        // '*' represents a blank in Scrabble
        if (Character.isLetter( tile ) || tile == '*')
        {
            if( Character.isLowerCase( tile ))
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
        tileFrequencies = new HashMap<>(){
            {
                put('E', 12); put('A', 9); put('I', 9); put('O', 8);
                put('N', 6); put('R', 6); put('T', 6); put('L', 4);
                put('S', 4); put('U', 4); put('D', 4); put('G', 3);
                put('B', 2); put('C', 2); put('M', 2); put('P', 2);
                put('F', 2); put('H', 2); put('V', 2); put('W', 2);
                put('Y', 2); put('K', 1); put('J', 1); put('X', 1);
                put('Q', 1); put('Z', 1); put('*', 2);
            }};
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
        if (tilesInPool == 0)
        {
            return ' ';
        }

        Object[] tiles = tileFrequencies.keySet().toArray();
        Random random = new Random();

        // Generating a random tile from the array of keys from the hashmap of frequencies
        Object drawnTile = tiles[random.nextInt( tiles.length )];

        // Avoiding errors of suspicious calls due to object drawnTile being a Character
        assert drawnTile instanceof Character;

        int tileFrequency = tileFrequencies.get( ( drawnTile ));
        while ( tileFrequency < 1 )
        {
            // DRAWING A NEW TILE AGAIN
            drawnTile = tiles[random.nextInt( tiles.length )];
            assert drawnTile instanceof Character;
            tileFrequency = tileFrequencies.get( drawnTile );
        }

        // Decrementing frequency of drawn tile in its corresponding position in the hashmap
        tileFrequencies.put( (Character) drawnTile, tileFrequency - 1);

        tilesInPool -= 1;

        return (Character) drawnTile;
    }
}
