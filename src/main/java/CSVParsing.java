import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVParsing {

    public CSVParsing(GenerateMetro metro, ArrayList<String> CVSFiles) {
        CVSFiles.forEach(file -> run(metro, file));
    }

    public void run(GenerateMetro metro, String filePath) {

        String feature = contentCheck(filePath);

        try {
            List<String> lines = Files
                    .readAllLines(Paths.get(filePath));

            for (String line : lines) {
                String correctedLine = line.replaceAll("\"", "");
                String[] fragments = correctedLine.split(",", 2);
                if (fragments.length != 2) {
                    System.out.println("Wrong line " + correctedLine);
                    continue;
                }
                metro.getStations().forEach(st ->
                {
                    if (st.getName().equalsIgnoreCase(fragments[0])) {
                        if (feature.equalsIgnoreCase("date")) {
                            st.setDate(fragments[1]);
                        } else {
                            st.setDepth(fragments[1]);
                        }
                    }
                });
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String contentCheck(String filePath) {
        if (filePath.contains("date")) {
            return "date";
        } else { return "depth"; }
    }
}
