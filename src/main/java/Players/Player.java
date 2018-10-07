package Players;

import Cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

abstract public class Player {
    protected ArrayList<Card> hand;
    protected ArrayList<Integer> score;
    protected boolean playing;
    protected int playerNumber;
    protected int money;
    protected int currentBet;

    public Player(){
        this(-1);
    }

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        handReset();
        money = 1000;
        currentBet = 0;
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

    public int getBestScore() {
        if (isBust()) {
            return 0;
        }
        return Collections.max(score);
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying() {
        playing = false;
    }

    boolean isBust() {
        if (score.size() == 0) {
            return true;
        }
        return false;
    }

    public int getPlayerNumber() {
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

    abstract public String getViewDeck();

    protected ArrayList<Card> getHand() {
        return hand;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public boolean setCurrentBet(int bet) {
        if (money >= currentBet) {
            currentBet = bet;
            money -= currentBet;
            return true;
        } else {
            return false;
        }
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int bet) {
        money += bet;
    }
}
