public class PlayerTest
{
    public static void main( String[] args )
    {
        Pool pool = new Pool();

        System.out.println("\n****************** TESTING INITIALISATION OF POOL *******************\n");

        System.out.println("The tiles and their associated scores:\n" + pool.getTileValues().toString() + "\n");

        System.out.println("Number of each tile in the pool:\n" + pool.getTileFrequencies().toString() + "\n");

        System.out.println("Expected total number of tiles in the pool: 100. Actual: " + Pool.getTilesInPool() + "\n");

        System.out.println("\n****************** TESTING INITIALISATION OF FRAME *******************\n");

        Frame frameOne = new Frame();

        System.out.print("Frame One contains the following tiles drawn from the pool: ");
        frameOne.displayFrame();

        System.out.println();
        System.out.println("Tiles in pool at this stage:\n" + pool.getTileFrequencies().toString() + "\n");
        System.out.println("Expected total number of tiles in the pool: (100 - 7 = 93). Actual: " + Pool.getTilesInPool() + "\n");

        Frame frameTwo = new Frame();

        System.out.print("Frame Two contains the following tiles drawn from the pool: ");
        frameTwo.displayFrame();

        System.out.println();
        System.out.println("Tiles in pool at this stage:\n" + pool.getTileFrequencies().toString() + "\n");
        System.out.println("Expected total number of tiles in the pool: (93 - 7 = 86). Actual: " + Pool.getTilesInPool() + "\n");

        Player playerOne = new Player(frameOne);
        Player playerTwo = new Player(frameTwo);

        System.out.println("\n****************** TESTING NAME SETTING FUNCTIONALITY OF PLAYERS *******************\n");

        System.out.println( "Setting player one's name to 'Dmitriy'" );
        playerOne.setName("Dmitriy");

        System.out.println("Expected name for player one: 'Dmitriy'. Actual: '" + playerOne.getName() + "'");
        System.out.println("Expected score for " + playerOne.getName() + ": 0. Actual: " + playerOne.getScore());
        System.out.print(playerOne.getName() + "'s frame: ");
        playerOne.getPlayerFrame().displayFrame();

        System.out.println();

        System.out.println( "Setting player two's name to 'Andra' " );
        playerTwo.setName("Andra");

        System.out.println("Expected name for player one: 'Andra'. Actual: '" + playerTwo.getName() + "'");
        System.out.println("Expected score for " + playerTwo.getName() + ": 0. Actual: " + playerOne.getScore());
        System.out.print(playerTwo.getName() + "'s frame: ");
        playerTwo.getPlayerFrame().displayFrame();

        System.out.println();

        // Increasing player's scores
        System.out.println("\n********************* TESTING INCREMENT FUNCTIONALITY OF SCORE *********************\n");

        playerOne.incrementScore(12);
        System.out.println("Incrementing " + playerOne.getName() + "'s score by 12. Expected: (0 + 12 = 12). Actual: " + playerOne.getScore());

        playerOne.incrementScore(6);
        System.out.println("Incrementing " + playerOne.getName() + "'s score by 5. Expected: (12 + 6 = 18). Actual: " + playerOne.getScore() + "\n");

        playerTwo.incrementScore(17);
        System.out.println("Incrementing " + playerTwo.getName() + "'s score by 17. Expected: (0 + 17 = 17). Actual: " + playerTwo.getScore());

        playerTwo.incrementScore(3);
        System.out.println("Incrementing " + playerTwo.getName() + "'s score by 3. Expected: (17 + 3 = 20). Actual: " + playerTwo.getScore() + "\n");

        // Removing a tile from a player's frame when making a move
        System.out.println("\n******************* TESTING TILE REMOVAL FROM FRAME FUNCTIONALITY ******************\n");

        System.out.print(playerOne.getName() + "'s frame before removing a tile: ");
        playerOne.getPlayerFrame().displayFrame();

        char tileToRemove = playerOne.getPlayerFrame().getFrame()[0];
        char removedTile = playerOne.getPlayerFrame().removeTile( tileToRemove );

        System.out.println("Should remove '" + tileToRemove + "' from the frame. Removed: " + removedTile);

        System.out.print(playerOne.getName() + "'s frame with '" + removedTile + "' removed: ");
        playerOne.getPlayerFrame().displayFrame(); // at this stage, the frame already has another tile in place

        System.out.println();

        System.out.print(playerTwo.getName() + "'s frame before removing a tile: ");
        playerTwo.getPlayerFrame().displayFrame();
        tileToRemove = playerTwo.getPlayerFrame().getFrame()[3];
        removedTile = playerTwo.getPlayerFrame().removeTile( tileToRemove );

        System.out.println("Should remove '" + tileToRemove + "' from the frame. Removed: " + removedTile);

        System.out.print(playerTwo.getName() + "'s frame with '" + removedTile + "' removed: ");
        playerTwo.getPlayerFrame().displayFrame();    // at this stage, the frame already has another tile in place
        System.out.println();


        System.out.println("Frequencies of tiles in pool. Should be decrementing frequencies:\n" + pool.getTileFrequencies().toString() +"\n");
        System.out.println("Expected total number of tiles in the pool: 84. Actual: " + Pool.getTilesInPool() + "\n");

        // Needs to be added when method is implemented
        System.out.println("\n****************** TESTING QUERY OF A TILE'S VALUE FUNCTIONALITY *******************");

        System.out.println();

        System.out.println("\n***************** TESTING POOL AND FRAMES TO SEE IF THEY'RE EMPTY ******************\n");

        System.out.println("Checking if the pool is empty. Actual: false. Returned: " + Pool.isPoolEmpty() + "\n");

        System.out.println("Checking if " + playerOne.getName() + "'s frame is empty. Actual: false. Returned: " +
                playerOne.getPlayerFrame().isEmpty() + "\n");

        System.out.println("Checking if " + playerTwo.getName() + "'s frame is empty. Actual: false. Returned: " +
                playerTwo.getPlayerFrame().isEmpty() + "\n");

        System.out.println("\n********* TESTING TO SEE IF POOL IS EMPTY AFTER REMOVING ALL TILES FROM IT *********\n");

        // Removing tiles from frames and filling them a few times to exhaust the number of tiles in the pool //
        for (int numOfTimes = 0; numOfTimes < 6; numOfTimes++)
        {
            int i;

            for ( i = 0; i < 7; i++)
            {
                frameOne.removeTile(playerOne.getPlayerFrame().getFrame()[i]);
                frameTwo.removeTile(playerTwo.getPlayerFrame().getFrame()[i]);
            }

            System.out.println("Removed " + i * 2 + " tiles from the pool to replace all tiles in each players frame.");
            System.out.println("Number of tiles in the pool at this stage: " + Pool.getTilesInPool() +"\n");
        }

        System.out.println("Testing to see that pool is empty now. Should return true. Actual: " + Pool.isPoolEmpty() + "\n");

        System.out.println("\n********** TESTING IF FRAMES ARE EMPTY AFTER REMOVING ALL TILES FROM THEM **********\n");

        System.out.println("(All tiles are being removed one by one from both frames in the background)\n");

        for (int i = 0; i < 7; i++)
        {
            System.out.print(playerOne.getName() + "'s Frame: ");
            playerOne.getPlayerFrame().displayFrame();

            System.out.println("Removed tile '" + playerOne.getPlayerFrame().getTile(i) + "'");

            playerOne.getPlayerFrame().removeTile( playerOne.getPlayerFrame().getFrame()[i] );

            System.out.print(playerOne.getName() + "'s frame now: ");
            playerOne.getPlayerFrame().displayFrame();

            System.out.println();
        }

        System.out.println("Testing to see that " + playerOne.getName() + "'s frame is empty now. Expected: true. Actual: "
            + playerOne.getPlayerFrame().isEmpty() + "\n");

        for (int i = 0; i < 7; i++)
        {
            System.out.print(playerTwo.getName() + "'s Frame: ");
            playerTwo.getPlayerFrame().displayFrame();

            System.out.println("Removed tile '" + playerTwo.getPlayerFrame().getTile(i) + "'");

            playerTwo.getPlayerFrame().removeTile( playerTwo.getPlayerFrame().getFrame()[i] );

            System.out.print(playerTwo.getName() + "'s frame now: ");
            playerTwo.getPlayerFrame().displayFrame();

            System.out.println();
        }

        System.out.println("Testing to see that " + playerTwo.getName() + "'s frame is empty now. Expected: true. Actual: "
                + playerTwo.getPlayerFrame().isEmpty() + "\n");

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
