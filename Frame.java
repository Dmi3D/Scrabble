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
    }

    /* RETURNS A CERTAIN TILE FROM THE FRAME */
    //Insert code
    //Should remove it from frame aswell as return it if remove was successful

    /* REMOVES A CERTAIN TILE FROM THE FRAME, RETURNS REMOVE SUCCESSFUL OR FAIL */
    private boolean removeTile(char tileFromPool)
    {
        // If the letter is in the frame
        if(isTileInFrame( tileFromPool ))
        {
            // Look through the letters in the frame
            for(char tile : tiles)
            {
                // When we find the letter
                if(tile == tileFromPool)
                {
                    // Remove that tile
                    tile = ' ';

                    // Return SUCCESS state
                    return true;
                }
            }
        }

        // If the tileFromPool is not in the frame, return FAIL state
        return false;
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
    public boolean isTileInFrame(char tileToBeFound)
    {
        // If the frame is empty
        if(isEmpty())
        {
            // Return FAIL state
            return false;
        }

        //Otherwise, look through the tiles in the frame
        for(char tile : tiles)
        {
            // If the tile is found in the frame
            if(tile == tileToBeFound)
            {
                // Return SUCCESS state
                return true;
            }
        }

        // If the tile is not found in the frame, return FAIL state
        return false;
    }

    /* RETURNS THE FRAME IN THE FORM OF A STRING */
    public String getFrame()
    {
        return Arrays.toString(tiles);
    }

    /* FILLS ALL EMPTY SPACES IN THE FRAME*/
    public void fillFrame()
    {
        // Look through the tiles in the frame
       for(char tile : tiles)
       {
           // If a tile is empty
           if(tile == ' ')
           {
               // Draw a tile from the pool and replace the empty tile
               tile = Pool.drawTile();
           }
       }
    }
}
