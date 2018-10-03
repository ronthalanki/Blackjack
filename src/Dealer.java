class Dealer extends Player{

    String getViewDeck() {
        return "\nDealer's Cards: " + hand.get(0);

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
