import core.Line;
import core.Station;

import java.util.HashMap;
import java.util.TreeSet;

public class GenerateMetro {

    private TreeSet<Line> lines;
    private TreeSet<Station> stations;

    public GenerateMetro(TreeSet<Line> lines, TreeSet<Station> stations) {
        this.lines = lines;
        this.stations = stations;
        generateLines2Stations();
    }

    public void generateLines2Stations() {
        lines.forEach(line -> {
            stations.forEach(st ->
            {
                if (st.getNumber().equals(line.getNumber())) {
                    line.setStation(st.getName());
                    st.setNameLine(line.getName());
                }
            });
        });
    }

    public TreeSet<Line> getLines() {
        return lines;
    }

    public TreeSet<Station> getStations() {
        return stations;
    }

    public HashMap<String, TreeSet<String>> getLines2Stations() {
        HashMap<String, TreeSet<String>> lines2Stations = new HashMap<>();

        lines.forEach(line -> lines2Stations.put(line.getName(), line.getStations()));

        return lines2Stations;
    }

    @Override
    public String toString() {

        StringBuilder stationsToString = new StringBuilder();

        stations.forEach(st -> stationsToString.append("\t" + st + "\n"));

        return "GenerateMetro{" +
                "lines=" + lines +
                ", \nstations=\n" + stationsToString +
                '}';
    }
}
