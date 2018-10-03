class Person extends Player{

    Person(int playerNumber) {
        super(playerNumber);
    }

    private String getViewDeckHelper() {
        if (isBust()) {
            return "Current Score: Bust";
        }
        return "Current Scores: " + getScore();
    }

    String getViewDeck() {
        return "\nPerson " + playerNumber + "'s Cards: " + hand + "\n" + getViewDeckHelper();

    }
}
