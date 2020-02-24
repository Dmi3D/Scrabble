# Assignment Two
This is our implementation of the board. 

Points to note: 

1. We represented a square on a board as an object which contains information about the values i.e. the score / weights
associated with each (e.g Double Letter), as well as the character tiles i.e. the letters they contain when a tile has been placed on such square (otherwise null).

2. We represented the board as a 15x15 2D Array of type Square.

3. For the purpose of testing, there is a method which displays the board with its weights&types i.e. an ASCII representation 
of the board where each square shows the information regarding its score e.g. Triple Word score, Double letter score etc.
This method is called displayBoardWithWeights().

4. There is also a method that displays the board's tiles only. This method is called displayBoard().

5. The method which calls all method checks that ensure the proper placement of a word on the board is called placeWord().
Following a valid positioning and word input, placeWord() will place the word on the board and remove the corresponding tiles
from the player's frame.

6. The logic behind placing a word on the board that will overlap with another word is as follows:
The player will input the full word they are willing to place on the board. If there are letters already on the board in the word's path, then the player does NOT need to have the overlapped letter (if the overlap is valid).

    e.g. Player One wants to place the word 'CAT' on the frame, starting at a position that will interlock this word with another
    word on the board, e.g. 'FLAME'. The two words will interlock at the letter 'A'. Player One doesn't have letter A, but because
    the trajectory of the placement of 'CAT' is through the letter 'A' of the word 'FLAME', the word will be placed and the player's
    corresponding tiles will be removed from the frame.
