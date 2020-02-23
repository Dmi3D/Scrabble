import jdk.swing.interop.SwingInterOpUtils;

import java.util.Scanner;

public class BoardTest
{
    public static void main(String[] args)
    {
        Pool Pool = new Pool();
        Frame FrameOne = new Frame(Pool);
        Frame FrameTwo = new Frame(Pool);
        Player PlayerOne = new Player(FrameOne);
        Player PlayerTwo = new Player(FrameTwo);
        PlayerOne.setName( "Andra" );
        PlayerTwo.setName( "Dmitriy" );
        System.out.println("\n******************************************** SETTING UP PLAYERS AND THEIR FRAMES ********************************************\n");
        System.out.print(PlayerOne.getName() + "'s frame: ");
        PlayerOne.getPlayerFrame().displayFrame();
        System.out.println();
        System.out.print(PlayerTwo.getName() + "'s frame: ");
        PlayerTwo.getPlayerFrame().displayFrame();
        System.out.println("\n");

        System.out.println("\n************************************** THE BOARD AND CORRESPONDING SCORE VALUES/WEIGHTS **************************************\n");
        Board Board = new Board();
        Board.displayBoardWithWeights();
        System.out.println("\n");

        System.out.println("\n******************************************** THE EMPTY BOARD BEFORE PLACING TILES ********************************************\n");
        Board.displayBoard();
        System.out.println();

        System.out.println("\n******************************************** ILLEGAL WORD PLACEMENT ON BOARD TEST ********************************************\n");
        System.out.println("Attempting to place first word in places that don't go through the centre square of the board. Centre is (8, H).");
        System.out.println("Word to place consists of tiles in " + PlayerOne.getName() + "'s frame from index 0 to 4.");
        System.out.println("Attempting to place the word down the board starting at position (2, H).");
        StringBuilder word = new StringBuilder();
        for ( int i = 0; i < 5; i++ )
        {
            word.append( PlayerOne.getPlayerFrame().getTile( i ) );
        }
        boolean canPlace = Board.placeWord( word.toString(), 'D', 2, 'H', PlayerOne );
        System.out.println("Method call to placeWord() should return false. Actual: " + canPlace + "\n");

        System.out.println("Attempting to place the word across the board starting at position (8, A).");
        canPlace = Board.placeWord( word.toString(), 'A', 8, 'A', PlayerOne );
        System.out.println("Method call to placeWord() should return false. Actual: " + canPlace + "\n");

        System.out.println("Attempting to place the word down the board starting at position (1, B).");
        canPlace = Board.placeWord( word.toString(), 'D', 1, 'B', PlayerOne );
        System.out.println("Method call to placeWord() should return false. Actual: " + canPlace + "\n");

        System.out.println("Attempting to place the word across the board starting at position (3, D).");
        canPlace = Board.placeWord( word.toString(), 'A', 3, 'D', PlayerOne );
        System.out.println("Method call to placeWord() should return false. Actual: " + canPlace + "\n");

        System.out.println("Placing the first word across the board starting at position (8, F), which goes through centre");
        canPlace = Board.placeWord( word.toString(), 'A', 8, 'F', PlayerOne );
        System.out.println("Method call to placeWord() should return true. Actual: " + canPlace + "\n");
        Board.displayBoard();
        System.out.print(PlayerOne.getName() + "'s frame now: ");
        PlayerOne.getPlayerFrame().displayFrame();
        PlayerOne.getPlayerFrame().fillFrame( Pool );
        System.out.println("\n");

        System.out.println("Attempting to place second word in illegal place.");
        System.out.println("Word to place consists of tiles in " + PlayerTwo.getName() + "'s frame from index 2 to 6.");
        System.out.println("Attempting to place the word down the board starting at position (1, F).");
        System.out.println("This move is illegal because it doesn't overlap or touch any of the tiles of the first word on the board");
        word = new StringBuilder();
        for ( int i = 2; i < 7; i++ )
        {
            word.append( PlayerTwo.getPlayerFrame().getTile( i ) );
        }
        canPlace = Board.placeWord( word.toString(), 'D', 1, 'F', PlayerTwo );
        System.out.println("Method call to placeWord() should return false. Actual: " + canPlace + "\n");
        System.out.println("Board will still only contain the first word placed:");
        Board.displayBoard();
        System.out.println("Placing the word down the board starting at position (3, G).");
        System.out.println("This move is legal because it touches one of the tiles of the first word found at position (8, G).");
        canPlace = Board.placeWord( word.toString(), 'D', 3, 'G', PlayerTwo );
        System.out.println("Method call to placeWord() should return true. Actual: " + canPlace + "\n");
        System.out.println("Board now contains the two words on the board:");
        Board.displayBoard();
        System.out.print(PlayerTwo.getName() + "'s frame now: ");
        PlayerTwo.getPlayerFrame().displayFrame();
        System.out.println("\n");
        PlayerTwo.getPlayerFrame().fillFrame( Pool );

        System.out.println("Placing the third word on the board in illegal place.");
        System.out.println("Word to place consists of tiles in " + PlayerOne.getName() + "'s frame from index 0 to 6.");
        System.out.println("Attempting to place the word across the board starting at position (9, J).");
        System.out.println("The word touches a tile at position (8, J) but it has 7 tiles and it goes out of bounds.");
        word = new StringBuilder();
        for ( int i = 0; i < 7; i++ )
        {
            word.append( PlayerOne.getPlayerFrame().getTile( i ) );
        }
        canPlace = Board.placeWord( word.toString(), 'A', 9, 'J', PlayerOne );
        System.out.println("Method call to placeWord() should return false. Actual: " + canPlace + "\n");
        System.out.println("Board will still only contain only two words placed:");
        Board.displayBoard();
        System.out.println("\n");

        System.out.println("Placing the third word across on the board starting at position (9, I). This is a legal move.");
        System.out.println("The word touches two tiles at positions (8, I) and (8, J) and fills all positions to the end of the board.");
        canPlace = Board.placeWord( word.toString(), 'A', 9, 'I', PlayerOne );
        System.out.println("Method call to placeWord() should return true. Actual: " + canPlace + "\n");
        System.out.println("Board now contains three words:");
        Board.displayBoard();
        System.out.print(PlayerOne.getName() + "'s frame now: ");
        PlayerOne.getPlayerFrame().displayFrame();
        System.out.println("\n");
        PlayerOne.getPlayerFrame().fillFrame( Pool );
        System.out.println("\n");


        System.out.println("Attempting to place the fourth word down the board starting at position (-1, F).");
        System.out.println("Word to place consists of tiles in " + PlayerTwo.getName() + "'s frame from index 3 to 7.");
        System.out.println("The word would touche a tile at position (3, G) but its starting position is out of bounds.");
        word = new StringBuilder();
        for ( int i = 3; i < 7; i++ )
        {
            word.append( PlayerTwo.getPlayerFrame().getTile( i ) );
        }
        canPlace = Board.placeWord( word.toString(), 'D', -1, 'F', PlayerTwo );
        System.out.println("Method call to placeWord() should return false. Actual: " + canPlace + "\n");
        System.out.println("Board will still only contain only three words:");
        Board.displayBoard();
        System.out.println("\n");


//        System.out.println("Attempting to place the word down the board starting at position out of bounds (-1, G)");
//        canPlace = Board.placeWord( word.toString(), 'D', -1, 'G', PlayerOne );
//        System.out.println("Method call to placeWord() should return false. Actual: " + canPlace + "\n");







//        System.out.print( "Please enter word you want to place on the board: " );
//        System.out.print( "\nPlease enter the direction in which you want to place the word ('A' for across or 'D' for down): " );
//        System.out.print( "\nPlease enter row number of the first letter: " );
//        System.out.print( "\nPlease enter column letter of the first letter: " );




    }
}
