class Game {
    private MainDeck mainDeck;
    private Person[] people;
    private Bot[] bots;
    private Dealer dealer;
    private Player[] players;

    Game (int numPlayers, int numBots) {
        mainDeck = new MainDeck();
        people = new Person[numPlayers];
        bots = new Bot[numBots];
        dealer = new Dealer();
        players = new Player[numPlayers + numBots + 1];

        //initialize all of the arrays
        for (int i = 0; i < numPlayers; i++) {
            people[i] = new Person(i);
            players[i] = people[i];
        }

        for (int i = numPlayers; i < numPlayers + numBots; i++) {
            bots[i - numPlayers] = new Bot(i);
            players[i] = bots[i - numPlayers];
        }

        players[numPlayers + numBots] = dealer;
    }

    void play() {
        do {
            playRound();
            System.out.println("Press q to quit and any other key to play another round");
        } while(Input.scanForLetter('q'));
    }

    private void playRound() {
        //inital setup
        for (Player p: players) {
            drawCard(p);
            drawCard(p);
        }

        //each player draws cards until they hold or bust
        for (Person p: people) {
            while (p.isPlaying()) {
                spaceBetweenSections();
                System.out.println("\nPerson " + p.getPlayerNumber() + " is playing");

                printCurrentState();

                System.out.println("\nPerson " + p.getPlayerNumber() + ": Do you want to hold (0) or draw (1)");

                int x = Input.getIntegerInput(0,2);

                if (x == 1) {
                    drawCard(p);
                } else if (x == 0) {
                    p.setPlaying();
                }
            }
            spaceBetweenSections();
        }

        for (Bot b: bots) {
            while (b.getNextAction()) {
                drawCard(b);
            }
        }

        while (dealer.getNextAction()) {
            drawCard(dealer);
        }

        spaceBetweenSections();

        //display all of the decks
        for (Player p: players) {
            System.out.println(p.getViewDeck());
        }

        //print the winners
        System.out.println("\nWinners");
        for (Person p: people) {
            if (p.getBestScore() > dealer.getBestScore()) {
                System.out.println("Player: " + p.getPlayerNumber() + " wins!");
            }
        }

        for (Bot b: bots) {
            if (b.getBestScore() > dealer.getBestScore()) {
                System.out.println("Bot " + b.getPlayerNumber() + " wins!");
            }
        }

        System.out.println("\nEnd Round");

        for (Player p: players) {
            p.handReset();
        }
    }

    private void drawCard(Player p) {
        Card c = mainDeck.drawCard();
        p.addCard(c);
    }

    private void printCurrentState() {
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
