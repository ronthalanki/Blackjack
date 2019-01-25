package Cards;

import java.util.*;

public class Deck extends ArrayList<Card> {

    protected static Map<Integer, String> dictSymbols = new HashMap<Integer, String>();
    protected static Map<Integer, String> dictSuits = new HashMap<Integer, String>();
    private int seed;

    public Deck() {
        addNewDeck();
        dictHelper();
        this.seed = 1;
    }

    public Deck(int seed) {
        this.seed = seed;
    }

    private void addNewDeck() {
        this.removeAll(this);
        for (int i = 0; i < 52; i++) {
            Card temp = new Card((i % 13 + 1), (i / 13 + 1));
            this.add(temp);
        }
        shuffle();
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

    private void shuffle() {
        Collections.shuffle(this, new Random(seed));
        seed += 1;
    }

    public Card drawCard() {
        if (this.size() > 0) {
            return this.remove(this.size() - 1);
        } else {
            addNewDeck();
            return drawCard();
        }
    }
}
