package pau;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
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
     * Filter hash map results via a specified lower limit value.
     * @param map Map to filter.
     * @param lowerLimit Pass over any values lower than this lower limit.
     * @return HashMap of filtered results.
     */
    public static HashMap<String, Integer> filterResults(HashMap<String, Integer> map, int lowerLimit) {
        
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        
        for (Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= lowerLimit) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        
        return result;
    }
    
    /* 
     * Find number of occurrences for individual tokens.
     * @param path Path for input data file.
     * @return String of found results.
     */
    public static HashMap<String, Integer> findOccurrences(Path path) {
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
    
    /*
     * Add results to a new "dictionary" hash map.
     * @param path Path for input data.
     * @return Hash map of results.
     */
    public static HashMap<String, String> addToDictionary(Path path) {
        try {
            HashMap<String, String> foundWords = new HashMap<String, String>();
            
            // read in data file
            List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));

            // read through each line from data file
            for (String line : lines) {
                String[] items = line.split(" ");
                for (String item : items) {
                    item = clean(item);
                    foundWords.put(item, "");
                }
            }
            return foundWords;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /*
     * Add results to a new "dictionary + many definitions" hash map.
     * @param path Path for input data.
     * @return Hash map of results.
     */
    public static HashMap<String, List<String>> addToRules(Path path) {
        try {
            HashMap<String, List<String>> foundWords = new HashMap<String, List<String>>();
            
            // read in data file
            List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));

            // read through each line from data file
            for (String line : lines) {
                String[] items = line.split(" ");
                for (int i = 0; i < items.length-1; i++) {
                    String prev = "";
                    String curr = "";
                    String next = "";
                    
                    if ((i > 0) && (items[i-1] != null))
                        prev = items[i-1];
                    
                    curr = items[i];
                    
                    if (items[i+1] != null)
                        next = items[i+1];
                    
                    //System.out.println("prev: " + prev + " curr: " + curr + " next: " + next);
                    // check if prev/next list items already exist
                    // if exist, append new prev/next items
                    if (foundWords.get(clean(curr)) != null) {
                        List<String> oldList = new ArrayList<String>();
                        oldList.addAll(foundWords.get(clean(curr)));
                        
                        List<String> newList = new ArrayList<String>();
                        newList.add(clean(prev));
                        newList.add(clean(next));
                        
                        oldList.addAll(newList);
                        
                        foundWords.put(clean(curr), oldList);
                    }
                    // else, add new prev/next items
                    else {
                        foundWords.put(clean(curr), Arrays.asList(clean(prev), clean(next)));
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
        
        HashMap<String, HashMap<String, Double>> learnedPOS = new HashMap<String, HashMap<String, Double>>();
        
        PrintStream out = new PrintStream(System.out, true, "UTF-8");
        String[] fileNames = { "src/data/data-en.txt", "src/data/data-en2.txt" };
        //String[] fileNames = { "src/data/data-vi.txt", "src/data/data-vi2.txt", "src/data/data-vi3.txt" };
        
        HashMap<String, String> dictionary = new HashMap<String, String>();
        HashMap<String, List<String>> rules = new HashMap<String, List<String>>();
        
        for (String fileName : fileNames) {
            Path path = FileSystems.getDefault().getPath(fileName);
            
            /*
            // find word occurrences
            HashMap<String, Integer> singleOccurences = findOccurrences(path);
            //out.println("find single word occurances:");
            //printHashMap(singleOccurences, out, "all");
            
            // possible common POS and/or subjects
            out.println("\npossible common POS and/or subjects:");
            printHashMap(singleOccurences, out, "keys", 3);
            
            HashMap<String, Integer> filteredOccurenceResults = new HashMap<String, Integer>();
            filteredOccurenceResults = filterResults(singleOccurences, 3);
            
            // loop through filtered results for weight setting/updating
            // weights indicate if POS is a possible common POS (e.g. in, on, at...)
            // TODO: capture neighboring words for these common POS candidates
            //       so that we can possibly determine what type of POS neighboring words might be
            for (Entry<String, Integer> entry : filteredOccurenceResults.entrySet()) {
                HashMap<String, Double> updatedValue = new HashMap<String, Double>();
                
                // check if possible common POS already exists in learnedPOS
                // if exists, update existing weight +0.1                
                if (learnedPOS.containsKey(entry.getKey())) {
                    updatedValue = learnedPOS.get(entry.getKey());
                    updatedValue.put("weight", (Double) updatedValue.get("weight") + 0.1);
                }
                // if !exists, add possible common POS to learnedPOS with new weight of 0.1
                else {
                    updatedValue.put("weight", 0.1);
                }
                learnedPOS.put(entry.getKey(), updatedValue);
                out.println("adding value... key:" + entry.getKey() + " weight:" + updatedValue);
            }
            
            // find word occurrence neighbors
            HashMap<?, ?> neighborOccurences = findNeighbors(path);
            out.println("\nfind neighbor word occurances:");
            printHashMap(neighborOccurences, out, "all", 3);
            
            // possible data topics
            out.println("\npossible data topics:");
            printHashMap(neighborOccurences, out, "keys", 3);
            */
            
            // Generating Meaning: Algorithm A (GMA:A)
            // (GMA:A) 1. parse and assign words to dictionary
            HashMap<String, String> dictionaryAdditions = addToDictionary(path);
            dictionary.putAll(dictionaryAdditions);
            
            // (GMA:A) 2. add previous, current, and next words for each dictionary-assigned word into a list of rules
            //   - ex: this and that _previous_, _current_, and _next_
            HashMap<String, List<String>> ruleAdditions = addToRules(path);
            rules.putAll(ruleAdditions);
            
            // (GMA:A) 3. repeat process until all words are assigned to dictionary
        }
        
        out.println("dictionary:\n");
        printHashMap(dictionary, out, "all");
        
        out.println("rules:\n");
        printHashMap(rules, out, "all");
        
        // TODO: (GMA:A) 4. run through rules list and try to find patterns that can be condensed/abstracted
        // for now... this just means... search through other rule sets and look for similar words?
        
        // TODO: (GMA:A) 5. update rules list with condensed/updated data ("definitions")
        // TODO: (GMA:A) 6. add found "definitions" to words in dictionary
    }
}