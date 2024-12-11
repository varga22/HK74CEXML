package hk74ce1112;

import java.io.FileReader;
import org.json.simple.parser.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONReadHK74CE {

    public static void main(String[] args) {
        try {
            // JSON fájl beolvasása
            FileReader reader = new FileReader("orarendHK74CE.json"); // fájlnév módosítva a JSON fájl nevére

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            JSONArray orarend = (JSONArray) jsonObject.get("VB_orarend");

            System.out.println("Orarend: Programtervező Informatikus 2024\n");

            // Az órák adatainak kiírása
            for (Object obj : orarend) {
                JSONObject ora = (JSONObject) obj;
                
                System.out.println("Óra ID: " + ora.get("id"));
                System.out.println("Típus: " + ora.get("típus"));
                System.out.println("Tárgy: " + ora.get("targy"));

                // Időpont adatok kiírása
                JSONObject idopont = (JSONObject) ora.get("idopont");
                System.out.println("Időpont:");
                System.out.println("  Nap: " + idopont.get("nap"));
                System.out.println("  Tól: " + idopont.get("tol"));
                System.out.println("  Ig: " + idopont.get("ig"));

                System.out.println("Helyszín: " + ora.get("helyszin"));
                System.out.println("Oktató: " + ora.get("oktato"));
                System.out.println("Szak: " + ora.get("szak"));
                System.out.println("-----------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace(); // Hibakezelés és hibaüzenet kiírása
        }
    }
}
