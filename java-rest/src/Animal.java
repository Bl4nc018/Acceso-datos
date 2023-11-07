import org.json.JSONObject;

public class Animal {

    // Ejercicio #22:

    private String name;
    private int lifespanYears;
    private boolean dangerous;
    private Magic magic;

    public Animal(String name, int lifespanYears, boolean dangerous, Magic magic) {
        this.name = name;
        this.lifespanYears = lifespanYears;
        this.dangerous = dangerous;
        this.magic = magic;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonResult = new JSONObject();
        jsonResult.put("name", this.name);
        jsonResult.put("lifespanYears", this.lifespanYears);
        jsonResult.put("dangerous", this.dangerous);
        jsonResult.put("magic", this.magic.toJSONObject());
        return jsonResult;
    }

}
