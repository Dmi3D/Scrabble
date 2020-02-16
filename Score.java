public class Score
{
    private int value;
    private boolean isWordScore;

    public Score(int value, boolean isWordScore)
    {
        this.value = value;
        this.isWordScore = isWordScore;
    }

    public int getValue()
    {
        return value;
    }

    public boolean isWordScore()
    {
        return isWordScore;
    }
}
