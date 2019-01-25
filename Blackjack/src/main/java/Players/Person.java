package Players;

public class Person extends Player {

    public Person(int playerNumber) {
        super(playerNumber, 1000);
    }

    public String getViewDeck() {
        return "\nPerson " + playerNumber + "'s Cards: " + getHand() + "\n" + getViewDeckHelper();

    }
}
