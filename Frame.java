import java.util.Arrays;

public class Frame
{
    //Stores the tile in the frame
    private char[] tiles;

    /* CONSTRUCTOR FOR THE FRAME */
    public Frame()
    {
        // Initialises array to size seven
        this.tiles = new char[7];

        // Fill all the 0 char with ' ' char
        Arrays.fill( tiles, ' ' ); //We represent empty tile as ' '

        // Fill the frame with tiles
        fillFrame();
    }

    /* RETURNS A CERTAIN TILE FROM THE FRAME */
    public char takeTile(char tileToBeRetrieved)
    {
        int tileToBeRemoved = isTileInFrame(tileToBeRetrieved);

        // If the tile is in the frame
        if(tileToBeRemoved == -1)
        {
           return ' ';
        }

        //Remove this tile from the frame
        removeTile( tiles[tileToBeRemoved] );

        //Fill that space with a frame if pool still contains tiles
        if(!Pool.isPoolEmpty())
        {
            fillFrame();
        }

        // Return the tile we are taking
        return tileToBeRetrieved;
    }

    /* REMOVES A CERTAIN TILE FROM THE FRAME, RETURNS REMOVE SUCCESSFUL OR FAIL */
    private boolean removeTile(char tileFromPool)
    {
        //Storing index of tile to be removed
        int tileToBeRemoved = isTileInFrame(tileFromPool);

        //If tile to be removed is not in the frame, remove nothing & return FAIL state
        if(tileToBeRemoved == -1)
        {
            return false;
        }

        //Set tile to empty tile ' '
        tiles[tileToBeRemoved] = ' ';

        // Return SUCCESS state
        return true;
    }

    /* CHECKS IF THE FRAME IS EMPTY */
    public boolean isEmpty()
    {
        // Look through the tiles in the frame
        for(char tile : tiles)
        {
            // If the frame contains any tile
            if(tile != ' ')
            {
                // Return FAIL state
                return false;
            }
        }

        // If the frame did not contain any tiles, return SUCCESS state
        return true;
    }

    /* CHECKS IF A CERTAIN TILE IS IN A FRAME */
    private int isTileInFrame(char tileToBeFound)
    {
        // If the frame is empty
        if(isEmpty())
        {
            // Return FAIL index
            return -1;
        }

        //Otherwise, look through the tiles in the frame
        for(int i = 0; i < tiles.length; i++)
        {
            // If the tile is found in the frame
            if(tiles[i] == tileToBeFound)
            {
                // Return index of tile
                return i;
            }
        }

        // If the tile is not found in the frame, return FAIL index
        return -1;
    }

    /* RETURNS THE FRAME IN THE FORM OF A STRING */
    public String getFrame()
    {
        return Arrays.toString(tiles);
    }

    /* FILLS ALL EMPTY SPACES IN THE FRAME*/
    private void fillFrame()
    {
        // Look through the tiles in the frame
       for(int i = 0; i < tiles.length; i++)
       {
           //If a tile is empty
           if(tiles[i] == ' ')
           {
               //Draw a tile from the pool and replace the empty tile
               tiles[i] = Pool.drawTile();
           }
       }
    }

    /* RESETS THE FRAME TO ARRAY OF EMPTY SPACES */
    public void reset()
    {
        // Fill the frame with empty tiles
        Arrays.fill( tiles, ' ' );

        // Fill the frame with tiles from pool
        fillFrame();
    }

}
