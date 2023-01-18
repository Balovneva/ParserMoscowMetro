import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.PrintWriter;

public class CreateJSONFiles {
    public CreateJSONFiles(GenerateMetro metro) {
        generateJson(metro);
    }

    public void generateJson(GenerateMetro metro) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JSONObject objectStations = new JSONObject();
        JSONObject objectMap = new JSONObject();
        objectStations.put("stations", metro.getStations());
        objectMap.put("lines", metro.getLines2Stations());

        String pathToStations = "result/stations.json";
        String pathToMap = "result/map.json";

        try(PrintWriter stations = new PrintWriter(new FileWriter(pathToStations));
                PrintWriter lines = new PrintWriter(new FileWriter(pathToMap))) {
            stations.write(gson.toJson(objectStations));
            lines.write(gson.toJson(objectMap));
            stations.flush();
            lines.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
