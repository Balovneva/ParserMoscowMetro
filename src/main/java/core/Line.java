package core;

import java.util.TreeSet;

public class Line implements Comparable<Line> {
    private String name;
    private String number;

    private TreeSet<String> stations = new TreeSet<>();

    public Line(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public TreeSet<String> getStations() {
        return stations;
    }

    public void setStation(String station) {
        stations.add(station);
    }

    @Override
    public int compareTo(Line line) {
        return name.compareTo(line.getName());
    }

    @Override
    public boolean equals(Object obj) {
     return compareTo((Line) obj) == 0;
    }
}
