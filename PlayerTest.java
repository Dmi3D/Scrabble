public class PlayerTest
{
    public static void main( String[] args )
    {
        Pool Pool = new Pool();

        System.out.println("\n****************** TESTING INITIALISATION OF POOL *******************\n");

        System.out.println("The tiles and their associated scores:\n" + Pool.getTileValues().toString() + "\n");

        System.out.println("Number of each tile in the Pool:\n" + Pool.getTileFrequencies().toString() + "\n");

        System.out.println("Expected total number of tiles in the Pool: 100. Actual: " + Pool.getTilesInPool() + "\n");

        System.out.println("\n****************** TESTING INITIALISATION OF FRAME *******************\n");

        Frame FrameOne = new Frame();

        System.out.print("Frame One contains the following tiles drawn from the Pool: ");
        FrameOne.displayFrame();

        System.out.println();
        System.out.println("Tiles in Pool at this stage:\n" + Pool.getTileFrequencies().toString() + "\n");
        System.out.println("Expected total number of tiles in the Pool: (100 - 7 = 93). Actual: " + Pool.getTilesInPool() + "\n");

        Frame FrameTwo = new Frame();

        System.out.print("Frame Two contains the following tiles drawn from the Pool: ");
        FrameTwo.displayFrame();

        System.out.println();
        System.out.println("Tiles in Pool at this stage:\n" + Pool.getTileFrequencies().toString() + "\n");
        System.out.println("Expected total number of tiles in the Pool: (93 - 7 = 86). Actual: " + Pool.getTilesInPool() + "\n");

        Player PlayerOne = new Player(FrameOne);
        Player PlayerTwo = new Player(FrameTwo);

        System.out.println("\n****************** TESTING NAME SETTING FUNCTIONALITY OF PLAYERS *******************\n");

        System.out.println( "Setting player one's name to 'Dmitriy'" );
        PlayerOne.setName("Dmitriy");

        System.out.println("Expected name for player one: 'Dmitriy'. Actual: '" + PlayerOne.getName() + "'");
        System.out.println("Expected score for " + PlayerOne.getName() + ": 0. Actual: " + PlayerOne.getScore());
        System.out.print(PlayerOne.getName() + "'s frame: ");
        PlayerOne.getPlayerFrame().displayFrame();

        System.out.println();

        System.out.println( "Setting player two's name to 'Andra' " );
        PlayerTwo.setName("Andra");

        System.out.println("Expected name for player one: 'Andra'. Actual: '" + PlayerTwo.getName() + "'");
        System.out.println("Expected score for " + PlayerTwo.getName() + ": 0. Actual: " + PlayerOne.getScore());
        System.out.print(PlayerTwo.getName() + "'s frame: ");
        PlayerTwo.getPlayerFrame().displayFrame();

        System.out.println();

        // Increasing player's scores
        System.out.println("\n********************* TESTING INCREMENT FUNCTIONALITY OF SCORE *********************\n");

        PlayerOne.incrementScore(12);
        System.out.println("Incrementing " + PlayerOne.getName() + "'s score by 12. Expected: (0 + 12 = 12). Actual: " + PlayerOne.getScore());

        PlayerOne.incrementScore(6);
        System.out.println("Incrementing " + PlayerOne.getName() + "'s score by 5. Expected: (12 + 6 = 18). Actual: " + PlayerOne.getScore() + "\n");

        PlayerTwo.incrementScore(17);
        System.out.println("Incrementing " + PlayerTwo.getName() + "'s score by 17. Expected: (0 + 17 = 17). Actual: " + PlayerTwo.getScore());

        PlayerTwo.incrementScore(3);
        System.out.println("Incrementing " + PlayerTwo.getName() + "'s score by 3. Expected: (17 + 3 = 20). Actual: " + PlayerTwo.getScore() + "\n");

        // Removing a tile from a player's frame when making a move
        System.out.println("\n******************* TESTING TILE REMOVAL FROM FRAME FUNCTIONALITY ******************\n");

        System.out.print(PlayerOne.getName() + "'s frame before removing a tile: ");
        PlayerOne.getPlayerFrame().displayFrame();

        char tileToRemove = PlayerOne.getPlayerFrame().getTile(0);
        int indexOfTile = PlayerOne.getPlayerFrame().getIndexOfTile(tileToRemove);
        char removedTile = PlayerOne.getPlayerFrame().removeTile( tileToRemove );
        char replacedTile = PlayerOne.getPlayerFrame().getFrame()[indexOfTile];

        System.out.println("Should remove '" + tileToRemove + "' from the frame. Removed: " + removedTile);
        System.out.println("'" + tileToRemove + "' has been replaced by '" + replacedTile + "' at index " + indexOfTile);

        System.out.print(PlayerOne.getName() + "'s frame with '" + removedTile + "' removed: ");
        PlayerOne.getPlayerFrame().displayFrame(); // at this stage, the frame already has another tile in place

        System.out.println();

        System.out.print(PlayerTwo.getName() + "'s frame before removing a tile: ");
        PlayerTwo.getPlayerFrame().displayFrame();

        tileToRemove = PlayerTwo.getPlayerFrame().getTile(3);
        indexOfTile = PlayerTwo.getPlayerFrame().getIndexOfTile(tileToRemove);
        removedTile = PlayerTwo.getPlayerFrame().removeTile( tileToRemove );
        replacedTile = PlayerTwo.getPlayerFrame().getFrame()[indexOfTile];

        System.out.println("Should remove '" + tileToRemove + "' from the frame. Removed: " + removedTile);
        System.out.println("'" + tileToRemove + "' has been replaced by '" + replacedTile + "' at index " + indexOfTile);

        System.out.print(PlayerTwo.getName() + "'s frame with '" + removedTile + "' removed: ");
        PlayerTwo.getPlayerFrame().displayFrame();    // at this stage, the frame already has another tile in place
        System.out.println();


        System.out.println("Frequencies of tiles in Pool. Should be decrementing frequencies:\n" + Pool.getTileFrequencies().toString() +"\n");
        System.out.println("Expected total number of tiles in the Pool: 84. Actual: " + Pool.getTilesInPool() + "\n");

        System.out.println("\n****************** TESTING QUERY OF A TILE'S VALUE FUNCTIONALITY *******************\n");

        System.out.print(PlayerOne.getName() + "'s frame: ");
        PlayerOne.getPlayerFrame().displayFrame();

        System.out.println();

        //checking value of each tile in each frame
        for(int i = 0; i < 7; i++)
        {
            char tile = PlayerOne.getPlayerFrame().getTile(i);
            int value = Pool.getValue(tile);

            System.out.println("Value of '" + tile + "' at position " + i + ": " + value);
        }

        System.out.println();

        System.out.print(PlayerTwo.getName() + "'s frame: ");
        PlayerTwo.getPlayerFrame().displayFrame();

        System.out.println();

        //checking value of each tile in each frame
        for(int i = 0; i < 7; i++)
        {
            char tile = PlayerTwo.getPlayerFrame().getTile(i);
            int value = Pool.getValue(tile);

            System.out.println("Value of '" + tile + "' at position " + i + ": " + value);
        }

        System.out.println();

        System.out.println("\n***************** TESTING POOL AND FRAMES TO SEE IF THEY'RE EMPTY ******************\n");

        System.out.println("Checking if the Pool is empty. Actual: false. Returned: " + Pool.isPoolEmpty() + "\n");

        System.out.println("Checking if " + PlayerOne.getName() + "'s frame is empty. Actual: false. Returned: " +
                PlayerOne.getPlayerFrame().isEmpty() + "\n");

        System.out.println("Checking if " + PlayerTwo.getName() + "'s frame is empty. Actual: false. Returned: " +
                PlayerTwo.getPlayerFrame().isEmpty() + "\n");

        System.out.println("\n********* TESTING TO SEE IF POOL IS EMPTY AFTER REMOVING ALL TILES FROM IT *********\n");

        // Removing tiles from frames and filling them a few times to exhaust the number of tiles in the Pool //
        for (int numOfTimes = 0; numOfTimes < 6; numOfTimes++)
        {
            int i;

            for ( i = 0; i < 7; i++)
            {
                FrameOne.removeTile(PlayerOne.getPlayerFrame().getFrame()[i]);
                FrameTwo.removeTile(PlayerTwo.getPlayerFrame().getFrame()[i]);
            }

            System.out.println("Removed " + i * 2 + " tiles from the Pool to replace all tiles in each players frame.");
            System.out.println("Number of tiles in the Pool at this stage: " + Pool.getTilesInPool() +"\n");
        }

        System.out.println("Testing to see that Pool is empty now. Should return true. Actual: " + Pool.isPoolEmpty() + "\n");

        System.out.println("\n********** TESTING IF FRAMES ARE EMPTY AFTER REMOVING ALL TILES FROM THEM **********\n");

        System.out.println("(All tiles are being removed one by one from both frames in the background)\n");

        for (int i = 0; i < 7; i++)
        {
            System.out.print(PlayerOne.getName() + "'s Frame: ");
            PlayerOne.getPlayerFrame().displayFrame();

            System.out.println("Removed tile '" + PlayerOne.getPlayerFrame().getTile(i) + "'");

            PlayerOne.getPlayerFrame().removeTile( PlayerOne.getPlayerFrame().getFrame()[i] );

            System.out.print(PlayerOne.getName() + "'s frame now: ");
            PlayerOne.getPlayerFrame().displayFrame();

            System.out.println();
        }

        System.out.println("Testing to see that " + PlayerOne.getName() + "'s frame is empty now. Expected: true. Actual: "
            + PlayerOne.getPlayerFrame().isEmpty() + "\n");

        for (int i = 0; i < 7; i++)
        {
            System.out.print(PlayerTwo.getName() + "'s Frame: ");
            PlayerTwo.getPlayerFrame().displayFrame();

            System.out.println("Removed tile '" + PlayerTwo.getPlayerFrame().getTile(i) + "'");

            PlayerTwo.getPlayerFrame().removeTile( PlayerTwo.getPlayerFrame().getFrame()[i] );

            System.out.print(PlayerTwo.getName() + "'s frame now: ");
            PlayerTwo.getPlayerFrame().displayFrame();

            System.out.println();
        }

        System.out.println("Testing to see that " + PlayerTwo.getName() + "'s frame is empty now. Expected: true. Actual: "
                + PlayerTwo.getPlayerFrame().isEmpty() + "\n");

        System.out.println();
        

    }
}
       /* *//*
            DMITRIY TESTING FRAME & POOL
         *//*

        //Creating a pool
        Pool pool = new Pool();

        //Creating a new frame
        Frame testFrame = new Frame();

        System.out.println("isEmpty should return false: " +  testFrame.isEmpty());

        //Testing print the frame
        System.out.println("Filled frame: " + testFrame.getFrame());

        //Removing letter from the frame
        System.out.println("Attempting to remove A from the frame");
        System.out.println("Char taken: " + testFrame.takeTile('A' ));

        //Removing letter from the frame
        System.out.println("Attempting to remove B from the frame");
        System.out.println("Char taken: " + testFrame.takeTile('B' ));

        //Removing letter from the frame
        System.out.println("Attempting to remove C from the frame");
        System.out.println("Char taken: " + testFrame.takeTile('C' ));

        System.out.println("isEmpty should return false: " +  testFrame.isEmpty());

        //Testing print the frame
        System.out.println("Frame with A removed (if it was there initially): " + testFrame.getFrame());

        //Testing print the frame
        System.out.println("Frame should be full again: " + testFrame.getFrame());



        *//*
            ANDRA TESTING POOL
         *//*

        Pool bagOfTiles = new Pool();

        System.out.println( "The tiles and their values: " + bagOfTiles.getTileValues().toString() );
        System.out.println( "Tiles in pool at the moment: " + bagOfTiles.getTileFrequencies().toString() );

        bagOfTiles.getTileFrequencies().put( 'A', 7 );
        bagOfTiles.getTileFrequencies().put( '*', 0 );

        System.out.println( "Tiles in pool at the moment: " + bagOfTiles.getTileFrequencies().toString() );

        for ( int i = 0; i < 7; i++ )
        {
            char tileDrawn = Pool.drawTile();
            System.out.println("Drawn tile: " + tileDrawn);
            bagOfTiles.displayTiles();
        }

        System.out.println("Tiles and frequencies in the pool now: " + bagOfTiles.getTileFrequencies().toString());

        boolean isEmpty = bagOfTiles.isPoolEmpty();

        if ( isEmpty )
        {
            System.out.println("Pool is empty");
        }

        else
        {
            System.out.println("Pool is not empty. ");
            bagOfTiles.displayTiles();
            System.out.println(" Tiles and frequencies: " + bagOfTiles.getTileFrequencies().toString() );
        }

        Scanner scanner = new Scanner( System.in );

        for (int i = 0; i < 10; i++)
        {
            char tile = scanner.next().charAt( 0 );
            int tileValue = bagOfTiles.getTileValue( tile );

            if ( tileValue == -1 )
            {
                System.out.println(tile + " is not a tile. try again.");
                continue;
            }

            System.out.println("Value of " + tile + " = " + tileValue);
        }



        *//*
            ANDRA TESTING PLAYER
         *//*

        Frame frameOne = new Frame();

        Player playerOne = new Player(frameOne);
        playerOne.setName( "Andra" );

        System.out.println("Current score for: " + playerOne.getName() + " = " + playerOne.getScore());
        System.out.println(playerOne.getName() + "'s Frame: " + playerOne.getFrame());

        playerOne.incrementScore( 12 );

        System.out.println("Current score for: " + playerOne.getName() + " = " + playerOne.getScore());

        playerOne.resetPlayer();

        System.out.println("PlayerOne data: " + playerOne.getName() + ", " + playerOne.getScore()
                + ", " + playerOne.getFrame());*/
/*    }
}*/
