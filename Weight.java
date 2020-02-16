public class Weight
{
    private int weight;
    private boolean isWordScore;

    public Weight()
    {
        this.weight = 0;
    }

    public Weight(int value, boolean isWordScore)
    {
        this.weight = value;
        this.isWordScore = isWordScore;
    }

    public int getWeight()
    {
        return weight;
    }

    public boolean isWordScore()
    {
        return isWordScore;
    }

    public boolean isEmpty()
    {
        if(weight == 0)
            return true;

        return false;
    }
}
