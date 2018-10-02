public class Runner {
    public static void main(String[] args) {

        System.out.println("How many players?");
        int numPlayers = Input.getIntegerInput(1, 8);

        Game g = new Game(numPlayers);
        g.play();
    }
}
