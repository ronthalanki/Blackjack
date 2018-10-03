import java.util.ArrayList;

class Bot extends Player{

    Bot(int playerNumber) {
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

    //returns false if hold, true if draw(hit)
    boolean getNextAction() {
        return simpleBot();
    }

    private boolean simpleBot() {
        if (this.getBestScore() < 17) {
            return true;
        }
        return false;
    }
}
