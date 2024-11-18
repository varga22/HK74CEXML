package JSONValidationPackage;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONValidationHK74CE {
    public static void main(String[] args) {
        try {
            // JSON fájl beolvasása
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("OrarendHK74CE.json");
            JSONObject root = (JSONObject) parser.parse(reader);

            // Fő mező ellenőrzése
            if (!root.containsKey("VB_orarend")) {
                throw new Exception("Hiba: 'VB_orarend' mező hiányzik!");
            }

            // Órarend ellenőrzése
            JSONArray orarend = (JSONArray) root.get("VB_orarend");
            for (Object item : orarend) {
                JSONObject ora = (JSONObject) item;

                // Kötelező mezők ellenőrzése
                validateField(ora, "id");
                validateField(ora, "típus");
                validateField(ora, "targy");
                validateField(ora, "helyszin");
                validateField(ora, "oktato");
                validateField(ora, "szak");

                // Időpont ellenőrzése
                if (!ora.containsKey("idopont")) {
                    throw new Exception("Hiba: Az 'idopont' mező hiányzik az óránál: " + ora);
                }
                JSONObject idopont = (JSONObject) ora.get("idopont");
                validateField(idopont, "nap");
                validateField(idopont, "tol");
                validateField(idopont, "ig");
            }

            System.out.println("Validáció sikeres: A JSON dokumentum helyes struktúrájú.");

        } catch (Exception e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }

    // Segédfüggvény mező ellenőrzéséhez
    private static void validateField(JSONObject obj, String field) throws Exception {
        if (!obj.containsKey(field)) {
            throw new Exception("Hiba: A(z) '" + field + "' mező hiányzik az objektumban: " + obj);
        }
    }
}
