import java.util.Scanner;

class Input {

    static int getIntegerInput(int low, int high) {
        Scanner scan = new Scanner(System.in);
        int input = 0;
        boolean validInput = false;
        while (validInput == false) {
            try {
                input = Integer.parseInt(scan.nextLine());
                if (input > high || input < low) {
                    throw new Exception("Too Big or Too Small");
                }
                validInput = true;
            } catch (Exception e) {
                System.out.println("Please enter a number between " + low  + " and " + (high - 1));
            }
        }
        return input;
    }

    static boolean scanForLetter(char letter) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                String input = scan.nextLine();
                if (input.equals("" + Character.toLowerCase(letter)) || input.equals("" + Character.toUpperCase(letter))) {
                    return false;
                }
                return true;
            } catch (Exception e) {
                System.out.println("Please enter valid input");
            }
        }
    }
}
