// Implementation of Pool Class
// Team Name: Leap Card
// Team Members: Andra Antal-Berbecaru and Dmitriy Dranko

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Pool
{
    // Storing value of each tile
    Map<Character, Integer> tileValues;

    // Storing number of each tile
    private HashMap<Character, Integer> tileFrequencies;

    // Storing the number of tiles in the pool
    private int tilesInPool;

    // CLASS CONSTRUCTOR INITIALISING INSTANCE VARIABLES
    public Pool()
    {
        // Initialising an immutable HashMap containing the tile letters and their corresponding tile scores
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
        setTilesInPool(100);
    }

    // ACCESSOR FOR tileValues Hash Map
    public Map<Character, Integer> getTileValues()
    {
        return tileValues;
    }

    // QUERYING THE VALUE OF A TILE
    public int getTileValue(char tile)
    {
        char newTile;
        // IF CHARACTER IS A LETTER OR A STAR (*) REPRESENTING A BLANK TILE
        if (Character.isLetter( tile ) || tile == '*')
        {
            if( Character.isLowerCase( tile ))
            {
                // CONVERT TO UPPERCASE IF CHARACTER PROVIDED WAS LOWERCASE
                newTile = Character.toUpperCase( tile );

                // RETURN VALUE OF newTile IN tileValues HASH MAP
                return getTileValues().get( newTile );
            }

            // RETURNING THE VALUE OF THE TILE BY ACCESSING tileValues HASH MAP
            return getTileValues().get( tile );
        }
        // IF CHARACTER PROVIDED AT METHOD CALL IS NOT A LETTER AND NOT A STAR,
        // THEN RETURN VALUE -1 AS THERE IS NO VALUE FOR SUCH TILE
        return -1;

    }

    // ACCESSOR FOR tileFrequencies Hash Map
    public HashMap<Character, Integer> getTileFrequencies()
    {
        return tileFrequencies;
    }

    // Accessor for number of tiles in the pool at time of call

    // ** MIGHT NEED TO CHANGE IT TO PRIVATE IF IT'S NOT SPECIFICALLY USED ELSEWHERE
    public int getTilesInPool()
    {
        return tilesInPool;
    }

    // DISPLAYS THE NUMBER OF TILES CURRENTLY IN THE POOL TO THE CALLER
    public void displayTiles()
    {
        System.out.println( "NUMBER OF TILES CURRENTLY IN POOL: " + getTilesInPool() );
    }

    public void setTilesInPool(int tilesInPool)
    {
        this.tilesInPool = tilesInPool;
    }

    public void setTileFrequencies()
    {
        //Initialising a mutable HashMap containing the tile letters and their initial corresponding number
        this.tileFrequencies = new HashMap<>(){
            {
                put('A', 9); put('I', 9); put('O', 8); put('N', 6);
                put('R', 6); put('T', 6); put('L', 4); put('S', 4);
                put('U', 4); put('D', 4); put('G', 3); put('B', 2);
                put('C', 2); put('M', 2); put('P', 2); put('F', 2);
                put('H', 2); put('V', 2); put('W', 2); put('Y', 2);
                put('K', 1); put('J', 1); put('X', 1); put('Q', 1);
                put('Z', 1); put('*', 2);
            }};
    }

    // Method that resets the pool i.e. the number of tiles in the pool get back to original
    // which is 100 for total number of tiles, and the original frequency for each individual
    // tile
    public void reset()
    {
        setTilesInPool(100);
        setTileFrequencies();
        System.out.println("The pool was successfully reset to original number of tiles.");
    }


    // CHECKING TO SEE IF THE POOL IS EMPTY,
    public boolean isPoolEmpty()
    {
        return getTilesInPool() == 0;
    }

    // METHOD RETURNING A TILE DRAWN AT RANDOM FROM THE POOL
    public char drawTile()
    {
        // RANDOMISING HASH MAP DIRECTLY
        // NEEDS TO BE TESTED TO ENSURE THAT IT WORKS WITHOUT HAVING TO RESHUFFLE THE
        // HASH MAP AFTER RANDOMISING EACH TILE, FOR THE PURPOSE OF PRESERVING THE
        // NATURE OF RANDOMISATION

        // VARIABLE FOR STORING THE FREQUENCY OF A TILE
        int tileFrequency;

        // INITIALISING A Random OBJECT
        Random random = new Random();

        // GENERATING A RANDOM TILE FROM THE HASH MAP tileFrequencies
        Object[] tiles = getTileFrequencies().keySet().toArray();

        // GENERATING A RANDOM TILE FROM THE HASH MAP
        Object drawnTile = tiles[random.nextInt( tiles.length )];
        assert drawnTile instanceof Character;

        // QUERYING THE TILE'S CORRESPONDING FREQUENCY VALUE
        tileFrequency = getTileFrequencies().get( ( drawnTile ));

        // CONTINUING TO GENERATE A RANDOM TILE UNTIL WE FIND ONE IN THE POOL
        // i.e. THERE MIGHT BE CERTAIN TILES THAT REACHED A FREQUENCY OF 0
        // AFTER MULTIPLE DRAWS, SO IT'S NECESSARY TO PREVENT THOSE TILES
        // FROM BEING USED WHEN THEIR FREQUENCY REACHED ZERO
        while ( tileFrequency < 1 )
        {
            // DRAWING A NEW TILE AGAIN
            drawnTile = tiles[random.nextInt( tiles.length )];
            // UPDATING THE tileFrequency TO STORE THE NEW DRAWN TILE'S
            // CORRESPONDING FREQUENCY

            // ENSURING drawnTile is of type Character
            // AVOIDING SUSPICIOUS CALLS ERRORS
            assert drawnTile instanceof Character;
            tileFrequency = getTileFrequencies().get( drawnTile );
        }

        // DECREASING THE FREQUENCY OF THE DRAWN TILE THAT SATISFIES ABOVE
        // CONDITION
        getTileFrequencies().put( (Character) drawnTile, tileFrequency - 1);

        // SUBTRACTING FROM THE VARIABLE HOLDING NUMBER OF TILES
        setTilesInPool( this.tilesInPool-1 );

        // RETURNING THE RANDOMLY DRAWN TILE TO THE CALLER
        return (Character) drawnTile;

    }
}
