package sample;

public class Player
{
    private String name;
    private int score;
    private Frame frame;

    public Player( Frame frame )
    {
        this.name = null;
        this.score = 0;
        this.frame = frame;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void incrementScore( int score )
    {
        this.score += score;
    }

    public int getScore()
    {
        return this.score;
    }

    public Frame getPlayerFrame()
    {
        return frame;
    }

    public void reset( Pool pool )
    {
        this.name = " ";
        this.score = 0;
        /* Frame associated with player also gets reset by calling its own reset function.
           This is done to ensure that the frame will not have tiles taken from the pool
           used in the previous game. When game is reset, the frame will get tiles from
           the reset pool */
        frame.reset( pool );
    }

}
