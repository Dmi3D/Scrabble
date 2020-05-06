# Assignment Five
### This branch oversees the implementation of the bot functionality.

### Points to note: 

#### 1. We implemented the bot with 2 parts in mind: 
        1. The first play - when Board.isFirstPlay is true, the Bot would be in charge of making the first word placement
        on the board using the tiles available in its frame.
        2. Any other play, when the bot has to carry out its algorithm to make a play using the tiles available to it
        both in its frame as well as the ones already placed on the board.

#### 2. The first play is implemented as follows:
        The getCommand() method in the Bot returns the specific command to do with the placement of the first word.
        The Bot creates combinations of all the letters in its frame, and then generates all possible permutations for 
        those combinations.
        The permutations are then subjected to a check to see whether or not they contain possible valid words in the 
        English dictionary.
        Due to the nature of storing our permutations in a Priority Queue of WordEntries (an object we defined as an inner
        class in the Bot class), our checking for a valid word will yield us the highest scoring word possible. 
        This is because we are manipulating Java's implementation of a Minimum Priority Queue to act like a Maximum 
        Priority Queue so that we may store the words in descending order of scoring values associated with each word.
        In this manner, when a valid word is found to be placed, it will be guaranteed to be the best possible word 
        to place as the first play given the Bot's frame.
        
### 3. All subsequent word placements are implemented as follows:
         The Bot checks the Board for tiles placed prior and stores a String of such Tile letters.
         Following that, the Bot creates combinations of the letters in its frame.
         
         At this point, the letters on the board are stored in a Priority Queue in descending order of their score values.
         This is needed because we want to use every letter on the board (in the worst case scenario) to possibly try
         use to create a word to place. 

         NOTE: Repeated letters on the board are not stored, to avoid generating permutations for the same combinations
         when the same letter at a different location is appended to such combinations.
         
         To every combination found, the highest scoring letter from the board is appended, and then permutations are
         generated for all these combinations using the same logic we used in the first play.
         Just like in the first play, the permuations are checked against the Dictionary to determine whether or not 
         there are any valid words, and if there are, we store them in a Priority Queue.
         
         The locations of the letters on the board are recorded in an ArrayList of integer arrays. This is to deal with
         possible repeated letters. It works as follows:
         
         Let's say the following letters are on the board: T, R, A, G, E, D, Y, L, F, L, U, G, G, A, G, E
         As visible, there are repeated letters, them being G, E, and L.
         In the case scenario where the Bot has word 'CARED' to place, where 'E' is the letter taken from the Board, 
         it could possibly place it at more than just one location given repeated letters 'E' on the board. Therefore
         an Arraylist of the locations of the repeated letters are stored.
         
         The locations are used to allow the checking of the word against few possible placements, before it is discarded
         for reasons such as out of bounds invalid placement, invalid overlapping, creation of non-existing words in the
         process.
         
         If at this point the word was successful in passing the legal checking, then the command is given to getCommand()
         to place the word at the correct starting location and in the correct direction (characteristics determined prior based
         on the location of the letter taken from the board to create such word).
         
         If however at this point the word was unsuccessful in its placement, the next word is checked against the letter
         location/s and it will continue to do this until exhausting all the words created with this letter.
         
         At this stage, if nothing worked, the Bot moves on to following these steps with the next letter taken from the
         Board and it will continue to do so until a valid word that can be placed is found.
         
         If no valid word is ever found, the Bot attempt to exchange its tiles given that it will be allowed to do so.
         
         If that is not possible, the Bot will ultimately pass in defeat.


