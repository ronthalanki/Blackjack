package Cards;

import Cards.Card;

import java.util.ArrayList;
import java.util.Collections;

public class MainDeck extends ArrayList<Card> {

    public MainDeck() {
        createNewDeck();
    }

    private void createNewDeck() {
        this.removeAll(this);
        for (int i = 0; i < 52; i++) {
            Card temp = new Card((i % 13 + 1), (i / 13 + 1));
            this.add(temp);
        }
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(this);
    }

    public Card drawCard() {
        if (this.size() > 0) {
            return this.remove(this.size() - 1);
        }

        createNewDeck();
        return drawCard();
    }
}
