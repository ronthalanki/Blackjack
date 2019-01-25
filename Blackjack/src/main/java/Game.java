import Cards.*;
import Players.*;

class Game {
    private Deck deck;
    private Person[] people;
    private Bot[] bots;
    private Dealer dealer;
    private Player[] players;

    Game (int numPlayers, int numBots) {
        deck = new Deck();
        people = new Person[numPlayers];
        bots = new Bot[numBots];
        dealer = new Dealer();
        players = new Player[numPlayers + numBots];

        //initialize all of the arrays
        for (int i = 0; i < numPlayers; i++) {
            people[i] = new Person(i);
            players[i] = people[i];
        }

        for (int i = numPlayers; i < numPlayers + numBots; i++) {
            bots[i - numPlayers] = new Bot(i);
            players[i] = bots[i - numPlayers];
        }
    }

    void play() {
        do {
            playRound();
            System.out.println("Press q to quit and p to play another round");
        } while(Input.scanForLetter('q'));
    }

    private void playRound() {
        //setup betting
        for (Person p: people) {
            boolean betWorks = false;
            while (!betWorks) {
                System.out.println("Participant " + p.getPlayerNumber() + ": Place a bet up to the total amount of money you have.");
                System.out.println("Participant " + p.getPlayerNumber() + " has " + p.getMoney());
                int bet = Input.getIntegerInput(1, p.getMoney());
                betWorks = p.setCurrentBet(bet);
            }
        }

        for (Bot b: bots) {
            b.setCurrentBet();
        }

        //initial setup
        for (Player p: players) {
            drawCard(p);
            drawCard(p);
        }
        drawCard(dealer);
        drawCard(dealer);

        //each participant draws cards until they hold or bust
        for (Person p: people) {
            while (p.isPlaying()) {
                spaceBetweenSections();
                System.out.println("\nPerson " + p.getPlayerNumber() + " is playing");

                printCurrentState(p);

                System.out.println("\nPerson " + p.getPlayerNumber() + ": Do you want to hold (0) or draw (1)");

                int x = Input.getIntegerInput(0,2);

                if (x == 1) {
                    drawCard(p);
                } else if (x == 0) {
                    p.stopPlaying();
                }
            }
            spaceBetweenSections();
        }

        for (Bot b: bots) {
            while (b.getNextAction() && b.isPlaying()) {
                drawCard(b);
            }
        }

        //TODO: move dealer.isPlaying() into dealer.getNextAction()
        while (dealer.getNextAction() && dealer.isPlaying()) {
            drawCard(dealer);
        }

        spaceBetweenSections();

        //display all of the decks
        for (Player p: players) {
            System.out.println(p.getViewDeck());
        }
        System.out.println(dealer.getFullViewDeck());

        //print the winners and decide who wins/loses money
        System.out.println("\nWinners: ");
        boolean anyWinners = false;
        for (Player p: players) {
            if (p.getBestScore() > dealer.getBestScore()) {
                p.addMoney(p.getCurrentBet());
                dealer.transferMoney(- p.getCurrentBet());
                System.out.println("Participant " + p.getPlayerNumber() + " wins!");
                anyWinners = true;
            } else if (p.getBestScore() < dealer.getBestScore()) { // do nothing if score is equal
                p.addMoney(-p.getCurrentBet());
            }
        }

        if (!anyWinners) {
            System.out.println("None");
        }

        System.out.println("\nCurrent Money: ");
        for (Player p: players) {
            System.out.println("Participant " + p.getPlayerNumber() + ": " + p.getMoney());
        }

        System.out.println("\nEnd Round");

        for (Player p: players) {
            p.handReset();
        }
        dealer.handReset();

        System.out.println("Card Counting: " + bots[0].getCardCountingValue());
        System.out.println("Number of Cards Left: " + deck.size());
    }

    private void drawCard(Participant p) {
        Card c = deck.drawCard();
        p.addCard(c);

        for (Bot b: bots) {
            b.updateCardCountingValue(c.getValue());
        }
    }

    private void printCurrentState(Player p) {
        System.out.println(p.getViewDeck());
        System.out.println(dealer.getViewDeck());
    }

    private void spaceBetweenSections() {
        for(int i = 0; i < 20; i++) {
            System.out.println();
        }
    }
}
