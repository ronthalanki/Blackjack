class Dealer extends Player{

    String getViewDeck() {
        String returnVal = "\nDealer's Cards: [xxx of xxx, ";
        for (int i = 1; i < hand.size(); i++) {
            returnVal += hand.get(i) + ", ";
        }
        return returnVal.substring(0,returnVal.length() - 2) + "]";
    }

    //returns false if hold, true if draw(hit)
    boolean getNextAction() {
        return simpleBot();
    }

    private boolean simpleBot() {
        if (getBestScore() < 17 && isPlaying()) {
            return true;
        }
        return false;
    }
}
