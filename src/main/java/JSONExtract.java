import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;

public class JSONExtract {

    public JSONExtract(GenerateMetro metro, ArrayList<String> JSONFiles) {
        JSONFiles.forEach(file -> run(metro, file));
    }

    public void run(GenerateMetro metro, String filePath) {

        String feature = contentCheck(filePath);

        try (FileReader reader = new FileReader(filePath)) {

            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

            jsonArray.forEach(it -> {
                JSONObject stationJsonObject = (JSONObject) it;
                String nameStation;
                String token;

                if (filePath.contains("depths-3.json")) {
                    nameStation = (String) stationJsonObject.get("station_name");
                    token = String.valueOf(stationJsonObject.get("depth_meters"));
                } else {
                    nameStation = (String) stationJsonObject.get("name");
                    token = String.valueOf(stationJsonObject.get(feature));
                }

                metro.getStations().forEach(st -> {
                    if (st.getName().equalsIgnoreCase(nameStation)) {
                        if (feature.equalsIgnoreCase("date")) {
                            st.setDate(token);
                        } else {
                                st.setDepth(token);
                        }
                    }
                });
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String contentCheck(String filePath) {
        if (filePath.contains("date")) {
            return "date";
        } else {
            return "depth";
        }
    }
}
