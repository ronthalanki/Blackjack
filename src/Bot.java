class Bot extends Player{

    Bot(int playerNumber) {
        super(playerNumber);
    }

    String getViewDeck() {
        return "\nBot " + playerNumber + "'s Cards: " + getHand() + "\n" + getViewDeckHelper();

    }

    //returns false if hold, true if draw(hit)
    boolean getNextAction() {
        return simpleBot();
    }

    private boolean simpleBot() {
        if (this.getBestScore() < 17  && isPlaying()) {
            return true;
        }
        return false;
    }
}
