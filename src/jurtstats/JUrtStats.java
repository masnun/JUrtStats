/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jurtstats;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author masnun
 */
public class JUrtStats {

    public static void main(String[] args) {
        try {

            UDPClient client = new UDPClient();
            String data = client.getData("urtbd.com", 2222);
            DataParser parser = new DataParser();

            ArrayList srv = parser.parseData(data);

            ArrayList players = (ArrayList) srv.get(1);
            for (Object player : players) {
                HashMap playerData = (HashMap<String, String>) player;
                System.out.println(playerData.get("name"));
                System.out.println(playerData.get("ping"));
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
