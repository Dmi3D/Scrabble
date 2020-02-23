import java.util.Scanner;

public class BoardTest
{
    public static void main(String[] args)
    {
        Board Board = new Board();

        Pool thePool = new Pool();
        Frame frameOne = new Frame(thePool);
        Player playerOne = new Player(frameOne);
        System.out.print("The frame: ");
        playerOne.getPlayerFrame().displayFrame();
        System.out.println();

        Scanner scanner = new Scanner( System.in );
        System.out.print( "Please enter word you want to place on the board: " );
        String word = scanner.next();
        System.out.print( "\nPlease enter the direction in which you want to place the word ('A' for across or 'D' for down): " );
        char direction = scanner.next().charAt( 0 );
        System.out.print( "\nPlease enter row number of the first letter: " );
        int row = scanner.nextInt();
        System.out.print( "\nPlease enter column letter of the first letter: " );
        char column = scanner.next().charAt( 0 );
        Board.placeWord( word, direction, row, column, playerOne );
        Board.displayBoard();System.out.println("The frame: ");
        playerOne.getPlayerFrame().displayFrame();
        /*System.out.println(Board.getSquareAt(1, 'A').getWeight());
        System.out.println(Board.getSquareAt(1, 'A').getType());

        Board.displayBoardWithWeights();*/

    }
}
