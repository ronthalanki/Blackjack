public class Runner {
    public static void main(String[] args) {

        System.out.println("How many players?");
        int numPlayers = Input.getIntegerInput(1, 4);

        System.out.println("How many bots?");
        int numBots = Input.getIntegerInput(0, 8);

        Game g = new Game(numPlayers, numBots);
        g.play();
    }
}
