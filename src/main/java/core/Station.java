package core;

public class Station implements Comparable<Station> {
    private String name;
    private String number;

    private String nameLine;

    private String date;
    private String depth;

    private boolean hasConnection;

    public Station(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getDate() {
        return date;
    }

    public String getDepth() {
        return depth;
    }

    public String getNameLine() {
        return nameLine;
    }

    public void setNameLine(String nameLine) {
        this.nameLine = nameLine;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    @Override
    public int compareTo(Station station) {
        return name.compareToIgnoreCase(station.getName());
    }

    @Override
    public boolean equals(Object obj)
    {
        return compareTo((Station) obj) == 0;
    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", nameLine='" + nameLine + '\'' +
                ", date='" + date + '\'' +
                ", depth='" + depth + '\'' +
                ", hasConnection=" + hasConnection +
                '}';
    }
}
