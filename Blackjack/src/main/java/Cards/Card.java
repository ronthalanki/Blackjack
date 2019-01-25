package Cards;

public class Card {

    //1 = Ace, 2 - 10 = 1 - 10, 11 = Jack, 12 = Queen, 13 = King
    private int symbol;

    // 1 = Hearts, 2 = Diamonds, 3 = Clubs, 4 = Spades
    private int suit;

    public Card(int symbol, int suit) {
        this.symbol = symbol;
        this.suit = suit;
    }

    public int getValue() {
        if (this.symbol <= 10) {
            return symbol;
        } else {
            return 10;
        }
    }

    @Override
    public String toString() {
        return Deck.dictSymbols.get(symbol) + " of " + Deck.dictSuits.get(suit);
    }
}
