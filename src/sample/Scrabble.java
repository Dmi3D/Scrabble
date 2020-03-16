package sample;

public class Scrabble
{
    // should be class that allows players to play Scrabble
    public static void main( String[] args )
    {
        OpeningWindowController openingWindow = new OpeningWindowController();
        Pool GamePool = new Pool();
        Frame FrameOne = new Frame(GamePool);
        Frame FrameTwo = new Frame(GamePool);
        Player PlayerOne = new Player( FrameOne );
        Player PlayerTwo = new Player( FrameTwo );
        PlayerOne.setName( openingWindow.getPlayerOneName() );
        PlayerTwo.setName( openingWindow.getPlayerTwoName() );
        System.out.println("Player One: " + PlayerOne.getName());
        System.out.println("Player Two: " + PlayerTwo.getName());
    }
}
