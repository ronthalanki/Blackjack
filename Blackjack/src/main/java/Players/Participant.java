package Players;

import Cards.Card;

import java.util.ArrayList;
import java.util.Collections;

abstract public class Participant {
    protected ArrayList<Card> hand;
    protected ArrayList<Integer> score;
    protected boolean playing;

    protected Participant() {
        handReset();
    }

    public void handReset() {
        score = new ArrayList<Integer>();
        score.add(0);

        hand = new ArrayList<Card>();
        playing = true;
    }

    public void addCard(Card c) {
        hand.add(c);
        updateCurrentScore(c);
    }

    //calculates the current score(s) based on the player's current hand
    private void updateCurrentScore(Card c) {

        if (c.getValue() == 1) {
            int numPotentialScores = score.size();
            for (int i = 0; i < numPotentialScores; i++) {
                score.add(score.get(i) + 11);
                score.set(i, score.get(i) + 1);
            }
        } else {
            for (int i = 0; i < score.size(); i++) {
                score.set(i, score.get(i) + c.getValue());
            }
        }

        score.removeIf(s -> (s > 21));
        Collections.sort(score);

        //checks if all possible scores are bust
        if (isBust()) {
            stopPlaying();
        }
    }

    ArrayList<Integer> getScore() {
        return score;
    }

    public int getBestScore() {
        if (isBust()) {
            return 0;
        }
        return Collections.max(score);
    }

    public boolean isPlaying() {
        return playing;
    }

    public void stopPlaying() {
        playing = false;
    }

    boolean isBust() {
        if (score.size() == 0) {
            return true;
        }
        return false;
    }

    protected ArrayList<Card> getHand() {
        return hand;
    }
}
