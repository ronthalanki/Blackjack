import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

abstract class Player {
    protected ArrayList<Card> hand;
    protected ArrayList<Integer> score;
    protected boolean playing;
    protected int playerNumber;
    protected int money;

    Player(){
        this.playerNumber = -1;
        handReset();
    }

    Player(int playerNumber) {
        this.playerNumber = playerNumber;
        handReset();
    }

    void handReset() {
        score = new ArrayList<Integer>();
        score.add(0);

        hand = new ArrayList<Card>();
        playing = true;
    }

    void addCard(Card c) {
        hand.add(c);
        updateCurrentScore(c);
    }

    //calculates the score based on the player's current hand
    private void updateCurrentScore(Card c) {
        int symbol = c.getSymbol();

        if (symbol == 1) {
            int origSize = score.size();
            for (int i = 0; i < origSize; i++) {
                score.add(score.get(i) + 11);
                score.set(i, score.get(i) + 1);
            }
        } else {
            int tempScore;
            if (symbol >= 2 && symbol <= 10) {
                tempScore = symbol;
            } else {
                tempScore = 10;
            }

            for (int i = 0; i < score.size(); i++) {
                score.set(i, score.get(i) + tempScore);
            }
        }

        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (int i: score) {
            if (i <= 21) {
                hashSet.add(i);
            }
        }
        score = new ArrayList<Integer>(hashSet);
        Collections.sort(score);

        //checks if all possible scores are bust
        if (isBust()) {
            playing = false;
        }
    }

    ArrayList<Integer> getScore() {
        return score;
    }

    int getBestScore() {
        if (isBust()) {
            return 0;
        }
        return Collections.max(score);
    }

    boolean isPlaying() {
        return playing;
    }

    void setPlaying() {
        playing = false;
    }

    boolean isBust() {
        if (score.size() == 0) {
            return true;
        }
        return false;
    }

    int getPlayerNumber() {
        return playerNumber;
    }

    protected String getViewDeckHelper() {
        if (isBust()) {
            return "Current Score: Bust";
        } else if (score.size() == 1) {
            return "Current Score: " + getScore().get(0);
        }
        return "Current Scores: " + getScore();
    }

    abstract String getViewDeck();

    protected ArrayList<Card> getHand() {
        return hand;
    }
}
