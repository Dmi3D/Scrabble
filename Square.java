public class Square
{
    private char tile;
    private int weight;
    private boolean isWordScore;

    public Square()
    {
        this.weight = 0;
    }

    public Square(int value, boolean isWordScore)
    {
        this.weight = value;
        this.isWordScore = isWordScore;
    }

    public int getWeight()
    {
        return weight;
    }

    public char getTile()
    {
        return tile;
    }

    public void setTile(char tile)
    {
        this.tile = tile;
    }

    public boolean isWordScore()
    {
        return isWordScore;
    }

    public String getType()
    {
        boolean isWord = isWordScore();

        if (getWeight() == 0)
            return "none";

        else if(isWord)
            return "word";

        else
            return "letr";
    }

    public boolean isEmpty()
    {
        return weight == 0;
    }

    public boolean hasTile()
    {
        return !(getTile() == '\u0000');
    }

}
