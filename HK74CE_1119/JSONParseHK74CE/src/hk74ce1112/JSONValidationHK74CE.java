package hk74ce1112;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.InputStream;

public class JSONValidationHK74CE {
    public static void main(String[] args) {
        try {
            // JSON Schema és fájl beolvasása
            InputStream schemaStream = new FileInputStream("orarendHK74CESchema.json");
            InputStream jsonStream = new FileInputStream("orarendHK74CE.json");

            JSONObject jsonSchema = new JSONObject(new JSONTokener(schemaStream));
            JSONObject jsonData = new JSONObject(new JSONTokener(jsonStream));

            // Séma betöltése és validáció
            Schema schema = SchemaLoader.load(jsonSchema);
            schema.validate(jsonData);

            // Validáció eredménye
            System.out.println("A JSON dokumentum érvényes a séma alapján.");
        } catch (Exception e) {
            System.out.println("A JSON dokumentum nem érvényes. Hiba:");
            e.printStackTrace();
        }
    }
}
