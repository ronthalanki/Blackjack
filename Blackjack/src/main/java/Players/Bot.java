package Players;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Bot extends Player {

    public Bot(int playerNumber) {
        super(playerNumber);
    }

    public String getViewDeck() {
        return "\nBot " + playerNumber + "'s Cards: " + getHand() + "\n" + getViewDeckHelper();

    }

    //returns false if hold, true if draw(hit)
    public boolean getNextAction() {
        return simpleBot();
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
