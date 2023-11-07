import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class WrestlersReader {
    // Ejercicio #34:
    public void readWrestlers(){
        try {
            FileReader reader = new FileReader("assets\\wrestlers.json");

            JSONTokener tokener = new JSONTokener(reader);
            JSONArray jsonArray = new JSONArray(tokener);

            for (int i = 0; i < jsonArray.length(); i++) {
                String wrestlerName = jsonArray.getString(i);
                System.out.println(wrestlerName);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
