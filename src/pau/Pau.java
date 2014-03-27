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
        result = input.replaceAll("[,.!?()]", "");
        return result;
    }
    
    /*
     * Print out hash map contents.
     * @param map Map to print out.
     * @param out PrintStream for where output will be sent.
     * @return String of output.
     */
    public static void printHashMap(HashMap<?, ?> map, PrintStream out, String display) {
        String result = "";
        for (Entry<?, ?> entry : map.entrySet()) {
            if (display == null | display.equals("all")) {
                result += entry.getKey() + " : " + entry.getValue() + "\n";
            } else if (display.equals("keys")) {
                result += entry.getKey() + "\n";
            }
        }
        out.println(result);
    }
    
    /*
     * Print out hash map contents.
     * @param map Map to print out.
     * @param out PrintStream for where output will be sent.
     * @param lowerLimit Pass over any values lower than this lower limit.
     * @return String of output.
     */
    public static void printHashMap(HashMap<?, ?> map, PrintStream out, String display, int lowerLimit) {
        String result = "";
        for (Entry<?, ?> entry : map.entrySet()) {
            if (display == null | display.equals("all")) {
                if ((Integer) entry.getValue() >= lowerLimit) {
                    result += entry.getKey() + " : " + entry.getValue() + "\n";
                }
            } else if (display.equals("keys")) {
                if ((Integer) entry.getValue() >= lowerLimit) {
                    result += entry.getKey() + "\n";
                }
            }
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
        
        HashMap<String, HashMap<String, ?>> learnedPOS = new HashMap<String, HashMap<String, ?>>();
        
        PrintStream out = new PrintStream(System.out, true, "UTF-8");
        String[] fileNames = { "src/data/data-en.txt", "src/data/data-en2.txt" };
        
        out.println("en data:\n");
        
        for (String fileName : fileNames) {
            Path path = FileSystems.getDefault().getPath(fileName);
            
            // find word occurrences
            HashMap<?, ?> singleOccurences = findOccurrences(path);
            out.println("find single word occurances:");
            printHashMap(singleOccurences, out, "all");
            
            // possible common POS and/or subjects
            out.println("\npossible common POS and/or subjects:");
            printHashMap(singleOccurences, out, "keys", 3);
            
            // TODO: check if possible POS already exists in learnedPOS
            // TODO: if exists, update existing weight +0.1
            // TODO: if !exists, add possible POS to learnedPOS with new weight of 0.1
            
            // find word occurrence neighbors
            HashMap<?, ?> neighborOccurences = findNeighbors(path);
            out.println("\nfind neighbor word occurances:");
            printHashMap(neighborOccurences, out, "all", 2);
            
            // possible data topics
            out.println("\npossible data topics:");
            printHashMap(neighborOccurences, out, "keys", 2);
        }
    }
}