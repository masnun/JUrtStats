package jurtstats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author masnun
 */
public class DataParser {

    public ArrayList parseData(String UDPResponse) throws Exception {
        
        String[] parts = UDPResponse.split("\n");
        if (parts.length > 1) {

            // Parse the server variables
            HashMap serverVars = new HashMap<String, String>();
            String serverVariables = parts[1];
            String[] serverVariableParts = serverVariables.split("\\\\");

            for (int i = 1; i < serverVariableParts.length;) {
                String key = serverVariableParts[i];
                i++;
                String val = serverVariableParts[i];
                i++;
                serverVars.put(key, val);

            }

            
            // Parse the players 
            ArrayList<HashMap> players = new ArrayList<HashMap>();
            for (int i = 2; i < parts.length; i++) {
                if (parts[i].trim().length() > 0) {
                    String[] playerInfo = parts[i].split(" ");
                    HashMap stats = new HashMap<String, String>();
                    stats.put("name", playerInfo[2]);
                    stats.put("points", playerInfo[0]);
                    stats.put("ping", playerInfo[1]);

                    players.add(stats);
                }
            }
            
            ArrayList data = new ArrayList();
            data.add(serverVars);
            data.add(players);
            
            return data;

        } else {
            throw new Exception("Invalid UDP Response!");
        }
    }
}
