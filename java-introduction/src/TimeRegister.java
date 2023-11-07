import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TimeRegister {

    // Ejercicio #36:
    public int totalHours;
    public int getTotalHours() { return totalHours; }
    public void setTotalHours(int totalHours) { this.totalHours = totalHours; }
    private ArrayList<String> hours = new ArrayList<>();

    public void addHours(short entranceHour, short exitHour) {
        if (entranceHour >= 0 && entranceHour < 24 && entranceHour < exitHour) {
            hours.add(String.valueOf(entranceHour));
        } else {
            throw new RuntimeException("La hora de entrada no cumple con las restricciones.");
        }
        if (exitHour < 24) {
            hours.add(String.valueOf(exitHour));
        } else {
            throw new RuntimeException("La hora de salida no cumple con las restricciones.");
        }
        setTotalHours(getTotalHours() + (exitHour - entranceHour));
    }

    public void produceJSON() {
        JSONObject punchCard = new JSONObject();
        punchCard.put("totalHours", getTotalHours());
        JSONArray actualHours = new JSONArray();

        for (int i = 0; i < hours.size(); i += 2) {
            short eH = Short.parseShort(hours.get(i));
            short exH = Short.parseShort(hours.get(i + 1));

            JSONObject entryHours = new JSONObject();
            entryHours.put("entranceHour", eH);
            entryHours.put("exitHour", exH);

            actualHours.put(entryHours);
        }

        punchCard.put("registers", actualHours);

        try {
            FileWriter writer = new FileWriter("assets\\hours.json");
            punchCard.write(writer, 2, 0);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}