# Assignment Four
### This branch oversees the implementation of the challenge functionality and blank tile handling in the game of Scrabble.

#### Prerequisites
    Running the jar requires the installation of Java SDK 8, due to IntelliJ issues regarding the building of JavaFX 
    applications for newer versions of Java and JavaFX. Please ensure you have the right sdk installed before attempting
    to run Scrabble.jar.

#### Points to note: 

#### 1. To run the game, simply run the main of 'Scrabble', or the .jar file.

#### 2. The Challenge Functionality can be accessed during a Player's turn by clicking the button 'CHALLENGE' on the interface
display.

#### 3. The Challenge Functionality is implemented as follows:
    1. The challenger simply clicks the 'CHALLENGE' button during their move when they think their opponent's play is invalid.
    2. Our implementation has the SOWPODS dictionary file loaded into the software when the game is started. i.e. 'START GAME'
    button is pressed.
    3. The file contains each valid word on a new line. When 'START GAME' button is pressed and the boardGraphic.fxml file
    is loaded, the file is being read into HashSet dictionary declared in BoardController.java
    4. Reading the file has a O(n) running time complexity, but it is only loaded once during the game. A BufferedReader
    is used to read the file instead of Scanner due to increased efficiency when reading files composed of large number
    of new lines.
    5. The dictionary is only ever accessed when the 'CHALLENGE' button is pressed by a Player.
    6. This triggers the checking of not just the main word placed on the board, but also any additional words that may have
    been created in the process. 
    7. The validity checking is O(m) running time complexity, where m is the number of words created. This is due to O(1)
    constant running time complexity of method contains() of the HashSet data structure.
    8. When a word has been found to be invalid i.e. not found in dictionary (dictionary.contains(word) returns false),
    then a message is displayed in the bottom right corner, announcing the failed challenge. The challenger will then lose
    their turn.
    9. When all words (if more than one) are valid i.e. found in dictionary, then a message is displayed in bottom right
    corner, announcing the successful challenge. The challenger gets to make another move, while the word is removed from
    the board and the opponent's score is returned to the one prior to word placement. 
    10. The tiles of the word are also returned to the rightful owner.


#### 4. The 'CHALLENGE' button is enabled during the game <b>EXCEPT</b> when: 
    1. No first play has been made yet.
    2. A Player placed a word on the board, and their opponent passed their turn, exchanged tiles, or challenged the
     Player's word unsuccessfully. This ensures a player cannot challenge their own play.
    3. When a Player placed a word on the board and their opponent successfully challenged it. This ensures the opponent
    whose turn it is cannot challenge twice in a row.

#### 5. Blank tile handling is done as follows:
    1. When a Player places a word containing a blank tile, blankContent.fxml is loaded.
    2. This file is controlled by BlankContentController.java
    3. The Controller parses the input from the Player passed into the .fxml file. The input is the letter the Player 
    wishes the blank tile to replace.
    4. The letter is then passed to methods which replace the word that was already stored in the back-end and override 
    it with a word containing the replacement letter instead of blank tile.
        ######This is done to facilitate challenge functionality, which is based on word checking against a dictionary.
    5. The word containing the replacement letter is displayed on the board and on the top right corner in the 'Words Created'
    panel.

 
