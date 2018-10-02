import java.util.ArrayList;
import java.util.Collections;

class MainDeck extends ArrayList<Card> {

    MainDeck() {
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

    Card drawCard() {
        if (this.size() > 0) {
            return this.remove(this.size() - 1);
        }

        createNewDeck();
        return drawCard();
    }
}
