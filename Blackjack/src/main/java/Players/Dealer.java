package Players;

public class Dealer extends Participant {
    protected int money;

    public Dealer() {
        super();
        this.money = 0;
    }

    //returns false if hold, true if draw(hit)
    public boolean getNextAction() {
        if (getBestScore() < 17) {
            return true;
        }
        return false;
    }

    //positive amount if Dealer wins money, negative if Dealer loses money
    public void transferMoney(int amount) {
        money += amount;
    }

    public String getViewDeck() {
        String returnVal = "\nDealer's Cards: [xxx of xxx, ";
        for (int i = 1; i < hand.size(); i++) {
            returnVal += hand.get(i) + ", ";
        }
        return returnVal.substring(0,returnVal.length() - 2) + "]";
    }

    public String getFullViewDeck() {
        String returnVal = "\nDealer's Cards: [";
        for (int i = 0; i < hand.size(); i++) {
            returnVal += hand.get(i) + ", ";
        }
        return returnVal.substring(0,returnVal.length() - 2) + "]";
    }
}
