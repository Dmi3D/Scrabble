public class Player
{
    private String name;        //Stores the player name
    private int score;      //Stores the player's score
    private Frame frame;    // Stored the player's frame

    //Constructor of player
    public Player()
    {
        this.name = null;
        this.score = 0;
        this.frame = new Frame();
    }

    //Sets the name of the player
    public void setName(String name)
    {
        this.name = name;
    }

    //Increments the player score according to passed in value
    public void incrementScore(int score)
    {
        this.score += score;
    }

    //Returns the player name
    public String getName()
    {
        return this.name;
    }

    //Returns the player score
    public int getScore()
    {
        return this.score;
    }

    //Allows reset of player to default values
    public void resetPlayer()
    {
        this.name = null;
        this.score = 0;
    }

    // METHOD RETURNING FRAME OF PLAYER
    public String getFrame()
    {
        return Frame.getFrame();
    }

}
