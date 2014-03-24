package pau;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pau {
    public static void main(String[] args) {
        
        Path path = FileSystems.getDefault().getPath("src/data/data.txt");
        
        try {
            PrintStream out = new PrintStream(System.out, true, "UTF-8");
            
            HashMap<String, Integer> foundWords = new HashMap<String, Integer>();
            
            // read in data file
            List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));

            // read through each line from data file
            for (String line : lines) {
                String[] items = line.split(" ");
                for (String item : items) {
                    if (foundWords.containsKey(item)) {
                        foundWords.put(item, foundWords.get(item) + 1);
                    } else {
                        foundWords.put(item, 1);
                    }
                }
            }
            
            // display results
            for (Map.Entry<String, Integer> entry : foundWords.entrySet()) {
                out.println(entry.getKey());
                out.println("  " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}