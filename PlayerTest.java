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
        System.out.println("Frame One contains the following tiles drawn from the pool: " + frameOne.getFrame());
        System.out.println("Tiles in pool at this stage: " + pool.getTileFrequencies().toString());
        System.out.println("Total number of tiles in the pool at this stage: " + Pool.getTilesInPool() + "\n");

        Frame frameTwo = new Frame();
        System.out.println("Frame Two contains the following tiles drawn from the pool: " + frameTwo.getFrame());
        System.out.println("Tiles in pool at this stage: " + pool.getTileFrequencies().toString());
        System.out.println("Total number of tiles in the pool at this stage: " + Pool.getTilesInPool() + "\n");

        Player playerOne = new Player( frameOne );
        Player playerTwo = new Player( frameTwo );

        Scanner input = new Scanner( System.in );

        String playerName;

        System.out.println( "Player One, please enter your name: " );
        playerName = input.nextLine();

        playerOne.setName( playerName );

        System.out.println( "Player Two, please enter your name: " );
        playerName = input.nextLine();

        playerTwo.setName( playerName );

        input.close();

        System.out.println("Player One's name: " + playerOne.getName());
        System.out.println("Player One's score: " + playerOne.getScore());
        System.out.println("Player One's frame: " + playerOne.getFrame());

        System.out.println();

        System.out.println("Player Two's name: " + playerTwo.getName());
        System.out.println("Player Two's score: " + playerTwo.getScore());
        System.out.println("Player Two's frame: " + playerTwo.getFrame());
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
