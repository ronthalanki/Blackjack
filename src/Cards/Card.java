package Cards;

import java.util.HashMap;
import java.util.Map;

public class Card {

    /*
    * Symbol represents the value on the card
    * 1 = Ace, 2 - 10 = 1 - 10, 11 = Jack, 12 = Queen, 13 = King
    * */
    private int symbol;

    /*
    * Suit represents the suit of the card
    * 1 = Hearts, 2 = Diamonds, 3 = Clubs, 4 = Spades
    * */
    private int suit;

    private Map<Integer, String> dictSymbols = new HashMap<Integer, String>();
    private Map<Integer, String> dictSuits = new HashMap<Integer, String>();

    Card(int symbol, int suit) {
        this.symbol = symbol;
        this.suit = suit;

        dictHelper();
    }

    private void dictHelper() {
        dictSymbols.put(1, "Ace");
        for (int i = 2; i <= 10; i++) {
            dictSymbols.put(i, "" + i);
        }
        dictSymbols.put(11, "Jack");
        dictSymbols.put(12, "Queen");
        dictSymbols.put(13, "King");

        dictSuits.put(1, "Hearts");
        dictSuits.put(2, "Diamonds");
        dictSuits.put(3, "Clubs");
        dictSuits.put(4, "Spades");
    }

    public int getSymbol() {
        return symbol;
    }

    int getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return dictSymbols.get(symbol) + " of " + dictSuits.get(suit);
    }
}
