public class PlayerTest
{
    public static void main( String[] args )
    {
        //Creating a pool
        Pool pool = new Pool();

        //Creating a new frame
        Frame testFrame = new Frame();

        System.out.println("isEmpty should return false: " +  testFrame.isEmpty());

        //Testing print the frame
        System.out.println("Filled frame: " + testFrame.getFrame());

        //Removing letter from the pool
        System.out.println("Attempting to remove A from the frame");
        System.out.println("Char taken: " + testFrame.takeTile('A' ));

        //Removing letter from the pool
        System.out.println("Attempting to remove B from the frame");
        System.out.println("Char taken: " + testFrame.takeTile('B' ));

        //Removing letter from the pool
        System.out.println("Attempting to remove C from the frame");
        System.out.println("Char taken: " + testFrame.takeTile('C' ));

        System.out.println("isEmpty should return false: " +  testFrame.isEmpty());

        //Testing print the frame
        System.out.println("Frame with A removed (if it was there initially): " + testFrame.getFrame());

        //Testing print the frame
        System.out.println("Frame should be full again: " + testFrame.getFrame());
    }
}
