import com.sun.source.tree.Tree;
import core.Line;
import core.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class WebParse {

    private String webAddress;
    private Document doc;

    private TreeSet<Line> lines = new TreeSet<>();
    private TreeSet<Station> stations = new TreeSet<>();

    public WebParse(String webAddress) {
        this.webAddress = webAddress;
        getHtml();
        getLinesFromHtml();
        getStationsFromHtml();
    }

    public Document getHtml() {
        try {
            doc = Jsoup.connect(webAddress).get();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return doc;
    }

    public void getLinesFromHtml() {
        Elements elements = doc.select(".js-metro-line");

        elements.forEach(element -> lines
                .add(new Line(element.text(), element.attr("data-line"))));

    }

    public void getStationsFromHtml() {
        Elements elements = doc.select(".js-metro-stations");

        HashMap<String, String> number2stations = new HashMap<>();

        elements.forEach(e -> number2stations
                .put(e.attr("data-line"), e.text()));

        number2stations.entrySet().forEach(item -> {
            String numberOfLine = item.getKey();
            List<String> stationsParser = Arrays.asList(item.getValue().split("\s*\\d+\\.\s"));
            stationsParser.forEach(st -> {
                if (!st.isEmpty()) {
                    stations.add(new Station(st, numberOfLine));
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

    @Override
    public String toString() {

        StringBuilder linesList = new StringBuilder();

        lines.forEach(l -> {
            linesList.append("\t\t" + l.getNumber() + " -> " + l.getName() + "\n");
        });

        StringBuilder stationsList = new StringBuilder();

        stations.forEach(s -> {
            stationsList.append("\t\t" + s.getNumber() + " -> " + s.getName() + "\n");
        });

        return "Class WebParse\n" +
                "\tlines:\n" + linesList +
                "\n\tstations:\n" + stationsList;
    }
}
