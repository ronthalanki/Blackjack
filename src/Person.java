import java.util.ArrayList;

class Person extends Player{

    private String getViewDeckHelper() {
        ArrayList<Integer> pScores = getScore();
        if (isBust()) {
            return "Current Score: Bust";
        }

        return "Current Scores: " + pScores;
    }

    String getViewDeck() {
        return "\nPerson " + playerNumber + "'s Cards: " + hand + "\n" + getViewDeckHelper();

    }

    String getViewDealerDeck() {
        return "Dealer's Cards: " + hand + "\nScore: " + getBestScore();
    }
}
