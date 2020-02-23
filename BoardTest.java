import jdk.swing.interop.SwingInterOpUtils;

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

        System.out.println("\n*********************************************** ILLEGAL PLACEMENT OF FIRST WORD **********************************************\n");
        System.out.println("Attempting to place first word in places that don't go through the centre square of the board. Centre is (8, H).");
        System.out.println("Attempting to place the word down the board starting at position (2, H).");
        StringBuilder word = new StringBuilder();
        for ( int i = 0; i < 5; i++ )
        {
            word.append( PlayerOne.getPlayerFrame().getTile( i ) );
        }
        System.out.println("Word to place consists of tiles in " + PlayerOne.getName() + "'s frame from index 0 to 4. Word: " + word);
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

        System.out.println("Board is still empty:");
        Board.displayBoard();
        System.out.println("\n");


        System.out.println("\n********************************************* LEGAL WORD PLACEMENT OF FIRST WORD **********************************************\n");
        System.out.println("Placing the first word across the board starting at position (8, F), which goes through centre");
        canPlace = Board.placeWord( word.toString(), 'A', 8, 'F', PlayerOne );
        System.out.println("Method call to placeWord() should return true. Actual: " + canPlace + "\n");
        Board.displayBoard();
        System.out.print(PlayerOne.getName() + "'s frame now: ");
        PlayerOne.getPlayerFrame().displayFrame();
        PlayerOne.getPlayerFrame().fillFrame( Pool );
        System.out.println("\n");


        System.out.println("\n*************************************** PLACEMENT OF SECOND WORD AWAY FROM TILES ON BOARD *************************************\n");
        System.out.println("Attempting to place second word down the board starting at position (1, F).");
        System.out.println("This move is illegal because it doesn't overlap or touch any of the tiles of the first word on the board");
        word = new StringBuilder();
        for ( int i = 2; i < 7; i++ )
        {
            word.append( PlayerTwo.getPlayerFrame().getTile( i ) );
        }
        System.out.println("Word to place consists of tiles in " + PlayerTwo.getName() + "'s frame from index 2 to 6. Word: " + word);
        canPlace = Board.placeWord( word.toString(), 'D', 1, 'F', PlayerTwo );
        System.out.println("Method call to placeWord() should return false. Actual: " + canPlace + "\n");
        System.out.println("Board will still only contain the first word placed:");
        Board.displayBoard();
        System.out.println("\n");


        System.out.println("\n********************************************** LEGAL WORD PLACEMENT OF SECOND WORD *******************************************\n");
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


        System.out.println("\n**************************************** PLACEMENT OF THIRD WORD GOING OUT OF BOUNDS *****************************************\n");
        System.out.println("Placing the third word on the board in illegal place.");
        System.out.println("Attempting to place the word across the board starting at position (9, J).");
        System.out.println("The word touches a tile at position (8, J) but it has 7 tiles and it goes out of bounds.");
        word = new StringBuilder();
        for ( int i = 0; i < 7; i++ )
        {
            word.append( PlayerOne.getPlayerFrame().getTile( i ) );
        }
        System.out.println("Word to place consists of tiles in " + PlayerOne.getName() + "'s frame from index 0 to 6. Word: " + word);
        canPlace = Board.placeWord( word.toString(), 'A', 9, 'J', PlayerOne );
        System.out.println("Method call to placeWord() should return false. Actual: " + canPlace + "\n");
        System.out.println("Board will still only contain only two words placed:");
        Board.displayBoard();
        System.out.println("\n");


        System.out.println("\n********************************************** LEGAL WORD PLACEMENT OF THIRD WORD ********************************************\n");
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


        System.out.println("\n********************************* PLACEMENT OF FOURTH WORD STARTING AT POSITION OUT OF BOUNDS ********************************\n");
        System.out.println("Attempting to place the fourth word down the board starting at position (-1, F).");
        System.out.println("The word would touch a tile at position (3, G) but its starting position is out of bounds.");
        word = new StringBuilder();
        for ( int i = 3; i < 7; i++ )
        {
            word.append( PlayerTwo.getPlayerFrame().getTile( i ) );
        }
        System.out.println("Word to place consists of tiles in " + PlayerTwo.getName() + "'s frame from index 3 to 7. Word: " + word);
        canPlace = Board.placeWord( word.toString(), 'D', -1, 'F', PlayerTwo );
        System.out.println("Method call to placeWord() should return false. Actual: " + canPlace + "\n");
        System.out.println("Board still contains three words:");
        Board.displayBoard();
        System.out.println("\n");


        System.out.println("\n*********************** WORD PLACEMENT ON BOARD WHEN PLAYER DOESN'T HAVE NECESSARY TILES FOR THE WORD *************************\n");
        System.out.println("Attempting to place the fifth word down the board. This word has letters which cannot be found in the player's frame.");
        System.out.println("Word to place would be " + PlayerOne.getName() + "'s and it would be placed down the board starting at position (9, H)");
        word = new StringBuilder();
        for ( int i = 0; i < 7; i++ )
        {
            char tileToAppend = (char) (PlayerTwo.getPlayerFrame().getTile( i ) + 1);
            word.append( tileToAppend );
        }
        System.out.println("Word to place: " + word);
        canPlace = Board.placeWord( word.toString(), 'D', 9, 'H', PlayerTwo );
        System.out.println("Method call to placeWord() should return false. Actual: " + canPlace + "\n");
        System.out.println("Board still contains four words:");
        Board.displayBoard();
        System.out.println("\n");


        System.out.println("\n***************************** LEGAL PLACEMENT OF WORD THAT OVERLAPS WITH TILES ALREADY ON BOARD *******************************\n");
        System.out.println("Placing the fifth word down the board starting at position (7, F). The word will overlap with tile at position (8, F).");
        System.out.println("This tests for correct placement when the word requires a tile already present on the board.");
        word = new StringBuilder();
        char tileOnBoard = Board.getSquareAt( 8, 'F' ).getTile();
        for ( int i = 2; i < 6; i++ )
        {
            if ( i == 3 ) {
                word.append( tileOnBoard );
            }else {
                word.append( PlayerTwo.getPlayerFrame().getTile( i ) );
            }
        }
        System.out.println("Word to place: " + word);
        canPlace = Board.placeWord( word.toString(), 'D', 7, 'F', PlayerTwo );
        System.out.println("Method call to placeWord() should return true. Actual: " + canPlace + "\n");
        System.out.println("Board now contains five words:");
        Board.displayBoard();
        System.out.print(PlayerTwo.getName() + "'s frame now: ");
        PlayerOne.getPlayerFrame().displayFrame();
        System.out.println("\n");
        PlayerOne.getPlayerFrame().fillFrame( Pool );


        System.out.println("\n**************************** ILLEGAL PLACEMENT OF WORD THAT OVERLAPS WITH TILES ALREADY ON BOARD ******************************\n");
        System.out.println("Attempting to place the sixth word across starting at position (10, D). The word will overlap with tile at position (10, F).");
        System.out.println("This tests for incorrect placement when the word to place conflicts with existing letter on the board.");
        tileOnBoard = (char) (Board.getSquareAt( 10, 'F' ).getTile() + 1);
        word = new StringBuilder();
        for ( int i = 4; i < 7; i++ )
        {
            if ( i == 6 ) {
                word.append(tileOnBoard);
            }else {
                word.append( PlayerOne.getPlayerFrame().getTile( i ) );
            }
        }
        System.out.println("Word to place: " + word);
        canPlace = Board.placeWord( word.toString(), 'A', 10, 'D', PlayerOne );
        System.out.println("Method call to placeWord() should return false. Actual: " + canPlace + "\n");
        System.out.println("Board still contains five words:");
        Board.displayBoard();
        System.out.println("\n");

        System.out.println("\n**************************************************** RESETTING THE BOARD ******************************************************\n");
        System.out.println("Board currently should have 5 words currenyl. Actually: " + Board.getNumOfWords());
        System.out.println("Board will be reset.");
        Board.reset();
        System.out.println("Board should now have 0 words. Actual: " + Board.getNumOfWords());
        Board.displayBoard();
    }
}
