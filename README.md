# Blackjack
Blackjack Game

Instructions:
1) cd into the src directory of the Blackjack folder
2) Compile all of the java files in the src directory by running javac Runner.java
3) type java Runner in order to start the game
4) Follow typical Blackjack rules and the instructions on the screen in order to play the game

Design Choices:
- Language: Java; I used Java in order to create this program as it is object-oriented and has static typing.
This allowed me to quickly ensure that code was modular and limited breaking of abstraction barriers.

- ArrayList for Person Hand, MainDeck: Both of these objects had changing sizes and thus an array would not be effective to use to store the objects.
Additionally, a HashMap would also not be effective since I was simply iterating through each object rather than searching for a specific object.

- Input Class: The Input class handled any type of user input to ensure that user's could not enter malformed input.

- Collections Shuffle: I used the Collections class in order to handle shuffling since it is part of java.utils.
Thus, it is optimized for use with arraylists and other built-in Java data structures.

- HashMap for Dictionary in Card.java Class: I used a HashMap to map the values of the symbols and suits to their names as HashMap optimizes "get" queries.
