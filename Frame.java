import java.util.Arrays;

public class Frame
{
    private char[] tiles;

    public Frame()
    {
        this.tiles = new char[7];

        // Filling array with empty tiles, represented as ' '
        Arrays.fill( tiles, ' ' );

        // The frame object is filled automatically when created
        fillFrame();
    }

    // Providing access to the frame of tiles
    public char[] getFrame()
    {
        return tiles;
    }

    /* RETURNS A CERTAIN TILE FROM THE FRAME */
    public char removeTile(char tileToBeRetrieved)
    {
        // If the tile cannot be set to blank because it does not exist
        if(!setBlank(tileToBeRetrieved))
        {
           return ' ';
        }

        // If we reach here, the tile has been set to blank

        // Filling that space with a frame if pool still contains tiles
        if(!Pool.isPoolEmpty())
        {
            fillFrame();
        }

        // Return the tile we are taking from the frame
        return tileToBeRetrieved;
    }

    /* SETS A CERTAIN TILE IN THE FRAME TO BLANK, RETURNS SUCCESS OR FAIL */
    private boolean setBlank(char tileFromPool)
    {
        // Storing index of tile to be blanked
        int tileToBeBlanked = isTileInFrame(tileFromPool);

        // If tile to be removed is not in the frame, remove nothing & return FAIL state
        if(tileToBeBlanked == -1)
        {
            return false;
        }

        // Setting tile to empty tile ' '
        tiles[tileToBeBlanked] = ' ';

        // Return SUCCESS state
        return true;
    }

    public boolean isEmpty()
    {
        for(char tile : tiles)
        {
            if(tile != ' ')
            {
                return false;
            }
        }

        return true;
    }

    /* CHECKS IF A CERTAIN TILE IS IN A FRAME */
    private int isTileInFrame(char tileToBeFound)
    {
        if(isEmpty())
        {
            return -1;
        }

        for(int i = 0; i < tiles.length; i++)
        {
            if(tiles[i] == tileToBeFound)
            {
                // Return index of tile found in the frame
                return i;
            }
        }

        return -1;
    }

    /* PRINTS THE FRAME IN THE FORM OF A STRING */
    public void displayFrame()
    {
        System.out.println(Arrays.toString(tiles));
    }

    private void fillFrame()
    {
       for(int i = 0; i < tiles.length; i++)
       {
           if(tiles[i] == ' ')
           {
               // Replacing the empty tile in the frame with the drawn tile from the pool
               tiles[i] = Pool.drawTile();
           }
       }
    }

    public void reset()
    {
        // Fill the frame with empty tiles
        Arrays.fill( tiles, ' ' );

        // Filling the frame with tiles from pool.
        // This is called at the end of the game after resetting the pool to
        // fill the frame object with tiles from the reset pool.
        fillFrame();
    }

}
