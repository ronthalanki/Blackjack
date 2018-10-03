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

        for (Player p: players) {
            drawCard(p);
            drawCard(p);
        }

        for (Player p: players) {
            System.out.println(p.isPlaying());
        }

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

        while (dealer.getBestScore() < 17 & dealer.isPlaying()) {
            drawCard(dealer);
        }

        spaceBetweenSections();
        System.out.println(dealer.getViewDeck());

        for (Person p: people) {
            if (p.getBestScore() > dealer.getBestScore()) {
                System.out.println("Person " + p.getPlayerNumber() + " wins!");
            }
        }

        System.out.println("End Round");

        dealer.handReset();
        for (Person p: people) {
            p.handReset();
        }
    }

    private void drawCard(Player p) {
        Card c = mainDeck.drawCard();
        p.addCard(c);
    }

    private void printCurrentState() {
        System.out.println(dealer.getViewDeck());
        for (Person p: people) {
            System.out.println(p.getViewDeck());
        }
    }

    private void spaceBetweenSections() {
        for(int i = 0; i < 20; i++) {
            System.out.println();
        }
    }
}
