import java.util.Scanner;

public class PlayerTest
{
    public static void main( String[] args )
    {
        Pool pool = new Pool();

        System.out.println("GAME HAS BEGUN\n");

        System.out.println("The tiles and their associated scores: " + pool.getTileValues().toString());
        System.out.println("Number of each tile in the pool: " + pool.getTileFrequencies().toString());
        System.out.println("Total number of tiles in the pool: " + Pool.getTilesInPool() + "\n");

        Frame frameOne = new Frame();
        System.out.println("Frame One contains the following tiles drawn from the pool: ");
        frameOne.displayFrame();
        System.out.println();
        System.out.println(" Tiles in pool at this stage: " + pool.getTileFrequencies().toString());
        System.out.println("Total number of tiles in the pool at this stage: " + Pool.getTilesInPool() + "\n");

        Frame frameTwo = new Frame();
        System.out.print("Frame Two contains the following tiles drawn from the pool: ");
        frameTwo.displayFrame();
        System.out.println();
        System.out.println("Tiles in pool at this stage: " + pool.getTileFrequencies().toString());
        System.out.println("Total number of tiles in the pool at this stage: " + Pool.getTilesInPool() + "\n");

        Player playerOne = new Player( frameOne );
        Player playerTwo = new Player( frameTwo );

        System.out.println("********** TESTING NAME SETTING FUNCTIONALITY OF PLAYERS **********");

        System.out.println( "Setting player one's name to 'Dmitriy'" );
        playerOne.setName( "Dmitriy" );

        System.out.println("Expected name for player one: 'Dmitriy'. Actual: '" + playerOne.getName() + "'");
        System.out.println("Expected score for " + playerOne.getName() + ": 0. Actual: " + playerOne.getScore());
        System.out.print(playerOne.getName() + "'s frame: ");
        frameOne.displayFrame();

        System.out.println();

        System.out.println( "Setting player two's name to 'Andra' " );
        playerTwo.setName( "Andra" );

        System.out.println("Expected name for player one: 'Andra'. Actual: '" + playerTwo.getName() + "'");
        System.out.println("Expected score for " + playerOne.getName() + ": 0. Actual: " + playerOne.getScore());
        System.out.print(playerTwo.getName() + "'s frame: ");
        frameTwo.displayFrame();

        System.out.println();

        System.out.println("Checking if the pool is empty. Should return false. Returned: " + Pool.isPoolEmpty() + "\n");

        // Increasing player's scores
        System.out.println("********** TESTING INCREMENT FUNCTIONALITY OF SCORE **********");
        playerOne.incrementScore( 12 );
        System.out.println("Incrementing " + playerOne.getName() + "'s score by 12. Expected: 12. Actual: " + playerOne.getScore());

        playerTwo.incrementScore( 17 );
        System.out.println("Incrementing " + playerOne.getName() + "'s score by 17. Expected: 17. Actual: " + playerTwo.getScore() + "\n");

        // Removing a tile from a player's frame when making a move
        System.out.println("********** TESTING TILE REMOVAL FROM FRAME FUNCTIONALITY **********");
        System.out.println(playerOne.);
        char tileToRemove = frameOne.getFrame()[0];
        char removedTile = frameOne.removeTile( tileToRemove );
        System.out.println("Should remove '" + tileToRemove + "' from the frame. Removed: " + removedTile);

        System.out.print(playerOne.getName() + "'s frame with '" + removedTile + "' removed: ");
        frameOne.displayFrame();    // at this stage, the frame already has another tile in place
        System.out.println();

        // Removing a tile from a player's frame when making a move
        System.out.println("********** TESTING TILE REMOVAL FROM FRAME FUNCTIONALITY **********");
        tileToRemove = frameTwo.getFrame()[3];
        removedTile = frameTwo.removeTile( tileToRemove );

        System.out.println("Should remove '" + tileToRemove + "' from the frame. Removed: " + removedTile);

        System.out.print(playerTwo.getName() + "'s frame with '" + removedTile + "' removed: ");
        frameTwo.displayFrame();    // at this stage, the frame already has another tile in place
        System.out.println();


        System.out.println("Frequencies of tiles in pool. Should be decrementing frequencies: " + pool.getTileFrequencies().toString());
        System.out.println("Expected 84 tiles in pool at this stage. Actual: " + Pool.getTilesInPool() + "\n");

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
