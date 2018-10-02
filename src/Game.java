class Game {
    private MainDeck mainDeck;
    private Player[] players;
    private Player dealer;

    Game (int numPlayers) {
        players = new Player[numPlayers];
        dealer = new Player(-1);
        mainDeck = new MainDeck();

        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(i);
        }
    }

    void play() {
        do {
            playRound();
            System.out.println("Press q to quit and any other key to play another round");
        } while(Input.scanForLetter('q'));
    }

    private void playRound() {
        drawCard(dealer);
        drawCard(dealer);

        for (Player p: players) {
            drawCard(p);
            drawCard(p);
        }

        for (Player p: players) {
            while (p.isPlaying()) {
                spaceBetweenSections();
                System.out.println("\nPlayer " + p.getPlayerNumber() + " is playing");

                printCurrentState();
                System.out.println("\nPlayer " + p.getPlayerNumber() + ": Do you want to hold (0) or draw (1)");

                int x = Input.getIntegerInput(0,2);

                if (x == 1) {
                    drawCard(p);
                } else if (x == 0) {
                    p.setPlaying();
                }
            }
            spaceBetweenSections();
        }

        while (dealer.getBestScore() < 17 & dealer.isPlaying()) {
            drawCard(dealer);
        }

        spaceBetweenSections();
        System.out.println(dealer.getViewDealerDeck());

        for (Player p: players) {
            if (p.getBestScore() > dealer.getBestScore()) {
                System.out.println("Player " + p.getPlayerNumber() + " wins!");
            }
        }

        System.out.println("End Round");

        dealer.reset();
        for (Player p: players) {
            p.reset();
        }
    }

    private void drawCard(Player p) {
        Card c = mainDeck.drawCard();
        p.addCard(c);
    }

    private void printCurrentState() {
        System.out.println(dealer.getViewDeck());
        for (Player p: players) {
            System.out.println(p.getViewDeck());
        }
    }

    private void spaceBetweenSections() {
        for(int i = 0; i < 20; i++) {
            System.out.println();
        }
    }
}
