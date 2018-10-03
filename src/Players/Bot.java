package Players;

public class Bot extends Player {

    public Bot(int playerNumber) {
        super(playerNumber);
    }

    public String getViewDeck() {
        return "\nBot " + playerNumber + "'s Cards: " + getHand() + "\n" + getViewDeckHelper();

    }

    //returns false if hold, true if draw(hit)
    public boolean getNextAction() {
        return simpleBot();
    }

    private boolean simpleBot() {
        if (this.getBestScore() < 17  && isPlaying()) {
            return true;
        }
        return false;
    }
}
