package pau;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Pau {
    
    private static Boolean debugMode = false;
    
    /* 
     * Remove extra characters from string.
     * @param input String of input.
     * @return String of cleaned result.
     */
    public static String clean(String input)
    {
        String result = "";
        result = input.replaceAll("[,.!?]", "");
        return result;
    }
    
    /*
     * Print out hash map contents.
     * @param map Map to print out.
     * @param out PrintStream for where output will be sent.
     * @return String of output.
     */
    public static void printHashMap(HashMap<?, ?> map, PrintStream out) {
        String result = "";
        for (Entry<?, ?> entry : map.entrySet()) {
            result += entry.getKey() + "\n";
            result += "  " + entry.getValue() + "\n";
        }
        out.println(result);
    }
    
    /* 
     * Find number of occurrences for individual tokens.
     * @param path Path for input data file.
     * @return String of found results.
     */
    public static HashMap<?, ?> findOccurrences(Path path) {
        try {
            HashMap<String, Integer> foundWords = new HashMap<String, Integer>();
            
            // read in data file
            List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));

            // read through each line from data file
            for (String line : lines) {
                String[] items = line.split(" ");
                for (String item : items) {
                    item = clean(item);
                    if (foundWords.containsKey(item)) {
                        foundWords.put(item, foundWords.get(item) + 1);
                    } else {
                        foundWords.put(item, 1);
                    }
                }
            }
            return foundWords;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /* 
     * Find number of occurrences for neighboring tokens.
     * @param path Path for input data file.
     * @return String of found results.
     */
    public static HashMap<?, ?> findNeighbors(Path path)
    {
        try {
            HashMap<String, Integer> foundWords = new HashMap<String, Integer>();
            
            // read in data file
            List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));

            // read through each line from data file
            for (String line : lines) {
                String[] items = line.split(" ");
                String lastItem = "";
                for (String item : items) {
                    if (!item.isEmpty()) {
                        String newKeyItem = clean(lastItem + " " + item);
                        if (foundWords.containsKey(newKeyItem)) {
                            foundWords.put(newKeyItem, foundWords.get(newKeyItem) + 1);
                        } else {
                            foundWords.put(newKeyItem, 1);
                        }
                        if (debugMode)
                            System.out.println("last: " + lastItem + " current: " + item);
                        lastItem = clean(item);
                    }
                }
            }
            return foundWords;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException {
        
        Path path = FileSystems.getDefault().getPath("src/data/data.txt");
        
        PrintStream out = new PrintStream(System.out, true, "UTF-8");
        
        // find word occurrences
        HashMap<?, ?> singleOccurences = findOccurrences(path);
        out.println("find single word occurances:");
        printHashMap(singleOccurences, out);
        
        // find word occurrence neighbors
        HashMap<?, ?> neighborOccurences = findNeighbors(path);
        out.println("\nfind neighbor word occurances:");
        printHashMap(neighborOccurences, out);
    }
}