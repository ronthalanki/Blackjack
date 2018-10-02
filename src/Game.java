class Game {
    private MainDeck mainDeck;
    private Person[] people;
    private Person dealer;

    Game (int numPlayers) {
        people = new Person[numPlayers];
        dealer = new Person(-1);
        mainDeck = new MainDeck();

        for (int i = 0; i < numPlayers; i++) {
            people[i] = new Person(i);
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

        for (Person p: people) {
            drawCard(p);
            drawCard(p);
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
        System.out.println(dealer.getViewDealerDeck());

        for (Person p: people) {
            if (p.getBestScore() > dealer.getBestScore()) {
                System.out.println("Person " + p.getPlayerNumber() + " wins!");
            }
        }

        System.out.println("End Round");

        dealer.reset();
        for (Person p: people) {
            p.reset();
        }
    }

    private void drawCard(Person p) {
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
