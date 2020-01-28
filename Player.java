public class Player
{
    String name;        //Stores the player name
    int score = 0;      //Stores the player's score

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

    //insert code to allow access to a player's frame (tiles)
    //once Frame class is finished
}
