# Scrabble

Scrabble is a 2-player game that involves placing words on the board formed by the tiles drawn at random by each player during their turn. There are 100 tiles in the pool consisting of the letters of the alphabet together with 2 'wild cards' represented as * which can take the place of any letter in the alphabet.  The words placed by the players have to be part of the English Dictionary, otherwise they can be challenged and removed from the board during one of the players' turn. Each player can choose between challenging their opponents' last play (i.e. last word placement), placing a word, exchanging their tiles/letters, and passing during their turn. The game ends when one of two scenarios is met:
    1. The players passed more than 2 times in a row, indicating they no longer have moves.
    2. A player used all their letters and there are less than 7 letters in the pool.

### Prerequisites
    Running the jar requires the installation of Java SDK 8, due to IntelliJ issues regarding the building of JavaFX 
    applications for newer versions of Java and JavaFX. Please ensure you have the right sdk installed before attempting
    to run Scrabble.jar.

## Installation

Download all files and create a jar file called Scrabble.jar. If double-clicking doesn't start the application, use the following 
command in the cmd/command line at the corresponding location:

```bash 
$ java -jar Scrabble.jar
```

## How it works

Scrabble is implemented in Java and JavaFX. Relevant information about the data structures and algorithms used to power the application are explained throughly in the branches correlating to the sprints during which they were coded. There are 4 branches relating to said sprints, them being AssignmentTwo, AssignmentThree, AssignmentFour, and AssignmentFive. Each branch contains its own README with comprehensive details regarding the backend implementation and corresponding functionalities.


## The application

### Main page
The main page allows two users to input their names and start the game.

![Main Page](https://github.com/Dmi3D/Scrabble/blob/master/src/img/mainmenu.PNG?raw=true)

### The playing area
This is where the game is played. The area consists of the board on which tiles are placed, the name of the current player, the current player's tiles and score, the words placed by the opponent on their last play, the moves that the player can make, and lastly, an area where error messages appear or moves can be confirmed.

![Playing area](https://github.com/Dmi3D/Scrabble/blob/master/src/img/playingarea.PNG?raw=true)

### Options
### Placing word
The 'place word' button allows the player to place a word on the board. When the button is clicked, the player is allowed to indicate the word itself, the location and direction of its placement.

![Placing word](https://github.com/Dmi3D/Scrabble/blob/master/src/img/placingword.PNG?raw=true)

### Help 
The 'help' button allows a player to quickly verify basic rules of the game, including information about the multiplication of scores determined by the color of certain squares.

![Help](https://github.com/Dmi3D/Scrabble/blob/master/src/img/help.PNG?raw=true)

### Challenge
The 'challenge' button allows the player to challenge their opponent's last play if they think the word doesn't belong in the dictionary. A message will appear in the window informing the players of the success of the challenge.

Outcome: successful
![Challenge Successful](https://github.com/Dmi3D/Scrabble/blob/master/src/img/challenge-successful.PNG?raw=true)
Outcome: unsuccessful (different play)
![Challenge Unsuccessful](https://github.com/Dmi3D/Scrabble/blob/master/src/img/challenge-unsuccessful.PNG?raw=true)

### Exchange tiles
The 'exchange tiles' button allows the player to choose tiles in their frame they wish to return to the pool in exchange of new random ones. The press of the button triggers the message below:

![Exchange Tiles](https://github.com/Dmi3D/Scrabble/blob/master/src/img/exchange.PNG?raw=true)

### Pass
The 'pass' button simply passes the current player's turn.

### Quit
The 'quit' button allows the players to end the game prematurely and exit the application, with no further indication of the score. A message appears in the window to allow the players to confirm their decision. This is done to prevent prematurely closing the application due to a missclick.

![Quit](https://github.com/Dmi3D/Scrabble/blob/master/src/img/quit.PNG?raw=true)

### Error handling
Any errors that might be triggered during the game due to an invalid word placement or move are shown in the window to inform the players.

![Error](https://github.com/Dmi3D/Scrabble/blob/master/src/img/errorhandling.PNG?raw=true)


### Dealing with wild cards or *
When a player wishes to use their * tile as replacement for a letter, they can do so. First, the word containing the * has to be input into the word placement functionality. Then the application detects the * and allows the player to indicate the letter that the * is replacing.

Indicating the word

![Placing wildcard](https://github.com/Dmi3D/Scrabble/blob/master/src/img/placing-wildcard.PNG?raw=true)

Replacing *

![Replacing *](https://github.com/Dmi3D/Scrabble/blob/master/src/img/placing-wildcard-handling.PNG?raw=true)

### Game Over
When the conditions for game ending are met, the window changes indicating the scores of each player and their status i.e. winner/loser.

![Game Over](https://github.com/Dmi3D/Scrabble/blob/master/src/img/gameover.PNG?raw=true)

