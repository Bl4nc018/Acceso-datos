import org.json.JSONObject;

public class Magic {

    // Ejercicio #22:

    private int power;
    private String ability;


    //# Constructor

    public Magic(int power, String ability) {
        this.power = power;
        this.ability = ability;
    }


    public JSONObject toJSONObject() {
        JSONObject jsonResult = new JSONObject();
        jsonResult.put("power", this.power);
        jsonResult.put("ability", this.ability);
        return jsonResult;
    }


}
