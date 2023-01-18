import java.io.File;

public class Main {
    public static void main(String[] args) {

        WebParse webParse = new WebParse("https://skillbox-java.github.io");

        GenerateMetro moscowMetro = new GenerateMetro(webParse.getLines(), webParse.getStations());

        SearchFilesInDirs searchFilesInDirs = new SearchFilesInDirs(new File("data"));

        JSONExtract jsonExtract = new JSONExtract(moscowMetro, searchFilesInDirs.getJSONFiles());

        CSVParsing csvParsing = new CSVParsing(moscowMetro, searchFilesInDirs.getCSVFiles());

        CreateJSONFiles createJSONFiles = new CreateJSONFiles(moscowMetro);

    }
}
