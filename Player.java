public class Player
{
    private String name;        //Stores the player name
    private int score;      //Stores the player's score

    //Constructor of player
    public Player()
    {
        this.name = null;
        this.score = 0;
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

    //insert code to allow access to a player's frame (tiles)
    //once Frame class is finished
}
