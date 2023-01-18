import java.io.File;
import java.util.ArrayList;

public class SearchFilesInDirs {

    private ArrayList<String> dataFiles = new ArrayList<>();
    private ArrayList<String> JSONFiles = new ArrayList<>();
    private ArrayList<String> CSVFiles = new ArrayList<>();

    public SearchFilesInDirs(File folder) {
        getFiles(folder);
        sortFiles();
    }

    public void getFiles(File folder) {
        if (folder.isFile()) {
            dataFiles.add(folder.getAbsolutePath());
            return;
        }

        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                dataFiles.add(file.getAbsolutePath());
            } else {
                getFiles(file);
            }
        }
        return;
    }

    public void sortFiles() {
        dataFiles.forEach(file ->
        {
            String regexJSON = ".+\\.(json)";
            String regexCSV = ".+\\.(csv)";

            if (file.matches(regexJSON)) {
                JSONFiles.add(file);
            } else if (file.matches(regexCSV)) {
                CSVFiles.add(file);
            } else {
                return;
            }
        });
    }

    public ArrayList<String> getCSVFiles() {
        return CSVFiles;
    }

    public ArrayList<String> getJSONFiles() {
        return JSONFiles;
    }

    @Override
    public String toString() {
        return "SearchFilesInDirs\n" +
                "JSONFiles:\n" + JSONFiles +
                "\nCSVFiles:\n" + CSVFiles;
    }
}
