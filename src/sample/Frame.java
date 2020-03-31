package sample;

import java.util.Arrays;

public class Frame
{
    private char[] tiles;

    public Frame( Pool pool )
    {
        this.tiles = new char[7];

        // Filling array with empty tiles, represented as ' '
        Arrays.fill( tiles, ' ' );

        // The frame object is filled automatically when created
        fillFrame( pool );
    }

    public Frame( Frame anotherFrame )    // copy Constructor
    {
        this.tiles = new char[7];
        for ( int i = 0; i < 7; i++ )
        {
            this.tiles[i] = anotherFrame.getTile( i );
        }
    }

    public char getTile( int index )
    {
        if ( index >= 0 && index < 7 )
        {
            return tiles[index];
        }

        return ' ';
    }

    public char[] getFrame()
    {
        return tiles;
    }

    public char removeTile( char tileToBeRetrieved )
    {
        // If the tile cannot be set to blank because it does not exist
        if ( !setBlank( tileToBeRetrieved ) )
        {
            return ' ';
        }

        // If we reach here, the tile has been set to blank
        return tileToBeRetrieved;
    }

    private boolean setBlank( char tileFromPool )
    {
        // Storing index of tile to be blanked
        int tileToBeBlanked = getIndexOfTile( tileFromPool );

        // If tile to be removed is not in the frame
        if ( tileToBeBlanked == -1 )
        {
            return false;
        }

        // Setting tile to empty tile ' '
        tiles[tileToBeBlanked] = ' ';

        return true;
    }

    public boolean isEmpty()
    {
        for ( char tile : tiles )
        {
            if ( tile != ' ' )
            {
                return false;
            }
        }

        return true;
    }

    public boolean hasEmpty()
    {
        for ( char tile : tiles )
        {
            if ( tile == ' ' )
            {
                return true;
            }
        }

        return false;
    }

    public int getIndexOfTile( char tileToBeFound )
    {
        if ( isEmpty() )
        {
            return -1;
        }

        for ( int i = 0; i < tiles.length; i++ )
        {
            if ( tiles[i] == tileToBeFound )
            {
                // Return index of tile found in the frame
                return i;
            }
        }

        return -1;
    }

    public void displayFrame()
    {
        System.out.println( Arrays.toString( tiles ) );
    }

    public void fillFrame( Pool pool )
    {
        for ( int i = 0; i < tiles.length; i++ )
        {
            if ( tiles[i] == ' ' )
            {
                // Replacing the empty tile in the frame with the drawn tile from the pool
                tiles[i] = pool.drawTile();
            }
        }
    }

    public void fillFrameWithWord( char[] word )
    {
        int letterIndex = 0;
        int frameIndex = 0;

        while (letterIndex < word.length && frameIndex < tiles.length)
        {
            while (letterIndex < word.length && word[letterIndex] == ' ')
            {
                letterIndex++;
            }

            if ( tiles[frameIndex] == ' ' )
            {
                tiles[frameIndex] = word[letterIndex];
                frameIndex++;
                letterIndex++;
            }

            else
            {
                frameIndex++;
            }
        }
    }

    /* EXCHANGES THE LETTERS PASSED IN WITH OTHER RANDOM LETTERS FROM THE POOL */
    public boolean exchangeTiles( char[] lettersToExchange, Pool pool )
    {
        if ( hasEmpty() || lettersToExchange.length > 7 )
        {
            return false;
        }

        for ( char letter : lettersToExchange )
        {
            if ( getIndexOfTile( letter ) == -1 )
            {
                return false;
            }
        }

        for ( char letter : lettersToExchange )
        {
            char tileToRemove = removeTile( letter );   // removing each corresponding letter from the frame
            int frequency = pool.getTileFrequencies().get( tileToRemove );  // getting how many letters like the one removed are currently in the pool
            pool.getTileFrequencies().put( tileToRemove, frequency + 1 );   // putting the removed letter back into the pool, incrementing its number
        }
        fillFrame( pool );  // filling frame with random letters from the pool
        pool.increaseTilesInPool( lettersToExchange.length );       // re-incrementing the number of tiles in pool to actual number


        return true;
    }

    public int getScoreOnFrame( Pool pool )
    {
        int scoreOfTilesOnFrame = 0;
        for ( char tile : tiles )
        {
            scoreOfTilesOnFrame += pool.getValue( tile );
        }
        return scoreOfTilesOnFrame;
    }


    public void reset( Pool pool )
    {
        // Fill the frame with empty tiles
        Arrays.fill( tiles, ' ' );

        // Filling the frame with tiles from pool.
        fillFrame( pool );
    }

}
