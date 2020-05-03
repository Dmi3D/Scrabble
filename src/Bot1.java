//LEAP-CARD BOT
public class Bot1 implements BotAPI
{

    // The public API of Bot must not change
    // This is ONLY class that you can edit in the program
    // Rename Bot to the name of your team. Use camel case.
    // Bot may not alter the state of the game objects
    // It may only inspect the state of the board and the player objects

    private PlayerAPI me;
    private OpponentAPI opponent;
    private BoardAPI board;
    private UserInterfaceAPI info;
    private DictionaryAPI dictionary;
    private int turnCount;

    Bot1( PlayerAPI me, OpponentAPI opponent, BoardAPI board, UserInterfaceAPI ui, DictionaryAPI dictionary )
    {
        this.me = me;
        this.opponent = opponent;
        this.board = board;
        this.info = ui;
        this.dictionary = dictionary;
        turnCount = 0;
    }

    public String getCommand()
    {
        // Add your code here to input your commands

        // Your code must give the command NAME <botname> at the start of the game
        String command = "";

        switch ( turnCount )
        {
            case 0:
                command = "NAME Bot0";
                break;
            default:
                command = "PASS";
                break;
        }
        turnCount++;
        return command;
    }
}
