import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

class Player {
    private ArrayList<Card> hand;
    private ArrayList<Integer> score;
    private int playerNumber;
    private boolean playing;

    Player(int playerNumber) {
        this.playerNumber = playerNumber;
        reset();
    }

    void reset() {
        score = new ArrayList<Integer>();
        score.add(0);

        hand = new ArrayList<Card>();
        playing = true;
    }

    void addCard(Card c){
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

        if (score.size() == 0) {
            playing = false;
        }
    }

    ArrayList<Integer> getScore() {
        return score;
    }

    int getBestScore() {
        if (score.size() == 0) {
            return 0;
        } else {
            return Collections.max(score);
        }
    }

    boolean isBust(){
        if (score.size() == 0) {
            return true;
        }
        return false;
    }

    int getPlayerNumber() {
        return playerNumber;
    }

    boolean isPlaying() {
        return playing;
    }

    void setPlaying() {
        playing = false;
    }

    private String getViewDeckHelper() {
        ArrayList<Integer> pScores = getScore();
        if (isBust()) {
            return "Current Score: Bust";
        } else if (pScores.size() == 1) {
            return "Current Score: " + pScores.get(0);
        } else {
            return "Current Scores: " + pScores;
        }
    }

    String getViewDeck() {
        if (playerNumber != -1) {
            return "\nPlayer " + playerNumber + "'s Cards: " + hand + "\n" + getViewDeckHelper();
        } else {
            String returnVal = "\nDealer's Cards: [xxx of xxx, ";
            for (int i = 1; i < hand.size(); i++) {
                returnVal += hand.get(i);
            }
            return returnVal + "]";
        }
    }

    String getViewDealerDeck() {
        return "Dealer's Cards: " + hand + "\nScore: " + getBestScore();
    }
}
