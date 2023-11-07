import org.json.JSONObject;

public class Character {
        private String name;
        private String surname;
        private String description;
        private String imageUrl;

        public Character(String name, String surname, String description, String imageUrl) {
            this.name = name;
            this.surname = surname;
            this.description = description;
            this.imageUrl = imageUrl;
        }

        public JSONObject toJSONObject() {
            JSONObject object = new JSONObject();
            object.put("firstName", this.name);
            object.put("surname", this.surname);
            object.put("desc", this.description);
            object.put("imageUrl", this.imageUrl);
            return object;
        }
}

