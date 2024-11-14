package hk74ce1112;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONWriteHK74CE {

    public static void main(String[] args) {
        try {
            // JSON fájl beolvasása
            FileReader reader = new FileReader("orarendHK74CE.json");
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            // Beolvasott órák JSON tömbjének mentése
            JSONArray orarend = (JSONArray) jsonObject.get("VB_orarend");

            // Konzolra írás blokk formában
            System.out.println("Orarend blokk formában:");
            for (Object obj : orarend) {
                JSONObject ora = (JSONObject) obj;
                
                System.out.println("Óra ID: " + ora.get("id"));
                System.out.println("Típus: " + ora.get("típus"));
                System.out.println("Tárgy: " + ora.get("targy"));
                
                JSONObject idopont = (JSONObject) ora.get("idopont");
                System.out.println("Időpont: " + idopont.get("nap") + ", " + idopont.get("tol") + " - " + idopont.get("ig"));
                
                System.out.println("Helyszín: " + ora.get("helyszin"));
                System.out.println("Oktató: " + ora.get("oktato"));
                System.out.println("Szak: " + ora.get("szak"));
                System.out.println("-----------------------------");
            }

            // Új JSON objektum létrehozása fájlba íráshoz
            JSONObject outputJson = new JSONObject();
            outputJson.put("VB_orarend", orarend);

            // JSON adatokat fájlba írás
            try (FileWriter file = new FileWriter("orarendHK74CE_1.json")) {
                file.write(outputJson.toJSONString());
                System.out.println("A JSON fájlba írás sikeresen befejeződött.");
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
