# Assignment Three
###This is our implementation of the game interface and its link to the back-end 

Points to note: 

-> Our interface look is based on fxml content switching as well as scene switching in the window. The fxml files 
can be found in the fxml folder.

-> We don't have a class 'UI' because we separated styling into separate fxml files. Therefore, the UI interface is composed of 
all the fxml files, and their controllers.

-> The fxml files are being controlled by their own specific controllers (classes can be found on matching names).

-> The class 'Scrabble' is the entry point to the entire game. It loads 'openingWindow.fxml' which prompts the players
to introduce their names and press a button to start the game.

-> BoardController is the main controller, which controls most of the game playing. It contains information used by the other
controllers to ensure the game rolls as it should. It contains references to the objects needed i.e. Board, Pool, Players, Frames,
and methods for display of tiles on board and frames, display of scores, and handling of choice buttons.

-> To run the game, simply run the main of 'Scrabble', or the .jar file.

-> Initially we had this written in Java SDK 11, and JavaFX 11, but had to migrate to SDK 8. If the .jar file doesn't run
and triggers exceptions, please check you have the correct SDK installed.

-> OpeningWindowController (the controller of openingWindow.fxml) upon press of 'START GAME' button loads the boardGraphic.fxml
which contains the interface allowing the playing of the game.

-> boardGraphic.fxml contains a representation of the board, choice buttons for the players based on the move they wish
to take i.e. 'CHALLENGE', 'PASS', 'PLACE WORD', 'EXCHANGE', 'HELP', and 'QUIT', information about the words placed, and 
the player's turn and score.

-> All of these buttons except for 'PASS' trigger fxml content switching within boardGraphic.fxml to allow the player to:
 - Learn about the game when pressing 'HELP'
 - Input the tiles they wish to exchange when pressing 'EXCHANGE'
 - Input the word they wish to place on the board together with position and direction when pressing 'PLACE WORD'
 - Choose whether they truly wish to quit the game when pressing 'QUIT'
   
-> 'PASS' strictly triggers the switching of players, without any effect on the board, tiles, or scoring.

-> The game ends when one of two situations occur :
-  #####The pool is empty and a player used all of their tiles in the frame to place a word
            In this player's case, their score increases by the sum of values of tiles in their opponent's frame.
            The opponent's score decreases by the sum of values of tiles in their own frame.
- #####The board is full, and the players could only pass twice in succession each due to empty Pool, triggering the end of the game.
            In this case, both players subtract from their scores the sum of values of tiles in their corresponding frames if 
            the pool is empty. 
            If the pool is not empty, players are penalised for passing twice in succession each with termination of the game,
            and no effect on their scoring.
            When this happens, gameOverWindow.fxml is loaded and the winner and loser are displayed together with their scores.


 