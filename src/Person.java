class Person extends Player{

    Person(int playerNumber) {
        super(playerNumber);
    }

    String getViewDeck() {
        return "\nPerson " + playerNumber + "'s Cards: " + getHand() + "\n" + getViewDeckHelper();

    }
}
