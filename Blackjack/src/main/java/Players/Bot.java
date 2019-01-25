package Players;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Bot extends Player {
    int cardCountingValue = 0;
    public Bot(int playerNumber) {
        super(playerNumber, 1000);
    }

    public String getViewDeck() {
        return "\nBot " + playerNumber + "'s Cards: " + getHand() + "\n" + getViewDeckHelper();

    }

    //returns false if hold, true if draw(hit)
    public boolean getNextAction() {
        return strategy17orHold();
    }

    public void setCurrentBet() {
        super.setCurrentBet(betBot());
    }

    private int betBot() {
        if (this.getMoney() >= 20) {
            return (int) (20 * (1 / (1 + Math.pow(Math.E,(-1 * cardCountingValue))))); //if cardCountingValue = 0, bet = 10
        }
        return this.getMoney();
    }

    public void updateCardCountingValue(int cardValue) {
        if (cardValue == 1 || cardValue == 10) {
            cardCountingValue -= 1;
        } else if (cardValue <= 6) {
            cardCountingValue += 1;
        }
    }

    public int getCardCountingValue() {
        return cardCountingValue;
    }

    private boolean strategy17orHold() {
        if (getBestScore() < 17) {
            return true;
        }
        return false;
    }
    private boolean simpleBot() {
        if (isPlaying()) {
            String inline = "";

            try {
                String url_String = "http://localhost:5000/blackjack/simple/" + ((Integer) getBestScore()).toString();
                URL url = new URL(url_String);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                int responsecode = conn.getResponseCode();
                if (responsecode != 200) {
                    throw new RuntimeException("HttpResponseCode: " + responsecode);
                }

                Scanner scan = new Scanner(url.openStream());
                while (scan.hasNext()) {
                    inline += scan.nextLine();
                }
                scan.close();

                JSONParser parse = new JSONParser();
                JSONObject jsonObject = (JSONObject) parse.parse(inline);
                Long action = (Long) jsonObject.get("action");

                conn.disconnect();

                if (action == 1) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }

        return false;
    }
}
