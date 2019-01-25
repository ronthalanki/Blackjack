package Players;

abstract public class Player extends Participant{
    protected int playerNumber;
    protected int money;
    protected int currentBet;

    public Player(int playerNumber, int money) {
        this.playerNumber = playerNumber;
        this.money = money;
        this.currentBet = 0;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public boolean setCurrentBet(int bet) {
        if (money >= currentBet) {
            this.currentBet = bet;
            return true;
        }
        return false;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int bet) {
        money += bet;
    }

    //TODO: add this to dealer class
    protected String getViewDeckHelper() {
        if (isBust()) {
            return "Current Score: Bust";
        } else if (score.size() == 1) {
            return "Current Score: " + getScore().get(0);
        }
        return "Current Scores: " + getScore();
    }

    abstract public String getViewDeck();
}
