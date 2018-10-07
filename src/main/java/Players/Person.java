package Players;

public class Person extends Player {

    public Person(int playerNumber) {
        super(playerNumber);
    }

    public String getViewDeck() {
        return "\nPerson " + playerNumber + "'s Cards: " + getHand() + "\n" + getViewDeckHelper();

    }
}
